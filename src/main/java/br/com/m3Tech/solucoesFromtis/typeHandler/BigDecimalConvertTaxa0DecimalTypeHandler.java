package br.com.m3Tech.solucoesFromtis.typeHandler;

import java.math.BigDecimal;
import java.util.Properties;

import org.beanio.types.BigDecimalTypeHandler;
import org.beanio.types.ConfigurableTypeHandler;
import org.beanio.types.TypeConversionException;
import org.beanio.types.TypeHandler;

import br.com.m3Tech.solucoesFromtis.util.StringUtils;

public class BigDecimalConvertTaxa0DecimalTypeHandler implements ConfigurableTypeHandler{
	
	private final BigDecimalTypeHandler handler;
	private boolean removeCaracteresEspecial = true;
	
	public BigDecimalConvertTaxa0DecimalTypeHandler(){
		this.handler = new BigDecimalTypeHandler();
	}

	public BigDecimalConvertTaxa0DecimalTypeHandler(final BigDecimalTypeHandler handler) {
		this.handler = handler;
	}
	
	public BigDecimalConvertTaxa0DecimalTypeHandler(final boolean removeCaracteresEspecial){
		this.handler = new BigDecimalTypeHandler();
		this.removeCaracteresEspecial = removeCaracteresEspecial;
	}

	@Override
	public Object parse(String text) throws TypeConversionException {
		if (text == null || text.trim().isEmpty()) {
            return null;
        }

        BigDecimal value = null;
        try{
        	value = (BigDecimal) handler.parse(text.replaceAll(",", "."));
        } catch (TypeConversionException e){
        	throw new TypeConversionException("O conteudo do campo deve ser numerico."); 
        }
        return value;
	}

	@Override
	public String format(Object value) {
		if(value != null) {
			
			int scale = 0;
						
			BigDecimal newvalue = new BigDecimal(value.toString());
		
			newvalue = newvalue.setScale(scale, BigDecimal.ROUND_UNNECESSARY);
		
			return removeCaracteresEspecial ? StringUtils.removerNaoNumeros(newvalue.toString()) : handler.format(value);
		}
		
		return null;
	}

	@Override
	public Class<?> getType() {
		return handler.getType();
	}

	@Override
	public TypeHandler newInstance(Properties properties)
			throws IllegalArgumentException {
		return new BigDecimalConvertTaxa0DecimalTypeHandler((BigDecimalTypeHandler) handler.newInstance(properties));
	}

}
