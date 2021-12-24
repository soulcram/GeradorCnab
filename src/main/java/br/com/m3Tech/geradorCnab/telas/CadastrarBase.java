package br.com.m3Tech.geradorCnab.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.m3Tech.geradorCnab.model.Base;
import br.com.m3Tech.geradorCnab.telas.componentes.Botao;
import br.com.m3Tech.geradorCnab.telas.componentes.Label;
import br.com.m3Tech.geradorCnab.telas.componentes.Text;

public class CadastrarBase extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Text url;
	private Text usuario;
	private Text senha;
	private Botao bCadastrar;
	private Botao bAtualizar;
	private Botao bExcluir;
	private JTable tabela;
	
	private Integer idAtual;

	public CadastrarBase() {
		
		
		this.setBounds(1, 1, 1000, 690);
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		this.add(new Label("Cadastrar Base", 10, 10, 500, 20, 14, Color.BLACK));
		
		this.add(new Label("Url", 10, 50, 100, 20, 14, Color.BLACK));
		this.add(new Label("Usuario", 410, 50, 100, 20, 14, Color.BLACK));
		this.add(new Label("Senha", 610, 50, 100, 20, 14, Color.BLACK));
		
		url = new Text(10, 80, 390, 20,true);
		usuario = new Text(410, 80, 190, 20,true);
		senha = new Text(610, 80, 190, 20,true);
		
		this.add(url);
		this.add(usuario);
		this.add(senha);
		
		bCadastrar = new Botao("Cadastrar", 810, 80, 100, 20, getActionCadastrar());
		bCadastrar.setVisible(true);
		
		bAtualizar = new Botao("Atualizar", 810, 80, 100, 20, getActionAtualizar());
		bAtualizar.setVisible(false);
		
		bExcluir = new Botao("Excluir", 810, 110, 100, 20, getActionExcluir());
		bExcluir.setVisible(false);
		
		this.add(bAtualizar);
		this.add(bCadastrar);
		this.add(bExcluir);
		
		iniciarTabela();
		
		this.repaint();
		
		
	}

	@SuppressWarnings("serial")
	private void iniciarTabela() {

		try {
			
			DefaultTableModel modelo = new DefaultTableModel(
					new Object[][] {},
					new String[] {"ID","Url","Usuário","Senha"}
					);
			
			tabela = new JTable(modelo){
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};


			popularTabela();

			tabela.addMouseListener(getActionEditarRow());
			
			tabela.setVisible(true);
			tabela.repaint();
			
			JScrollPane scroll = new JScrollPane(tabela);
			scroll.setBounds(10, 150, 900, 500);
			
			this.add(scroll);
			this.repaint();

		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	private void popularTabela() {
		
		try {
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		
		modelo.setNumRows(0);
		
		List<Base> bases;
		
			bases = new Base().findAll();
		
		
		
		
		for(Base b : bases ) {
			
			modelo.addRow(new Object[] {
					
					b.getId(),
					b.getUrl(),
					b.getUsuario(),
					b.getSenha()
					
			});
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private MouseListener getActionEditarRow() {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() > 1) {
					idAtual = Integer.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
					url.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
					usuario.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
					senha.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
					
					bCadastrar.setVisible(false);
					bAtualizar.setVisible(true);
					bExcluir.setVisible(true);
				}
			}
		};
	}

	private ActionListener getActionExcluir() {
		
		return new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				try {
					
				Base base = new Base(url.getText(), usuario.getText(), senha.getText());
				base.setId(idAtual);
				
				
				base.delete();
				
				idAtual = null;
				url.setText("");
				usuario.setText("");
				senha.setText("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				popularTabela();
				bCadastrar.setVisible(true);
				bAtualizar.setVisible(false);
				bExcluir.setVisible(false);
			}
		};
		
	}

	private ActionListener getActionAtualizar() {
		
		return new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				try {
					
				Base base = new Base(url.getText(), usuario.getText(), senha.getText());
				base.setId(idAtual);
				
				
				base.save();
				
				idAtual = null;
				url.setText("");
				usuario.setText("");
				senha.setText("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				popularTabela();
				bCadastrar.setVisible(true);
				bAtualizar.setVisible(false);
				bExcluir.setVisible(false);
			}
		};
	}

	private ActionListener getActionCadastrar() {
		
		return new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				try {
					
				Base base = new Base(url.getText(), usuario.getText(), senha.getText());
				
				
					base.save();
										
					url.setText("");
					usuario.setText("");
					senha.setText("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				popularTabela();
				bCadastrar.setVisible(true);
				bAtualizar.setVisible(false);
				bExcluir.setVisible(false);
				
			}
		};
	}

}
