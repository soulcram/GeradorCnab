package br.com.m3Tech.solucoesFromtis.telas.componentes;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import br.com.m3Tech.solucoesFromtis.model.Base;

public class ComboBoxTela{
	
	public static JComboBox<String> novo(int posX,int posY, int tamHor, int tamVer, ActionListener acao) {
		
		try {
		JComboBox<String> cb = new JComboBox<String>();
		cb.setBounds(posX, posY, tamHor, tamVer);
		
		cb.addItem("Selecione...");
		cb.addItem("Gerar Cnab Aquisição");
		cb.addItem("Gerar Cnab Aquisição Dinâmico");
		cb.addItem("Gerar Cnab Baixa");
		cb.addItem("Gerar Cnab Liquidação Parcial");
		cb.addItem("Gerar Cnab Recompra");
		cb.addItem("Gerar Cnab Recompra Parcial");
		cb.addItem("Gerar Cnab Prorrogação");
		cb.addItem("Gerar Cnab de um Arquivo Csv");
		cb.addItem("Comparar Versões");
		cb.addItem("Cadastrar Base");
		
		
		cb.addActionListener(acao);
		
		return cb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
