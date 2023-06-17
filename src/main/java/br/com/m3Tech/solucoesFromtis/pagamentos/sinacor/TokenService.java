package br.com.m3Tech.solucoesFromtis.pagamentos.sinacor;

import java.time.LocalTime;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.m3Tech.solucoesFromtis.dto.Token;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import lombok.Setter;

@Named

public class TokenService {
	
	private final IConfGlobalService confGlobalService;
	private String codigo;
	
	@Setter
	private LocalTime horaGeracao;
	
	@Inject
	public TokenService(final IConfGlobalService confGlobalService) {
		this.confGlobalService = confGlobalService;
		this.codigo = ValorAleatorioUtil.getValor(20);
		this.horaGeracao = LocalTime.now();
	}
	
	public Token getToken() {
		
		Integer minExpiracaotoken = confGlobalService.getConfGlobal().getMinExpiracaoToken();
		Integer tempo = minExpiracaotoken != null ? minExpiracaotoken : 1;
		
		Integer diferencaDeTempo  = LocalTime.now().toSecondOfDay() - horaGeracao.toSecondOfDay();
		Integer tempoRestante = (tempo * 60) - diferencaDeTempo;
		return new Token(codigo, "Bearer", tempoRestante.toString());
	}
	
	public void gerarToken(LocalTime horaAtual) {
		
		Integer minExpiracaotoken = confGlobalService.getConfGlobal().getMinExpiracaoToken();
		Integer tempo = minExpiracaotoken != null ? minExpiracaotoken : 1;
		
		Integer diferencaDeTempo  = horaAtual.toSecondOfDay() - horaGeracao.toSecondOfDay();
		Integer tempoRestante = (tempo * 60) - diferencaDeTempo;
		
		if(tempoRestante < 1) {
			this.codigo = ValorAleatorioUtil.getValor(20);
			this.horaGeracao = LocalTime.now();
		}
		
	}

}
