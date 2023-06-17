package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.BancoDto;
import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.ImportacaoSimuladaDto;
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.dto.RiscoDto;
import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.enuns.TipoMovimentacao;
import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IBancoService;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.ICedenteService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFilaService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.IGeradorCnab;
import br.com.m3Tech.solucoesFromtis.service.IMovimentoService;
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;
import br.com.m3Tech.solucoesFromtis.service.IRiscoService;
import br.com.m3Tech.solucoesFromtis.service.ISacadoService;
import br.com.m3Tech.solucoesFromtis.service.ISimularImportacaoPortal;
import br.com.m3Tech.solucoesFromtis.util.NumericUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;

@Service
public class SimularAquisicaoPortalImpl implements ISimularImportacaoPortal, Serializable {
	
	private static final Logger logger = LoggerFactory.getLogger(SimularAquisicaoPortalImpl.class);

	private static final long serialVersionUID = 1L;
	
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
	private IConfGlobalService confGlobalService;
	@Autowired
	private  IGeradorCnab geradorCnab;
	@Autowired
	private IRiscoService riscoService;
	@Autowired
	private IFilaService filaService;
	
	private CnabDto cnab;
	
	private List<FundoDto> fundos;

	@Override
	public void gerar(ImportacaoSimuladaDto importacaoSimuladaDto) throws Exception {

		if(TipoMovimentacao.AQUISICAO.equals(importacaoSimuladaDto.getTipoMovimentacao())) {
			
			cnab = new CnabDto();
			
			if(importacaoSimuladaDto.getQuantTitulosPorArquivo() == null || importacaoSimuladaDto.getQuantTitulosPorArquivo() == 0) {
				throw new BussinesException("Quantidade de Títulos é obrigatório");
			}
			
			if(importacaoSimuladaDto.getQuantArquivos() == null || importacaoSimuladaDto.getQuantArquivos() == 0) {
				throw new BussinesException("Quantidade de Arquivos é obrigatório");
			}
			
			if(importacaoSimuladaDto.getQuantTitulosPorArquivo() > 999997) {
				throw new BussinesException("Quantidade de Títulos máxima é 999.997");
			}
			atualizarFundos();
			if(fundos.isEmpty()) {
				throw new BussinesException("Nenhum fundo encontrado com a data atual");
			}
			
			ConfGlobal confGlobalRobo = confGlobalService.getConfGlobal();
			
			Base base = baseService.findById(confGlobalRobo.getIdBasePadrao());

			String pathRepositorio = confGlobalService.getPathRepositorio(base);

			BancoDto banco = bancoService.findOneByNumBanco(base, "001");
			List<RiscoDto> riscos = riscoService.findAll(base);
			
			FundoDto fundoAtual = fundos.get(ValorAleatorioUtil.getValorNumerico(fundos.size()));
			
			StringBuilder pathSistema = new StringBuilder();
			
			pathSistema.append(pathRepositorio)
				.append("/")
				.append("VALIDACAO_ARQUIVO")
				.append("/")
				.append(fundoAtual.getCodigoIsin())
				.append("/")
				.append(fundoAtual.getDataFundo().format(DateTimeFormatter.ofPattern("dd_MM_yyyy")))
				.append("/")
				.append("AGUARDANDO")
				.append("/");
			
			StringBuilder pathRobo = new StringBuilder();
			
			pathRobo.append(confGlobalRobo.getPath())
				.append("/")
				.append("VALIDACAO_ARQUIVO")
				.append("/")
				.append(fundoAtual.getCodigoIsin())
				.append("/")
				.append(fundoAtual.getDataFundo().format(DateTimeFormatter.ofPattern("dd_MM_yyyy")))
				.append("/")
				.append("AGUARDANDO")
				.append("/");
			
			List<CedenteDto> cedentes = cedenteService.findAll(base, fundoAtual.getIdFundo());
			if(cedentes.isEmpty()) {
				throw new BussinesException("Nenhum Cedente encontrado para o fundo " + fundoAtual.getNomeFundo());
			}
			List<SacadoDto> sacados = sacadoService.findAll(base, fundoAtual.getIdFundo());
			if(sacados.isEmpty()) {
				throw new BussinesException("Nenhum Sacado encontrado para o fundo " + fundoAtual.getNomeFundo());
			}
			List<OriginadorDto> originadores = originadorService.findAll(base, fundoAtual.getIdFundo());
			if(originadores.isEmpty()) {
				throw new BussinesException("Nenhum Originador encontrado para o fundo " + fundoAtual.getNomeFundo());
			}
			List<MovimentoDto> movimentos = movimentoService.findAllMovimentosAquisicao(base, fundoAtual.getLayoutAquisicao());
			if(movimentos.isEmpty()) {
				throw new BussinesException("Nenhum Movimento encontrado para o layout " + fundoAtual.getLayoutAquisicao());
			}
			
			for (int j = 0; j < importacaoSimuladaDto.getQuantArquivos(); j++) {
				
				
				

				
				cnab.setBanco(banco);
				cnab.setDataGravacao(LocalDate.now());
				cnab.setFundo(fundoAtual);
				cnab.setLayout(LayoutEnum.parse(fundoAtual.getLayoutAquisicao()));
				cnab.setOriginador(originadores.get(ValorAleatorioUtil.getValorNumerico(originadores.size())) );
				
				CedenteDto cedenteAtual = cedentes.get(ValorAleatorioUtil.getValorNumerico(cedentes.size()));
				
				for (int i = 0; i < importacaoSimuladaDto.getQuantTitulosPorArquivo(); i++) {
	
					addTitulo(fundoAtual, 
							  cedenteAtual, 
							  sacados.get(ValorAleatorioUtil.getValorNumerico(sacados.size())),
							  movimentos.stream().filter(c -> c.getCdOcorrencia().equals("1")).findFirst().get(),
							  riscos.get(0)
							  );
				}
				
				ConfGlobal confGlobal = confGlobalService.getConfGlobal();
				cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
				confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
				confGlobalService.salvar(confGlobal);
				
				logger.info("Gerando CNAB: {}", cnab);
				File file = geradorCnab.gerar(cnab, "AQUISICAO_PORTAL", false, pathRobo.toString());
				
				if(file == null) {
					continue;
				}
				
				Long idFila = filaService.inserirFilaImportacaoArquivo(base, fundoAtual);
				
				filaService.inserirArquivoValidacao(base, idFila , file, fundoAtual.getLayoutAquisicao(), pathSistema.toString());
				
				filaService.updateFilaImportacaoArquivo(base, idFila);
				
				cnab = new CnabDto();
			}
			
			
		}
		
	}
	
	private void atualizarFundos() {
		
		try {
		
			Base base = baseService.findById(confGlobalService.getConfGlobal().getIdBasePadrao());
		
			fundos = fundoService.findAllComDataAtual(base);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	private void addTitulo(FundoDto fundoAtual, CedenteDto cedente, SacadoDto sacado, MovimentoDto movimento,RiscoDto risco) {
		
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
//			titulo.setChaveNfe("31190600006388319890559240000000311006164587");
			titulo.setCoobrigacao(cedente.getCoobrigacao().equalsIgnoreCase("N") ? "02" : "01");
			titulo.setRisco(risco);
	
			titulo.setNumBanco(cnab.getBanco().getCodigoBanco());
			cnab.getTitulos().add(titulo.getCopy());
			
			titulo = new TituloDto();
		
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
		}
	}

	
}
