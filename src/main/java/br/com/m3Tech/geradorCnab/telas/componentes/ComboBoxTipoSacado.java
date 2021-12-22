package br.com.m3Tech.geradorCnab.telas.componentes;

import javax.swing.JComboBox;

public class ComboBoxTipoSacado{
	
	public static JComboBox<String> novo(int posX,int posY, int tamHor, int tamVer) {
		
		try {

		JComboBox<String> cb = new JComboBox<String>();
		cb.setBounds(posX, posY, tamHor, tamVer);
		cb.addItem("Sacados Cadastrados");
		cb.addItem("Novos Sacados");
		cb.addItem("Ambos");
		
		return cb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
