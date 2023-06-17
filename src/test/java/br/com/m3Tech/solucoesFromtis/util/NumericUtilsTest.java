package br.com.m3Tech.solucoesFromtis.util;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class NumericUtilsTest {

	@Test
	void testDeveRetornarTrueIfNull() {

		assertTrue(NumericUtils.isNull(null));

	}

	@Test
	void testDeveRetornarFalseIfNotNull() {

		assertFalse(NumericUtils.isNull(BigDecimal.ZERO));

	}
	
	@Test
	void testDeveRetornarNullIfNull() {

		assertNull(NumericUtils.getOnlyNumericsOfString(null));

	}
	
	@Test
	void testDeveRetornarApenasNumerosDaString() {

		assertEquals(123,NumericUtils.getOnlyNumericsOfString("0123"));
		assertEquals(123,NumericUtils.getOnlyNumericsOfString("A01b23C"));
		assertEquals(1230,NumericUtils.getOnlyNumericsOfString("01230"));
		assertEquals(0,NumericUtils.getOnlyNumericsOfString("ABc"));

		

	}

}
