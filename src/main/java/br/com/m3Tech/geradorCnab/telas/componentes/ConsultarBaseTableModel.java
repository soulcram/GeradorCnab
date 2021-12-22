package br.com.m3Tech.geradorCnab.telas.componentes;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

public class ConsultarBaseTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private Object[][] rows = { { "id","url","usuario","senha", new JButton("editar"), new JButton("excluir") }};

	
	private String[] columns = { "Id", "Url", "Usuário", "Senha", "Editar", "Excluir" };

	public String getColumnName(int column) {
		return columns[column];
	}

	public int getRowCount() {
		return rows.length;
	}

	public int getColumnCount() {
		return columns.length;
	}

	public Object getValueAt(int row, int column) {
		return rows[row][column];
	}

	public void addRow(Object[] objects) {
		// TODO Auto-generated method stub
		
	}


}
