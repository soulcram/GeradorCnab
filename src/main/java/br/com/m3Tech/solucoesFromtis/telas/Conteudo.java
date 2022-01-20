package br.com.m3Tech.solucoesFromtis.telas;

import java.awt.Color;

import javax.swing.JPanel;

public class Conteudo extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public Conteudo () {

		this.setBounds(10, 80, ConfigTela.largura, ConfigTela.altura);
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
	}
	
	public void atualizarConteudo(JPanel painel) {
		
		this.removeAll();
		this.add(painel);
		this.repaint();
		
	}
	
	public void clearConteudo() {
		
		this.removeAll();
		this.repaint();
		
	}

}
