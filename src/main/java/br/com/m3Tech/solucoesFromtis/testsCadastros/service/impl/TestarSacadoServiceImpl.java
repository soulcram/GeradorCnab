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
import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;
import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.ISacadoService;
import br.com.m3Tech.solucoesFromtis.service.impl.GeradorCpfCnpjRgFake;
import br.com.m3Tech.solucoesFromtis.testsCadastros.TestesAtivos;
import br.com.m3Tech.solucoesFromtis.testsCadastros.sacado.Sacado;
import br.com.m3Tech.solucoesFromtis.testsCadastros.service.IExecutorTestes;
import br.com.m3Tech.solucoesFromtis.util.BooleanUtils;
import br.com.m3Tech.solucoesFromtis.util.CpfCnpjUtils;

@Named
public class TestarSacadoServiceImpl implements IExecutorTestes {

	private static final Logger logger = LoggerFactory.getLogger(TestarSacadoServiceImpl.class);

	@Autowired
	private IConfGlobalService confGlobalService;
	@Autowired
	private IBaseService baseService;
	@Autowired
	private IFundoService fundoService;
	@Autowired
	private ISacadoService sacadoService;

	private String urlCustodia;
	private String usuarioCustodia;
	private String senhaCustodia;
	private Base base;
	private ConfGlobal confGlobal;

	private List<ResultadoTesteCustodiaDto> resultados = Lists.newArrayList();

	@Override
	public List<ResultadoTesteCustodiaDto> executarTeste(TestesAtivos testesAtivos) {

		if (!testesAtivos.isTestarSacado()) {
			return null;
		}

		logger.info("Aplicando Teste Cadastro Sacado");
		try {

			resultados = Lists.newArrayList();
			logger.info("Aplicando Testes");
			
			carregarParametros();

			base = baseService.findById(confGlobal.getIdBasePadrao());

			GeradorCpfCnpjRgFake geradorDoc = new GeradorCpfCnpjRgFake();

			ParametrosTestesDto parametros = new ParametrosTestesDto(null, null, geradorDoc.cpf(true));

			List<FundoDto> fundos = fundoService.findAll(base);

			if (fundos != null && !fundos.isEmpty()) {
				parametros.setFundoExistente(fundos.get(0));
			}else {
				resultados.add(new ResultadoTesteCustodiaDto("Teste Cadastro de Sacado", "Erro", "Nenhum fundo ecnontrado"));
				return resultados;
			}

			Sacado sacadoTeste = new Sacado(urlCustodia, usuarioCustodia, senhaCustodia, parametros);
			logger.info("Iniciando testPermitirCadastrarNovoSacado");
			Boolean testPermitirCadastrarNovoSacado = sacadoTeste.testPermitirCadastrarNovoSacado();

			SacadoDto sacadoByCpfCnpj = sacadoService.getSacadoByCpfCnpj(base,
					parametros.getFundoExistente().getIdFundo(),
					CpfCnpjUtils.removerFormatacao(parametros.getDocSacadoNovo()));

			Boolean dadosFoiInserido = false;

			if (sacadoByCpfCnpj != null) {
				parametros.setIdSacadoInserido(sacadoByCpfCnpj.getIdSacado());
				parametros.setCnpjSacadoExistente(sacadoByCpfCnpj.getDocSacado());
				dadosFoiInserido = CpfCnpjUtils.removerFormatacao(parametros.getDocSacadoNovo())
						.equals(sacadoByCpfCnpj.getDocSacado());
			} else {
				List<SacadoDto> sacadosByFundo = sacadoService.findAll(base,
						parametros.getFundoExistente().getIdFundo());

				if (sacadosByFundo != null && !sacadosByFundo.isEmpty()) {
					parametros.setCnpjSacadoExistente(sacadosByFundo.get(0).getDocSacado());
				}
			}

			resultados.add(new ResultadoTesteCustodiaDto("PermitirCadastrarNovoSacado",
					BooleanUtils.defaultFalseIfNull(testPermitirCadastrarNovoSacado)
							|| BooleanUtils.defaultFalseIfNull(dadosFoiInserido) ? "Sucesso" : "Erro",
					""));
			logger.info("Iniciando testNaoPermitirCadastrarSacadoJaCadastrado");
			sacadoTeste = new Sacado(urlCustodia, usuarioCustodia, senhaCustodia, parametros);
			Boolean testNaoPermitirCadastrarSacadoJaCadastrado = sacadoTeste
					.testNaoPermitirCadastroDeSacadoJaCadadastrado();

			resultados.add(new ResultadoTesteCustodiaDto("NaoPermitirCadastroDeSacadoJaCadadastrado",
					BooleanUtils.defaultFalseIfNull(testNaoPermitirCadastrarSacadoJaCadastrado) ? "Sucesso" : "Erro",
					""));

			logger.info("Iniciando testNaoPermitirCadastrarSacadoSemDadosObrigatorios");
			sacadoTeste = new Sacado(urlCustodia, usuarioCustodia, senhaCustodia, parametros);
			Boolean testNaoPermitirCadastrarSacadoSemDadosObrigatorios = sacadoTeste
					.testNaoPermitirCadastrarSacadoSemDadosObrigatorios();

			resultados.add(new ResultadoTesteCustodiaDto("NaoPermitirCadastrarSacadoSemDadosObrigatorios",
					BooleanUtils.defaultFalseIfNull(testNaoPermitirCadastrarSacadoSemDadosObrigatorios) ? "Sucesso"
							: "Erro",
					""));

			if (parametros.getIdSacadoInserido() != null) {
				logger.info("Deletando Sacado caso tenha sido cadastrado");
				sacadoService.deleteByIdSacado(base, parametros.getIdSacadoInserido());
			}

			logger.info("Fim Aplicando Teste Cadastro Sacado");
		} catch (Exception e) {
			logger.info("Cadastro sacado com erro: {}", e.getLocalizedMessage());
			resultados.add(new ResultadoTesteCustodiaDto("Teste cadastrar sacado", "Erro", e.getMessage()));
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
