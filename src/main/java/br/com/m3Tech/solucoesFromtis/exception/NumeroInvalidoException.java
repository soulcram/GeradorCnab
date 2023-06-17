package br.com.m3Tech.solucoesFromtis.exception;

public class NumeroInvalidoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NumeroInvalidoException(String msg){
        super(msg);
    }
	
	public NumeroInvalidoException(String msg, Throwable cause){
        super(msg, cause);
    }

}
