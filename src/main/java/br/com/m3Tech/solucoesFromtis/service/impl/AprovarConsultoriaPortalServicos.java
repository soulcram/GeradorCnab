package br.com.m3Tech.solucoesFromtis.service.impl;

import java.rmi.RemoteException;

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
	
	public String executar(ParametrosCadastrosAutomaticos parametros, DadosOperacaoParaAprovacaoDto dadosOperacaoDto ) {
		
		try {
			
			logger.info("Iniciando Aprovação Consultoria | {}", dadosOperacaoDto);
		
			String endpoint = "/portal-servicos/servicos/soap/aprovacaoConsultoria?wsdl";
			
			logger.info("Criando Serviço | {} ", parametros.getUrl() + endpoint);
			AprovacaoConsultoriaWSProxy service = new AprovacaoConsultoriaWSProxy(parametros.getUrl() + endpoint, parametros.getUsuario(), parametros.getSenha());

			ContaCorrenteParaAprovacao contaCorrente = new ContaCorrenteParaAprovacao(dadosOperacaoDto.getContaCorrente().getAgencia(), 
					dadosOperacaoDto.getContaCorrente().getConta(), dadosOperacaoDto.getContaCorrente().getCodigoBanco());
		
			DadosOperacaoParaAprovacao dadosOperacao = new DadosOperacaoParaAprovacao(dadosOperacaoDto.getCnpjFundo(), 
					dadosOperacaoDto.getCpfCnpjCedente(), dadosOperacaoDto.getNomeArquivo(), dadosOperacaoDto.getReembolso(), contaCorrente);
		
			RetornoAprovacao retornoAprovacao = service.aprovarOperacaoConsultoria(dadosOperacao);
			logger.info("Retorno | {} ", retornoAprovacao.getMensagem());
						
			return retornoAprovacao.getMensagem();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return "";

	}

}
