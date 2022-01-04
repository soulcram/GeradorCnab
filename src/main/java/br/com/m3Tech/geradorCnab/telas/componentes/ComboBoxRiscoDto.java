package br.com.m3Tech.geradorCnab.telas.componentes;

import javax.swing.JComboBox;

import br.com.m3Tech.geradorCnab.dto.RiscoDto;

public class ComboBoxRiscoDto{
	
	public static JComboBox<RiscoDto> novo(int posX,int posY, int tamHor, int tamVer) {
		
		try {

		JComboBox<RiscoDto> cb = new JComboBox<RiscoDto>();
		cb.setBounds(posX, posY, tamHor, tamVer);
		
		return cb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
