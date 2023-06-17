package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.File;
import java.time.LocalDate;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.beanio.Cnab240DetalheT;
import br.com.m3Tech.solucoesFromtis.beanio.Cnab240DetalheU;
import br.com.m3Tech.solucoesFromtis.beanio.Cnab240HeaderArquivo;
import br.com.m3Tech.solucoesFromtis.beanio.Cnab240HeaderLote;
import br.com.m3Tech.solucoesFromtis.beanio.Cnab240TraillerArquivo;
import br.com.m3Tech.solucoesFromtis.beanio.Cnab240TraillerLote;
import br.com.m3Tech.solucoesFromtis.beanio.CnabDetail;
import br.com.m3Tech.solucoesFromtis.beanio.CnabDetail2L41;
import br.com.m3Tech.solucoesFromtis.beanio.CnabDetail2L47E56;
import br.com.m3Tech.solucoesFromtis.beanio.CnabDetail2L90;
import br.com.m3Tech.solucoesFromtis.beanio.CnabDetailCobranca;
import br.com.m3Tech.solucoesFromtis.beanio.CnabHeader;
import br.com.m3Tech.solucoesFromtis.beanio.CnabHeaderCobranca;
import br.com.m3Tech.solucoesFromtis.beanio.CnabTrailler;
import br.com.m3Tech.solucoesFromtis.beanio.DadosArquivoBaixas;
import br.com.m3Tech.solucoesFromtis.beanio.DadosArquivoSng;
import br.com.m3Tech.solucoesFromtis.beanio.RegistroRetornoB3Detail;
import br.com.m3Tech.solucoesFromtis.beanio.RegistroRetornoB3Header;
import br.com.m3Tech.solucoesFromtis.beanio.RegistroRetornoB3Trailer;
import br.com.m3Tech.solucoesFromtis.dto.CnabBaixaOrgaoDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabCobrancaDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabIntegracaoSngDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.service.IGeradorCnab;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;

@Service
public class GeradorCnabImpl implements IGeradorCnab {

	private static final Logger logger = LoggerFactory.getLogger(GeradorCnabImpl.class);

