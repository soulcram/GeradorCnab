package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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
public class SimularRecompraParcialPortalImpl implements ISimularImportacaoPortal, Serializable {
	
	private static final Logger logger = LoggerFactory.getLogger(SimularRecompraParcialPortalImpl.class);

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
	private IMovimentoService movimentoService;
	@Autowired
	private IConfGlobalService confGlobalService;
	@Autowired
	private  IGeradorCnab geradorCnab;
	@Autowired
	private IFilaService filaService;
	@Autowired
	private ISacadoService sacadoService;
	@Autowired
	private ICedenteService cedenteService;
	@Autowired
	private IRiscoService riscoService;
	
	private CnabDto cnab;
	
	private List<FundoDto> fundos;

	@Override
	public void gerar(ImportacaoSimuladaDto importacaoSimuladaDto) throws Exception {

		if(TipoMovimentacao.RECOMPRA_PARCIAL.equals(importacaoSimuladaDto.getTipoMovimentacao())) {
			
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
			
			Base base = baseService.findById(confGlobalService.getConfGlobal().getIdBasePadrao());

			String pathRepositorio = confGlobalService.getPathRepositorio(base);

			BancoDto banco = bancoService.findOneByNumBanco(base, "001");
			
			List<TituloDto> findAllTituloEmEstoque = movimentoService.findAllTituloEmEstoque(base);

			List<RiscoDto> riscos = riscoService.findAll(base);
			if(riscos.isEmpty()) {
				throw new BussinesException("Nenhum Risco TB_CLASS_RISCO encontrado");
			}
			
			for(int i = 0; i < importacaoSimuladaDto.getQuantArquivos(); i++) {
				
				if(findAllTituloEmEstoque == null || findAllTituloEmEstoque.isEmpty()) {
					logger.info("Nenhum titulo disponível para baixa.");
					break;
				}
				
				FundoDto fundoAtual = fundos.get(ValorAleatorioUtil.getValorNumerico(fundos.size()));
				
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
				List<MovimentoDto> movimentosRecompraAquisicao = movimentoService.findAllMovimentosRecompraAquisicao(base, fundoAtual.getLayoutAquisicao());
				if(movimentosRecompraAquisicao.isEmpty()) {
					throw new BussinesException("Nenhum Movimento Recompra Aquisicao encontrado para o layout " + fundoAtual.getLayoutAquisicao());
				}
				
				List<MovimentoDto> movimentosRecompraParcial = movimentoService.findAllMovimentosRecompraParcial(base, fundoAtual.getLayoutAquisicao());
				if(movimentosRecompraParcial.isEmpty()) {
					throw new BussinesException("Nenhum Movimento Recompra Baixa encontrado para o layout " + fundoAtual.getLayoutAquisicao());
				}

				
									
				List<TituloDto> titulos = findAllTituloEmEstoque.stream()
				                      .filter(t -> t.getIdFundo().equals(fundoAtual.getIdFundo()))
				                      .limit(importacaoSimuladaDto.getQuantTitulosPorArquivo()).collect(Collectors.toList());
				
				if(titulos == null || titulos.isEmpty()) {
					fundos.remove(fundoAtual);
					continue;
				}else {
					findAllTituloEmEstoque.removeAll(titulos);
				}
				
				titulos.forEach(t -> {t.setMovimento(movimentosRecompraParcial.get(0));
					t.setValorAbatimento(NumericUtils.getValorMenos20PorCento(t.getValorTitulo()));
					t.setValorPago(NumericUtils.getValorMenos20PorCento(t.getValorTitulo()));
				});
				
				addTitulo(fundoAtual, 
						      cedentes.get(ValorAleatorioUtil.getValorNumerico(cedentes.size())), 
							  sacados.get(ValorAleatorioUtil.getValorNumerico(sacados.size())),
							  movimentosRecompraAquisicao.get(0),
							  riscos.get(0)
							  );
				
				StringBuilder path = new StringBuilder();
				
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
				
				ConfGlobal confGlobal = confGlobalService.getConfGlobal();
				cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
				confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
				confGlobalService.salvar(confGlobal);
				
				cnab.setBanco(banco);
				cnab.setDataGravacao(LocalDate.now());
				cnab.setFundo(fundoAtual);
				cnab.setLayout(LayoutEnum.parse(fundoAtual.getLayoutAquisicao()));
				cnab.setOriginador(originadores.get(ValorAleatorioUtil.getValorNumerico(originadores.size())) );
				
				cnab.setTitulos(titulos);
				
				logger.info("Gerando CNAB: {}", cnab);
				File file = geradorCnab.gerar(cnab, "RECOMPRA_PARCIAL_PORTAL", false, path.toString());
				
				if(file == null) {
					continue;
				}
				
				filaService.inserirArquivoValidacao(base, filaService.inserirFilaImportacaoArquivo(base, fundoAtual), file, fundoAtual.getLayoutAquisicao(),path.toString());
				
				cnab = new CnabDto();
				
			}
			
			
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
					
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
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
}
