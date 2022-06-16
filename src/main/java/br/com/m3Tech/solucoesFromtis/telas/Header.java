package br.com.m3Tech.solucoesFromtis.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxTela;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Label;

@Component
public class Header extends JPanel {

	private static final long serialVersionUID = 1L;

	
	private final Conteudo conteudo;
	private final GerarCnabAquisicao gerarCnabAquisicao;
	private final GerarCnabAquisicaoDinamico gerarCnabAquisicaoDinamico;
	private final GerarCnabBaixa gerarCnabBaixa;
	private final GerarCnabParcial gerarCnabParcial;
	private final GerarCnabRecompra gerarCnabRecompra;
	private final GerarCnabRecompraParcial gerarCnabRecompraParcial;
	private final GerarCnabProrrogacao gerarCnabProrrogacao;
	private final GerarCnabCsv gerarCnabCsv;
	private final CompararVersoes compararVersoes;
	private final CadastrarBase cadastrarBase;
	private final TestesDiversos testesDiversos;
	private final GerarInsertAquisicaoCustodia3 gerarInsertAquisicaoCustodia3;
	private final SimularImportacaoPortal simularImportacaoPortal;

	private JComboBox<String> cbTelas;
	
	@Autowired
	public Header(final Conteudo conteudo,
				  final GerarCnabAquisicao gerarCnabAquisicao,
				  final GerarCnabAquisicaoDinamico gerarCnabAquisicaoDinamico,
				  final GerarCnabBaixa gerarCnabBaixa,
				  final GerarCnabParcial gerarCnabParcial,
				  final GerarCnabRecompra gerarCnabRecompra,
				  final GerarCnabRecompraParcial gerarCnabRecompraParcial,
				  final GerarCnabProrrogacao gerarCnabProrrogacao,
				  final GerarCnabCsv gerarCnabCsv,
				  final CompararVersoes compararVersoes,
				  final CadastrarBase cadastrarBase,
				  final TestesDiversos testesDiversos,
				  final GerarInsertAquisicaoCustodia3 gerarInsertAquisicaoCustodia3,
				  final SimularImportacaoPortal simularImportacaoPortal) {
		
		this.conteudo= conteudo;
		this.gerarCnabAquisicao = gerarCnabAquisicao;
		this.gerarCnabAquisicaoDinamico = gerarCnabAquisicaoDinamico;
		this.gerarCnabBaixa = gerarCnabBaixa;
		this.gerarCnabParcial = gerarCnabParcial;
		this.gerarCnabRecompra = gerarCnabRecompra;
		this.gerarCnabRecompraParcial = gerarCnabRecompraParcial;
		this.gerarCnabProrrogacao = gerarCnabProrrogacao;
		this.gerarCnabCsv = gerarCnabCsv;
		this.compararVersoes = compararVersoes;
		this.cadastrarBase = cadastrarBase;
		this.testesDiversos = testesDiversos;
		this.gerarInsertAquisicaoCustodia3 = gerarInsertAquisicaoCustodia3;
		this.simularImportacaoPortal = simularImportacaoPortal;

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

						conteudo.atualizarConteudo(gerarCnabAquisicao);

						break;

					case "Gerar Cnab Aquisição Dinâmico":

						conteudo.atualizarConteudo(gerarCnabAquisicaoDinamico);

						break;

					case "Gerar Cnab Baixa":

						conteudo.atualizarConteudo(gerarCnabBaixa);

						break;

					case "Gerar Cnab Liquidação Parcial":

						conteudo.atualizarConteudo(gerarCnabParcial);

						break;

					case "Gerar Cnab Recompra":

						conteudo.atualizarConteudo(gerarCnabRecompra);

						break;

					case "Gerar Cnab Recompra Parcial":

						conteudo.atualizarConteudo(gerarCnabRecompraParcial);

						break;
					case "Gerar Cnab Prorrogação":

						conteudo.atualizarConteudo(gerarCnabProrrogacao);

						break;
					case "Gerar Cnab de um Arquivo Csv":

						conteudo.atualizarConteudo(gerarCnabCsv);

						break;
						
					case "Simular Aquisições Via Portal":

						conteudo.atualizarConteudo(simularImportacaoPortal);

						break;

//					case "Comparar Versões":
//
//						conteudo.atualizarConteudo(compararVersoes);
//
//						break;
					case "Cadastrar Base":

						conteudo.atualizarConteudo(cadastrarBase);

						break;
						
					case "Testes diversos":
						conteudo.atualizarConteudo(testesDiversos);
						
						break;
						
					case "Gerar Insert Aquisição Custodia 3":
						conteudo.atualizarConteudo(gerarInsertAquisicaoCustodia3);
						
						break;
						
						

					default:

						conteudo.clearConteudo();

					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		};
	}

}
