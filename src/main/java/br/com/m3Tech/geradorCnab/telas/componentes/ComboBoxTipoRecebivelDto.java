package br.com.m3Tech.geradorCnab.telas.componentes;

import javax.swing.JComboBox;

import br.com.m3Tech.geradorCnab.dto.TipoRecebivelDto;

public class ComboBoxTipoRecebivelDto{
	
	public static JComboBox<TipoRecebivelDto> novo(int posX,int posY, int tamHor, int tamVer) {
		
		try {

		JComboBox<TipoRecebivelDto> cb = new JComboBox<TipoRecebivelDto>();
		cb.setBounds(posX, posY, tamHor, tamVer);
		
		return cb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
