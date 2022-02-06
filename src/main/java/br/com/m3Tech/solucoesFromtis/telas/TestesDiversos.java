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
	private JComboBox<FundoDto> cbFundo;
//	private JComboBox<LayoutEnum> cbLayout;
//	private JComboBox<OriginadorDto> cbOriginador;
//	private JComboBox<BancoDto> cbBanco;
//	private JComboBox<String> cbCoobrigacao;
//	private JComboBox<CedenteDto> cbCedente;
//	private JComboBox<SacadoDto> cbSacado;
//	private JComboBox<MovimentoDto> cbMovimento;
//	private JComboBox<TipoRecebivelDto> cbTipoRecebivel;
//	private JComboBox<IndexadorDto> cbIndexador;
//	private JComboBox<RiscoDto> cbRisco;
	
//	private Text dataGravacao;
//	private Text dataVencimento;
//	private Text dataCarencia;
//	private Text seuNumero;
//	private Text numeroDocumento;
//	private Text valorTitulo;
//	private Text valorAquisicao;
//	private Text taxaJurosIndexador;
//	private Text chaveNfe;
//	private Text termoCessao;
//	private Text path;
//	private Text variacaoCambial;
	
	private JTable tabela;
	
//	private Label erro;
	
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
			
			testarProcedures = new CheckBox("Testar Procedures", 10, 90, 150, 20, 14);
			
			this.add(new Label("Fundo: ", 480, 40, 100, 20, 14, Color.BLACK));
			cbFundo = ComboBoxFundoDto.novo(600, 40, 350, 20, getActionCbFundoDto());
//			this.add(new Label("Data Gravação: ", 470, 70, 100, 20, 14, Color.BLACK));
//			dataGravacao= new Text(580, 70, 100, 20, true);
//			this.add(new Label("Layout: ", 690, 70, 50, 20, 14, Color.BLACK));
//			cbLayout = ComboBoxLayoutRemessa.novo(750, 70, 200, 20);
//			
//			this.add(new Label("Originador: ", 10, 100, 100, 20, 14, Color.BLACK));
//			cbOriginador = ComboBoxOriginadorDto.novo(110, 100, 350, 20);
//			this.add(new Label("Banco: ", 470, 100, 100, 20, 14, Color.BLACK));
//			cbBanco = ComboBoxBancoDto.novo(580, 100, 350, 20);
//			
//			this.add(new Label("Coobrigação: ", 10, 150, 100, 20, 14, Color.BLACK));
//			cbCoobrigacao = ComboBoxCoobrigacaoDto.novo(110, 150, 350, 20);
//			this.add(new Label("Vencimento: ", 470, 150, 100, 20, 14, Color.BLACK));
//			dataVencimento = new Text(580, 150, 100, 20, true);
//			
//			this.add(new Label("Cedente: ", 10, 180, 100, 20, 14, Color.BLACK));
//			cbCedente = ComboBoxCedenteDto.novo(110, 180, 350, 20);
//			this.add(new Label("Sacado: ", 470, 180, 100, 20, 14, Color.BLACK));
//			cbSacado = ComboBoxSacadoDto.novo(580, 180, 350, 20);
//			
//			this.add(new Label("Movimento: ", 10, 210, 100, 20, 14, Color.BLACK));
//			cbMovimento = ComboBoxMovimentoDto.novo(110, 210, 350, 20);
//			this.add(new Label("Tipo Recebível: ", 470, 210, 100, 20, 14, Color.BLACK));
//			cbTipoRecebivel = ComboBoxTipoRecebivelDto.novo(580, 210, 350, 20);
//
//			this.add(new Label("Seu Número: ", 10, 240, 100, 20, 14, Color.BLACK));
//			seuNumero = new Text(110, 240, 300, 20, true);
//			this.add(new Botao("@", 410, 240, 50, 20, getActionGerarSeuNumero()));
//			this.add(new Label("Número Doc.: ", 470, 240, 100, 20, 14, Color.BLACK));
//			numeroDocumento = new Text(580, 240, 150, 20, true);
//			this.add(new Botao("@", 730, 240, 50, 20, getActionGerarNumeroDocumento()));
//			
//			this.add(new Label("Valor Título: ", 10, 270, 100, 20, 14, Color.BLACK));
//			valorTitulo = new Text(110, 270, 150, 20, true);
//			this.add(new Label("Valor Aquisição: ", 470, 270, 100, 20, 14, Color.BLACK));
//			valorAquisicao = new Text(580, 270, 150, 20, true);
//			
//			this.add(new Label("Chave Nfe: ", 10, 300, 100, 20, 14, Color.BLACK));
//			chaveNfe = new Text(110, 300, 300, 20, true);
//			this.add(new Botao("@", 410, 300, 50, 20, getActionGerarChaveNfe()));
//			this.add(new Label("Termo Cessão: ", 470, 300, 100, 20, 14, Color.BLACK));
//			termoCessao = new Text(580, 300, 150, 20, true);
//			this.add(new Botao("@", 730, 300, 50, 20, getActionGerarTermoCessao()));
//			
//			this.add(new Label("Indexador: ", 10, 330, 100, 20, 14, Color.BLACK));
//			cbIndexador = ComboBoxIndexadorDto.novo(110, 330, 100, 20);
//			this.add(new Label("Data Carencia: ", 220, 330, 100, 20, 14, Color.BLACK));
//			dataCarencia = new Text(330, 330, 100, 20, true);
//			this.add(new Label("TaxaJurosIndexador: ", 440, 330, 100, 20, 14, Color.BLACK));
//			taxaJurosIndexador = new Text(550, 330, 100, 20, true);
//			this.add(new Label("VariacaoCambial: ", 660, 330, 100, 20, 14, Color.BLACK));
//			variacaoCambial = new Text(770, 330, 100, 20, true);
//			this.add(new Label("Risco: ", 10, 360, 100, 20, 14, Color.BLACK));
//			cbRisco = ComboBoxRiscoDto.novo(110, 360, 200, 20);
//			
//			this.add(new Botao("Adicionar Título", 400, 400, 150, 20, getActionAdicionarTitulo()));
		
			this.add(new Botao("Iniciar Testes", 650, 460, 150, 20, getActionIniciarTestes()));
			
			iniciarTabela();
			
