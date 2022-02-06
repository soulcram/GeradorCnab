package br.com.m3Tech.solucoesFromtis.telas.componentes;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import br.com.m3Tech.solucoesFromtis.model.Base;

public class ComboBoxBase{
	
	public static JComboBox<Base> novo(int posX,int posY, int tamHor, int tamVer, ActionListener acao) {
		
		try {
		JComboBox<Base> cb = new JComboBox<Base>();
		cb.setBounds(posX, posY, tamHor, tamVer);
		
		cb.addItem(new Base("Selecione", "", "", false));
		
		for(Base b : new Base().findAll()) {
			cb.addItem(b);
		}
		
		cb.addActionListener(acao);
		
		return cb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
