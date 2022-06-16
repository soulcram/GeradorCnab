package br.com.m3Tech.solucoesFromtis.controller;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.google.common.base.Preconditions;

import br.com.m3Tech.solucoesFromtis.dao.Conexao;
import br.com.m3Tech.solucoesFromtis.dto.BancoDto;
import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.IndexadorDto;
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.dto.RiscoDto;
import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;
import br.com.m3Tech.solucoesFromtis.dto.TipoRecebivelDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IBancoService;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.ICedenteService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFilaService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.IGeradorCnab;
import br.com.m3Tech.solucoesFromtis.service.IIndexadorService;
import br.com.m3Tech.solucoesFromtis.service.IMovimentoService;
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;
import br.com.m3Tech.solucoesFromtis.service.IRiscoService;
import br.com.m3Tech.solucoesFromtis.service.ISacadoService;
import br.com.m3Tech.solucoesFromtis.service.ITipoRecebivelService;
import br.com.m3Tech.solucoesFromtis.util.LayoutUtils;
import br.com.m3Tech.solucoesFromtis.util.NumericUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import br.com.m3Tech.utils.BigDecimalUtils;
import br.com.m3Tech.utils.BooleanUtils;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class simularImportacaoCnabPortalController implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private static final String VOLTAR = "/pages/cadastros/home.xhtml";


	@Autowired
	private IBaseService baseService;
	@Autowired
	private IFundoService fundoService;
	@Autowired
	private IBancoService bancoService;
	@Autowired
	private IOriginadorService originadorService;
	@Autowired
	private ISacadoService sacadoService;
	@Autowired
	private ICedenteService cedenteService;
	@Autowired
	private IMovimentoService movimentoService;
	@Autowired
	private ITipoRecebivelService tipoRecebivelService;
	@Autowired
	private IConfGlobalService confGlobalService;
	@Autowired
	private  IGeradorCnab geradorCnab;
	@Autowired
	private IIndexadorService indexadorService;
	@Autowired
	private IRiscoService riscoService;
	@Autowired
	private IFilaService filaService;
		
	
	
	private Integer baseSelecionada;

	private Integer quantidadeTitulos;
	private Integer quantidadeArquivos;
	

	
	private CnabDto cnab;
	
	private List<Base> bases;
	private List<FundoDto> fundos;
	
	private Bucket bucket;
	
	@PostConstruct
	public void init() {
		
		bases = baseService.findAll();
		fundos = new ArrayList<>();
		cnab = new CnabDto();
		quantidadeTitulos = 1;
		quantidadeArquivos = 1;
		
		Bandwidth limit = Bandwidth.classic(1, Refill.greedy(1, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
            .addLimit(limit)
            .build();

				
	}
	
	public void selecionandoBase() {
		System.out.println("Selecionando Base " + baseSelecionada );
		cnab = new CnabDto();
		atualizarFundos();
		
	}
	
	public List<LayoutEnum> getLayoutsRemessa() {
		List<LayoutEnum> retorno = LayoutEnum.findAllRemessa();
		
		return retorno;
	}

	
	
	private void atualizarFundos() {
		
		try {
		
			Base base = baseService.findById(baseSelecionada);
		
			fundos = fundoService.findAllComDataAtual(Conexao.getConnection(base));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	public void atualizarBases() {
		System.out.println("Atualizar Base");
		
		bases = baseService.findAll();
	}
	
	public void addTitulo(FundoDto fundoAtual, CedenteDto cedente, SacadoDto sacado, MovimentoDto movimento,RiscoDto risco) {
		
		try {
	
			TituloDto titulo = new TituloDto();
			
			BigDecimal valor = ValorAleatorioUtil.getValorDecimal(null,null);
	
			titulo.setValorTitulo(valor);
			titulo.setValorAquisicao(NumericUtils.getValorMenos20PorCento(valor));
			titulo.setTaxaJuros(ValorAleatorioUtil.getTaxaDecimal());
			titulo.setEspecie("01");
			titulo.setMovimento(movimento);
			titulo.setCedente(cedente);
			titulo.setSacado(sacado);
			titulo.setDataVencimento(fundoAtual.getDataFundo().plusDays(45));
			titulo.setSeuNumero(ValorAleatorioUtil.getValor(25));
			titulo.setNumeroDocumento(ValorAleatorioUtil.getValor(10));
			titulo.setTermoCessao(ValorAleatorioUtil.getValor(10));
			titulo.setChaveNfe("31190600006388319890559240000000311006164587");
			titulo.setCoobrigacao(cedente.getCoobrigacao().equalsIgnoreCase("N") ? "02" : "01");
			titulo.setRisco(risco);
	
			titulo.setNumBanco(cnab.getBanco().getCodigoBanco());
			cnab.getTitulos().add(titulo.getCopy());
			
			titulo = new TituloDto();
		
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
		}
	}
	
	public void gerar() {
		try {
			
			if (!bucket.tryConsume(1)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Excedeu limite de requisições por minuto"));
				return;
			}
			
			if(quantidadeTitulos == null || quantidadeTitulos == 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Quantidade de Títulos é obrigatório"));
				return;
			}
			
			if(quantidadeArquivos == null || quantidadeArquivos == 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Quantidade de Arquivos é obrigatório"));
				return;
			}
			
			if(quantidadeTitulos > 999997) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Quantidade de Títulos máxima é 999.997"));
				return;
			}
			
			if(fundos.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Nenhum fundo encontrado com a data atual"));
				return;
			}
			
			
			
			Base base = baseService.findById(baseSelecionada);
			
			Connection con = Conexao.getConnection(base);
			
			String pathRepositorio = confGlobalService.getPathRepositorio(con);
			
			StringBuilder path = new StringBuilder();
			

			BancoDto banco = bancoService.findOneByNumBanco(con, "001");
			List<RiscoDto> riscos = riscoService.findAll(con);
			
			
			
			for (int j = 0; j < quantidadeArquivos; j++) {
				
				FundoDto fundoAtual = fundos.get(ValorAleatorioUtil.getValorNumerico(fundos.size()));
				
				path.append(pathRepositorio)
					.append(File.separator)
					.append("VALIDACAO_ARQUIVO")
					.append(File.separator)
					.append(fundoAtual.getCodigoIsin())
					.append(File.separator)
					.append(fundoAtual.getDataFundo().format(DateTimeFormatter.ofPattern("dd_MM_yyyy")))
					.append(File.separator)
					.append("AGUARDANDO")
					.append(File.separator);
				
				List<CedenteDto> cedentes = cedenteService.findAll(con, fundoAtual.getIdFundo(), base);
				if(cedentes.isEmpty()) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Nenhum Cedente encontrado para o fundo " + fundoAtual.getNomeFundo()));
					return;
				}
				List<SacadoDto> sacados = sacadoService.findAll(con, fundoAtual.getIdFundo());
				if(sacados.isEmpty()) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Nenhum Sacado encontrado para o fundo " + fundoAtual.getNomeFundo()));
					return;
				}
				List<OriginadorDto> originadores = originadorService.findAll(con, fundoAtual.getIdFundo());
				if(originadores.isEmpty()) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Nenhum Originador encontrado para o fundo " + fundoAtual.getNomeFundo()));
					return;
				}
				List<MovimentoDto> movimentos = movimentoService.findAllMovimentosAquisicao(con, fundoAtual.getLayoutAquisicao());
				if(movimentos.isEmpty()) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Nenhum Movimento encontrado para o layout " + fundoAtual.getLayoutAquisicao()));
					return;
				}
				
				cnab.setBanco(banco);
				cnab.setDataGravacao(LocalDate.now());
				cnab.setFundo(fundoAtual);
				cnab.setLayout(LayoutEnum.parse(fundoAtual.getLayoutAquisicao()));
				cnab.setOriginador(originadores.get(ValorAleatorioUtil.getValorNumerico(originadores.size())) );
				
				for (int i = 0; i < quantidadeTitulos; i++) {
	
					addTitulo(fundoAtual, 
							  cedentes.get(ValorAleatorioUtil.getValorNumerico(cedentes.size())), 
							  sacados.get(ValorAleatorioUtil.getValorNumerico(sacados.size())),
							  movimentos.stream().filter(c -> c.getCdOcorrencia().equals("1")).findFirst().get(),
							  riscos.get(0)
							  );
				}
				
				ConfGlobal confGlobal = confGlobalService.getConfGlobal();
				cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
				confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
				confGlobal.save();
				
				File file = geradorCnab.gerar(cnab, "AQUISICAO_PORTAL", false, path.toString());
				
				filaService.inserirArquivoValidacao(con, filaService.inserirFilaImportacaoArquivo(con, fundoAtual), file, fundoAtual.getLayoutAquisicao());
				
				cnab = new CnabDto();
			}

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Importação simulada Com Sucesso."));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public void removerTitulo(TituloDto titulo) {
		cnab.getTitulos().remove(titulo);
	}
	
	public String voltar() {
		return VOLTAR;
	}

}
