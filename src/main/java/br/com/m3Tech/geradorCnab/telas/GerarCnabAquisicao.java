package br.com.m3Tech.geradorCnab.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import br.com.m3Tech.geradorCnab.dto.CedenteDto;
import br.com.m3Tech.geradorCnab.dto.CnabDto;
import br.com.m3Tech.geradorCnab.dto.FundoDto;
import br.com.m3Tech.geradorCnab.dto.IndexadorDto;
import br.com.m3Tech.geradorCnab.dto.MovimentoDto;
import br.com.m3Tech.geradorCnab.dto.OriginadorDto;
import br.com.m3Tech.geradorCnab.dto.RiscoDto;
import br.com.m3Tech.geradorCnab.dto.SacadoDto;
import br.com.m3Tech.geradorCnab.dto.TipoRecebivelDto;
import br.com.m3Tech.geradorCnab.dto.TituloDto;
import br.com.m3Tech.geradorCnab.enuns.LayoutEnum;
import br.com.m3Tech.geradorCnab.model.Base;
import br.com.m3Tech.geradorCnab.model.ConfGlobal;
import br.com.m3Tech.geradorCnab.service.IBancoService;
import br.com.m3Tech.geradorCnab.service.ICedenteService;
import br.com.m3Tech.geradorCnab.service.IConfGlobalService;
import br.com.m3Tech.geradorCnab.service.IFundoService;
import br.com.m3Tech.geradorCnab.service.IIndexadorService;
import br.com.m3Tech.geradorCnab.service.IMovimentoService;
import br.com.m3Tech.geradorCnab.service.IOriginadorService;
import br.com.m3Tech.geradorCnab.service.IRiscoService;
import br.com.m3Tech.geradorCnab.service.ISacadoService;
import br.com.m3Tech.geradorCnab.service.ITipoRecebivelService;
import br.com.m3Tech.geradorCnab.service.impl.BancoServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.CedenteServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.ConfGlobalServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.FundoServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.IndexadorServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.MovimentoServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.OriginadorServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.RiscoServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.SacadoServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.TipoRecebivelServiceImpl;
import br.com.m3Tech.geradorCnab.telas.componentes.Botao;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxBancoDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxBase;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxCedenteDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxCoobrigacaoDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxFundoDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxIndexadorDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxLayoutRemessa;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxMovimentoDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxOriginadorDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxRiscoDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxSacadoDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxTipoRecebivelDto;
import br.com.m3Tech.geradorCnab.telas.componentes.Label;
import br.com.m3Tech.geradorCnab.telas.componentes.Text;
import br.com.m3Tech.geradorCnab.util.StringUtils;
import br.com.m3Tech.geradorCnab.util.ValorAleatorioUtil;
import br.com.m3Tech.utils.LocalDateUtils;

