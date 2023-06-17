package br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper;

import java.time.LocalDateTime;

import br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao.RequisicaoCertificadoDigital;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequisicaoCertificadoraDigitalWrapper {

	private RequisicaoCertificadoDigital requisicao;
	private StatusRequisicao statusRequisicao;
	private LocalDateTime data;
	
	public static RequisicaoCertificadoraDigitalWrapper requisicaoNaoEnviada(RequisicaoCertificadoDigital requisicaoCertificadoDigital) {
		return new RequisicaoCertificadoraDigitalWrapper(requisicaoCertificadoDigital, StatusRequisicao.naoEnviada(), LocalDateTime.now());
	}
}
