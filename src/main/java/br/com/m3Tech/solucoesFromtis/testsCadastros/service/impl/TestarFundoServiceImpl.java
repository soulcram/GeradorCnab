package br.com.m3Tech.solucoesFromtis.testsCadastros.service.impl;

import java.util.List;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.beust.jcommander.internal.Lists;

import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.ParametrosTestesDto;
import br.com.m3Tech.solucoesFromtis.dto.ResultadoTesteCustodiaDto;
import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.impl.GeradorCpfCnpjRgFake;
import br.com.m3Tech.solucoesFromtis.testsCadastros.TestesAtivos;
import br.com.m3Tech.solucoesFromtis.testsCadastros.fundo.Fundo;
import br.com.m3Tech.solucoesFromtis.testsCadastros.service.IExecutorTestes;
import br.com.m3Tech.solucoesFromtis.util.BooleanUtils;
import br.com.m3Tech.solucoesFromtis.util.CpfCnpjUtils;

@Named
public class TestarFundoServiceImpl implements IExecutorTestes {

	private static final Logger logger = LoggerFactory.getLogger(TestarFundoServiceImpl.class);

	@Autowired
	private IConfGlobalService confGlobalService;
	@Autowired
	private IBaseService baseService;
	@Autowired
	private IFundoService fundoService;

	private String urlCustodia;
	private String contextoCustodia;
	private String usuarioCustodia;
	private String senhaCustodia;
	private Base base;
	private ConfGlobal confGlobal;

	private List<ResultadoTesteCustodiaDto> resultados = Lists.newArrayList();

