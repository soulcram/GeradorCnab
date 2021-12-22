package br.com.m3Tech.geradorCnab.telas.componentes;

import javax.swing.JComboBox;

import br.com.m3Tech.geradorCnab.enuns.LayoutEnum;

public class ComboBoxLayout{
	
	public static JComboBox<LayoutEnum> novo(int posX,int posY, int tamHor, int tamVer) {
		
		try {

		JComboBox<LayoutEnum> cb = new JComboBox<LayoutEnum>();
		cb.setBounds(posX, posY, tamHor, tamVer);
		
		for(LayoutEnum layout : LayoutEnum.values()) {
			cb.addItem(layout);
		}
		
		return cb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
