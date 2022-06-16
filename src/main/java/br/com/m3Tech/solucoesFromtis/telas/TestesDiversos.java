package br.com.m3Tech.solucoesFromtis.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Controller;

import br.com.m3Tech.solucoesFromtis.dao.Conexao;
import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.ResultadoTesteDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.service.IBancoService;
import br.com.m3Tech.solucoesFromtis.service.ICedenteService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.IIndexadorService;
import br.com.m3Tech.solucoesFromtis.service.IMovimentoService;
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;
import br.com.m3Tech.solucoesFromtis.service.IRiscoService;
import br.com.m3Tech.solucoesFromtis.service.ISacadoService;
import br.com.m3Tech.solucoesFromtis.service.ITesteService;
import br.com.m3Tech.solucoesFromtis.service.ITipoRecebivelService;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Botao;
import br.com.m3Tech.solucoesFromtis.telas.componentes.CheckBox;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxBase;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxFundoDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Label;

@Controller
public class TestesDiversos extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<Base> cbBase;
	private JCheckBox testarProcedures;
	private JCheckBox cadastrarSacado;
	private JComboBox<FundoDto> cbFundo;

	
	private JTable tabela;
	
	
	private CnabDto cnab;
	private TituloDto titulo;
	
	private final IFundoService fundoService;
	private final IOriginadorService originadorService;
	private final IBancoService bancoService;
	private final ICedenteService cedenteService;
	private final ISacadoService sacadoService;
	private final IMovimentoService movimentoService;
	private final ITipoRecebivelService tipoRecebivelService;
	private final IConfGlobalService confGlobalService;
	private final IIndexadorService indexadorService;
	private final IRiscoService riscoService;
	private final ITesteService testeService;

	
	public TestesDiversos(final IFundoService fundoService,
			  final IOriginadorService originadorService,
			  final IBancoService bancoService,
			  final ICedenteService cedenteService,
			  final ISacadoService sacadoService,
			  final IMovimentoService movimentoService,
			  final ITipoRecebivelService tipoRecebivelService,
			  final IConfGlobalService confGlobalService,
			  final IIndexadorService indexadorService,
			  final IRiscoService riscoService,
			  final ITesteService testeService) {

		this.fundoService = fundoService;
		this.originadorService = originadorService;
		this.bancoService = bancoService;
		this.cedenteService = cedenteService;
		this.sacadoService = sacadoService;
		this.movimentoService = movimentoService;
		this.tipoRecebivelService = tipoRecebivelService;
		this.confGlobalService = confGlobalService;
		this.indexadorService = indexadorService;
		this.riscoService = riscoService;
		this.testeService = testeService;
		
		try {
			
			cnab = new CnabDto();	
			titulo = new TituloDto();
			
			
			this.setBounds(1, 1, ConfigTela.largura, ConfigTela.altura);
			this.setLayout(null);
			this.setBackground(Color.WHITE);
		
			this.add(new Label("Testes Diversos", 10, 10, 500, 20, 14, Color.BLUE));
		
			this.add(new Label("Selecionar Base", 10, 40, 100, 20, 14, Color.BLACK));
			cbBase = ComboBoxBase.novo(110, 40, 350, 20, getActionCbBase());
			
			testarProcedures = new CheckBox("Testar Procedures", 10, 90, 150, 20, 14,null);
			
			cadastrarSacado = new CheckBox("Cadastrar Sacado", 30, 90, 150, 20, 14,null);
			
			this.add(new Label("Fundo: ", 480, 40, 100, 20, 14, Color.BLACK));
			cbFundo = ComboBoxFundoDto.novo(600, 40, 350, 20, getActionCbFundoDto());

		
			this.add(new Botao("Iniciar Testes", 650, 460, 150, 20, getActionIniciarTestes()));
			
			iniciarTabela();

			if(!"Selecione".equals(((Base)cbBase.getSelectedItem()).getUrl())) {
				preencherComboFundos();

			}

			
			
			this.add(cbBase);
			this.add(testarProcedures);
			this.add(cadastrarSacado);
			this.add(cbFundo);

			this.repaint();
		
		} catch (Exception e) {
			e.printStackTrace();
		};
		
		
		
	}
	
	@SuppressWarnings("serial")
	private void iniciarTabela() {

		try {
			
			DefaultTableModel modelo = new DefaultTableModel(
					new Object[][] {},
					new String[] {"Processo","Info","Resultado"}
					);
			
			tabela = new JTable(modelo){
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};

			
			tabela.setVisible(true);
			tabela.repaint();
			
			JScrollPane scroll = new JScrollPane(tabela);
			scroll.setBounds(10, 500, ConfigTela.largura - 20, 200);
			
			this.add(scroll);

		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	private ActionListener getActionCbBase() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					preencherComboFundos();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		};
	}
	
	private ActionListener getActionIniciarTestes() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) tabela.getModel();
				
				model.setNumRows(0);
				
				if("Selecione".equals(((Base) cbBase.getSelectedItem()).getUrl())) {
					JOptionPane.showMessageDialog(null, "Selecione uma base.","Erro", 0);
					return;
				}
				
				try {
					
					if(testarProcedures.isSelected()) {
						List<ResultadoTesteDto> retorno = testeService.testarProcedures(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto) cbFundo.getSelectedItem()), ((Base) cbBase.getSelectedItem()));
						
						retorno.forEach(r -> {
							
							model.addRow(new Object[] {
									
									r.getProcesso(),
									r.getInfo(),
									r.getResultado()
									
							});
						});
					}
					
					if(cadastrarSacado.isSelected()) {
						List<ResultadoTesteDto> retorno = testeService.testarProcedures(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto) cbFundo.getSelectedItem()), ((Base) cbBase.getSelectedItem()));
						
						retorno.forEach(r -> {
							
							model.addRow(new Object[] {
									
									r.getProcesso(),
									r.getInfo(),
									r.getResultado()
									
							});
						});
					}
					
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		};
	}
	
	private ActionListener getActionCbFundoDto() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(cbFundo != null && cbFundo.getItemCount() > 0) {
					try {
					
						DefaultTableModel model = (DefaultTableModel) tabela.getModel();
						model.setNumRows(0);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		};
	}
	
	private void preencherComboFundos() throws Exception {
		List<FundoDto> fundos = fundoService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()));
		cbFundo.removeAllItems();
		if(fundos != null && !fundos.isEmpty()) {
			for(FundoDto fundo : fundos) {
				cbFundo.addItem(fundo);
			}

		}
	}

}
