package br.com.m3Tech.solucoesFromtis.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Controller;

import br.com.m3Tech.solucoesFromtis.dao.Conexao;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Botao;
import br.com.m3Tech.solucoesFromtis.telas.componentes.CheckBox;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Label;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Text;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;

@Controller
public class CadastrarBase extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Text url;
	private Text usuario;
	private Text senha;
	
	private Botao bTestarConexao;
	private Botao bCadastrar;
	private Botao bAtualizar;
	private Botao bExcluir;
	
	private CheckBox versaoMercado;
	
	private JTable tabela;
	
	private Integer idAtual;

	public CadastrarBase() {
		
		
		this.setBounds(1, 1, ConfigTela.largura, 690);
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		this.add(new Label("Cadastrar Base", 10, 10, 500, 20, 14, Color.BLACK));
		
		this.add(new Label("Url", 10, 50, 100, 20, 14, Color.BLACK));
		this.add(new Label("Usuario", 410, 50, 100, 20, 14, Color.BLACK));
		this.add(new Label("Senha", 610, 50, 100, 20, 14, Color.BLACK));
		
		url = new Text(10, 80, 350, 20,true);
		usuario = new Text(370, 80, 190, 20,true);
		senha = new Text(570, 80, 190, 20,true);
		versaoMercado = new CheckBox("Versão Mercado", 770, 80, 130, 20, 14,null);
		
		this.add(url);
		this.add(usuario);
		this.add(senha);
		this.add(versaoMercado);
		
		bTestarConexao = new Botao("Testar Conexão", 410, 110, 150, 20, getActionTestarConexao());
		bTestarConexao.setVisible(true);
		
		bCadastrar = new Botao("Cadastrar", 910, 80, 100, 20, getActionCadastrar());
		bCadastrar.setVisible(true);
		
		bAtualizar = new Botao("Atualizar", 910, 80, 100, 20, getActionAtualizar());
		bAtualizar.setVisible(false);
		
		bExcluir = new Botao("Excluir", 910, 110, 100, 20, getActionExcluir());
		bExcluir.setVisible(false);
		
		this.add(bAtualizar);
		this.add(bCadastrar);
		this.add(bExcluir);
		this.add(bTestarConexao);
		
		
		iniciarTabela();
		
		this.repaint();
		
		
	}

	@SuppressWarnings("serial")
	private void iniciarTabela() {

		try {
			
			DefaultTableModel modelo = new DefaultTableModel(
					new Object[][] {},
					new String[] {"ID","Url","Usuário","Senha", "Versão Mercado"}
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
			scroll.setBounds(10, 150, 1000, 500);
			
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
					b.getSenha(),
					b.getVersaoMercado() == null || ! b.getVersaoMercado() ? "Não" : "Sim"
					
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
					versaoMercado.setSelected("Sim".equals(tabela.getValueAt(tabela.getSelectedRow(), 4).toString()));
					
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
					
				Base base = new Base(url.getText(), usuario.getText(), senha.getText(), versaoMercado.isSelected());
				base.setId(idAtual);
				
				
				base.delete();
				
				idAtual = null;
				url.setText("");
				usuario.setText("");
				senha.setText("");
				versaoMercado.setSelected(false);
				
				} catch (Exception e1) {
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
					
					if(!validarBase()) {
						return;
					}
					
					Base base = new Base(url.getText(), usuario.getText(), senha.getText(), versaoMercado.isSelected());
					base.setId(idAtual);
					
					
					base.save();
					
					idAtual = null;
					url.setText("");
					usuario.setText("");
					senha.setText("");
					versaoMercado.setSelected(false);
					
				} catch (Exception e1) {
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
					if(!validarBase()) {
						return;
					}
					
					Base base = new Base(url.getText(), usuario.getText(), senha.getText(), versaoMercado.isSelected());
				
				
					base.save();
										
					url.setText("");
					usuario.setText("");
					senha.setText("");
					versaoMercado.setSelected(false);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				popularTabela();
				bCadastrar.setVisible(true);
				bAtualizar.setVisible(false);
				bExcluir.setVisible(false);
				
			}
		};
	}
	
	private ActionListener getActionTestarConexao() {
		
		return new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if(!validarBase()) {
						return;
					}
						
					Base base = new Base(url.getText(), usuario.getText(), senha.getText(), versaoMercado.isSelected());
				
					Connection con = Conexao.getConnection(base);
				
					if(con == null) {
						JOptionPane.showMessageDialog(null, "Erro ao conectar com a base informada","Erro", 0);
					}else {
						JOptionPane.showMessageDialog(null, "Conexão com a base de dados bem sucedida.","Sucesso", 1);
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao conectar com a base informada: " + e1.getMessage(),"Erro", 0);
					e1.printStackTrace();
				}
				
			}

		};
	}
	

	private boolean validarBase() {
		
		if(StringUtils.EmptyOrNull(url.getText())) {
			JOptionPane.showMessageDialog(null, "Url é obrigatório","Erro", 0);
			return false;
		}
		
		if(StringUtils.EmptyOrNull(usuario.getText())) {
			JOptionPane.showMessageDialog(null, "Usuário é obrigatório","Erro", 0);
			return false;
		}
		
		if(StringUtils.EmptyOrNull(senha.getText())) {
			JOptionPane.showMessageDialog(null, "Senha é obrigatório","Erro", 0);
			return false;
		}

		return true;
		
	}
	
	
}
