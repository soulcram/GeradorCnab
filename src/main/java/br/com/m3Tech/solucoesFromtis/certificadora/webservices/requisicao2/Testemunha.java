package br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao2;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Testemunha {

	@XmlElement(required=true)
	private String nome;
	
	@XmlElement(required=true)
	private String cpf;
	
	@XmlElement(required=true)
	private String email;
	
}
