package br.com.m3Tech.solucoesFromtis.telas.componentes;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class ComboBoxTipoArquivo{
	
	public static JComboBox<String> novo(int posX,int posY, int tamHor, int tamVer, ActionListener acao) {
		
		try {
		JComboBox<String> cb = new JComboBox<String>();
		cb.setBounds(posX, posY, tamHor, tamVer);
		
		cb.addItem("Todos");
		cb.addItem(".java");
		cb.addItem(".sql");
		cb.addItem(".xhtml");
		cb.addItem(".html");
		cb.addItem(".xml");
		cb.addItem(".jrxml");
		
		
		cb.addActionListener(acao);
		
		return cb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
