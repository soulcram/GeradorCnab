package br.com.m3Tech.geradorCnab.telas.componentes;

import javax.swing.JComboBox;

import br.com.m3Tech.geradorCnab.dto.SacadoDto;

public class ComboBoxSacadoDto{
	
	public static JComboBox<SacadoDto> novo(int posX,int posY, int tamHor, int tamVer) {
		
		try {

		JComboBox<SacadoDto> cb = new JComboBox<SacadoDto>();
		cb.setBounds(posX, posY, tamHor, tamVer);
		
		return cb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
