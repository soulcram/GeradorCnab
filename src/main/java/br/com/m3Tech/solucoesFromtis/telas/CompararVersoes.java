package br.com.m3Tech.solucoesFromtis.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Controller;

import com.google.common.collect.Lists;

import br.com.m3Tech.solucoesFromtis.model.Classe;
import br.com.m3Tech.solucoesFromtis.model.ClasseAnalisada;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Botao;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxTipoArquivo;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Label;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Text;
import br.com.m3Tech.solucoesFromtis.util.FileUtil;

@Controller
public class CompararVersoes extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Text nomeDiretorioMercado;
	private Text nomeDiretorioLegado;
	
	Label quantidade;
	
	private JTable tabela;
	
	private JComboBox<String> cbTipoArquivo;
	
	private JTextArea conteudoMercado;
	private JTextArea conteudoLegado;
	
	private File diretorioMercado;
	private File diretorioLegado;
	
	private List<File> filesLegado;
	private List<File> filesMercado;
	
	List<ClasseAnalisada> classesAnalisadas;
	
	public CompararVersoes() {
		
		this.setBounds(1, 1, ConfigTela.largura, ConfigTela.altura);
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		this.add(new Label("Diretório Versão Mercado: ", 10, 10, 200, 20, 14, Color.BLACK));
		nomeDiretorioMercado = new Text(10, 30, 450, 20, true);
		this.add(new Botao("Diretório Mercado", 470, 30, 150, 20, getActionCarregarDiretorioMercado()));
		
		
		this.add(new Label("Diretório Versão Legado: ", 650, 10, 200, 20, 14, Color.BLACK));
		nomeDiretorioLegado = new Text(650, 30, 450, 20, true);
		this.add(new Botao("Diretório Legado", 1120, 30, 150, 20, getActionCarregarDiretorioLegado()));
		
		this.add(new Label("Filtrar por tipo arquivo: ", 10, 65, 150, 20, 14, Color.BLACK));
		cbTipoArquivo = ComboBoxTipoArquivo.novo(170, 65, 290, 20, getActionCbTipoArquivo());
		
		this.add(new Botao("Carregar Arquivos", 10, 310, 150, 20, getActionCarregarArquivos()));
		
		quantidade = new Label("Quantidade de arquivo com diferenças: ", 180, 310, 600, 20, 14, Color.BLACK);
		
		this.add(new Label("Conteudo Versão Mercado: ", 10, 350, 200, 20, 14, Color.BLACK));
		this.add(new Label("Conteudo Versão Legado: ", 670, 350, 200, 20, 14, Color.BLACK));
		
		iniciarTabela();
		iniciarConteudoMercado();
		iniciarConteudoLegado();
		
		this.add(nomeDiretorioMercado);
		this.add(nomeDiretorioLegado);
		this.add(quantidade);
		this.add(cbTipoArquivo);
		
		this.repaint();

	}
	
	private ActionListener getActionCbTipoArquivo() {
		return new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				getActionCarregarArquivos();

			}
		};
	}

	private void iniciarConteudoMercado() {

		conteudoMercado = new JTextArea();

		JScrollPane scroll = new JScrollPane(conteudoMercado);
		scroll.setBounds(10, 380, 670, 350);
		
		this.add(scroll);
		
	}
	
	private void iniciarConteudoLegado() {

		conteudoLegado = new JTextArea();

		JScrollPane scroll = new JScrollPane(conteudoLegado);
		scroll.setBounds(700, 380, 670, 350);
		
		this.add(scroll);
		
	}

	private ActionListener getActionCarregarArquivos() {
		return new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				if (diretorioMercado == null && "".equals(nomeDiretorioMercado.getText())) {
					JOptionPane.showMessageDialog(null, "Diretório da Versão Mercado é obrigatório.","Erro", 0);
					return;
				}
				
				if (diretorioLegado == null && "".equals(nomeDiretorioLegado.getText())) {
					JOptionPane.showMessageDialog(null, "Diretório da Versão Legado é obrigatório.","Erro", 0);
					return;
				}
				
				if (diretorioMercado == null) {
					diretorioMercado = new File(nomeDiretorioMercado.getText());
				}
				
				if (diretorioLegado == null) {
					diretorioLegado = new File(nomeDiretorioLegado.getText());
				}
				
				filesMercado = FileUtil.getFiles(nomeDiretorioMercado.getText(), cbTipoArquivo.getSelectedItem().toString());
				filesLegado = FileUtil.getFiles(nomeDiretorioLegado.getText(),cbTipoArquivo.getSelectedItem().toString());
				
				List<Classe> listaClasseMercado = getListaClasse(filesMercado);
				
				List<Classe> listaClasseLegado = getListaClasse(filesLegado);
				
				classesAnalisadas = Lists.newArrayList();
				
				for (Classe c : listaClasseLegado) {
				
							Classe classeParaComparar = getClasse(c.getNomeClasse(), listaClasseMercado);
				
							ClasseAnalisada classeAnalisada = compararClasse(c,classeParaComparar);
							
							if(classeAnalisada.getConteudoMercado() == null || !classeAnalisada.getConteudoLegado().equals(classeAnalisada.getConteudoMercado())) {
								classesAnalisadas.add(classeAnalisada);
							}
							
						}
				
				quantidade.setText("Quantidade de arquivo com diferenças: " + classesAnalisadas.size());;

				DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
				
				modelo.setNumRows(0);
				
				for(ClasseAnalisada f : classesAnalisadas) {
					modelo.addRow(new Object[] {
							
							f.getNomeClasse()
							
					});
				}
				
			}
		};
	}
	
	private Classe getClasse(String nomeClasse, List<Classe> listaClasseDestino) {

		for (Classe c : listaClasseDestino) {
			if (nomeClasse != null && c != null && c.getNomeClasse() != null && nomeClasse.equalsIgnoreCase(c.getNomeClasse())) {
				return c;
			}
		}
		return null;
	}
	
	private List<Classe> getListaClasse(List<File> arquivos) {
	List<Classe> classes = Lists.newArrayList();

	for (File file : arquivos) {
		classes.add(new Classe(file));
	}

	return classes;
}
	
	private ClasseAnalisada compararClasse(Classe classeLegado, Classe classeMercado) {

		ClasseAnalisada classeAnalisada = new ClasseAnalisada();
		
		classeAnalisada.setNomeClasse(classeLegado.getNomeClasse());
		
		classeAnalisada.setConteudoLegado(classeLegado.getConteudo());
		
		if(classeMercado != null) {
			classeAnalisada.setConteudoMercado(classeMercado.getConteudo());
		}
		
//		for(Metodo metodoSource : classeSource.getMetodos()) {
//			
//			if(metodoSource == null || classeDestino == null || classeDestino.getMetodos() == null || classeDestino.getMetodos().isEmpty()) {
//				continue;
//			}
//			
//			Metodo metodoDestino = getMetodo(metodoSource.getNome(),classeDestino.getMetodos());
//			
//			if(metodoSource != null && metodoDestino != null && metodoSource.getConteudo() != null&& metodoDestino.getConteudo() != null && !metodoSource.getConteudo().equals(metodoDestino.getConteudo())) {
//				
//				MetodoAnalisado metodoAnalisado = new MetodoAnalisado(metodoDestino, metodoSource);
//				
//				classeAnalisada.addMetodoAnalisado(metodoAnalisado);
//			}
//		}
		
		return classeAnalisada;
	}

	@SuppressWarnings("serial")
	private void iniciarTabela() {
try {
			
			DefaultTableModel modelo = new DefaultTableModel(
					new Object[][] {},
					new String[] {"Nome Classe"}
					);
			
			tabela = new JTable(modelo){
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};


			tabela.addMouseListener(getActionEditarRow());
			
			tabela.setVisible(true);
			tabela.repaint();
			
			JScrollPane scroll = new JScrollPane(tabela);
			scroll.setBounds(10, 100, 1150, 200);
			
			this.add(scroll);

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	private MouseListener getActionEditarRow() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() > 1) {
					
					String selecao = tabela.getValueAt(tabela.getSelectedRow(), 0).toString();
					
					ClasseAnalisada classeSelecionada = getClasseSelecionada(selecao);
					
					conteudoMercado.setText(classeSelecionada.getConteudoMercado());
					conteudoLegado.setText(classeSelecionada.getConteudoLegado());
					
				}
			}

		};
	}
	

	private ClasseAnalisada getClasseSelecionada(String selecao) {

		for(ClasseAnalisada c : classesAnalisadas) {
			if(selecao.equals(c.getNomeClasse())) {
				return c;
			}
		}
			
		return null;
	}

	private ActionListener getActionCarregarDiretorioMercado() {
		return new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				int ret = fileChooser.showOpenDialog(CompararVersoes.this);
				if (ret == JFileChooser.APPROVE_OPTION) {
					diretorioMercado = fileChooser.getSelectedFile();
					nomeDiretorioMercado.setText(diretorioMercado.getPath());
				}

			}
		};
	}
	
	private ActionListener getActionCarregarDiretorioLegado() {
		return new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				int ret = fileChooser.showOpenDialog(CompararVersoes.this);
				if (ret == JFileChooser.APPROVE_OPTION) {
					diretorioLegado = fileChooser.getSelectedFile();
					nomeDiretorioLegado.setText(diretorioLegado.getPath());
				}

			}
		};
	}
	

}
