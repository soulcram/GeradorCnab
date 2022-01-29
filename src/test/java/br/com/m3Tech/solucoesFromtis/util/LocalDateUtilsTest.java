package br.com.m3Tech.solucoesFromtis.util;

import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.m3Tech.solucoesFromtis.App;

@RunWith(SpringRunner.class)
public class LocalDateUtilsTest {
	
	static {
		@SuppressWarnings("unused")
		ApplicationContext contexto = new SpringApplicationBuilder(App.class)
                .web(WebApplicationType.NONE)
                .headless(false)
                .bannerMode(Banner.Mode.OFF)
                .run();
	}
	
	@Test
	void pegandoDiaUtil() {
	
		Assertions.assertEquals(LocalDate.parse("2022-01-31"),LocalDateUtils.getDiaUtil(LocalDate.parse("2022-01-29")));
		Assertions.assertEquals(LocalDate.parse("2022-01-31"),LocalDateUtils.getDiaUtil(LocalDate.parse("2022-01-30")));
		
		Assertions.assertNotEquals(LocalDate.parse("2022-01-31"),LocalDateUtils.getDiaUtil(LocalDate.parse("2022-01-28")));
		
	}

}
