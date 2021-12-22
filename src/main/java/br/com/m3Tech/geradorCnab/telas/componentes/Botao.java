package br.com.m3Tech.geradorCnab.telas.componentes;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Botao extends JButton{
	
	private static final long serialVersionUID = 1L;

	public Botao(String nomeBotao,int posX,int posY, int tamHor, int tamVer, ActionListener acao) {
		
		this.setText(nomeBotao);
		this.setBounds(posX, posY, tamHor, tamVer);
		this.addActionListener(acao);
	}
	

}