//			this.add(new Label("Salvar Cnab em: ", 10, 650, 110, 20, 14, Color.BLACK));
//			path = new Text(130, 650, 500, 20, true);
			
			
//			erro = new Label("", 10, 670, 1000, 20, 14, Color.RED);
//			this.add(erro);

			if(!"Selecione".equals(((Base)cbBase.getSelectedItem()).getUrl())) {
				preencherComboFundos();
//				preencherComboBanco();
//				preencherComboMovimento();
//				preencherComboIndexador();
//				preencherComboRisco();
//				preencherComboTipoRecebivel();
			}
//			dataVencimento.setText(LocalDate.now().plusDays(30).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//		
//			path.setText(confGlobalService.getConfGlobal().getPath());
			
			
			this.add(cbBase);
			this.add(testarProcedures);
			this.add(cbFundo);
//			this.add(dataGravacao);
//			this.add(cbLayout);
//			this.add(cbOriginador);
//			this.add(cbBanco);
//			this.add(cbCoobrigacao);
//			this.add(dataVencimento);
//			this.add(cbCedente);
//			this.add(cbSacado);
//			this.add(cbMovimento);
//			this.add(cbTipoRecebivel);
//			this.add(cbIndexador);
//			this.add(dataCarencia);
//			this.add(taxaJurosIndexador);
//			this.add(variacaoCambial);
//			this.add(cbRisco);
//			this.add(seuNumero);
//			this.add(numeroDocumento);
//			this.add(valorTitulo);
//			this.add(valorAquisicao);
//			this.add(chaveNfe);
//			this.add(termoCessao);
//			this.add(path);
			this.repaint();
		
		} catch (Exception e) {
//			erro.setText(e.getMessage());
//			this.repaint();
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


//			tabela.addMouseListener(getActionEditarRow());
			
			tabela.setVisible(true);
			tabela.repaint();
			
			JScrollPane scroll = new JScrollPane(tabela);
			scroll.setBounds(10, 500, ConfigTela.largura - 20, 200);
			
			this.add(scroll);

		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
//	private MouseListener getActionEditarRow() {
//		// TODO Auto-generated method stub
//		return new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//
//				if (e.getClickCount() > 1) {
//					
//				}
//			}
//		};
//	}

	private ActionListener getActionCbBase() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					preencherComboFundos();
//					preencherComboBanco();
//					preencherComboMovimento();
//					preencherComboIndexador();
//					preencherComboRisco();
//					preencherComboTipoRecebivel();
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
						List<ResultadoTesteDto> retorno = testeService.testarProcedures(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto) cbFundo.getSelectedItem()));
						
						retorno.forEach(r -> {
							
							model.addRow(new Object[] {
									
									r.getProcesso(),
									r.getInfo(),
									r.getResultado()
									
							});
						});
					}
					
					
					
