package br.com.m3Tech.solucoesFromtis.telas.componentes;

import javax.swing.JComboBox;

public class ComboBoxTipoCedente{
	
	public static JComboBox<String> novo(int posX,int posY, int tamHor, int tamVer) {
		
		try {

		JComboBox<String> cb = new JComboBox<String>();
		cb.setBounds(posX, posY, tamHor, tamVer);
		cb.addItem("Cedente Único");
		cb.addItem("Cedente Multiplos");
		
		return cb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
