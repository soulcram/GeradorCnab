package br.com.m3Tech.solucoesFromtis.testsCadastros.service.impl;

import java.util.List;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.beust.jcommander.internal.Lists;

import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.ParametrosTestesDto;
import br.com.m3Tech.solucoesFromtis.dto.ResultadoTesteCustodiaDto;
import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.ICedenteService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.impl.GeradorCpfCnpjRgFake;
import br.com.m3Tech.solucoesFromtis.testsCadastros.TestesAtivos;
import br.com.m3Tech.solucoesFromtis.testsCadastros.fundo.Cedente;
import br.com.m3Tech.solucoesFromtis.testsCadastros.service.IExecutorTestes;
import br.com.m3Tech.solucoesFromtis.util.BooleanUtils;
import br.com.m3Tech.solucoesFromtis.util.CpfCnpjUtils;

@Named
public class TestarCedenteServiceImpl implements IExecutorTestes {

	private static final Logger logger = LoggerFactory.getLogger(TestarCedenteServiceImpl.class);

	@Autowired
	private IConfGlobalService confGlobalService;
	@Autowired
	private IBaseService baseService;
	@Autowired
	private IFundoService fundoService;
	@Autowired
	private ICedenteService cedenteService;

	private String urlCustodia;
	private String usuarioCustodia;
	private String senhaCustodia;
	private Base base;
	private ConfGlobal confGlobal;

	private List<ResultadoTesteCustodiaDto> resultados = Lists.newArrayList();

	@Override
	public List<ResultadoTesteCustodiaDto> executarTeste(TestesAtivos testesAtivos) {

		if (!testesAtivos.isTestarCedente()) {
			return null;
		}

		logger.info("Aplicando Teste Cadastro Cedente");
		try {

			resultados = Lists.newArrayList();
			logger.info("Aplicando Testes");
			
			carregarParametros();

			base = baseService.findById(confGlobal.getIdBasePadrao());

			GeradorCpfCnpjRgFake geradorDoc = new GeradorCpfCnpjRgFake();

			ParametrosTestesDto parametros = new ParametrosTestesDto(geradorDoc.cnpj(true), null, null);

			List<FundoDto> fundos = fundoService.findAll(base);

			if (fundos != null && !fundos.isEmpty()) {
				parametros.setFundoExistente(fundos.get(0));
			}

			Cedente cedenteTeste = new Cedente(urlCustodia, usuarioCustodia, senhaCustodia, parametros);
			logger.info("Iniciando testPermitirCadastrarNovoCedente");
			Boolean testPermitirCadastrarNovoCedente = cedenteTeste.testPermitirCadastrarNovoCedente();

			CedenteDto cedenteByCpfCnpj = cedenteService.getCedenteByCpfCnpj(base,
					parametros.getFundoExistente().getIdFundo(),
					CpfCnpjUtils.removerFormatacao(parametros.getDocCedenteNovo()));

			Boolean dadosFoiInserido = false;

			if (cedenteByCpfCnpj != null) {
				parametros.setIdCedenteInserido(cedenteByCpfCnpj.getIdCedente());
				parametros.setCnpjCedenteExistente(cedenteByCpfCnpj.getDocCedente());
				dadosFoiInserido = CpfCnpjUtils.removerFormatacao(parametros.getDocCedenteNovo())
						.equals(cedenteByCpfCnpj.getDocCedente());
			} else {
				List<CedenteDto> cedentesByFundo = cedenteService.findAll(base,
						parametros.getFundoExistente().getIdFundo());

				if (cedentesByFundo != null && !cedentesByFundo.isEmpty()) {
					parametros.setCnpjCedenteExistente(cedentesByFundo.get(0).getDocCedente());
				}
			}

			resultados.add(new ResultadoTesteCustodiaDto("PermitirCadastrarNovoCedente",
					BooleanUtils.defaultFalseIfNull(testPermitirCadastrarNovoCedente)
							|| BooleanUtils.defaultFalseIfNull(dadosFoiInserido) ? "Sucesso" : "Erro",
					""));
			logger.info("Iniciando testNaoPermitirCadastrarCedenteJaCadastrado");
			cedenteTeste = new Cedente(urlCustodia, usuarioCustodia, senhaCustodia, parametros);
			Boolean testNaoPermitirCadastrarCedenteJaCadastrado = cedenteTeste
					.testNaoPermitirCadastrarCedenteJaCadastrado();

			resultados.add(new ResultadoTesteCustodiaDto("NaoPermitirCadastrarCedenteJaCadastrado",
					BooleanUtils.defaultFalseIfNull(testNaoPermitirCadastrarCedenteJaCadastrado) ? "Sucesso" : "Erro",
					""));

			logger.info("Iniciando testNaoPermitirCadastrarCedenteSemDadosObrigatorios");
			cedenteTeste = new Cedente(urlCustodia, usuarioCustodia, senhaCustodia, parametros);
			Boolean testNaoPermitirCadastrarCedenteSemDadosObrigatorios = cedenteTeste
					.testNaoPermitirCadastrarCedenteSemDadosObrigatorios();

			resultados.add(new ResultadoTesteCustodiaDto("NaoPermitirCadastrarCedenteSemDadosObrigatorios",
					BooleanUtils.defaultFalseIfNull(testNaoPermitirCadastrarCedenteSemDadosObrigatorios) ? "Sucesso"
							: "Erro",
					""));

			if (parametros.getIdCedenteInserido() != null) {
				logger.info("Deletando Cedente caso tenha sido cadastrado");
				cedenteService.deleteByIdCedente(base, parametros.getIdCedenteInserido());
			}

			logger.info("Fim Aplicando Teste Cadastro Cedente");
		} catch (Exception e) {
			logger.info("Cadastro cedente com erro: {}", e.getLocalizedMessage());
			resultados.add(new ResultadoTesteCustodiaDto("PermitirCadastrarNovoCedente", "Erro", e.getMessage()));
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