//					if(cnab.getTitulos() == null || cnab.getTitulos().isEmpty()) {
//						JOptionPane.showMessageDialog(null, "Nenhum título foi adicionado.","Erro", 0);
//						return;
//					}
//					
//					cnab.setBanco((BancoDto)cbBanco.getSelectedItem());
//					cnab.setDataGravacao(LocalDateUtils.parseDate(dataGravacao.getText()));
//					cnab.setFundo((FundoDto)cbFundo.getSelectedItem());
//					cnab.setLayout((LayoutEnum)cbLayout.getSelectedItem());
//					cnab.setOriginador((OriginadorDto)cbOriginador.getSelectedItem());
//					
//					
//					
//					ConfGlobal confGlobal = confGlobalService.getConfGlobal();
//					cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
//					confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
//					confGlobal.setPath(path.getText());
//					confGlobal.save();
//					
//					StreamFactory factory = StreamFactory.newInstance();
//			        
//					factory.loadResource("beanio.xml");
//			        
//			        File pathArquivo = new File(path.getText());
//			        
//			        if(!pathArquivo.exists()) {
//			        	pathArquivo.mkdirs();
//			        }
//			        
//			        File arquivoFinal = new File(pathArquivo, getNomeArquivo(cnab.getNumSeqArquivo()));
//			        
//			        BeanWriter out = factory.createWriter(cnab.getLayout().getNmLayout(),arquivoFinal );        
//			                
//			        out.write(new CnabHeader(cnab));
//			        int qtdeTitulos = 2;
//			        for(TituloDto dto : cnab.getTitulos()) {
//			        	out.write(new CnabDetail(dto, qtdeTitulos++));
//			        }
//			        out.write(new CnabTrailler(StringUtils.getNumeroComZerosAEsquerda(qtdeTitulos,6)));
//			        
//			        out.flush();
//			        out.close();
//			        
//			        System.out.println("Fim da Geração");
//			        
//			        erro.setText("Cnab Gerado com sucesso");
//			        
//			        ((DefaultTableModel)tabela.getModel()).setNumRows(0);
//			        cnab = new CnabDto();
			       
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		};
	}
	

