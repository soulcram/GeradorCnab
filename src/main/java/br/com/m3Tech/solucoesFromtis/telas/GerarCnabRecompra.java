package br.com.m3Tech.solucoesFromtis.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.common.base.Preconditions;

import br.com.m3Tech.solucoesFromtis.dao.Conexao;
import br.com.m3Tech.solucoesFromtis.dto.BancoDto;
import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;
import br.com.m3Tech.solucoesFromtis.dto.TipoRecebivelDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IBancoService;
import br.com.m3Tech.solucoesFromtis.service.ICedenteService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.IGeradorCnab;
import br.com.m3Tech.solucoesFromtis.service.IMovimentoService;
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;
import br.com.m3Tech.solucoesFromtis.service.ISacadoService;
import br.com.m3Tech.solucoesFromtis.service.ITipoRecebivelService;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Botao;
import br.com.m3Tech.solucoesFromtis.telas.componentes.CheckBox;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxBancoDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxBase;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxCedenteDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxCoobrigacaoDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxFundoDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxLayoutRemessa;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxMovimentoDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxOriginadorDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxSacadoDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxTipoRecebivelDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Label;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Text;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import br.com.m3Tech.utils.LocalDateUtils;

@Controller
public class GerarCnabRecompra extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<Base> cbBase;
	private JComboBox<FundoDto> cbFundo;
	private JComboBox<LayoutEnum> cbLayout;
	private JComboBox<OriginadorDto> cbOriginador;
	private JComboBox<BancoDto> cbBanco;
	private JComboBox<String> cbCoobrigacao;
	private JComboBox<CedenteDto> cbCedente;
	private JComboBox<SacadoDto> cbSacado;
	private JComboBox<MovimentoDto> cbMovimentoBaixaRecompra;
	private JComboBox<MovimentoDto> cbMovimentoAquisicaoRecompra;
	private JComboBox<TipoRecebivelDto> cbTipoRecebivel;
	
	private Text dataGravacao;
	private Text dataVencimento;
	private Text seuNumero;
	private Text numeroDocumento;
	private Text valorTitulo;
	private Text valorAquisicao;
	private Text chaveNfe;
	private Text termoCessao;
	private Text path;
	
	private JTable tabelaTituloParaBaixar;
	private JTable tabelaTitulosEmEstoque;
	
	private Label erro;
	
	private CheckBox importacaoAutomatica;
	
	private CnabDto cnab;
	private TituloDto titulo;
	private List<TituloDto> titulosEmEstoque;
	
	private final IFundoService fundoService;
	private final IOriginadorService originadorService;
	private final IBancoService bancoService;
	private final ICedenteService cedenteService;
	private final ISacadoService sacadoService;
	private final IMovimentoService movimentoService;
	private final ITipoRecebivelService tipoRecebivelService;
	private final IConfGlobalService confGlobalService;
	private final IGeradorCnab geradorCnab;

	@Autowired
	public GerarCnabRecompra(final IFundoService fundoService,
			final IOriginadorService originadorService,
			final IBancoService bancoService,
			final ICedenteService cedenteService,
			final ISacadoService sacadoService,
			final IMovimentoService movimentoService,
			final ITipoRecebivelService tipoRecebivelService,
			final IConfGlobalService confGlobalService,
			  final IGeradorCnab geradorCnab) {

		this.fundoService = fundoService;
		this.originadorService = originadorService;
		this.bancoService = bancoService;
		this.cedenteService = cedenteService;
		this.sacadoService = sacadoService;
		this.movimentoService = movimentoService;
		this.tipoRecebivelService = tipoRecebivelService;
		this.confGlobalService = confGlobalService;
		this.geradorCnab = geradorCnab;
		
		try {
			
			cnab = new CnabDto();	
			titulo = new TituloDto();
			
			this.setBounds(1, 1, ConfigTela.largura, ConfigTela.altura);
			this.setLayout(null);
			this.setBackground(Color.WHITE);
		
			this.add(new Label("Gerar Cnab Recompra", 10, 10, 500, 20, 14, Color.BLUE));
		
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
			cbMovimentoBaixaRecompra = ComboBoxMovimentoDto.novo(110, 130, 350, 20);
			
			this.add(new Label("Coobrigação: ", 10, 320, 100, 20, 14, Color.BLACK));
			cbCoobrigacao = ComboBoxCoobrigacaoDto.novo(110, 320, 350, 20);
			this.add(new Label("Vencimento: ", 470, 320, 100, 20, 14, Color.BLACK));
			dataVencimento = new Text(580, 320, 100, 20, true);
			
			this.add(new Label("Cedente: ", 10, 350, 100, 20, 14, Color.BLACK));
			cbCedente = ComboBoxCedenteDto.novo(110, 350, 350, 20);
			this.add(new Label("Sacado: ", 470, 350, 100, 20, 14, Color.BLACK));
			cbSacado = ComboBoxSacadoDto.novo(580, 350, 350, 20);
			
			this.add(new Label("Movimento: ", 10, 380, 100, 20, 14, Color.BLACK));
			cbMovimentoAquisicaoRecompra = ComboBoxMovimentoDto.novo(110, 380, 350, 20);
			this.add(new Label("Tipo Recebível: ", 470, 380, 100, 20, 14, Color.BLACK));
			cbTipoRecebivel = ComboBoxTipoRecebivelDto.novo(580, 380, 350, 20);
			
			this.add(new Label("Seu Número: ", 10, 410, 100, 20, 14, Color.BLACK));
			seuNumero = new Text(110, 410, 300, 20, true);
			this.add(new Botao("@", 410, 410, 50, 20, getActionGerarSeuNumero()));
			this.add(new Label("Número Doc.: ", 470, 410, 100, 20, 14, Color.BLACK));
			numeroDocumento = new Text(580, 410, 150, 20, true);
			this.add(new Botao("@", 730, 410, 50, 20, getActionGerarNumeroDocumento()));
			
			this.add(new Label("Valor Título: ", 10, 435, 100, 20, 14, Color.BLACK));
			valorTitulo = new Text(110, 435, 150, 20, false);
			this.add(new Label("Valor Aquisição: ", 470, 435, 100, 20, 14, Color.BLACK));
			valorAquisicao = new Text(580, 435, 150, 20, false);
			
			this.add(new Label("Chave Nfe: ", 10, 460, 100, 20, 14, Color.BLACK));
			chaveNfe = new Text(110, 460, 300, 20, true);
			this.add(new Botao("@", 410, 460, 50, 20, getActionGerarChaveNfe()));
			this.add(new Label("Termo Cessão: ", 470, 460, 100, 20, 14, Color.BLACK));
			termoCessao = new Text(580, 460, 150, 20, true);
			this.add(new Botao("@", 730, 460, 50, 20, getActionGerarTermoCessao()));
			
			this.add(new Botao("Adicionar Título", 400, 490, 150, 20, getActionAdicionarTituloAquisicao()));
		
			importacaoAutomatica = new CheckBox("Importação Automática?", 10, 630, 180, 20, 14, getActionImportacaoAutomatica());
			this.add(new Label("Salvar Cnab em: ", 10, 650, 110, 20, 14, Color.BLACK));
			path = new Text(130, 650, 500, 20, true);
			this.add(new Botao("Gerar Cnab", 650, 650, 100, 20, getActionGerarCnab()));
			
			erro = new Label("", 10, 670, 1000, 20, 14, Color.RED);
			this.add(erro);

			if(!"Selecione".equals(((Base)cbBase.getSelectedItem()).getUrl())) {
				preencherComboFundos();
				preencherComboBanco();
				preencherComboMovimentoRecompraBaixa();
				preencherComboMovimentoRecompraAquisicao();
				preencherComboTipoRecebivel();
			}
			
			dataVencimento.setText(LocalDate.now().plusDays(30).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
			path.setText(confGlobalService.getConfGlobal().getPath());
			
			iniciarTabelaTitulosEmEstoque();
			iniciarTabela();
			
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
			this.add(cbMovimentoBaixaRecompra);
			this.add(cbMovimentoAquisicaoRecompra);
			this.add(cbTipoRecebivel);
			this.add(seuNumero);
			this.add(numeroDocumento);
			this.add(valorTitulo);
			this.add(valorAquisicao);
			this.add(chaveNfe);
			this.add(termoCessao);
			this.add(importacaoAutomatica);
			this.add(path);
			this.repaint();
		
		} catch (Exception e) {
			e.printStackTrace();
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
			tabelaTitulosEmEstoque.setName("Títulos em Estoque");
			tabelaTitulosEmEstoque.setVisible(true);
			tabelaTitulosEmEstoque.repaint();
			
			this.add(new Label("Títulos em Estoque", 10, 160, 110, 20, 14, Color.BLUE));
			
			JScrollPane scroll = new JScrollPane(tabelaTitulosEmEstoque);
			scroll.setBounds(10, 180, 920, 100);
			scroll.setName("Títulos em Estoque");
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
					
					TituloDto tituloEncontrado = null;
					
					for(TituloDto dto : titulosEmEstoque) {
						
						if(seuNumeroSelecionado.equals(dto.getSeuNumero())) {
							tituloEncontrado = dto;
							dto.setMovimento((MovimentoDto)cbMovimentoBaixaRecompra.getSelectedItem());
							dto.setNumBanco(((BancoDto)cbBanco.getSelectedItem()).getCodigoBanco());
							cnab.getTitulos().add(dto.getCopy());
							preencherTabelaTitulosParaBaixar();
							break;
						}
					}
					
					if(tituloEncontrado != null) {
						titulosEmEstoque.remove(tituloEncontrado);
						
						DefaultTableModel modelo = (DefaultTableModel) tabelaTitulosEmEstoque.getModel();
						modelo.setNumRows(0);

						for (TituloDto dto : titulosEmEstoque) {
							modelo.addRow(new Object[] { dto.getSeuNumero(), dto.getValorTitulo(),
										dto.getCedente().getNomeCedente(), dto.getSacado().getNomeSacado() });
						}
						
					}
					
					BigDecimal valorAq = BigDecimal.ZERO;
					BigDecimal valorTit = BigDecimal.ZERO;
					
					for (TituloDto dto : cnab.getTitulos()) {
						valorAq = valorAq.add(dto.getValorAquisicao());
						valorTit = valorTit.add(dto.getValorTitulo());
					}
					
					valorAquisicao.setText(valorAq.toString());
					valorTitulo.setText(valorTit.toString());
				}
			}
		};
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
	
	@SuppressWarnings("serial")
	private void iniciarTabela() {

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


//			tabelaTituloParaBaixar.addMouseListener(getActionEditarRow());
			
			tabelaTituloParaBaixar.setVisible(true);
			tabelaTituloParaBaixar.repaint();
			
			this.add(new Label("Títulos adicionados", 10, 500, 310, 20, 14, Color.BLUE));
			
			JScrollPane scroll = new JScrollPane(tabelaTituloParaBaixar);
			scroll.setBounds(10, 520, 920, 130);
			
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
					preencherComboBanco();
					preencherComboMovimentoRecompraBaixa();
					preencherComboMovimentoRecompraAquisicao();
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
					
					geradorCnab.gerar(cnab, "RECOMPRA", importacaoAutomatica.isSelected(), path.getText());
			        
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
	

	private ActionListener getActionAdicionarTituloAquisicao() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				titulo.setCedente((CedenteDto)cbCedente.getSelectedItem());
				titulo.setChaveNfe(chaveNfe.getText());
				titulo.setCoobrigacao("Com coobrigação".equals(cbCoobrigacao.getSelectedItem()) ? "01" : "02");
				titulo.setDataVencimento(LocalDateUtils.parseDate(dataVencimento.getText()));
				titulo.setEspecie(((TipoRecebivelDto)cbTipoRecebivel.getSelectedItem()).getCdEspecie());
				titulo.setMovimento((MovimentoDto)cbMovimentoAquisicaoRecompra.getSelectedItem());
				titulo.setNumBanco(((BancoDto)cbBanco.getSelectedItem()).getCodigoBanco());
				titulo.setNumeroDocumento(numeroDocumento.getText());
				titulo.setSacado((SacadoDto)cbSacado.getSelectedItem());
				titulo.setSeuNumero(seuNumero.getText());
				titulo.setTermoCessao(termoCessao.getText());
				titulo.setValorAquisicao(new BigDecimal(valorAquisicao.getText().replaceAll(",", ".")));
				titulo.setValorTitulo(new BigDecimal(valorTitulo.getText().replaceAll(",", ".")));
				
				DefaultTableModel modelo = (DefaultTableModel) tabelaTituloParaBaixar.getModel();
				
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
						preencherTabelaTitulosEmEstoque();
						
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
	
	private void preencherComboMovimentoRecompraBaixa() throws Exception {
		List<MovimentoDto> movimentos = movimentoService.findAllMovimentosRecompraBaixa(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((LayoutEnum)cbLayout.getSelectedItem()).getCdLayout());
		cbMovimentoBaixaRecompra.removeAllItems();
		if(movimentos != null && !movimentos.isEmpty()) {
			for(MovimentoDto movimento : movimentos) {
				cbMovimentoBaixaRecompra.addItem(movimento);
			}
			
		}
	}
	
	private void preencherComboMovimentoRecompraAquisicao() throws Exception {
		List<MovimentoDto> movimentos = movimentoService.findAllMovimentosRecompraAquisicao(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((LayoutEnum)cbLayout.getSelectedItem()).getCdLayout());
		cbMovimentoAquisicaoRecompra.removeAllItems();
		if(movimentos != null && !movimentos.isEmpty()) {
			for(MovimentoDto movimento : movimentos) {
				cbMovimentoAquisicaoRecompra.addItem(movimento);
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
		List<CedenteDto> cedentes = cedenteService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto) cbFundo.getSelectedItem()).getIdFundo(),((Base)cbBase.getSelectedItem()));
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
