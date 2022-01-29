package br.com.m3Tech.solucoesFromtis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.m3Tech.solucoesFromtis.App;
import br.com.m3Tech.solucoesFromtis.telas.Conteudo;
import br.com.m3Tech.solucoesFromtis.telas.Janela;


@Configuration
@ComponentScan(basePackageClasses = App.class)
public class Config {
	
	@Bean
	Conteudo getConteudo() {
		return new Conteudo();
	}
	
	@Bean(initMethod ="init")
	Janela iniciar() {
		return new Janela();
	}

}
