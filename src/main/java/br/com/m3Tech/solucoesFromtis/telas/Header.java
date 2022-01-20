package br.com.m3Tech.solucoesFromtis.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxTela;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Label;

public class Header extends JPanel {

	private static final long serialVersionUID = 1L;

	private Conteudo conteudo;

	private JComboBox<String> cbTelas;

	public Header(Conteudo conteudo) {

		this.conteudo = conteudo;

		this.setBounds(10, 10, ConfigTela.largura, 80);
		this.setLayout(null);

		this.add(new Label("Soluções Fromtis", 500, 10, 500, 40, 35, Color.BLUE));

		this.add(new Label("Selecione uma tela", 10, 10, 200, 20, 14, Color.BLUE));

		cbTelas = ComboBoxTela.novo(10, 40, 350, 20, getActionCbTela());

		this.add(cbTelas);

	}

	private ActionListener getActionCbTela() {
		return new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					String telaSelecionada = cbTelas.getSelectedItem().toString();

					switch (telaSelecionada) {

					case "Gerar Cnab Aquisição":

						conteudo.atualizarConteudo(new GerarCnabAquisicao());

						break;

					case "Gerar Cnab Aquisição Dinâmico":

						conteudo.atualizarConteudo(new GerarCnabAquisicaoDinamico());

						break;

					case "Gerar Cnab Baixa":

						conteudo.atualizarConteudo(new GerarCnabBaixa());

						break;

					case "Gerar Cnab Liquidação Parcial":

						conteudo.atualizarConteudo(new GerarCnabParcial());

						break;

					case "Gerar Cnab Recompra":

						conteudo.atualizarConteudo(new GerarCnabRecompra());

						break;

					case "Gerar Cnab Recompra Parcial":

						conteudo.atualizarConteudo(new GerarCnabRecompraParcial());

						break;
					case "Gerar Cnab Prorrogação":

						conteudo.atualizarConteudo(new GerarCnabProrrogacao());

						break;
					case "Gerar Cnab de um Arquivo Csv":

						conteudo.atualizarConteudo(new GerarCnabCsv());

						break;

					case "Comparar Versões":

						conteudo.atualizarConteudo(new CompararVersoes());

						break;
					case "Cadastrar Base":

						conteudo.atualizarConteudo(new CadastrarBase());

						break;

					default:

						conteudo.clearConteudo();

					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", 0);
				}

			}
		};
	}

}
