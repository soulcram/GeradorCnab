package br.com.m3Tech.geradorCnab.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

import br.com.m3Tech.geradorCnab.beanio.CnabDetail;
import br.com.m3Tech.geradorCnab.beanio.CnabHeader;
import br.com.m3Tech.geradorCnab.beanio.CnabTrailler;
import br.com.m3Tech.geradorCnab.dao.Conexao;
import br.com.m3Tech.geradorCnab.dto.BancoDto;
import br.com.m3Tech.geradorCnab.dto.CnabDto;
import br.com.m3Tech.geradorCnab.dto.FundoDto;
import br.com.m3Tech.geradorCnab.dto.MovimentoDto;
import br.com.m3Tech.geradorCnab.dto.OriginadorDto;
import br.com.m3Tech.geradorCnab.dto.TituloDto;
import br.com.m3Tech.geradorCnab.enuns.LayoutEnum;
import br.com.m3Tech.geradorCnab.model.Base;
import br.com.m3Tech.geradorCnab.model.ConfGlobal;
import br.com.m3Tech.geradorCnab.service.IBancoService;
import br.com.m3Tech.geradorCnab.service.IConfGlobalService;
import br.com.m3Tech.geradorCnab.service.IFundoService;
import br.com.m3Tech.geradorCnab.service.IMovimentoService;
import br.com.m3Tech.geradorCnab.service.IOriginadorService;
import br.com.m3Tech.geradorCnab.service.impl.BancoServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.ConfGlobalServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.FundoServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.MovimentoServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.OriginadorServiceImpl;
import br.com.m3Tech.geradorCnab.telas.componentes.Botao;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxBancoDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxBase;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxFundoDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxLayoutRemessa;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxMovimentoDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxOriginadorDto;
import br.com.m3Tech.geradorCnab.telas.componentes.Label;
import br.com.m3Tech.geradorCnab.telas.componentes.Text;
import br.com.m3Tech.geradorCnab.util.StringUtils;

public class GerarCnabProrrogacao extends JPanel {

private static final long serialVersionUID = 1L;
	
	private JComboBox<Base> cbBase;
	private JComboBox<FundoDto> cbFundo;
	private JComboBox<LayoutEnum> cbLayout;
	private JComboBox<OriginadorDto> cbOriginador;
	private JComboBox<BancoDto> cbBanco;
	private JComboBox<MovimentoDto> cbMovimento;
	
	private Text dataGravacao;
	private Text path;
	private Text seuNumero;
	private Text vencimentoOriginal;
	private Text novoVencimento;
	
	private JTable tabelaTitulosEmEstoque;
	private JTable tabelaTituloParaBaixar;
	
	private Label erro;
	
	private CnabDto cnab;
	private TituloDto titulo;
	private List<TituloDto> titulosEmEstoque;
	
	private IFundoService fundoService;
	private IOriginadorService originadorService;
	private IBancoService bancoService;
	private IMovimentoService movimentoService;
	private IConfGlobalService confGlobalService;

