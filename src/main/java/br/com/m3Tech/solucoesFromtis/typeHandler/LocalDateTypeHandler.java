package br.com.m3Tech.solucoesFromtis.typeHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.beanio.types.ConfigurableTypeHandler;
import org.beanio.types.TypeConversionException;
import org.beanio.types.TypeHandler;

import br.com.m3Tech.solucoesFromtis.util.LocalDateUtils;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;


/**
 * TypeHandler do beanio para mapeamento de datas para o tipo {@link FromtisDate}.
 */
public class LocalDateTypeHandler implements ConfigurableTypeHandler {

	private String pattern;

	public LocalDateTypeHandler() {
	}
	
	public LocalDateTypeHandler(String pattern) {
		this.pattern = pattern;
	}
	
    @Override
    public Object parse(final String text) throws TypeConversionException {
    	if (text == null) {
    		return null;
    	}
    	
    	if(StringUtils.emptyOrNull(text.replaceAll("0", "").trim())){
    		return null;
    	}
    	
        try {
        	return LocalDateUtils.parseDate(text);
        	
        } catch (Exception e) {
			throw new TypeConversionException("A data " + text +" não pode ser formatada.");
        }
    }

    @Override
    public String format(final Object value) {
    	if(value == null){
    		return null;
    	}
    	LocalDate date = (LocalDate) value;
    	return pattern != null ? date.format(DateTimeFormatter.ofPattern(pattern)).toString() : date.toString();
    }

    @Override
    public Class<?> getType() {
        return LocalDate.class;
    }

    @Override
    public TypeHandler newInstance(final Properties properties) throws IllegalArgumentException {
        return new LocalDateTypeHandler(properties.getProperty(FORMAT_SETTING));
    }
}
