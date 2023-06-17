package br.com.m3Tech.solucoesFromtis.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.m3Tech.solucoesFromtis.dao.Conexao;
import br.com.m3Tech.solucoesFromtis.dto.ArquivoImportadoDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.service.IArquivoService;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFilaService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class ReimportarArquivosController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReimportarArquivosController.class);

	private static final String VOLTAR = "/pages/cadastros/home.xhtml";

	@Autowired
	private IBaseService baseService;
	@Autowired
	private IFundoService fundoService;
	@Autowired
	private IArquivoService arquivoService;
	@Autowired
	private IConfGlobalService confGlobalService;
	@Autowired
	private IFilaService filaService;

	private Integer baseSelecionada;

	private Integer quantidadeArquivos;

	private LocalDate data;

	private List<Base> bases;

	@PostConstruct
	public void init() {

		bases = baseService.findAll();
		data = LocalDate.now().minusDays(1L);
	}

	public void atualizarValidacao() {

	}

	public void selecionandoBase() {
		System.out.println("Selecionando Base " + baseSelecionada);

	}

	public void atualizarBases() {
		System.out.println("Atualizar Base");

		bases = baseService.findAll();
	}

	public void gerar() {
		try {

			if (baseSelecionada == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Base é obrigatório"));
				return;
			}

			if (data == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Data é obrigatório"));
				return;
			}

			logger.info("Inicio da reimportação de arquivo | data: {}", data.toString());

			Base base = baseService.findById(baseSelecionada);

			Connection con = Conexao.getConnection(base);

			Map<Integer, ArquivoImportadoDto> arquivoImportadoByData = arquivoService.getArquivoImportadoByData(base,
					data);

			logger.info("{} Arquivos encontrados para reimportar",
					arquivoImportadoByData != null ? arquivoImportadoByData.size() : 0);

			arquivoImportadoByData.forEach((i, a) -> {
				logger.info("Reimportando arquivo | idOriginal: {} | nomeArquivo: {}", i, a.getNomeArquivo());

				LayoutEnum layoutEnum = LayoutEnum.parse(a.getLayout());
				
				if(layoutEnum != null) {

					StringBuilder path = new StringBuilder();
	
					String tipoLayout = layoutEnum.getTipo();
	
					if ("C".equalsIgnoreCase(tipoLayout)) {
	
						path.append(a.getPathRepositorio()).append(File.separator).append(a.getFundo().getCodigoIsin())
								.append(File.separator).append("REMESSA").append(File.separator)
								.append(a.getFundo().getDataFundo().format(DateTimeFormatter.ofPattern("ddMMyyyy")))
								.append(File.separator);
					} else {
						path.append(a.getPathRepositorio()).append(File.separator).append("VALIDACAO_ARQUIVO")
								.append(File.separator).append(a.getFundo().getCodigoIsin()).append(File.separator)
								.append(a.getFundo().getDataFundo().format(DateTimeFormatter.ofPattern("dd_MM_yyyy")))
								.append(File.separator).append("AGUARDANDO").append(File.separator);
					}
					
					File caminhoArquivo = new File(path.toString());
					
					if(!caminhoArquivo.exists()) {
						caminhoArquivo.mkdirs();
					}
					
					File file = getFile(a, caminhoArquivo, layoutEnum);
	
					if ("C".equalsIgnoreCase(tipoLayout)) {
						logger.info("Arquivo de cobrança gravado na pasta: {}", i);
					}
					if (file != null) {
						filaService.inserirArquivoValidacao(base, filaService.inserirFilaImportacaoArquivo(base, a.getFundo()),
								file, a.getFundo().getLayoutAquisicao(),path.toString());
						logger.info("Arquivo de remessa enviado para fila de validação: {}", path.toString());
					} else {
						logger.info("Layout ainda não implementado: {}", a.getLayout());
					}
				}
			});

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "ReImportação simulada Com Sucesso."));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private File getFile(ArquivoImportadoDto a, File caminhoArquivo, LayoutEnum layoutEnum) {

		if (layoutEnum == null) {
			return null;
		}

		File arquivoFinal = new File(caminhoArquivo, a.getNomeArquivo());
		try {
			OutputStream os = new FileOutputStream(arquivoFinal);
			Writer wr = new OutputStreamWriter(os);
			BufferedWriter br = new BufferedWriter(wr);

			for (String s : a.getConteudo()) {
				String linha = "";
				if (isHeader(s)) {
					linha = replaceHeader(s, layoutEnum);
				} else if (isDetail(s)) {
					linha = replaceDetail(s, layoutEnum);
				} else {
					linha = s;
				}

				br.write(linha);
				br.newLine();

			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arquivoFinal;
	}

	private String replaceDetail(String s, LayoutEnum layoutEnum) {
		String retorno = "";

		if ("C".equalsIgnoreCase(layoutEnum.getTipo())) {
			retorno = s;
		} else {

			retorno = s.substring(0, layoutEnum.getSeuNumeroIni()) + ValorAleatorioUtil.getValor(25)
					+ s.substring(layoutEnum.getSeuNumeroIni() + 25, s.length());
		}
		return retorno;
	}

	private String replaceHeader(String s, LayoutEnum layoutEnum) {
		String retorno = "";

		retorno = s.substring(0, layoutEnum.getDataGravacaoIni())
				+ LocalDate.now().format(DateTimeFormatter.ofPattern(layoutEnum.getPattern()))
				+ s.substring(layoutEnum.getDataGravacaoIni() + layoutEnum.getPattern().length(), s.length());

		return retorno;
	}

	private boolean isDetail(String s) {
		return "1".equals(s.substring(0, 1)) || "3".equals(s.substring(0, 1));
	}

	private boolean isHeader(String s) {
		return "0".equals(s.substring(0, 1));
	}

	public void limparBase() {

		try {
			if (baseSelecionada == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Base é obrigatório"));
				return;
			}

			if (data == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Data é obrigatório"));
				return;
			}

			Base base = baseService.findById(baseSelecionada);

			Connection con = Conexao.getConnection(base);

			logger.info("Limpando base na data: {}", data);
			arquivoService.limparbase(base, data);
			logger.info("Limpando base concluida com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String voltar() {
		return VOLTAR;
	}

}
