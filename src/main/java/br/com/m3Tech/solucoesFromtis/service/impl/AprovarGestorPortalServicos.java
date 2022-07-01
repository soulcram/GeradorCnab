package br.com.m3Tech.solucoesFromtis.service.impl;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fromtis.fidc.portal.servicos.consulta.soap.AprovacaoOperacaoGestorWSProxy;
import br.com.fromtis.fidc.portal.servicos.consulta.soap.OperacaoAprovacaoGestor;
import br.com.fromtis.fidc.portal.servicos.consulta.soap.RetornoAprovacao;
import br.com.m3Tech.solucoesFromtis.dto.DadosOperacaoParaAprovacaoDto;
import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;

public class AprovarGestorPortalServicos  {

	private static final Logger logger  = LoggerFactory.getLogger(AprovarGestorPortalServicos.class);
	
	public String executar(ParametrosCadastrosAutomaticos parametros, DadosOperacaoParaAprovacaoDto dadosOperacaoDto ) {
		
		try {
			
			logger.info("Iniciando Aprovação Consultoria | {}", dadosOperacaoDto);
		
			String endpoint = "/portal-servicos/servicos/soap/aprovacaoOperacaoGestor?wsdl";
			
			logger.info("Criando Serviço | {} ", parametros.getUrl() + endpoint);
			AprovacaoOperacaoGestorWSProxy service = new AprovacaoOperacaoGestorWSProxy(parametros.getUrl() + endpoint, parametros.getUsuario(), parametros.getSenha());
		
			OperacaoAprovacaoGestor operacao = new OperacaoAprovacaoGestor(dadosOperacaoDto.getCnpjFundo(), 
					dadosOperacaoDto.getCpfCnpjCedente(), dadosOperacaoDto.getNomeArquivo());
			
			RetornoAprovacao retornoAprovacao = service.aprovarGestor(operacao);
			logger.info("Retorno | {} ", retornoAprovacao.getMensagem());
						
			return retornoAprovacao.getMensagem();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return "";

	}

}
