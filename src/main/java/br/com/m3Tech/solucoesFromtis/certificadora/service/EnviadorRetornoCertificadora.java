package br.com.m3Tech.solucoesFromtis.certificadora.service;

import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.RetornoProcessamento;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao.RequisicaoCertificadoDigital;

public interface EnviadorRetornoCertificadora {

	RetornoProcessamento enviarRetornoCertificadora(RequisicaoCertificadoDigital requisicaoCertificadoDigital);
}
