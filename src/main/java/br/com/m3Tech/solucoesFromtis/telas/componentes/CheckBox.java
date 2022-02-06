package br.com.m3Tech.solucoesFromtis.telas.componentes;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

public class CheckBox extends JCheckBox{
	
	private static final long serialVersionUID = 1L;

	public CheckBox(String nomeLabel,int posX,int posY, int tamHor, int tamVer, Integer tamLetra, ActionListener acao) {
		
		this.setText(nomeLabel);
		this.setBounds(posX, posY, tamHor, tamVer);
		this.setFont(new Font("Serif", Font.PLAIN, tamLetra));
		
		this.addActionListener(acao);
		
	}
	

}
