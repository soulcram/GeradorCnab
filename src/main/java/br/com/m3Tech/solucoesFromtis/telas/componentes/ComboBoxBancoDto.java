package br.com.m3Tech.solucoesFromtis.telas.componentes;

import javax.swing.JComboBox;

import br.com.m3Tech.solucoesFromtis.dto.BancoDto;

public class ComboBoxBancoDto{
	
	public static JComboBox<BancoDto> novo(int posX,int posY, int tamHor, int tamVer) {
		
		try {

		JComboBox<BancoDto> cb = new JComboBox<BancoDto>();
		cb.setBounds(posX, posY, tamHor, tamVer);
		
		return cb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
