package br.com.m3Tech.solucoesFromtis.telas;

import javax.swing.JFrame;

public class Janela extends JFrame{

	private static final long serialVersionUID = 1L;
	
	Conteudo conteudo;

	public Janela () {
		
		
		this.setTitle("Soluções Fromtis");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(ConfigTela.largura, ConfigTela.altura);
		this.setLayout(null);
		
		conteudo = new Conteudo();
		
		this.add(new Header(conteudo));
//		this.add(new Left(conteudo));
		this.add(conteudo);
		
		
		this.repaint();
		this.setVisible(true);
		
	}

}
