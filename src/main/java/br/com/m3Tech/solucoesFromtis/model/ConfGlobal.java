package br.com.m3Tech.solucoesFromtis.model;

import java.math.BigDecimal;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConfGlobal extends AbstractPersistable<Integer>{


	private Integer seqArquivo;
	private Integer idBasePadrao;
	private String path;
	private String tema;
	private String nomeApp;
	private String urlCustodia;
	private String contextoCustodia = "";
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
	private Boolean enviarPk7s;
	private Integer minExpiracaoToken;
	private String usuarioSinacor;
	private String senhaSinacor;
	private BigDecimal valorPl;
	
	public ConfGlobal(Integer seqArquivo, String path, String tema, String nomeApp) {
		this.seqArquivo = seqArquivo;
		this.path = path;
		this.tema = tema;
		this.nomeApp = nomeApp;
		this.contextoCustodia = "";
	}


}