	@Override
	public File gerar(CnabDto cnab, String tipo, Boolean importacaoAutomatica, String path) {

		try {
			StreamFactory factory = StreamFactory.newInstance();

			factory.loadResource("beanio.xml");

			File pathArquivo = new File(path);

			if (!pathArquivo.exists()) {
				pathArquivo.mkdirs();
			}

			File arquivoFinal = new File(pathArquivo, getNomeArquivo(cnab, tipo, importacaoAutomatica));

			BeanWriter out = factory.createWriter(cnab.getLayout().getNmLayout(), arquivoFinal);

			out.write(new CnabHeader(cnab));
			int qtdeTitulos = 2;
			for (TituloDto dto : cnab.getTitulos()) {
				out.write(new CnabDetail(dto, qtdeTitulos++, cnab.getLayout()));
				if (LayoutEnum.CNAB_500_REMESSA_FIDD.equals(cnab.getLayout())) {
					Integer quantidade = dto.getQtdLastros() != null ? Integer.parseInt(dto.getQtdLastros()) : 1;
					for (int i = 0; i < quantidade; i++) {
						out.write(new CnabDetail2L41(dto, quantidade, qtdeTitulos++));
					}
				}
				if ( ( LayoutEnum.CNAB_500_VERSAO_FINAXIS.equals(cnab.getLayout()) || LayoutEnum.CNAB_400_REMESSA_FINAXIS.equals(cnab.getLayout())) && "DM".equals(dto.getEspecie()) ) {
					out.write(new CnabDetail2L47E56(dto, qtdeTitulos++));
				}
				if (LayoutEnum.CNAB_444_REMESSA_AUTBANK.equals(cnab.getLayout()) ) {
					out.write(new CnabDetail2L90("Sem Mensagem", qtdeTitulos++));
				}
				
			}
			out.write(new CnabTrailler(StringUtils.getNumeroComZerosAEsquerda(qtdeTitulos,(LayoutEnum.CNAB_450_REMESSA_GENIAL.equals(cnab.getLayout()) || LayoutEnum.CNAB_450_REMESSA_BRASIL_PLURAL.equals(cnab.getLayout()) ) ? 10 : 6)));

			out.flush();
			out.close();

			System.out.println("Fim da Geração");

			return arquivoFinal;

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;

	}

	@Override
	public void gerarRetornoCobrança(CnabCobrancaDto cnab, String tipo, Boolean importacaoAutomatica, String path) {

		StreamFactory factory = StreamFactory.newInstance();

		factory.loadResource("beanio.xml");

		File pathArquivo = new File(path);

		if (!pathArquivo.exists()) {
			pathArquivo.mkdirs();
		}

		File arquivoFinal = new File(pathArquivo, getNomeArquivo(cnab, tipo, importacaoAutomatica));

		BeanWriter out = factory.createWriter(cnab.getLayout().getNmLayout(), arquivoFinal);
		
		if(cnab.getLayout().getTamLayout().equals(240)) {
			
			out.write(new Cnab240HeaderArquivo(cnab));
			out.write(new Cnab240HeaderLote(cnab));
			int qtdeTitulos = 3;
			for (TituloDto dto : cnab.getTitulos()) {
				out.write(new Cnab240DetalheT(dto, qtdeTitulos++));
				out.write(new Cnab240DetalheU(dto, qtdeTitulos++));
			}
			out.write(new Cnab240TraillerLote(StringUtils.getNumeroComZerosAEsquerda(qtdeTitulos, 6)));
			out.write(new Cnab240TraillerArquivo(StringUtils.getNumeroComZerosAEsquerda(qtdeTitulos, 6)));
			
		}else {

			out.write(new CnabHeaderCobranca(cnab));
			int qtdeTitulos = 2;
			for (TituloDto dto : cnab.getTitulos()) {
				out.write(new CnabDetailCobranca(dto, qtdeTitulos++, cnab.getLayout()));
			}
			out.write(new CnabTrailler(StringUtils.getNumeroComZerosAEsquerda(qtdeTitulos, 6)));
		}
		out.flush();
		out.close();

		System.out.println("Fim da Geração");

	}
	

	@Override
	public void gerarBaixaOrgao(CnabBaixaOrgaoDto cnab, String path) {
		StreamFactory factory = StreamFactory.newInstance();

		factory.loadResource("beanio.xml");

		File pathArquivo = new File(path);

		if (!pathArquivo.exists()) {
			pathArquivo.mkdirs();
		}

		File arquivoFinal = new File(pathArquivo, getNomeArquivoOrgaoBaixa(cnab));

		BeanWriter out = factory.createWriter("arquivosBaixas", arquivoFinal);


		for (DadosArquivoBaixas dto : cnab.getTitulos()) {
			out.write(dto);
		}

		out.flush();
		out.close();

		System.out.println("Fim da Geração");
		
	}
	
	@Override
	public void gerarArquivoSng(CnabIntegracaoSngDto cnab, String path) {
		StreamFactory factory = StreamFactory.newInstance();

		factory.loadResource("beanio.xml");

		File pathArquivo = new File(path);

		if (!pathArquivo.exists()) {
			pathArquivo.mkdirs();
		}

		File arquivoFinal = new File(pathArquivo, getNomeArquivoSng(cnab));

		BeanWriter out = factory.createWriter("retornoB3_v1", arquivoFinal);
		
		int qtdeTitulos = 1;

		out.write(new RegistroRetornoB3Header("H", 
				                              "A", 
				                              "nomeCliente", 
				                              LocalDate.now(),
				                              StringUtils.getNumeroComZerosAEsquerda(qtdeTitulos++, 9)
				                              )
				   );

		for (DadosArquivoSng dto : cnab.getTitulos()) {
			Integer ano = ValorAleatorioUtil.getValorNumerico(1980, 2100);
			out.write(new RegistroRetornoB3Detail(
						"D",
						StringUtils.getNumeroComZerosAEsquerda(qtdeTitulos++, 9),
						dto.getChassi(),
						1,
						"SP",
						ano,
						ano + 1,
						"01",
						1,
						LocalDate.now(),
						"F",
						"",
						2
					));
		}

		out.write(new RegistroRetornoB3Trailer(
				"T",
				cnab.getTitulos().size(),
				StringUtils.getNumeroComZerosAEsquerda(qtdeTitulos++, 9)
				));
		
		out.flush();
		out.close();

		System.out.println("Fim da Geração");
		
	}
	
	private String getNomeArquivoSng(CnabIntegracaoSngDto cnab) {
		return "B3_" + LocalDate.now().toString().replaceAll("-", "") + "_" + cnab.getNumSeqArquivo() + ".txt";
	}

	private static String getNomeArquivo(CnabDto cnab, String tipo, boolean importacaoAutomatica) {
		return (importacaoAutomatica ? cnab.getFundo().getCnpjFundo() + "_" : "") + "CNAB_"
				+ (cnab.getLayout() != null ? cnab.getLayout().getTamLayout().toString() : "") + "_" + tipo + "_"
				+ LocalDate.now().toString().replaceAll("-", "") + "_" + cnab.getNumSeqArquivo() + ".txt";
	}

	private static String getNomeArquivo(CnabCobrancaDto cnab, String tipo, boolean importacaoAutomatica) {
		return (importacaoAutomatica ? cnab.getFundo().getCnpjFundo() + "_" : "") + "CNAB_"
				+ (cnab.getLayout() != null ? cnab.getLayout().getTamLayout().toString() : "") + "_" + tipo + "_"
				+ LocalDate.now().toString().replaceAll("-", "") + "_" + cnab.getNumSeqArquivo() + ".txt";
	}
	
	private static String getNomeArquivoOrgaoBaixa(CnabBaixaOrgaoDto cnab) {
		return  "CNAB_BAIXA_ORGAO_" + cnab.getNumSeqArquivo() + ".txt";
	}


}
