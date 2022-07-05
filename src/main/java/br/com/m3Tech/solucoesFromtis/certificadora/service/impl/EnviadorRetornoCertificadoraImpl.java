package br.com.m3Tech.solucoesFromtis.certificadora.service.impl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.certificadora.service.EnviadorRetornoCertificadora;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.GeradorServiceWS;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.RetornoProcessamento;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao.RequisicaoCertificadoDigital;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno.RetornoCertificacaoDigital;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno.RetornoCertificadoDigital;
import lombok.extern.slf4j.Slf4j;

@Service("enviadorRetorno")
@Slf4j
public class EnviadorRetornoCertificadoraImpl implements EnviadorRetornoCertificadora {

	@Override
	public RetornoProcessamento enviarRetornoCertificadora(RequisicaoCertificadoDigital requisicaoCertificadoDigital) {
		log.info("Inicio do Envio Retorno para Operacao: {}", requisicaoCertificadoDigital.getId());

		RetornoCertificadoDigital retornoCertificadoDigital = new RetornoCertificadoDigital();
		BeanUtils.copyProperties(requisicaoCertificadoDigital, retornoCertificadoDigital);
		try {
			requisicaoCertificadoDigital.getCedente().criaCamposFaltando();
			retornoCertificadoDigital.setRetornoProcessamento(RetornoProcessamento.novoSucesso());
			
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(retornoCertificadoDigital.getClass());
				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				marshaller.marshal(retornoCertificadoDigital, System.out);

			} catch (Exception e) {
			}
			
			RetornoCertificacaoDigital retorno = new GeradorServiceWS().criaRequestRetornoCertificadora();
			BindingProvider bp  = (BindingProvider) retorno;
			//bp.getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 3000);
			//bp.getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT, 1000);
			
			RetornoProcessamento retornoProcessamento = retorno.retorno(retornoCertificadoDigital);
			
			log.info("Fim do Envio Retorno.");
			return retornoProcessamento;
		} catch (Exception e) {
			log.error("Erro ao criar o Request do Retorno: {}", e);
			return RetornoProcessamento.erroProcessamento(e.getMessage());
		}
	}
	
}