public class GerarCnabAquisicao extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<Base> cbBase;
	private JComboBox<FundoDto> cbFundo;
	private JComboBox<LayoutEnum> cbLayout;
	private JComboBox<OriginadorDto> cbOriginador;
	private JComboBox<BancoDto> cbBanco;
	private JComboBox<String> cbCoobrigacao;
	private JComboBox<CedenteDto> cbCedente;
	private JComboBox<SacadoDto> cbSacado;
	private JComboBox<MovimentoDto> cbMovimento;
	private JComboBox<TipoRecebivelDto> cbTipoRecebivel;
	private JComboBox<IndexadorDto> cbIndexador;
	private JComboBox<RiscoDto> cbRisco;
	
	private Text dataGravacao;
	private Text dataVencimento;
	private Text dataCarencia;
	private Text seuNumero;
	private Text numeroDocumento;
	private Text valorTitulo;
	private Text valorAquisicao;
	private Text taxaJurosIndexador;
	private Text chaveNfe;
	private Text termoCessao;
	private Text path;
	private Text variacaoCambial;
	
	private JTable tabela;
	
	private Label erro;
	
	private CnabDto cnab;
	private TituloDto titulo;
	
	private IFundoService fundoService;
	private IOriginadorService originadorService;
	private IBancoService bancoService;
	private ICedenteService cedenteService;
	private ISacadoService sacadoService;
	private IMovimentoService movimentoService;
	private ITipoRecebivelService tipoRecebivelService;
	private IConfGlobalService confGlobalService;
	private IIndexadorService indexadorService;
	private IRiscoService riscoService;

	public GerarCnabAquisicao() {
		try {
			
			cnab = new CnabDto();	
			titulo = new TituloDto();
			
			fundoService = new FundoServiceImpl();
			originadorService = new OriginadorServiceImpl();
			bancoService = new BancoServiceImpl();
			cedenteService = new CedenteServiceImpl();
			sacadoService = new SacadoServiceImpl();
			movimentoService = new MovimentoServiceImpl();
			tipoRecebivelService = new TipoRecebivelServiceImpl();
			confGlobalService = new ConfGlobalServiceImpl();
			indexadorService = new IndexadorServiceImpl();
			riscoService = new RiscoServiceImpl();
			
			this.setBounds(1, 1, 1000, 690);
			this.setLayout(null);
			this.setBackground(Color.WHITE);
		
			this.add(new Label("Gerar Cnab Aquisição", 10, 10, 500, 20, 14, Color.BLUE));
		
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
			
			this.add(new Label("Coobrigação: ", 10, 150, 100, 20, 14, Color.BLACK));
			cbCoobrigacao = ComboBoxCoobrigacaoDto.novo(110, 150, 350, 20);
			this.add(new Label("Vencimento: ", 470, 150, 100, 20, 14, Color.BLACK));
			dataVencimento = new Text(580, 150, 100, 20, true);
			
			this.add(new Label("Cedente: ", 10, 180, 100, 20, 14, Color.BLACK));
			cbCedente = ComboBoxCedenteDto.novo(110, 180, 350, 20);
			this.add(new Label("Sacado: ", 470, 180, 100, 20, 14, Color.BLACK));
			cbSacado = ComboBoxSacadoDto.novo(580, 180, 350, 20);
			
			this.add(new Label("Movimento: ", 10, 210, 100, 20, 14, Color.BLACK));
			cbMovimento = ComboBoxMovimentoDto.novo(110, 210, 350, 20);
			this.add(new Label("Tipo Recebível: ", 470, 210, 100, 20, 14, Color.BLACK));
			cbTipoRecebivel = ComboBoxTipoRecebivelDto.novo(580, 210, 350, 20);

			this.add(new Label("Seu Número: ", 10, 240, 100, 20, 14, Color.BLACK));
			seuNumero = new Text(110, 240, 300, 20, true);
			this.add(new Botao("@", 410, 240, 50, 20, getActionGerarSeuNumero()));
			this.add(new Label("Número Doc.: ", 470, 240, 100, 20, 14, Color.BLACK));
			numeroDocumento = new Text(580, 240, 150, 20, true);
			this.add(new Botao("@", 730, 240, 50, 20, getActionGerarNumeroDocumento()));
			
			this.add(new Label("Valor Título: ", 10, 270, 100, 20, 14, Color.BLACK));
			valorTitulo = new Text(110, 270, 150, 20, true);
			this.add(new Label("Valor Aquisição: ", 470, 270, 100, 20, 14, Color.BLACK));
			valorAquisicao = new Text(580, 270, 150, 20, true);
			
			this.add(new Label("Chave Nfe: ", 10, 300, 100, 20, 14, Color.BLACK));
			chaveNfe = new Text(110, 300, 300, 20, true);
			this.add(new Botao("@", 410, 300, 50, 20, getActionGerarChaveNfe()));
			this.add(new Label("Termo Cessão: ", 470, 300, 100, 20, 14, Color.BLACK));
			termoCessao = new Text(580, 300, 150, 20, true);
			this.add(new Botao("@", 730, 300, 50, 20, getActionGerarTermoCessao()));
			
			this.add(new Label("Indexador: ", 10, 330, 100, 20, 14, Color.BLACK));
			cbIndexador = ComboBoxIndexadorDto.novo(110, 330, 100, 20);
			this.add(new Label("Data Carencia: ", 220, 330, 100, 20, 14, Color.BLACK));
			dataCarencia = new Text(330, 330, 100, 20, true);
			this.add(new Label("TaxaJurosIndexador: ", 440, 330, 100, 20, 14, Color.BLACK));
			taxaJurosIndexador = new Text(550, 330, 100, 20, true);
			this.add(new Label("VariacaoCambial: ", 660, 330, 100, 20, 14, Color.BLACK));
			variacaoCambial = new Text(770, 330, 100, 20, true);
			this.add(new Label("Risco: ", 10, 360, 100, 20, 14, Color.BLACK));
			cbRisco = ComboBoxRiscoDto.novo(110, 360, 200, 20);
			
			this.add(new Botao("Adicionar Título", 400, 400, 150, 20, getActionAdicionarTitulo()));
		
			
			iniciarTabela();
			
			this.add(new Label("Salvar Cnab em: ", 10, 650, 110, 20, 14, Color.BLACK));
			path = new Text(130, 650, 500, 20, true);
			this.add(new Botao("Gerar Cnab", 650, 650, 100, 20, getActionGerarCnab()));
			
			erro = new Label("", 10, 670, 1000, 20, 14, Color.RED);
			this.add(erro);

			
			preencherComboFundos();
			preencherComboBanco();
			preencherComboMovimento();
			preencherComboIndexador();
			preencherComboRisco();
			preencherComboTipoRecebivel();
			dataVencimento.setText(LocalDate.now().plusDays(30).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
			path.setText(confGlobalService.getConfGlobal().getPath());
			
			
			this.add(cbBase);
			this.add(cbFundo);
			this.add(dataGravacao);
			this.add(cbLayout);
			this.add(cbOriginador);
			this.add(cbBanco);
			this.add(cbCoobrigacao);
			this.add(dataVencimento);
			this.add(cbCedente);
			this.add(cbSacado);
			this.add(cbMovimento);
			this.add(cbTipoRecebivel);
			this.add(cbIndexador);
			this.add(dataCarencia);
			this.add(taxaJurosIndexador);
			this.add(variacaoCambial);
			this.add(cbRisco);
			this.add(seuNumero);
			this.add(numeroDocumento);
			this.add(valorTitulo);
			this.add(valorAquisicao);
			this.add(chaveNfe);
			this.add(termoCessao);
			this.add(path);
			this.repaint();
		
		} catch (Exception e) {
			erro.setText(e.getMessage());
			this.repaint();
		};
		
		
		
	}
	
	@SuppressWarnings("serial")
	private void iniciarTabela() {

		try {
			
			DefaultTableModel modelo = new DefaultTableModel(
					new Object[][] {},
					new String[] {"Seu Numero","Valor Título","Cedente","Sacado"}
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
			scroll.setBounds(10, 430, 920, 200);
			
			this.add(scroll);

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
					preencherComboIndexador();
					preencherComboRisco();
					preencherComboTipoRecebivel();
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
			        	out.write(new CnabDetail(dto, qtdeTitulos++));
			        }
			        out.write(new CnabTrailler(StringUtils.getNumeroComZerosAEsquerda(qtdeTitulos,6)));
			        
			        out.flush();
			        out.close();
			        
			        System.out.println("Fim da Geração");
			        
			        erro.setText("Cnab Gerado com sucesso");
			        
			        ((DefaultTableModel)tabela.getModel()).setNumRows(0);
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
				
				BigDecimal vlAquisicao = new BigDecimal(valorAquisicao.getText().replaceAll(",", "."));
				BigDecimal vlTitulo = new BigDecimal(valorTitulo.getText().replaceAll(",", "."));
				BigDecimal vlTaxaJurosIndexador = new BigDecimal(taxaJurosIndexador.getText().replaceAll(",", "."));
				
				if(vlAquisicao.compareTo(vlTitulo) > 0) {
					JOptionPane.showMessageDialog(null, "Valor de aquisição não pode ser maior que o valor do título","Erro", 0);
					return;
				}
				
				if(cnab.getTitulos().size() > 999997) {
					JOptionPane.showMessageDialog(null, "Quantidade de Títulos máxima é 999.997","Erro", 0);
					return;
				}
				
				if(StringUtils.EmptyOrNull(seuNumero.getText())) {
					JOptionPane.showMessageDialog(null, "Seu Numero é obrigatório","Erro", 0);
					return;
				}
				
				if(StringUtils.EmptyOrNull(numeroDocumento.getText())) {
					JOptionPane.showMessageDialog(null, "Numero Documento é obrigatório","Erro", 0);
					return;
				}
				
				if(StringUtils.EmptyOrNull(termoCessao.getText())) {
					JOptionPane.showMessageDialog(null, "Termo Cessão é obrigatório","Erro", 0);
					return;
				}
				
				titulo.setCedente((CedenteDto)cbCedente.getSelectedItem());
				titulo.setChaveNfe(chaveNfe.getText());
				titulo.setCoobrigacao("Com coobrigação".equals(cbCoobrigacao.getSelectedItem()) ? "01" : "02");
				titulo.setDataVencimento(LocalDateUtils.parseDate(dataVencimento.getText()));
				titulo.setEspecie(((TipoRecebivelDto)cbTipoRecebivel.getSelectedItem()).getCdEspecie());
				titulo.setMovimento((MovimentoDto)cbMovimento.getSelectedItem());
				titulo.setNumBanco(((BancoDto)cbBanco.getSelectedItem()).getCodigoBanco());
				titulo.setNumeroDocumento(numeroDocumento.getText());
				titulo.setSacado((SacadoDto)cbSacado.getSelectedItem());
				titulo.setSeuNumero(seuNumero.getText());
				titulo.setTermoCessao(termoCessao.getText());
				titulo.setValorAquisicao(vlAquisicao);
				titulo.setValorTitulo(vlTitulo);
				titulo.setIndexador((IndexadorDto)cbIndexador.getSelectedItem());
				titulo.setRisco((RiscoDto)cbRisco.getSelectedItem());
				titulo.setVariacaoCambial(variacaoCambial.getText());
				titulo.setTaxaJurosIndexador(vlTaxaJurosIndexador);
				titulo.setDataCarencia(LocalDateUtils.parseDate(dataCarencia.getText()));
				
				DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
				
//				"Seu Numero","Valor Título","Cedente","Sacado"
				
				modelo.addRow(new Object[] {
						
						titulo.getSeuNumero(),
						titulo.getValorTitulo(),
						titulo.getCedente().getNomeCedente(),
						titulo.getSacado().getNomeSacado()
						
				});
				
				cnab.getTitulos().add(titulo.getCopy());
				
				titulo = new TituloDto();
				
			}
		};
	}
	
	private ActionListener getActionCbFundoDto() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(cbFundo != null && cbFundo.getItemCount() > 0) {
					try {
					
						dataGravacao.setText(((FundoDto)cbFundo.getSelectedItem()).getDataFundo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					
						preencherComboOriginador();
						preencherComboCedente();
						preencherComboSacado();
						
					} catch (Exception e1) {
						erro.setText(e1.getMessage());
					}
				}
			}
		};
	}
	
	private ActionListener getActionGerarSeuNumero() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				seuNumero.setText(ValorAleatorioUtil.getValor(25));
			}
		};
	}
	
	private ActionListener getActionGerarNumeroDocumento() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				numeroDocumento.setText(ValorAleatorioUtil.getValor(10));
			}
		};
	}
	
	private ActionListener getActionGerarChaveNfe() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				chaveNfe.setText("31190600006388319890559240000000311006164587");
			}
		};
	}
	
	private ActionListener getActionGerarTermoCessao() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				termoCessao.setText(ValorAleatorioUtil.getValor(10));
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
			preencherComboCedente();
			preencherComboSacado();
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
		List<MovimentoDto> movimentos = movimentoService.findAllMovimentosAquisicao(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((LayoutEnum)cbLayout.getSelectedItem()).getCdLayout());
		cbMovimento.removeAllItems();
		if(movimentos != null && !movimentos.isEmpty()) {
			for(MovimentoDto movimento : movimentos) {
				cbMovimento.addItem(movimento);
			}
			
		}
	}
	
	private void preencherComboIndexador() throws Exception {
		List<IndexadorDto> indexadores = indexadorService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()));
		cbIndexador.removeAllItems();
		if(indexadores != null && !indexadores.isEmpty()) {
			for(IndexadorDto indexador : indexadores) {
				cbIndexador.addItem(indexador);
			}
			
		}
	}
	
	private void preencherComboRisco() throws Exception {
		List<RiscoDto> riscos = riscoService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()));
		cbRisco.removeAllItems();
		if(riscos != null && !riscos.isEmpty()) {
			for(RiscoDto risco : riscos) {
				cbRisco.addItem(risco);
			}
			
		}
	}
	
	private void preencherComboTipoRecebivel() throws Exception {
		List<TipoRecebivelDto> listaTipoRecebivel = tipoRecebivelService.findAllTipoRecebivel(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto) cbFundo.getSelectedItem()).getIdFundo());
		cbTipoRecebivel.removeAllItems();
		if(listaTipoRecebivel != null && !listaTipoRecebivel.isEmpty()) {
			for(TipoRecebivelDto tipoRecebivel : listaTipoRecebivel) {
				cbTipoRecebivel.addItem(tipoRecebivel);
			}
			
		}
	}
	
	private void preencherComboCedente() throws Exception {
		List<CedenteDto> cedentes = cedenteService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto) cbFundo.getSelectedItem()).getIdFundo());
		cbCedente.removeAllItems();
		if(cedentes != null && !cedentes.isEmpty()) {
			for(CedenteDto cedente : cedentes) {
				cbCedente.addItem(cedente);
			}
			
		}
	}
	private void preencherComboSacado() throws Exception {
		List<SacadoDto> sacados = sacadoService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto) cbFundo.getSelectedItem()).getIdFundo());
		cbSacado.removeAllItems();
		if(sacados != null && !sacados.isEmpty()) {
			for(SacadoDto sacado : sacados) {
				cbSacado.addItem(sacado);
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
		return "CNAB_" + cnab.getLayout().getTamLayout() + "_AQUISICAO_" + LocalDate.now().toString().replaceAll("-", "")+ "_" + seq +".txt";
	}


}
