package br.com.m3Tech.solucoesFromtis.telas.componentes;

import javax.swing.JComboBox;

import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;

public class ComboBoxMovimentoDto{
	
	public static JComboBox<MovimentoDto> novo(int posX,int posY, int tamHor, int tamVer) {
		
		try {

		JComboBox<MovimentoDto> cb = new JComboBox<MovimentoDto>();
		cb.setBounds(posX, posY, tamHor, tamVer);
		
		return cb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
