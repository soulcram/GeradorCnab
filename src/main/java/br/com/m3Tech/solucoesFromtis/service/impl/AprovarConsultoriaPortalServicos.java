package br.com.m3Tech.solucoesFromtis.service.impl;

import java.rmi.RemoteException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fromtis.fidc.portal.servicos.consulta.soap.AprovacaoConsultoriaWSProxy;
import br.com.fromtis.fidc.portal.servicos.consulta.soap.ContaCorrenteParaAprovacao;
import br.com.fromtis.fidc.portal.servicos.consulta.soap.DadosOperacaoParaAprovacao;
import br.com.fromtis.fidc.portal.servicos.consulta.soap.RetornoAprovacao;
import br.com.m3Tech.solucoesFromtis.dto.DadosOperacaoParaAprovacaoDto;
import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;

public class AprovarConsultoriaPortalServicos  {

	private static final Logger logger  = LoggerFactory.getLogger(AprovarConsultoriaPortalServicos.class);
	
	public void executar(ParametrosCadastrosAutomaticos parametros, List<DadosOperacaoParaAprovacaoDto> dados ) {
		
		try {
			
			String endpoint = "/portal-servicos/servicos/soap/aprovacaoConsultoria?wsdl";
			
			logger.info("Criando Serviço | {} ", parametros.getUrl() + endpoint);
			AprovacaoConsultoriaWSProxy service = new AprovacaoConsultoriaWSProxy(parametros.getUrl() + endpoint, parametros.getUsuario(), parametros.getSenha());

			
			dados.forEach(o -> {
				try {
					logger.info("Iniciando Aprovação Consultoria | {}", o);
					
					try {
						Thread.sleep(1500);
						
					} catch (InterruptedException e) {
					}
					ContaCorrenteParaAprovacao contaCorrente = new ContaCorrenteParaAprovacao(o.getContaCorrente().getAgencia(), 
							o.getContaCorrente().getConta(), o.getContaCorrente().getCodigoBanco());
				
					DadosOperacaoParaAprovacao dadosOperacao = new DadosOperacaoParaAprovacao(o.getCnpjFundo(), 
							o.getCpfCnpjCedente(), o.getNomeArquivo(), o.getReembolso(), contaCorrente);
				
					RetornoAprovacao retornoAprovacao = service.aprovarOperacaoConsultoria(dadosOperacao);
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
