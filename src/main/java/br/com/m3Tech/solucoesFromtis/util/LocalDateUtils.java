package br.com.m3Tech.solucoesFromtis.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateUtils {
	
	private LocalDateUtils() {}
	
	
	public static LocalDate parseDate(String date) {
		
	    LocalDate newDate = null;
	    
	    newDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(getPattern(date)));

	    return newDate;
	}

	private static String getPattern(String date) {
		
		if(date.contains("/")) {
			if(date.length() == 10) {
				return "dd/MM/yyyy";
			}
			if(date.length() == 8) {
				return "dd/MM/yy";
			}
		}
		
		if(date.contains("-")) {
			String[] split = date.split("-");
			
			if(split.length == 3) {
				if(split[0].length() == 4) {
					return "yyyy-MM-dd";
				}
				if(split[2].length() == 4) {
					return "dd-MM-yyyy";
				}
				if(isDay(split[0]) && isMonth(split[1]) && isYear(split[2])) {
					return "dd-MM-yy";
				}
				if(isDay(split[2]) && isMonth(split[1]) && isYear(split[0])) {
					return "yy-MM-dd";
				}
			}
			return null;
		}
		
		if (date.length() == 8) {

			if (isDay(date.substring(0,2)) && isMonth(date.substring(2,4)) && isYear(date.substring(4,8))) {
				return "ddMMyyyy";
			}
			if (isDay(date.substring(6,8)) && isMonth(date.substring(4,6)) && isYear(date.substring(0,4))) {
				return "yyyyMMdd";
			}
		}
		
		if (date.length() == 6) {

			return "ddMMyy";
			
		}
		
		
		return null;
	}

	private static boolean isYear(String year) {
		Integer ano = Integer.parseInt(year);
		
		Integer anoMax = LocalDate.now().plusYears(200).getYear();
		Integer anoMin = LocalDate.now().minusYears(200).getYear();
		
		if(year.length() == 4) {
			return ano > anoMin && ano < anoMax;
		}else {
			return ano > 0 && ano < 100;
		}
	}

	private static boolean isMonth(String month) {
		Integer mes = Integer.parseInt(month);
		if(month.length() == 2) {
			return mes > 0 && mes < 13;
		}else {
			return false;
		}
	}

	private static boolean isDay(String day) {
		Integer dia = Integer.parseInt(day);
		if(day.length() == 2) {
			return dia > 0 && dia < 32;
		}else {
			return false;
		}
	}
	
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

	public static LocalDate getLocalDate(String valor) {

		String[] split = valor.split(" ");
		
		if(split.length > 1) {
			return LocalDate.parse(split[0]);
		}
		
		return LocalDate.parse(valor);
	}

	

}
