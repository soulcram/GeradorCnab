package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;

import br.com.m3Tech.solucoesFromtis.dto.ArquivoJSONDetailDto;
import br.com.m3Tech.solucoesFromtis.dto.ArquivoJSONDto;
import br.com.m3Tech.solucoesFromtis.dto.ArquivoJSONHeaderDto;
import br.com.m3Tech.solucoesFromtis.dto.ArquivoJSONLinhaDetailMensagemDto;
import br.com.m3Tech.solucoesFromtis.dto.ArquivoJSONTrailerDto;
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
import br.com.m3Tech.solucoesFromtis.service.ISimularImportacaoJson;
import br.com.m3Tech.solucoesFromtis.util.NumericUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import br.com.m3Tech.solucoesFromtis.webservices.RequestApiClient;

@Service
public class SimularAquisicaoJsonImpl implements ISimularImportacaoJson, Serializable {
	
	private static final Logger logger = LoggerFactory.getLogger(SimularAquisicaoJsonImpl.class);

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
	public void gerar(ImportacaoSimuladaDto importacaoSimuladaDto, Integer fundoSelecionado) throws Exception {

		if(TipoMovimentacao.AQUISICAO.equals(importacaoSimuladaDto.getTipoMovimentacao())) {
						
			if(importacaoSimuladaDto.getQuantTitulosPorArquivo() == null || importacaoSimuladaDto.getQuantTitulosPorArquivo() == 0) {
				throw new BussinesException("Quantidade de Títulos é obrigatório");
			}
			
			if(importacaoSimuladaDto.getQuantArquivos() == null || importacaoSimuladaDto.getQuantArquivos() == 0) {
				throw new BussinesException("Quantidade de Arquivos é obrigatório");
			}
			
			if(importacaoSimuladaDto.getQuantTitulosPorArquivo() > 999997) {
				throw new BussinesException("Quantidade de Títulos máxima é 999.997");
			}
			
			if(fundoSelecionado == null) {
				atualizarFundos();
				if(fundos.isEmpty()) {
					throw new BussinesException("Nenhum fundo encontrado com a data atual");
				}
			}
			
			ConfGlobal confGlobalRobo = confGlobalService.getConfGlobal();
			
			Base base = baseService.findById(confGlobalRobo.getIdBasePadrao());
			
			FundoDto fundoAtual =  (fundoSelecionado == null) ? fundos.get(ValorAleatorioUtil.getValorNumerico(fundos.size())) : fundoService.findOneById(base, fundoSelecionado);

			String usuarioSenha = confGlobalRobo.getUsuarioPortalServicos() + ":" + confGlobalRobo.getSenhaPortalServicos();
			
			for (int j = 0; j < importacaoSimuladaDto.getQuantArquivos(); j++) {

				ArquivoJSONDto cnabjson = gerarArquivoJson(importacaoSimuladaDto, fundoSelecionado);
				
				

				RestTemplate restTemplate = new RestTemplate();
				logger.info("Gerando CNAB Json: {}", cnabjson);
				ResponseEntity<String> response = new RequestApiClient(restTemplate, HttpMethod.POST,
						confGlobalRobo.getUrlPortalServicos())
						.addHeader("Authorization", "Basic " + Base64.encodeBase64String(usuarioSenha.getBytes()))
						.addContentTypeJson().bodyFromObject(cnabjson)
						.pathValue("portal-servicos").pathValue("servicos").pathValue("importacaoRemessaJSON").pathValue("importar")
						.requestParam("cnpjFundo", fundoAtual.getCnpjFundo()).build().enviar();
				
				logger.info("Reponse: {}", response.getBody());
				
				

			}
			
			
		}
		
	}
	
	@Override
	public ArquivoJSONDto gerarUnico(ImportacaoSimuladaDto importacaoSimuladaDto, Integer fundoSelecionado)
			throws Exception {

		if (TipoMovimentacao.AQUISICAO.equals(importacaoSimuladaDto.getTipoMovimentacao())) {

			if (importacaoSimuladaDto.getQuantTitulosPorArquivo() > 999997) {
				throw new BussinesException("Quantidade de Títulos máxima é 999.997");
			}

			if (fundoSelecionado == null) {
				atualizarFundos();
				if (fundos.isEmpty()) {
					throw new BussinesException("Nenhum fundo encontrado com a data atual");
				}
			}

			ArquivoJSONDto cnabjson = gerarArquivoJson(importacaoSimuladaDto, fundoSelecionado);

			return cnabjson;

		}

		return null;

	}
	
