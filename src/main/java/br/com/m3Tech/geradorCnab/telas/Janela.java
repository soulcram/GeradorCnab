package br.com.m3Tech.geradorCnab.telas;

import javax.swing.JFrame;

public class Janela extends JFrame{

	private static final long serialVersionUID = 1L;
	
	Conteudo conteudo;

	public Janela () {
		
		this.setTitle("Gerador Cnab");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1300,800);
		this.setLayout(null);
		
		conteudo = new Conteudo();
		
		this.add(new Header());
		this.add(new Left(conteudo));
		this.add(conteudo);
		
		
		this.repaint();
		this.setVisible(true);
		
	}

}
