package br.com.m3Tech.solucoesFromtis.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.common.base.Preconditions;

import br.com.m3Tech.solucoesFromtis.dao.Conexao;
import br.com.m3Tech.solucoesFromtis.dto.BancoDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IBancoService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.IGeradorCnab;
import br.com.m3Tech.solucoesFromtis.service.IMovimentoService;
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Botao;
import br.com.m3Tech.solucoesFromtis.telas.componentes.CheckBox;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxBancoDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxBase;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxFundoDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxLayoutRemessa;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxMovimentoDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxOriginadorDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Label;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Text;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import br.com.m3Tech.utils.LocalDateUtils;

@Controller
public class GerarCnabParcial extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<Base> cbBase;
	private JComboBox<FundoDto> cbFundo;
	private JComboBox<LayoutEnum> cbLayout;
	private JComboBox<OriginadorDto> cbOriginador;
	private JComboBox<BancoDto> cbBanco;
	private JComboBox<MovimentoDto> cbMovimento;
	
	private Text dataGravacao;
	private Text seuNumero;
	private Text numeroDocumento;
	private Text valorTitulo;
	private Text valorParcial;
	private Text path;
	
	private JTable tabelaTitulosEmEstoque;
	private JTable tabelaTituloParaBaixar;
	
	private Label erro;
	
	private CheckBox importacaoAutomatica;
	
	private CnabDto cnab;
	private TituloDto titulo;
	private List<TituloDto> titulosEmEstoque;
	
	private final IFundoService fundoService;
	private final IOriginadorService originadorService;
	private final IBancoService bancoService;
	private final IMovimentoService movimentoService;
	private final IConfGlobalService confGlobalService;
	private final IGeradorCnab geradorCnab;

	@Autowired
	public GerarCnabParcial(final IFundoService fundoService,
			final IOriginadorService originadorService,
			final IBancoService bancoService,
			final IMovimentoService movimentoService,
			final IConfGlobalService confGlobalService,
			  final IGeradorCnab geradorCnab) {

		this.fundoService = fundoService;
		this.originadorService = originadorService;
		this.bancoService = bancoService;
		this.movimentoService = movimentoService;
		this.confGlobalService = confGlobalService;
		this.geradorCnab = geradorCnab;
		
		try {
			
			cnab = new CnabDto();	
			titulo = new TituloDto();
			
			this.setBounds(1, 1, ConfigTela.largura, ConfigTela.altura);
			this.setLayout(null);
			this.setBackground(Color.WHITE);
		
			this.add(new Label("Gerar Cnab Liquidação Parcial", 10, 10, 500, 20, 14, Color.BLUE));
		
			this.add(new Label("Selecionar Base", 10, 40, 100, 20, 14, Color.BLACK));
			cbBase = ComboBoxBase.novo(110, 40, 350, 20, getActionCbBase());
			
			this.add(new Label("Fundo: ", 10, 70, 100, 20, 14, Color.BLACK));
			cbFundo = ComboBoxFundoDto.novo(110, 70, 350, 20, getActionCbFundoDto());
			this.add(new Label("Data Gravação: ", 470, 70, 100, 20, 14, Color.BLACK));
			dataGravacao= new Text(580, 70, 100, 20, true);
			this.add(new Label("Layout: ", 690, 70, 50, 20, 14, Color.BLACK));
			cbLayout = ComboBoxLayoutRemessa.novo(750, 70, 200, 20);
			
			this.add(new Label("Originador: ", 10, 100, 100, 20, 14, Color.BLACK));
			cbOriginador = ComboBoxOriginadorDto.novo(110, 100, 350, 20);
			this.add(new Label("Banco: ", 470, 100, 100, 20, 14, Color.BLACK));
			cbBanco = ComboBoxBancoDto.novo(580, 100, 350, 20);

			this.add(new Label("Movimento: ", 10, 130, 100, 20, 14, Color.BLACK));
			cbMovimento = ComboBoxMovimentoDto.novo(110, 130, 350, 20);
			
			this.add(new Label("Seu Número: ", 10, 160, 100, 20, 14, Color.BLACK));
			seuNumero = new Text(110, 160, 300, 20, false);
			this.add(new Label("Número Doc.: ", 470, 160, 100, 20, 14, Color.BLACK));
			numeroDocumento = new Text(580, 160, 150, 20, false);
			
			this.add(new Label("Valor Título: ", 10, 190, 100, 20, 14, Color.BLACK));
			valorTitulo = new Text(110, 190, 150, 20, false);
			this.add(new Label("Valor Parcial: ", 470, 190, 100, 20, 14, Color.BLACK));
			valorParcial = new Text(580, 190, 150, 20,true);

			this.add(new Botao("Adicionar Título", 400, 230, 150, 20, getActionAdicionarTitulo()));
		
			
			
			importacaoAutomatica = new CheckBox("Importação Automática?", 10, 630, 180, 20, 14, getActionImportacaoAutomatica());
			this.add(new Label("Salvar Cnab em: ", 10, 650, 110, 20, 14, Color.BLACK));
			path = new Text(130, 650, 500, 20,true);
			this.add(new Botao("Gerar Cnab", 650, 650, 100, 20, getActionGerarCnab()));
			
			erro = new Label("", 10, 670, 1000, 20, 14, Color.RED);
			this.add(erro);

			if(!"Selecione".equals(((Base)cbBase.getSelectedItem()).getUrl())) {
				preencherComboFundos();
				preencherComboBanco();
				preencherComboMovimento();
			}
		
			path.setText(confGlobalService.getConfGlobal().getPath());
			
			iniciarTabelaTitulosEmEstoque();
			iniciarTabelaTitulosParaBaixar();
			
			this.add(cbBase);
			this.add(cbFundo);
			this.add(dataGravacao);
			this.add(cbLayout);
			this.add(cbOriginador);
			this.add(cbBanco);
			this.add(cbMovimento);
			this.add(seuNumero);
			this.add(numeroDocumento);
			this.add(valorTitulo);
			this.add(valorParcial);
			this.add(importacaoAutomatica);
			this.add(path);
			this.repaint();
		
		} catch (Exception e) {
			erro.setText(e.getMessage());
			this.repaint();
		};
		
		
		
	}
	

	@SuppressWarnings("serial")
	private void iniciarTabelaTitulosEmEstoque() {

		try {
			
			DefaultTableModel modelo = new DefaultTableModel(
					new Object[][] {},
					new String[] {"Seu Numero","Valor Título","Cedente","Sacado"}
					);
			
			tabelaTitulosEmEstoque = new JTable(modelo){
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};
			
			preencherTabelaTitulosEmEstoque();

			tabelaTitulosEmEstoque.addMouseListener(getActionEditarTitulo());
			tabelaTitulosEmEstoque.setName("Títulos em Estoque");
			tabelaTitulosEmEstoque.setVisible(true);
			tabelaTitulosEmEstoque.repaint();
			
			this.add(new Label("Títulos em Estoque", 10, 250, 110, 20, 14, Color.BLUE));
			
			JScrollPane scroll = new JScrollPane(tabelaTitulosEmEstoque);
			scroll.setBounds(10, 280, 920, 150);
			scroll.setName("Títulos em Estoque");
			this.add(scroll);

		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	private void preencherTabelaTitulosEmEstoque() {
		
		if("Selecione".equals(((Base) cbBase.getSelectedItem()).getUrl())) {
			return;
		}

		try {

			DefaultTableModel modelo = (DefaultTableModel) tabelaTitulosEmEstoque.getModel();
			modelo.setNumRows(0);

			titulosEmEstoque = movimentoService.findAllTituloEmEstoqueByFundo(
					Conexao.getConnection((Base) cbBase.getSelectedItem()),
					((FundoDto) cbFundo.getSelectedItem()).getIdFundo(),
					false);

			if (titulosEmEstoque != null && !titulosEmEstoque.isEmpty()) {
				for (TituloDto dto : titulosEmEstoque) {
					modelo.addRow(new Object[] { dto.getSeuNumero(), dto.getValorTitulo(),
							dto.getCedente().getNomeCedente(), dto.getSacado().getNomeSacado() });
				}
			}
		} catch (Exception e) {
			erro.setText(e.getMessage());
			System.out.println(e.getMessage());
		}
	}
	
	private void preencherTabelaTitulosParaBaixar() {

		try {

			DefaultTableModel modelo = (DefaultTableModel) tabelaTituloParaBaixar.getModel();
			modelo.setNumRows(0);

			List<TituloDto> titulos = cnab.getTitulos();

			if (titulos != null && !titulos.isEmpty()) {
				for (TituloDto dto : titulos) {
					modelo.addRow(new Object[] { dto.getSeuNumero(), dto.getValorTitulo(),
							dto.getValorPago(),
							dto.getCedente().getNomeCedente() });
				}
			}
		} catch (Exception e) {
			erro.setText(e.getMessage());
			System.out.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("serial")
	private void iniciarTabelaTitulosParaBaixar() {

		try {
			
			DefaultTableModel modelo = new DefaultTableModel(
					new Object[][] {},
					new String[] {"Seu Numero","Valor Título","Parcial","Cedente"}
					);
			
			tabelaTituloParaBaixar = new JTable(modelo){
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};


			tabelaTituloParaBaixar.addMouseListener(getActionEditarTitulo());
			tabelaTituloParaBaixar.setName("Títulos que serão baixados");
			tabelaTituloParaBaixar.setVisible(true);
			tabelaTituloParaBaixar.repaint();
			
			this.add(new Label("Títulos que serão baixados", 10, 450, 310, 20, 14, Color.BLUE));
			
			JScrollPane scroll = new JScrollPane(tabelaTituloParaBaixar);
			scroll.setBounds(10, 470, 920, 150);
			scroll.setName("Títulos que serão baixados");
			
			this.add(scroll);

		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	private MouseListener getActionEditarTitulo() {
		
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() > 1) {
					String seuNumeroSelecionado = tabelaTitulosEmEstoque.getValueAt(tabelaTitulosEmEstoque.getSelectedRow(), 0).toString();
					
					
					for(TituloDto dto : titulosEmEstoque) {
						
						if(seuNumeroSelecionado.equals(dto.getSeuNumero())) {
							titulo = dto;

							seuNumero.setText(dto.getSeuNumero());
							numeroDocumento.setText(dto.getNumeroDocumento());
							valorTitulo.setText(dto.getValorTitulo().toString());
							
							break;
						}
					}
					

				}
			}
		};
	}

	private ActionListener getActionCbBase() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					preencherComboFundos();
					preencherComboBanco();
					preencherComboMovimento();
				} catch (Exception e1) {
					erro.setText(e1.getMessage());
				}
				
				
			}
		};
	}
	
	private ActionListener getActionGerarCnab() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if(cnab.getTitulos() == null || cnab.getTitulos().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nenhum título foi adicionado.","Erro", 0);
						return;
					}
					
					cnab.setBanco((BancoDto)cbBanco.getSelectedItem());
					cnab.setDataGravacao(LocalDateUtils.parseDate(dataGravacao.getText()));
					cnab.setFundo((FundoDto)cbFundo.getSelectedItem());
					cnab.setLayout((LayoutEnum)cbLayout.getSelectedItem());
					cnab.setOriginador((OriginadorDto)cbOriginador.getSelectedItem());
					
					
					
					ConfGlobal confGlobal = confGlobalService.getConfGlobal();
					cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
					confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
					if(!importacaoAutomatica.isSelected()) {
						confGlobal.setPath(path.getText());
					}
					
					confGlobal.save();
					
					geradorCnab.gerar(cnab, "BAIXA_PARCIAL", importacaoAutomatica.isSelected(), path.getText());
			        
			        erro.setText("Cnab Gerado com sucesso");
			        JOptionPane.showMessageDialog(null, "Cnab Gerado com sucesso.","Sucesso", 2);
			        
			        ((DefaultTableModel)tabelaTituloParaBaixar.getModel()).setNumRows(0);
			        cnab = new CnabDto();
			       
					
				} catch (Exception e1) {
					erro.setText(e1.getMessage());
					repaint();
				}
				
				
			}
		};
	}
	

	private ActionListener getActionAdicionarTitulo() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				if(!validarValorParcial()) {
					return;
				}
				
				BigDecimal valorParcial_BD = new BigDecimal(valorParcial.getText().replaceAll(",", "."));
				
				TituloDto copy = titulo.getCopy();
				
				copy.setMovimento((MovimentoDto)cbMovimento.getSelectedItem());
				copy.setNumBanco(((BancoDto)cbBanco.getSelectedItem()).getCodigoBanco());
				copy.setValorPago(valorParcial_BD);
				copy.setValorAbatimento(valorParcial_BD);

				
				
				cnab.getTitulos().add(copy);
				preencherTabelaTitulosParaBaixar();
				
				if(titulo != null) {
					titulosEmEstoque.remove(titulo);
					
					DefaultTableModel modelo = (DefaultTableModel) tabelaTitulosEmEstoque.getModel();
					modelo.setNumRows(0);

					for (TituloDto dto : titulosEmEstoque) {
						modelo.addRow(new Object[] { dto.getSeuNumero(), dto.getValorTitulo(),
									dto.getCedente().getNomeCedente(), dto.getSacado().getNomeSacado() });
					}
					
				}
				
				valorParcial.setText("");
				valorTitulo.setText("");
				seuNumero.setText("");
				numeroDocumento.setText("");
				titulo = new TituloDto();
				
			}
		};
	}
	
	private boolean validarValorParcial() {
		
		try {
			
		if(StringUtils.EmptyOrNull(valorParcial.getText())) {
			JOptionPane.showMessageDialog(null, "Valor da Parcial é obrigatório.","Ocorreu um erro", 0);
			return false;
		}
		
		BigDecimal valorParcial_BD = new BigDecimal(valorParcial.getText().replaceAll(",", "."));
		
		if(valorParcial_BD.compareTo(titulo.getValorTitulo()) >= 0) {
			JOptionPane.showMessageDialog(null, "Valor da Parcial não pode ser maior que o valor do titulo.","Ocorreu um erro", 0);
			return false;
		}
		
		}catch(Exception e) {
			new JOptionPane(e.getMessage());
			JOptionPane.showMessageDialog(null,e.getMessage(),"Ocorreu um erro", 0);
			return false;
		}
		
		return true;
		
	}
	
	private ActionListener getActionCbFundoDto() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(cbFundo != null && cbFundo.getItemCount() > 0) {
					try {
					
						dataGravacao.setText(((FundoDto)cbFundo.getSelectedItem()).getDataFundo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					
						preencherComboOriginador();
						preencherTabelaTitulosEmEstoque();
					} catch (Exception e1) {
						erro.setText(e1.getMessage());
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
			
			dataGravacao.setText(((FundoDto)cbFundo.getSelectedItem()).getDataFundo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			preencherComboOriginador();
			preencherTabelaTitulosEmEstoque();
		}
	}
	
	private void preencherComboOriginador() throws Exception {
		List<OriginadorDto> originadores = originadorService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto) cbFundo.getSelectedItem()).getIdFundo());
		cbOriginador.removeAllItems();
		if(originadores != null && !originadores.isEmpty()) {
			for(OriginadorDto originador : originadores) {
				cbOriginador.addItem(originador);
			}
			
		}
	}
	
	private void preencherComboMovimento() throws Exception {
		List<MovimentoDto> movimentos = movimentoService.findAllMovimentosParcial(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((LayoutEnum)cbLayout.getSelectedItem()).getCdLayout());
		cbMovimento.removeAllItems();
		if(movimentos != null && !movimentos.isEmpty()) {
			for(MovimentoDto movimento : movimentos) {
				cbMovimento.addItem(movimento);
			}
			
		}
	}
	
	private void preencherComboBanco() throws Exception {
		List<BancoDto> bancos = bancoService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()));
		cbBanco.removeAllItems();
		if(bancos != null && !bancos.isEmpty()) {
			for(BancoDto banco : bancos) {
				cbBanco.addItem(banco);
			}
			
		}
	}
	
	private ActionListener getActionImportacaoAutomatica() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					
					Base base = ((Base)cbBase.getSelectedItem());
					FundoDto fundoSelecionado = ((FundoDto)cbFundo.getSelectedItem());
					
					Preconditions.checkNotNull(base, "É obrigatório selecionar uma base");
					Preconditions.checkArgument(!base.getUrl().equals("Selecione"), "É obrigatório selecionar uma base");
					Preconditions.checkNotNull(fundoSelecionado, "É obrigatório selecionar uma base");
					
					path.setText(confGlobalService.getPathSalvarArquivo(Conexao.getConnection(base), importacaoAutomatica.isSelected(), base.getVersaoMercado(), fundoSelecionado));

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}
		};
	}


}