	public GerarCnabProrrogacao() {
		try {
			
			cnab = new CnabDto();	
			
			fundoService = new FundoServiceImpl();
			originadorService = new OriginadorServiceImpl();
			bancoService = new BancoServiceImpl();
			movimentoService = new MovimentoServiceImpl();
			confGlobalService = new ConfGlobalServiceImpl();
			
			this.setBounds(1, 1, 1000, 690);
			this.setLayout(null);
			this.setBackground(Color.WHITE);
		
			this.add(new Label("Gerar Cnab Prorrogação", 10, 10, 500, 20, 14, Color.BLUE));
		
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
			
			this.add(new Label("Nova Data Vencimento", 10, 350, 200, 20, 14, Color.BLUE));
			this.add(new Label("Seu Número: ", 150, 350, 100, 20, 14, Color.BLACK));
			seuNumero = new Text(250, 350, 170, 20, false);
			this.add(new Label("Vencimento Original: ", 440, 350, 200, 20, 14, Color.BLACK));
			vencimentoOriginal = new Text(580, 350, 100, 20, false);
			this.add(new Label("Novo Vencimento: ", 440, 380, 200, 20, 14, Color.BLACK));
			novoVencimento = new Text(580, 380, 100, 20, true);
			this.add(new Botao("Add Título", 700, 380, 100, 20, getActionAdicionarTituloProrrogacao()));
			
			this.add(new Label("Salvar Cnab em: ", 10, 650, 110, 20, 14, Color.BLACK));
			path = new Text(130, 650, 500, 20, true);
			this.add(new Botao("Gerar Cnab", 650, 650, 100, 20, getActionGerarCnab()));
			
			erro = new Label("", 10, 670, 1000, 20, 14, Color.RED);
			this.add(erro);

			
			preencherComboFundos();
			preencherComboBanco();
			preencherComboMovimento();
		
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
			this.add(vencimentoOriginal);
			this.add(novoVencimento);
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

			tabelaTitulosEmEstoque.addMouseListener(getActionAdicionarTitulo());
			tabelaTitulosEmEstoque.setName("Títulos disponivel para prorrogação");
			tabelaTitulosEmEstoque.setVisible(true);
			tabelaTitulosEmEstoque.repaint();
			
			this.add(new Label("Títulos disponivel para prorrogação", 10, 160, 250, 20, 14, Color.BLUE));
			
			JScrollPane scroll = new JScrollPane(tabelaTitulosEmEstoque);
			scroll.setBounds(10, 180, 920, 150);
			scroll.setName("Títulos disponivel para prorrogação");
			this.add(scroll);

		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	private void preencherTabelaTitulosEmEstoque() {

		try {

			DefaultTableModel modelo = (DefaultTableModel) tabelaTitulosEmEstoque.getModel();
			modelo.setNumRows(0);

			titulosEmEstoque = movimentoService.findAllTituloEmEstoqueByFundo(
					Conexao.getConnection((Base) cbBase.getSelectedItem()),
					((FundoDto) cbFundo.getSelectedItem()).getIdFundo(),
					true);

			if (titulosEmEstoque != null && !titulosEmEstoque.isEmpty()) {
				for (TituloDto dto : titulosEmEstoque) {
					modelo.addRow(new Object[] { dto.getSeuNumero(), dto.getValorTitulo(),
							dto.getCedente().getNomeCedente(), dto.getSacado().getNomeSacado() });
				}
			}else {
				JOptionPane.showMessageDialog(null, "Nenhum titulo em estoque que permita prorrogação");
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
							dto.getCedente().getNomeCedente(), dto.getSacado().getNomeSacado() });
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
					new String[] {"Seu Numero","Valor Título","Cedente","Sacado"}
					);
			
			tabelaTituloParaBaixar = new JTable(modelo){
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};


			tabelaTituloParaBaixar.addMouseListener(getActionAdicionarTitulo());
			tabelaTituloParaBaixar.setName("Títulos que serão prorrogados");
			tabelaTituloParaBaixar.setVisible(true);
			tabelaTituloParaBaixar.repaint();
			
			this.add(new Label("Títulos que serão prorrogados", 10, 410, 310, 20, 14, Color.BLUE));
			
			JScrollPane scroll = new JScrollPane(tabelaTituloParaBaixar);
			scroll.setBounds(10, 430, 920, 180);
			scroll.setName("Títulos que serão prorrogados");
			
			this.add(scroll);

		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	private MouseListener getActionAdicionarTitulo() {
		
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() > 1) {
					String seuNumeroSelecionado = tabelaTitulosEmEstoque.getValueAt(tabelaTitulosEmEstoque.getSelectedRow(), 0).toString();
					
					
					
					for(TituloDto dto : titulosEmEstoque) {
						
						if(seuNumeroSelecionado.equals(dto.getSeuNumero())) {
							titulo = dto;
							
							titulo.setMovimento((MovimentoDto)cbMovimento.getSelectedItem());
							titulo.setNumBanco(((BancoDto)cbBanco.getSelectedItem()).getCodigoBanco());
							
							seuNumero.setText(titulo.getSeuNumero());
							vencimentoOriginal.setText(titulo.getDataVencimento().format( DateTimeFormatter.ofPattern("dd/MM/yyyy")));
							break;
						}
					}
					

				}
			}
		};
	}
	
	private ActionListener getActionAdicionarTituloProrrogacao() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!validarDataVencimento()) {
					return;
				}
				
				LocalDate novaDataVencimento = LocalDate.parse(novoVencimento.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				
				if(titulo != null) {
					titulosEmEstoque.remove(titulo);
					
					DefaultTableModel modelo = (DefaultTableModel) tabelaTitulosEmEstoque.getModel();
					modelo.setNumRows(0);

					for (TituloDto dto : titulosEmEstoque) {
						modelo.addRow(new Object[] { dto.getSeuNumero(), dto.getValorTitulo(),
									dto.getCedente().getNomeCedente(), dto.getSacado().getNomeSacado() });
					}
					
				}
				
				TituloDto copy = titulo.getCopy();
				
				copy.setMovimento((MovimentoDto)cbMovimento.getSelectedItem());
				copy.setNumBanco(((BancoDto)cbBanco.getSelectedItem()).getCodigoBanco());
				copy.setDataVencimento(novaDataVencimento);
				
				cnab.getTitulos().add(copy);

				titulo = new TituloDto();
				preencherTabelaTitulosParaBaixar();
				novoVencimento.setText("");
				vencimentoOriginal.setText("");
				seuNumero.setText("");
				
			}


		};
	}
	
	private boolean validarDataVencimento() {
		if(StringUtils.EmptyOrNull(novoVencimento.getText())) {
			JOptionPane.showMessageDialog(null, "Vencimento é obrigatorio.","Erro", 0);
			return false;
		}
		
		try {
			LocalDate novaDataVencimento = LocalDate.parse(novoVencimento.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Data Vencimento com formato inesperado. Use o formato dd/MM/yyyy ","Erro", 0);
			return false;
		}
		
		
		Integer diasMaxProrrogacao = null;
		
		try {
			diasMaxProrrogacao = fundoService.findDiasMaxProrrogacao(Conexao.getConnection((Base)cbBase.getSelectedItem()),((FundoDto)cbFundo.getSelectedItem()).getIdFundo());
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao consultar dias máximos para prorrogação, Prorrogação não permitida para o fundo","Erro", 0);
			return false;
		}
		
		if(diasMaxProrrogacao != null) {
			
			long dias = ChronoUnit.DAYS.between(titulo.getDataVencimento(), LocalDate.parse(novoVencimento.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			
			if(dias < 1) {
				JOptionPane.showMessageDialog(null, "Dava inválida, A nova data de vencimento deve ser maior que a data de Vencimento Original","Erro", 0);
				return false;
			}
			
			if(dias > diasMaxProrrogacao) {
				JOptionPane.showMessageDialog(null, "Dava inválida, Fundo permite apenas " + diasMaxProrrogacao + " de prorrogação","Erro", 0);
				return false;
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "Erro ao consultar dias máximos para prorrogação, Prorrogação não permitida para o fundo","Erro", 0);
			return false;
		}		
		
		return true;
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
					cnab.setDataGravacao(LocalDate.parse(dataGravacao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					cnab.setFundo((FundoDto)cbFundo.getSelectedItem());
					cnab.setLayout((LayoutEnum)cbLayout.getSelectedItem());
					cnab.setOriginador((OriginadorDto)cbOriginador.getSelectedItem());
					
					
					
					ConfGlobal confGlobal = confGlobalService.getConfGlobal();
					cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
					confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
					confGlobal.setPath(path.getText());
					confGlobal.save();
					
					StreamFactory factory = StreamFactory.newInstance();
			        
					factory.loadResource("beanio.xml");
			        
			        File pathArquivo = new File(path.getText());
			        
			        if(!pathArquivo.exists()) {
			        	pathArquivo.mkdirs();
			        }
			        
			        File arquivoFinal = new File(pathArquivo, getNomeArquivo(cnab.getNumSeqArquivo()));
			        
			        BeanWriter out = factory.createWriter(cnab.getLayout().getNmLayout(),arquivoFinal );        
			                
			        out.write(new CnabHeader(cnab));
			        int qtdeTitulos = 2;
			        for(TituloDto dto : cnab.getTitulos()) {
			        	dto.setDataLiquidacao(null);
			        	out.write(new CnabDetail(dto, qtdeTitulos++));
			        }
			        out.write(new CnabTrailler(StringUtils.getNumeroComZerosAEsquerda(qtdeTitulos,6)));
			        
			        out.flush();
			        out.close();
			        
			        System.out.println("Fim da Geração");
			        
			        erro.setText("Cnab Gerado com sucesso");
			        
			        ((DefaultTableModel)tabelaTituloParaBaixar.getModel()).setNumRows(0);
			        cnab = new CnabDto();
			       
					
				} catch (Exception e1) {
					erro.setText(e1.getMessage());
					repaint();
				}
				
				
			}
		};
	}
	
	private ActionListener getActionCbFundoDto() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(cbFundo != null && cbFundo.getItemCount() > 0) {
					try {
					
						dataGravacao.setText(((FundoDto)cbFundo.getSelectedItem()).getDataFundo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					
						preencherTabelaTitulosEmEstoque();
						preencherComboOriginador();
						
					} catch (Exception e1) {
						erro.setText(e1.getMessage());
					}
				}
			}
		};
	}
	
	private void preencherComboFundos() throws Exception {
		List<FundoDto> fundos = fundoService.findAllProrrogacao(Conexao.getConnection((Base)cbBase.getSelectedItem()));
		cbFundo.removeAllItems();
		if(fundos == null || fundos.isEmpty()) {
			
			
			cbOriginador.removeAllItems();
			((DefaultTableModel) tabelaTitulosEmEstoque.getModel()).setNumRows(0);
			JOptionPane.showMessageDialog(null, "Nenhum fundo encontrado que permita prorrogação");
			return;
		}
		
		
		if(fundos != null && !fundos.isEmpty()) {
			for(FundoDto fundo : fundos) {
				cbFundo.addItem(fundo);
			}
			
			dataGravacao.setText(((FundoDto)cbFundo.getSelectedItem()).getDataFundo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			
			preencherTabelaTitulosEmEstoque();
			preencherComboOriginador();
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
		List<MovimentoDto> movimentos = movimentoService.findAllMovimentosProrrogacao(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((LayoutEnum)cbLayout.getSelectedItem()).getCdLayout());
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
	
	private String getNomeArquivo(Integer seq) {
		return "CNAB_" + cnab.getLayout().getTamLayout() + "_PRORROGACAO_" + LocalDate.now().toString().replaceAll("-", "")+ "_" + seq +".txt";
	}


}
