package br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusRequisicao {
	
	private String mensagem;
	private StatusEnum status;
	
	public boolean isTemErro() {
		if(StatusEnum.ERRO.equals(status)) {
			return true;
		}
		return false;
	}
	
	
	public static StatusRequisicao naoEnviada() {
		return new StatusRequisicao(null, StatusEnum.NAO_ENVIADO);
	}
	
	public static StatusRequisicao comErro(final String msgErro) {
		return new StatusRequisicao(msgErro, StatusEnum.ERRO);
	}
	
	public static StatusRequisicao comSucesso() {
		return new StatusRequisicao(null, StatusEnum.SUCESSO);
	}
}
