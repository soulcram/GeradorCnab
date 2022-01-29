package br.com.m3Tech.solucoesFromtis.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LocalDateUtils {
	
	private LocalDateUtils() {}
	
	public static LocalDate getDiaUtil(LocalDate valor) {
		
		while(isFimDeSemana(valor)) {
			valor = valor.plusDays(1L);
		}
		
		return valor;
		
	}
	
	public static boolean isFimDeSemana(LocalDate valor) {
		DayOfWeek d = valor.getDayOfWeek();
        return d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY;
		
	}

	

}