	private ArquivoJSONDto gerarArquivoJson(ImportacaoSimuladaDto importacaoSimuladaDto, Integer fundoSelecionado) throws Exception {
		
		ConfGlobal confGlobalRobo = confGlobalService.getConfGlobal();
		
		Base base = baseService.findById(confGlobalRobo.getIdBasePadrao());

		BancoDto banco = bancoService.findOneByNumBanco(base, "001");
		List<RiscoDto> riscos = riscoService.findAll(base);
		
		FundoDto fundoAtual =  (fundoSelecionado == null) ? fundos.get(ValorAleatorioUtil.getValorNumerico(fundos.size())) : fundoService.findOneById(base, fundoSelecionado);
		
		
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
		
		LayoutEnum layoutFundo = LayoutEnum.parse(fundoAtual.getLayoutAquisicao());	
		
		MovimentoDto movimentoDto = movimentos.stream().findFirst().get();
		
			
			ConfGlobal confGlobal = confGlobalService.getConfGlobal();
			String numSeqArquivo  = String.valueOf(confGlobal.getSeqArquivo());
			confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
			confGlobalService.salvar(confGlobal);
			
			ArquivoJSONDto cnabjson = new ArquivoJSONDto();
			
			cnabjson.setNomeArquivo(getNomeArquivo(fundoAtual.getCnpjFundo(), importacaoSimuladaDto.getTipoMovimentacao().toString(), numSeqArquivo));
			
			OriginadorDto originadorDto = originadores.get(ValorAleatorioUtil.getValorNumerico(originadores.size()));
			
			ArquivoJSONHeaderDto header = new ArquivoJSONHeaderDto("0", 
					resolveCodigoHeader(layoutFundo), 
					"REMESSA", 
					"01" , 
					"COBRANCA",
					originadorDto.getCodigoOriginador(), 
					originadorDto.getNomeOriginador(), 
					banco.getCodigoBanco(), 
					banco.getNomeBanco(), 
					LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
					resolveIdentificacaoSistemaHeader(layoutFundo), 
					"10", 
					LocalDate.now().toString());
			
			cnabjson.setHeader(Lists.newArrayList(header));
			

			
			for (int i = 0; i < importacaoSimuladaDto.getQuantTitulosPorArquivo(); i++) {
				
				CedenteDto cedenteAtual = cedentes.get(ValorAleatorioUtil.getValorNumerico(cedentes.size()));
				SacadoDto sacadoAtual = sacados.get(ValorAleatorioUtil.getValorNumerico(sacados.size()));
				
				BigDecimal valor = ValorAleatorioUtil.getValorDecimal(null,null);
				
				List<ArquivoJSONLinhaDetailMensagemDto> linhaDetailMensagem = Lists.newArrayList();
				
				ArquivoJSONLinhaDetailMensagemDto linhaDetailmsg = new ArquivoJSONLinhaDetailMensagemDto("2","2.00","Desconto concedido por pagamento antecipado");

				linhaDetailMensagem.add(linhaDetailmsg);
				
				
				
				ArquivoJSONDetailDto detail = new ArquivoJSONDetailDto("1", 
						"", 
						"", 
						"", 
						"01", 
						"", 
						"", 
						"", 
						"", 
						riscos.get(0).getCodRisco(), 
						ValorAleatorioUtil.getValor(25), 
						banco.getCodigoBanco(), 
						"", 
						"2.00", 
						NumericUtils.getValorMenos20PorCento(valor).toString(), 
						"", 
						"", 
						LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
						LocalDate.now().plusDays(10).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
						movimentoDto.getCdOcorrencia(), 
						ValorAleatorioUtil.getValor(10), 
						LocalDate.now().plusMonths(2).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
						valor.toString(), 
						"001", 
						"0001", 
						"01", 
						"", 
						LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
						"", 
						"", 
						cedenteAtual.getDocCedente().length() == 14 ? "02" : "01", 
						"", 
						ValorAleatorioUtil.getValor(10), 
						NumericUtils.getValorMenos20PorCento(valor).toString(), 
						"", 
						sacadoAtual.getDocSacado().length() == 14 ? "02" : "01",  
						sacadoAtual.getDocSacado(), 
						sacadoAtual.getNomeSacado(), 
						sacadoAtual.getEndereco(), 
						ValorAleatorioUtil.getStringNumeros(6), 
						"001", 
						sacadoAtual.getCep(), 
						cedenteAtual.getNomeCedente(), 
						cedenteAtual.getDocCedente(), 
						"31190600006388319890559240000000311006164587", 
						"", 
						"", 
						"", 
						"", 
						"", 
						"", 
						"", 
						"", 
						linhaDetailMensagem);
			
				cnabjson.setDetail(Lists.newArrayList(detail));
			}
			
			ArquivoJSONTrailerDto trailer = new ArquivoJSONTrailerDto("9");
			cnabjson.setTrailer(Lists.newArrayList(trailer));
			
			return cnabjson;
	}
	
	private String resolveCodigoHeader(LayoutEnum layout) {
		switch (layout) {
		case CNAB_500_REMESSA_DAYMAXX :
			return "8";
		case CNAB_500_REMESSA_FIDD:
			return "8";
		case CNAB_550_FINAXIS:
			return "5";
		case CNAB_500_REMESSA_PAULISTA:
			return "2";
		default:
			return "1";
		}
	}
	
	private String resolveIdentificacaoSistemaHeader(LayoutEnum layout) {
		switch (layout) {
		case CNAB_500_REMESSA_STONE:
			return "SFR";
		case CNAB_500_OT_REMESSA:
			return "SFR";
		case CNAB_600_DAYCOVAL:
			return "SFR";
		case CNAB_600_PLANNER_REM03:
			return "SFR";
		default:
			return "MX";
		}
	}
	
	private String getNomeArquivo(String cnpjFundo, String tipo, String numSeqArquivo) {
		return cnpjFundo + "_" + "CNAB_JSON" + "_" + tipo + "_" + LocalDate.now().toString().replaceAll("-", "") + "_" + numSeqArquivo + ".json";
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