	@Override
	public List<ResultadoTesteCustodiaDto> executarTeste(TestesAtivos testesAtivos) {

		if (!testesAtivos.isTestarFundo()) {
			return null;
		}

		try {

			carregarParametros();
			
			resultados = Lists.newArrayList();
			logger.info("Aplicando Testes");

			base = baseService.findById(confGlobal.getIdBasePadrao());

			GeradorCpfCnpjRgFake geradorDoc = new GeradorCpfCnpjRgFake();

			ParametrosTestesDto parametros = new ParametrosTestesDto(null, geradorDoc.cnpj(true), null);

			List<FundoDto> fundos = fundoService.findAll(base);

			if (fundos != null && !fundos.isEmpty()) {
				parametros.setFundoExistente(fundos.get(0));
			}

			logger.info("Aplicando Teste Cadastro Fundo");

			Fundo fundoTeste = new Fundo(urlCustodia,"fidcCustodia", usuarioCustodia, senhaCustodia, parametros);
			logger.info("Iniciando testPermitirCadastroDeNovoFundo");

			try {
				fundoTeste.testPermitirCadastroDeNovoFundo();
			} catch (BussinesException ex) {
				resultados.add(new ResultadoTesteCustodiaDto("PermitirCadastroDeNovoFundo", "Erro", ex.getMessage()));
			}

			FundoDto fundoByCpfCnpj = fundoService.findFundoByCnpj(base,
					CpfCnpjUtils.removerFormatacao(parametros.getDocFundoNovo()));

			Boolean dadosFoiInserido = false;

			if (fundoByCpfCnpj != null) {
				parametros.setIdFundoInserido(fundoByCpfCnpj.getIdFundo());
				parametros.setCnpjFundoExistente(CpfCnpjUtils.inserirFormatacao(fundoByCpfCnpj.getCnpjFundo()));
				dadosFoiInserido = CpfCnpjUtils.removerFormatacao(parametros.getDocFundoNovo())
						.equals(fundoByCpfCnpj.getCnpjFundo());
			} else {

				if (fundos != null && !fundos.isEmpty()) {
					parametros.setCnpjFundoExistente(CpfCnpjUtils.inserirFormatacao(fundos.get(0).getCnpjFundo()));
				}
			}

			resultados.add(new ResultadoTesteCustodiaDto("PermitirCadastroDeNovoFundo",
					BooleanUtils.defaultFalseIfNull(dadosFoiInserido) ? "Sucesso" : "Erro", ""));

			logger.info("Iniciando testNaoPermitirCadastrarCedenteJaCadastrado");
			fundoTeste = new Fundo(urlCustodia,"fidcCustodia", usuarioCustodia, senhaCustodia, parametros);

			try {
				Boolean testNaoPermitidoCadastroDeFundoJaCadastrado = fundoTeste
						.testNaoPermitidoCadastroDeFundoJaCadastrado();
				resultados.add(new ResultadoTesteCustodiaDto("NaoPermitidoCadastroDeFundoJaCadastrado",
						BooleanUtils.defaultFalseIfNull(testNaoPermitidoCadastroDeFundoJaCadastrado) ? "Sucesso"
								: "Erro",
						""));

			} catch (BussinesException ex) {
				resultados.add(new ResultadoTesteCustodiaDto("NaoPermitidoCadastroDeFundoJaCadastrado", "Erro",
						ex.getMessage()));
			}

			logger.info("Iniciando testNaoPermitirCadastrarFundoSemDadosObrigatorios");
			fundoTeste = new Fundo(urlCustodia,"fidcCustodia", usuarioCustodia, senhaCustodia, parametros);

			try {
				Boolean testNaoPermitirCadastrarFundoSemDadosObrigatorios = fundoTeste
						.testNaoPermitirCadastrarFundoSemDadosObrigatorios();
				resultados.add(new ResultadoTesteCustodiaDto("NaoPermitirCadastrarFundoSemDadosObrigatorios",
						BooleanUtils.defaultFalseIfNull(testNaoPermitirCadastrarFundoSemDadosObrigatorios) ? "Sucesso"
								: "Erro",
						""));

			} catch (BussinesException ex) {
				resultados.add(new ResultadoTesteCustodiaDto("NaoPermitirCadastrarFundoSemDadosObrigatorios", "Erro",
						ex.getMessage()));
			}

			if (parametros.getIdFundoInserido() != null) {
				logger.info("Deletando Fundo caso tenha sido cadastrado");
				fundoService.deleteByIdFundo(base, parametros.getIdFundoInserido());
			}

			logger.info("Fim Aplicando Teste Cadastro Fundo");
		} catch (Exception e) {
			logger.info("Cadastro fundo com erro: {}", e.getLocalizedMessage());
			resultados.add(new ResultadoTesteCustodiaDto("TesteCadastrarFundo", "Erro", e.getMessage()));
		}

		return resultados;

	}

	private void carregarParametros() throws BussinesException {
		logger.info("Inicio carregar parametros");
		if (confGlobalService == null) {
			logger.error("confGlobalService nulo");
			throw new BussinesException("confGlobalService não iniciada");
		}

		this.confGlobal = confGlobalService.getConfGlobal();

		if (confGlobal == null) {
			logger.error("Configurações Globais sem cadastro");
			throw new BussinesException("Configurações Globais sem cadastro");
		}

		urlCustodia = confGlobal.getUrlCustodia();
		if (urlCustodia == null) {
			logger.error("url Custodia sem cadastro");
			throw new BussinesException("Url Custodia sem cadastro");
		}
		usuarioCustodia = confGlobal.getUsuarioCustodia();
		if (usuarioCustodia == null) {
			logger.error("usuario Custodia sem cadastro");
			throw new BussinesException("Usuario Custodia sem cadastro");
		}
		senhaCustodia = confGlobal.getSenhaCustodia();
		if (senhaCustodia == null) {
			logger.error("senha Custodia sem cadastro");
			throw new BussinesException("Senha Custodia sem cadastro");
		}
		if (confGlobal.getIdBasePadrao() == null) {
			logger.error("Base de Dados Padrao sem cadastro");
			throw new BussinesException("Base Padrao sem cadastro");
		}
		base = baseService.findById(confGlobal.getIdBasePadrao());

		logger.info("Fim carregar parametros");
	}

}
