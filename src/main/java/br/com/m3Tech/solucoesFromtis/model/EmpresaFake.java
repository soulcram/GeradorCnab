package br.com.m3Tech.solucoesFromtis.model;

public class EmpresaFake {
	
	private String nomeEmpresa;
	
	private String cnpj;
	
	public EmpresaFake() {
		
	}

	public EmpresaFake(String nomeEmpresa, String cnpj) {
		super();
		this.nomeEmpresa = nomeEmpresa;
		this.cnpj = cnpj;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "EmpresaFake [nomeEmpresa=" + nomeEmpresa + ", cnpj=" + cnpj + "]";
	}

	
}