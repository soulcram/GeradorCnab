package br.com.m3Tech.geradorCnab.telas.componentes;

import javax.swing.JComboBox;

import br.com.m3Tech.geradorCnab.dto.CedenteDto;

public class ComboBoxCedenteDto{
	
	public static JComboBox<CedenteDto> novo(int posX,int posY, int tamHor, int tamVer) {
		
		try {

		JComboBox<CedenteDto> cb = new JComboBox<CedenteDto>();
		cb.setBounds(posX, posY, tamHor, tamVer);
		
		return cb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
