package br.com.m3Tech.solucoesFromtis.model;

import br.com.m3Tech.noSql.Crud;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfGlobal extends Crud<ConfGlobal> {


	private Integer seqArquivo;
	private String path;
	private String tema;
	private String nomeApp;
	private String urlCustodia;
	private String usuarioCustodia;
	private String senhaCustodia;
	private String urlPortal;
	private String usuarioPortal;
	private String senhaPortal;
	private String urlPortalServicos;
	private String usuarioPortalServicos;
	private String senhaPortalServicos;
	
	public ConfGlobal(Integer seqArquivo, String path, String tema, String nomeApp) {
		this.seqArquivo = seqArquivo;
		this.path = path;
		this.tema = tema;
		this.nomeApp = nomeApp;
	}

}
