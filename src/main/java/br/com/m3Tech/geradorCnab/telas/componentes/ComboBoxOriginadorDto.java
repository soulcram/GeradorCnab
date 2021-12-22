package br.com.m3Tech.geradorCnab.telas.componentes;

import javax.swing.JComboBox;

import br.com.m3Tech.geradorCnab.dto.OriginadorDto;

public class ComboBoxOriginadorDto{
	
	public static JComboBox<OriginadorDto> novo(int posX,int posY, int tamHor, int tamVer) {
		
		try {

		JComboBox<OriginadorDto> cb = new JComboBox<OriginadorDto>();
		cb.setBounds(posX, posY, tamHor, tamVer);
		
		return cb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
