package br.com.m3Tech.solucoesFromtis.service.impl;

import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fromtis.fidc.portal.servicos.consulta.soap.AprovacaoOperacaoGestorWSProxy;
import br.com.fromtis.fidc.portal.servicos.consulta.soap.OperacaoAprovacaoGestor;
import br.com.fromtis.fidc.portal.servicos.consulta.soap.RetornoAprovacao;
import br.com.m3Tech.solucoesFromtis.dto.DadosOperacaoParaAprovacaoDto;
import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;

public class AprovarGestorPortalServicos  {

	private static final Logger logger  = LoggerFactory.getLogger(AprovarGestorPortalServicos.class);
	
	public void executar(ParametrosCadastrosAutomaticos parametros, List<DadosOperacaoParaAprovacaoDto> dados ) {
		
		try {
			
			String endpoint = "/portal-servicos/servicos/soap/aprovacaoOperacaoGestor?wsdl";
			
			logger.info("Criando Serviço | {} ", parametros.getUrl() + endpoint);
			AprovacaoOperacaoGestorWSProxy service = new AprovacaoOperacaoGestorWSProxy(parametros.getUrl() + endpoint, parametros.getUsuario(), parametros.getSenha());
		
			
			dados.forEach(o -> {
				
				try {
					
					try {
						System.out.println(LocalTime.now());
						Thread.sleep(1500);
						System.out.println(LocalTime.now());
					} catch (InterruptedException e) {
					}
					
					logger.info("Iniciando Aprovação Gestor | {}", o);
					

					OperacaoAprovacaoGestor operacao = new OperacaoAprovacaoGestor(o.getCnpjFundo(), o.getCpfCnpjCedente(), o.getNomeArquivo());
					
					RetornoAprovacao retornoAprovacao = service.aprovarGestor(operacao);
					logger.info("Retorno | {} ", retornoAprovacao.getMensagem());
				
				} catch (RemoteException e) {
					e.printStackTrace();
				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
