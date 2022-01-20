package br.com.m3Tech.solucoesFromtis.telas.componentes;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import br.com.m3Tech.solucoesFromtis.dto.FundoDto;

public class ComboBoxFundoDto{
	
	public static JComboBox<FundoDto> novo(int posX,int posY, int tamHor, int tamVer, ActionListener acao) {
		
		try {

		JComboBox<FundoDto> cb = new JComboBox<FundoDto>();
		cb.setBounds(posX, posY, tamHor, tamVer);
		cb.addActionListener(acao);
		
		return cb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
