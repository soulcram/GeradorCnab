package br.com.m3Tech.solucoesFromtis.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import br.com.m3Tech.solucoesFromtis.telas.componentes.Botao;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Label;

public class Left extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private Conteudo conteudo;

	public Left (Conteudo conteudo) {

		this.conteudo = conteudo;
		
		this.setBounds(10, 60, 200, 690);
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		this.add(new Botao("Aquisição", 10, 10, 180, 30, getActionGerarCnabAquisicao()));
		this.add(new Botao("Aquisição Dinâmico", 10, 50, 180, 30, getActionGerarCnabAquisicaoDinamico()));
		this.add(new Botao("Baixa", 10, 90, 180, 30, getActionGerarCnabBaixa()));
		this.add(new Botao("Parcial", 10, 130, 180, 30, getActionGerarCnabParcial()));
		this.add(new Botao("Recompra", 10, 170, 180, 30, getActionGerarCnabRecompra()));
		this.add(new Botao("Recompra Parcial", 10, 210, 180, 30, getActionGerarCnabRecompraParcial()));
		this.add(new Botao("Prorrogação", 10, 250, 180, 30, getActionGerarCnabProrrogacao()));
		this.add(new Botao("Csv", 10, 290, 180, 30, getActionGerarCnabCsv()));
		this.add(new Botao("Cadastrar Base", 10, 330, 180, 30, getActionCadastrarBase()));
		this.add(new Label("Desenvolvido por m3Tech", 10, 670, 180, 20, 10, Color.RED));
		
	}

	private ActionListener getActionGerarCnabAquisicao() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				conteudo.atualizarConteudo(new GerarCnabAquisicao());
				
			}
		};
	}
	
	private ActionListener getActionGerarCnabAquisicaoDinamico() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				conteudo.atualizarConteudo(new GerarCnabAquisicaoDinamico());
				
			}
		};
	}
	
	private ActionListener getActionGerarCnabBaixa() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				conteudo.atualizarConteudo(new GerarCnabBaixa());
				
			}
		};
	}
	
	private ActionListener getActionGerarCnabParcial() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				conteudo.atualizarConteudo(new GerarCnabParcial());
				
			}
		};
	}
	
	private ActionListener getActionGerarCnabRecompra() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				conteudo.atualizarConteudo(new GerarCnabRecompra());
				
			}
		};
	}
	
	private ActionListener getActionGerarCnabRecompraParcial() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				conteudo.atualizarConteudo(new GerarCnabRecompraParcial());
				
			}
		};
	}
	
	private ActionListener getActionGerarCnabProrrogacao() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				conteudo.atualizarConteudo(new GerarCnabProrrogacao());
				
			}
		};
	}
	
	private ActionListener getActionGerarCnabCsv() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				conteudo.atualizarConteudo(new GerarCnabCsv());
				
			}
		};
	}
	
	private ActionListener getActionCadastrarBase() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				conteudo.atualizarConteudo(new CadastrarBase());
				
			}
		};
	}

}