//	private ActionListener getActionAdicionarTitulo() {
//		return new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				
//				BigDecimal vlAquisicao = new BigDecimal(valorAquisicao.getText().replaceAll(",", "."));
//				BigDecimal vlTitulo = new BigDecimal(valorTitulo.getText().replaceAll(",", "."));
//				BigDecimal vlTaxaJurosIndexador = null;
//						
//				if(!"".equals(taxaJurosIndexador.getText())) {
//					vlTaxaJurosIndexador = new BigDecimal(taxaJurosIndexador.getText().replaceAll(",", "."));
//				}
//				
//				if(vlAquisicao.compareTo(vlTitulo) > 0) {
//					JOptionPane.showMessageDialog(null, "Valor de aquisição não pode ser maior que o valor do título","Erro", 0);
//					return;
//				}
//				
//				if(cnab.getTitulos().size() > 999997) {
//					JOptionPane.showMessageDialog(null, "Quantidade de Títulos máxima é 999.997","Erro", 0);
//					return;
//				}
//				
//				if(StringUtils.EmptyOrNull(seuNumero.getText())) {
//					JOptionPane.showMessageDialog(null, "Seu Numero é obrigatório","Erro", 0);
//					return;
//				}
//				
//				if(StringUtils.EmptyOrNull(numeroDocumento.getText())) {
//					JOptionPane.showMessageDialog(null, "Numero Documento é obrigatório","Erro", 0);
//					return;
//				}
//				
//				if(StringUtils.EmptyOrNull(termoCessao.getText())) {
//					JOptionPane.showMessageDialog(null, "Termo Cessão é obrigatório","Erro", 0);
//					return;
//				}
//				
//				titulo.setCedente((CedenteDto)cbCedente.getSelectedItem());
//				titulo.setChaveNfe(chaveNfe.getText());
//				titulo.setCoobrigacao("Com coobrigação".equals(cbCoobrigacao.getSelectedItem()) ? "01" : "02");
//				titulo.setDataVencimento(LocalDateUtils.parseDate(dataVencimento.getText()));
//				titulo.setEspecie(((TipoRecebivelDto)cbTipoRecebivel.getSelectedItem()).getCdEspecie());
//				titulo.setMovimento((MovimentoDto)cbMovimento.getSelectedItem());
//				titulo.setNumBanco(((BancoDto)cbBanco.getSelectedItem()).getCodigoBanco());
//				titulo.setNumeroDocumento(numeroDocumento.getText());
//				titulo.setSacado((SacadoDto)cbSacado.getSelectedItem());
//				titulo.setSeuNumero(seuNumero.getText());
//				titulo.setTermoCessao(termoCessao.getText());
//				titulo.setValorAquisicao(vlAquisicao);
//				titulo.setValorTitulo(vlTitulo);
//				titulo.setIndexador((IndexadorDto)cbIndexador.getSelectedItem());
//				titulo.setRisco((RiscoDto)cbRisco.getSelectedItem());
//				titulo.setVariacaoCambial(variacaoCambial.getText());
//				titulo.setTaxaJurosIndexador(vlTaxaJurosIndexador);
//				if(!"".equals(dataCarencia.getText())) {
//					titulo.setDataCarencia(LocalDateUtils.parseDate(dataCarencia.getText()));
//				}
//				DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
//				
////				"Seu Numero","Valor Título","Cedente","Sacado"
//				
//				modelo.addRow(new Object[] {
//						
//						titulo.getSeuNumero(),
//						titulo.getValorTitulo(),
//						titulo.getCedente().getNomeCedente(),
//						titulo.getSacado().getNomeSacado()
//						
//				});
//				
//				cnab.getTitulos().add(titulo.getCopy());
//				
//				titulo = new TituloDto();
//				
//			}
//		};
//	}
	
	private ActionListener getActionCbFundoDto() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(cbFundo != null && cbFundo.getItemCount() > 0) {
					try {
					
						DefaultTableModel model = (DefaultTableModel) tabela.getModel();
						model.setNumRows(0);
						
//						dataGravacao.setText(((FundoDto)cbFundo.getSelectedItem()).getDataFundo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//					
//						preencherComboOriginador();
//						preencherComboCedente();
//						preencherComboSacado();
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		};
	}
//	
//	private ActionListener getActionGerarSeuNumero() {
//		return new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				
//				seuNumero.setText(ValorAleatorioUtil.getValor(25));
//			}
//		};
//	}
//	
//	private ActionListener getActionGerarNumeroDocumento() {
//		return new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				
//				numeroDocumento.setText(ValorAleatorioUtil.getValor(10));
//			}
//		};
//	}
//	
//	private ActionListener getActionGerarChaveNfe() {
//		return new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				
//				chaveNfe.setText("31190600006388319890559240000000311006164587");
//			}
//		};
//	}
//	
//	private ActionListener getActionGerarTermoCessao() {
//		return new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				
//				termoCessao.setText(ValorAleatorioUtil.getValor(10));
//			}
//		};
//	}
//	
//	
//	
	private void preencherComboFundos() throws Exception {
		List<FundoDto> fundos = fundoService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()));
		cbFundo.removeAllItems();
		if(fundos != null && !fundos.isEmpty()) {
			for(FundoDto fundo : fundos) {
				cbFundo.addItem(fundo);
			}
			
//			dataGravacao.setText(((FundoDto)cbFundo.getSelectedItem()).getDataFundo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//			preencherComboOriginador();
//			preencherComboCedente();
//			preencherComboSacado();
		}
	}
