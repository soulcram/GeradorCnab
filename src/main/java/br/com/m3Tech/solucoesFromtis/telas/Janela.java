package br.com.m3Tech.solucoesFromtis.telas;

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Janela extends JFrame{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	Conteudo conteudo;
	
	@Autowired
	Header header;

	public Janela () {

	}
	
	public void init() {
		this.setTitle("Soluções Fromtis");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(ConfigTela.largura, ConfigTela.altura);
		this.setLayout(null);
		
		
		this.add(header);
		this.add(conteudo);
		
		
		this.repaint();
		this.setVisible(true);
	}

}
