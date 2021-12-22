package br.com.m3Tech.geradorCnab.telas;

import java.awt.Color;

import javax.swing.JPanel;

import br.com.m3Tech.geradorCnab.telas.componentes.Label;

public class Header extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public Header () {

		this.setBounds(10, 10, 1200, 50);
		this.setLayout(null);
		
		this.add(new Label("Gerador Cnab", 500, 10, 500, 40, 35, Color.BLUE));
		
	}

}
