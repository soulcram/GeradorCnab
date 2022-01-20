package br.com.m3Tech.solucoesFromtis.telas.componentes;

import javax.swing.JComboBox;

import br.com.m3Tech.solucoesFromtis.dto.IndexadorDto;

public class ComboBoxIndexadorDto{
	
	public static JComboBox<IndexadorDto> novo(int posX,int posY, int tamHor, int tamVer) {
		
		try {

		JComboBox<IndexadorDto> cb = new JComboBox<IndexadorDto>();
		cb.setBounds(posX, posY, tamHor, tamVer);
		
		cb.addItem(new IndexadorDto(null, null, "Selecione"));
		
		return cb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
