package br.com.m3Tech.solucoesFromtis.service.impl;

import br.com.m3Tech.solucoesFromtis.dto.ParametrosTestesDto;
import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;
import br.com.m3Tech.solucoesFromtis.service.ICadastroAutomatizado;
import br.com.m3Tech.solucoesFromtis.testsCadastros.fundo.Fundo;

public class CadastrarFundo implements ICadastroAutomatizado {

	@Override
	public String executar(ParametrosCadastrosAutomaticos parametros) {

		try {
			GeradorCpfCnpjRgFake gerarDoc = new GeradorCpfCnpjRgFake();

			ParametrosTestesDto parametrosTeste = new ParametrosTestesDto();

			parametrosTeste.setDocFundoNovo(gerarDoc.cnpj(true));

			Fundo fundoTeste = new Fundo(parametros.getUrl(),parametros.getContextoCustodia(), parametros.getUsuario(), parametros.getSenha(),
					parametrosTeste);

			fundoTeste.testPermitirCadastroDeNovoFundo();
		} catch (BussinesException e) {
			return e.getMessage();
		}

		return "";
	}

}
