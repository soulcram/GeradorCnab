package br.com.m3Tech.geradorCnab.telas;

import java.awt.Color;

import javax.swing.JPanel;

public class Conteudo extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public Conteudo () {

		this.setBounds(220, 60, 1000, 690);
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
	}
	
	public void atualizarConteudo(JPanel painel) {
		
		this.removeAll();
		this.add(painel);
		this.repaint();
		
	}

}
