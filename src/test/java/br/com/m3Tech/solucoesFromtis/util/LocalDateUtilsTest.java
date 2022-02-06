package br.com.m3Tech.solucoesFromtis.util;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocalDateUtilsTest {

	@Test
	void pegandoDiaUtil() {
	
		Assertions.assertEquals(LocalDate.parse("2022-01-31"),LocalDateUtils.getDiaUtil(LocalDate.parse("2022-01-29")));
		Assertions.assertEquals(LocalDate.parse("2022-01-31"),LocalDateUtils.getDiaUtil(LocalDate.parse("2022-01-30")));
		
		Assertions.assertNotEquals(LocalDate.parse("2022-01-31"),LocalDateUtils.getDiaUtil(LocalDate.parse("2022-01-28")));
		
	}
	
	@Test
	void converterStringDateTimeEmDate() {
	
		Assertions.assertEquals(LocalDate.parse("2022-02-07"),LocalDateUtils.getLocalDate("2022-02-07 00:00:00.0"));
		Assertions.assertEquals(LocalDate.parse("2022-02-07"),LocalDateUtils.getLocalDate("2022-02-07"));
		
	}

}
