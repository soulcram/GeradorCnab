package br.com.m3Tech.solucoesFromtis.telas.componentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Label extends JLabel{
	
	private static final long serialVersionUID = 1L;

	public Label(String nomeLabel,int posX,int posY, int tamHor, int tamVer, Integer tamLetra, Color cor,boolean enable) {
		
		this.setText(nomeLabel);
		this.setBounds(posX, posY, tamHor, tamVer);
		this.setFont(new Font("Serif", Font.PLAIN, tamLetra));
		this.setForeground(cor);
		this.setEnabled(enable);
	}
	
	public Label(String nomeLabel,int posX,int posY, int tamHor, int tamVer, Integer tamLetra, Color cor) {
		
		this.setText(nomeLabel);
		this.setBounds(posX, posY, tamHor, tamVer);
		this.setFont(new Font("Serif", Font.PLAIN, tamLetra));
		this.setForeground(cor);
		this.setEnabled(true);
	}
	

}
