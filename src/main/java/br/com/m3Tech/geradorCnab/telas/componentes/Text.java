package br.com.m3Tech.geradorCnab.telas.componentes;

import java.awt.Font;

import javax.swing.JTextField;

public class Text extends JTextField{
	
	private static final long serialVersionUID = 1L;

	public Text(int posX,int posY, int tamHor, int tamVer,boolean enable) {

		this.setBounds(posX, posY, tamHor, tamVer);
		this.setFont(new Font("Serif", Font.PLAIN, 13));
		this.setEnabled(enable);
	}
	

}
