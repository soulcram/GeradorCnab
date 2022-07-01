package br.com.m3Tech.solucoesFromtis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConfGlobal extends AbstractPersistable<Integer> {


	private Integer seqArquivo;
	private Integer idBasePadrao;
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
	private Boolean aprovarConsultoriaAutomatico;
	private Boolean aprovarGestorAutomatico;
	private Boolean enviarRetornoAutomatico;
	
	public ConfGlobal(Integer seqArquivo, String path, String tema, String nomeApp) {
		this.seqArquivo = seqArquivo;
		this.path = path;
		this.tema = tema;
		this.nomeApp = nomeApp;
	}

}
