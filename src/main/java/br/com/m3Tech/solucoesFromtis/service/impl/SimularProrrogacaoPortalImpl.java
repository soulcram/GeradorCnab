package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.BancoDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.ImportacaoSimuladaDto;
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.enuns.TipoMovimentacao;
import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IBancoService;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFilaService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.IGeradorCnab;
import br.com.m3Tech.solucoesFromtis.service.IMovimentoService;
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;
import br.com.m3Tech.solucoesFromtis.service.ISimularImportacaoPortal;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;

@Service
public class SimularProrrogacaoPortalImpl implements ISimularImportacaoPortal, Serializable {
	
	private static final Logger logger = LoggerFactory.getLogger(SimularProrrogacaoPortalImpl.class);

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
	
	private CnabDto cnab;
	
	private List<FundoDto> fundos;

	@Override
	public void gerar(ImportacaoSimuladaDto importacaoSimuladaDto) throws Exception {

		if(TipoMovimentacao.PRORROGACAO.equals(importacaoSimuladaDto.getTipoMovimentacao())) {
			
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
			if(findAllTituloEmEstoque == null || findAllTituloEmEstoque.isEmpty()) {
				throw new BussinesException("Nenhum fundo encontrado com a data atual");
			}
			
			
			for(int i = 0; i < importacaoSimuladaDto.getQuantArquivos(); i++) {
				
				FundoDto fundoAtual = fundos.get(ValorAleatorioUtil.getValorNumerico(fundos.size()));
				
				List<OriginadorDto> originadores = originadorService.findAll(base, fundoAtual.getIdFundo());
				List<MovimentoDto> movimentos = movimentoService.findAllMovimentosProrrogacao(base, fundoAtual.getLayoutAquisicao());
									
				List<TituloDto> titulos = findAllTituloEmEstoque.stream()
				                      .filter(t -> t.getIdFundo().equals(fundoAtual.getIdFundo()))
				                      .limit(importacaoSimuladaDto.getQuantTitulosPorArquivo()).collect(Collectors.toList());
				
				if(titulos == null || titulos.isEmpty()) {
					fundos.remove(fundoAtual);
					continue;
				}else {
					findAllTituloEmEstoque.removeAll(titulos);
				}
				
				titulos.forEach(t -> {t.setMovimento(movimentos.get(ValorAleatorioUtil.getValorNumerico(movimentos.size())));
					t.setDataVencimento(t.getDataVencimento().plusDays(10));
				});
				
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
				File file = geradorCnab.gerar(cnab, "PRORROGACAO_PORTAL", false, path.toString());
				
				if(file == null) {
					continue;
				}
				
				filaService.inserirArquivoValidacao(base, filaService.inserirFilaImportacaoArquivo(base, fundoAtual), file, fundoAtual.getLayoutAquisicao(),path.toString());
				
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
	
}