//	
//	private void preencherComboOriginador() throws Exception {
//		List<OriginadorDto> originadores = originadorService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto) cbFundo.getSelectedItem()).getIdFundo());
//		cbOriginador.removeAllItems();
//		if(originadores != null && !originadores.isEmpty()) {
//			for(OriginadorDto originador : originadores) {
//				cbOriginador.addItem(originador);
//			}
//			
//		}
//	}
//	
//	private void preencherComboMovimento() throws Exception {
//		List<MovimentoDto> movimentos = movimentoService.findAllMovimentosAquisicao(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((LayoutEnum)cbLayout.getSelectedItem()).getCdLayout());
//		cbMovimento.removeAllItems();
//		if(movimentos != null && !movimentos.isEmpty()) {
//			for(MovimentoDto movimento : movimentos) {
//				cbMovimento.addItem(movimento);
//			}
//			
//		}
//	}
//	
//	private void preencherComboIndexador() throws Exception {
//		List<IndexadorDto> indexadores = indexadorService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()));
//		cbIndexador.removeAllItems();
//		if(indexadores != null && !indexadores.isEmpty()) {
//			for(IndexadorDto indexador : indexadores) {
//				cbIndexador.addItem(indexador);
//			}
//			
//		}
//	}
//	
//	private void preencherComboRisco() throws Exception {
//		List<RiscoDto> riscos = riscoService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()));
//		cbRisco.removeAllItems();
//		if(riscos != null && !riscos.isEmpty()) {
//			for(RiscoDto risco : riscos) {
//				cbRisco.addItem(risco);
//			}
//			
//		}
//	}
//	
//	private void preencherComboTipoRecebivel() throws Exception {
//		List<TipoRecebivelDto> listaTipoRecebivel = tipoRecebivelService.findAllTipoRecebivel(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto) cbFundo.getSelectedItem()).getIdFundo());
//		cbTipoRecebivel.removeAllItems();
//		if(listaTipoRecebivel != null && !listaTipoRecebivel.isEmpty()) {
//			for(TipoRecebivelDto tipoRecebivel : listaTipoRecebivel) {
//				cbTipoRecebivel.addItem(tipoRecebivel);
//			}
//			
//		}
//	}
//	
//	private void preencherComboCedente() throws Exception {
//		List<CedenteDto> cedentes = cedenteService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto) cbFundo.getSelectedItem()).getIdFundo());
//		cbCedente.removeAllItems();
//		if(cedentes != null && !cedentes.isEmpty()) {
//			for(CedenteDto cedente : cedentes) {
//				cbCedente.addItem(cedente);
//			}
//			
//		}
//	}
//	private void preencherComboSacado() throws Exception {
//		List<SacadoDto> sacados = sacadoService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto) cbFundo.getSelectedItem()).getIdFundo());
//		cbSacado.removeAllItems();
//		if(sacados != null && !sacados.isEmpty()) {
//			for(SacadoDto sacado : sacados) {
//				cbSacado.addItem(sacado);
//			}
//			
//		}
//	}
//	
//	private void preencherComboBanco() throws Exception {
//		List<BancoDto> bancos = bancoService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()));
//		cbBanco.removeAllItems();
//		if(bancos != null && !bancos.isEmpty()) {
//			for(BancoDto banco : bancos) {
//				cbBanco.addItem(banco);
//			}
//			
//		}
//	}
//	
//	private String getNomeArquivo(Integer seq) {
//		return "CNAB_" + cnab.getLayout().getTamLayout() + "_AQUISICAO_" + LocalDate.now().toString().replaceAll("-", "")+ "_" + seq +".txt";
//	}


}
