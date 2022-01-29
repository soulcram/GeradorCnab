package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.beanio.CnabDetail;
import br.com.m3Tech.solucoesFromtis.beanio.CnabHeader;
import br.com.m3Tech.solucoesFromtis.beanio.CnabTrailler;
import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;
import br.com.m3Tech.solucoesFromtis.dto.TipoRecebivelDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IBancoService;
import br.com.m3Tech.solucoesFromtis.service.ICedenteService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IGeracaoCnabPadrao;
import br.com.m3Tech.solucoesFromtis.service.IMovimentoService;
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;
import br.com.m3Tech.solucoesFromtis.service.ISacadoService;
import br.com.m3Tech.solucoesFromtis.service.ITipoRecebivelService;
import br.com.m3Tech.solucoesFromtis.util.NumericUtils;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;

@Service
public class GeracaoCnabPadrao implements IGeracaoCnabPadrao {
	
	private final ICedenteService cedenteService;
	private final ISacadoService sacadoService;
	private final IConfGlobalService confGlobalService;
	private final IMovimentoService movimentoService;
	private final ITipoRecebivelService tipoRecebivelService;
	private final IOriginadorService originadorService;
	private final IBancoService bancoService;
	
	@Autowired
	public GeracaoCnabPadrao(final ICedenteService cedenteService,
			final ISacadoService sacadoService,
			final IConfGlobalService confGlobalService,
			final IMovimentoService movimentoService,
			final ITipoRecebivelService tipoRecebivelService,
			final IOriginadorService originadorService,
			final IBancoService bancoService) {
		this.cedenteService = cedenteService;
		this.sacadoService = sacadoService;
		this.confGlobalService = confGlobalService;
		this.movimentoService = movimentoService;
		this.tipoRecebivelService = tipoRecebivelService;
		this.originadorService = originadorService;
		this.bancoService = bancoService;
	}
	
	private CnabDto cnab;
	private TituloDto titulo;

	@Override
	public void gerarCnabAquisicao(Connection con, FundoDto fundo) throws Exception {
		
			cnab = new CnabDto();
			titulo = new TituloDto();
		
			cnab.setTitulos(new ArrayList<>());
			
			cnab.setDataGravacao(LocalDate.now());
			cnab.setBanco(bancoService.findOneById(con, 2));
			
	
			BigDecimal valor = ValorAleatorioUtil.getValorDecimal();
			CedenteDto cedenteSelecionado = getCedenteSelecionado(con, fundo.getIdFundo());
			SacadoDto sacadoSelecionado = getSacadoSelecionado(con, fundo.getIdFundo());
	
			titulo.setValorTitulo(valor);
			titulo.setValorAquisicao(NumericUtils.getValorMenos10PorCento(valor));
			titulo.setSeuNumero(ValorAleatorioUtil.getValor(25));
			titulo.setNumeroDocumento(ValorAleatorioUtil.getValor(10));
			titulo.setTermoCessao(ValorAleatorioUtil.getValor(10));
			titulo.setDataVencimento(cnab.getDataGravacao().plusDays(35));
			titulo.setEspecie(getTipoRecebivelDto(con, fundo.getLayoutAquisicao()).getCdEspecie());
			titulo.setMovimento(getMovimentoAquisicaoDto(con, fundo.getLayoutAquisicao()));
			titulo.setCedente(cedenteSelecionado);
			titulo.setSacado(sacadoSelecionado);
			titulo.setCoobrigacao("N".equals(cedenteSelecionado.getCoobrigacao()) ? "02" : "01");
	
			titulo.setNumBanco(cnab.getBanco().getCodigoBanco());
			cnab.getTitulos().add(titulo.getCopy());
			
			titulo = new TituloDto();
		



		cnab.setDataGravacao(fundo.getDataFundo());
		cnab.setFundo(fundo);
		cnab.setLayout(LayoutEnum.parse(fundo.getLayoutAquisicao()));
		cnab.setOriginador(originadorService.getPrimeiroOriginador(con, fundo.getIdFundo()));
		
		
		
		gerar();
		
	}
	


	private void gerar() throws Exception {
		
		ConfGlobal confGlobal = confGlobalService.getConfGlobal();
		cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
		confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
		confGlobal.save();
		
		StreamFactory factory = StreamFactory.newInstance();
        
		factory.loadResource("beanio.xml");
        
        File pathArquivo = new File(confGlobal.getPath());
        
        if(!pathArquivo.exists()) {
        	pathArquivo.mkdirs();
        }
        
        File arquivoFinal = new File(pathArquivo, getNomeArquivo(cnab.getNumSeqArquivo()));
        
        BeanWriter out = factory.createWriter(cnab.getLayout().getNmLayout(),arquivoFinal );        
                
        out.write(new CnabHeader(cnab));
        int qtdeTitulos = 2;
       
        for(TituloDto dto : cnab.getTitulos()) {
        	out.write(new CnabDetail(dto, qtdeTitulos++));
        }
        
        out.write(new CnabTrailler(StringUtils.getNumeroComZerosAEsquerda(qtdeTitulos,6)));
        
        out.flush();
        out.close();
		
	}

	private String getNomeArquivo(Integer seq) {
		return "CNAB_TESTE_" + cnab.getLayout().getTamLayout()
				+ cnab.getTitulos().get(0).getMovimento().getNmMovimento()
				+ LocalDate.now().toString().replaceAll("-", "") + "_" + seq + ".txt";
	}

	private TipoRecebivelDto getTipoRecebivelDto(Connection con, Integer cdLayout) throws SQLException{
		return tipoRecebivelService.getPrimeiroTipoRecebivelAquisicao(con,cdLayout);
	}

	private MovimentoDto getMovimentoAquisicaoDto(Connection con, Integer cdLayout) throws SQLException {
		return movimentoService.getPrimeiroMovimentoAquisicao(con, cdLayout);
	}

	private SacadoDto getSacadoSelecionado(Connection con, Integer idFundo) throws SQLException{
		return sacadoService.getPrimeiroSacado(con, idFundo);
	}

	private CedenteDto getCedenteSelecionado(Connection con, Integer idFundo) throws SQLException {
		return cedenteService.getPrimeiroCedente(con, idFundo);
	}

}
