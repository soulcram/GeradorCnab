package br.com.m3Tech.solucoesFromtis.model;

public class PessoaFake {
	
	private String nomeCompleto;
	
	private String cpf;
	
	public PessoaFake() {
		
	}

	public PessoaFake(String nomeCompleto, String cpf) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "PessoaFake [nomeCompleto=" + nomeCompleto + ", cpf=" + cpf + "]";
	}

	
	
	

}
