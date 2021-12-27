package br.com.m3Tech.geradorCnab.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
import br.com.m3Tech.geradorCnab.dto.MovimentoDto;
import br.com.m3Tech.geradorCnab.dto.OriginadorDto;
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
import br.com.m3Tech.geradorCnab.service.IMovimentoService;
import br.com.m3Tech.geradorCnab.service.IOriginadorService;
import br.com.m3Tech.geradorCnab.service.ISacadoService;
import br.com.m3Tech.geradorCnab.service.ITipoRecebivelService;
import br.com.m3Tech.geradorCnab.service.impl.BancoServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.CedenteServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.ConfGlobalServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.FundoServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.MovimentoServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.OriginadorServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.SacadoServiceImpl;
import br.com.m3Tech.geradorCnab.service.impl.TipoRecebivelServiceImpl;
import br.com.m3Tech.geradorCnab.telas.componentes.Botao;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxBancoDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxBase;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxCoobrigacaoDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxFundoDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxLayout;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxOriginadorDto;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxTipoCedente;
import br.com.m3Tech.geradorCnab.telas.componentes.ComboBoxTipoSacado;
import br.com.m3Tech.geradorCnab.telas.componentes.Label;
import br.com.m3Tech.geradorCnab.telas.componentes.Text;
import br.com.m3Tech.geradorCnab.util.NumericUtils;
import br.com.m3Tech.geradorCnab.util.StringUtils;
import br.com.m3Tech.geradorCnab.util.ValorAleatorioUtil;
import br.com.m3Tech.utils.LocalDateUtils;

public class GerarCnabAquisicaoDinamico extends JPanel {

	private static final long serialVersionUID = 1L;

	private JComboBox<Base> cbBase;
	private JComboBox<FundoDto> cbFundo;
	private JComboBox<LayoutEnum> cbLayout;
	private JComboBox<OriginadorDto> cbOriginador;
	private JComboBox<BancoDto> cbBanco;
	private JComboBox<String> cbCoobrigacao;
	private JComboBox<String> cbCedente;
	private JComboBox<String> cbSacado;
	
	private Text dataGravacao;
	private Text dataVencimento;
	private Text quantTitulo;
	private Text path;
	
	private Label erro;
	
	private CnabDto cnab;
	private TituloDto titulo;
	private List<CedenteDto> cedentes;
	private List<SacadoDto> sacados;
	
	private IFundoService fundoService;
	private IOriginadorService originadorService;
	private IBancoService bancoService;
	private ICedenteService cedenteService;
	private ISacadoService sacadoService;
	private IMovimentoService movimentoService;
	private ITipoRecebivelService tipoRecebivelService;
	private IConfGlobalService confGlobalService;

	public GerarCnabAquisicaoDinamico() {
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
			
			this.setBounds(1, 1, 1000, 690);
			this.setLayout(null);
			this.setBackground(Color.WHITE);
		
			this.add(new Label("Gerar Cnab Aquisição Dinâmico", 10, 10, 500, 20, 14, Color.BLUE));
		
			this.add(new Label("Selecionar Base", 10, 40, 100, 20, 14, Color.BLACK));
			cbBase = ComboBoxBase.novo(110, 40, 350, 20, getActionCbBase());
			
			this.add(new Label("Fundo: ", 10, 70, 100, 20, 14, Color.BLACK));
			cbFundo = ComboBoxFundoDto.novo(110, 70, 350, 20, getActionCbFundoDto());
			this.add(new Label("Data Gravação: ", 470, 70, 100, 20, 14, Color.BLACK));
			dataGravacao= new Text(580, 70, 100, 20, true);
			this.add(new Label("Layout: ", 690, 70, 50, 20, 14, Color.BLACK));
			cbLayout = ComboBoxLayout.novo(750, 70, 200, 20);
			
			this.add(new Label("Originador: ", 10, 100, 100, 20, 14, Color.BLACK));
			cbOriginador = ComboBoxOriginadorDto.novo(110, 100, 350, 20);
			this.add(new Label("Banco: ", 470, 100, 100, 20, 14, Color.BLACK));
			cbBanco = ComboBoxBancoDto.novo(580, 100, 350, 20);
			
			this.add(new Label("Coobrigação: ", 10, 150, 100, 20, 14, Color.BLACK));
			cbCoobrigacao = ComboBoxCoobrigacaoDto.novo(110, 150, 350, 20);
			this.add(new Label("Vencimento: ", 470, 150, 100, 20, 14, Color.BLACK));
			dataVencimento = new Text(580, 150, 100, 20, true);
			
			this.add(new Label("Cedente: ", 10, 180, 100, 20, 14, Color.BLACK));
			cbCedente = ComboBoxTipoCedente.novo(110, 180, 350, 20);
			this.add(new Label("Sacado: ", 470, 180, 100, 20, 14, Color.BLACK));
			cbSacado = ComboBoxTipoSacado.novo(580, 180, 350, 20);
			
			this.add(new Label("Quantidade de titulos: ", 10, 210, 150, 20, 14, Color.BLACK));
			quantTitulo = new Text(170, 210, 100, 20, true);
			
			this.add(new Label("Salvar Cnab em: ", 10, 650, 110, 20, 14, Color.BLACK));
			path = new Text(130, 650, 500, 20, true);
			this.add(new Botao("Gerar Cnab", 650, 650, 100, 20, getActionGerarCnab()));
			
			erro = new Label("", 10, 670, 1000, 20, 14, Color.RED);
			this.add(erro);

			
			preencherComboFundos();
			preencherComboBanco();
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
			this.add(quantTitulo);
			this.add(cbCedente);
			this.add(cbSacado);
			this.add(path);
			this.repaint();
		
		} catch (Exception e) {
			e.printStackTrace();
			this.repaint();
		};
		
		
		
	}
	
	private ActionListener getActionCbBase() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					preencherComboFundos();
					preencherComboBanco();
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
					
					if(StringUtils.EmptyOrNull(quantTitulo.getText())) {
						JOptionPane.showMessageDialog(null, "Quantidade de Títulos é obrigatório","Erro", 0);
						return;
					}
					
					if(Integer.parseInt(quantTitulo.getText()) > 999997) {
						JOptionPane.showMessageDialog(null, "Quantidade de Títulos máxima é 999.997","Erro", 0);
						return;
					}
					
					cedentes = cedenteService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto) cbFundo.getSelectedItem()).getIdFundo());
					sacados = sacadoService.findAll(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto) cbFundo.getSelectedItem()).getIdFundo());
					
					cnab.setBanco((BancoDto)cbBanco.getSelectedItem());
					cnab.setDataGravacao(LocalDate.parse(dataGravacao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					cnab.setFundo((FundoDto)cbFundo.getSelectedItem());
					cnab.setLayout((LayoutEnum)cbLayout.getSelectedItem());
					cnab.setOriginador((OriginadorDto)cbOriginador.getSelectedItem());
					cnab.setTitulos(new ArrayList<>());
					
					
					ConfGlobal confGlobal = confGlobalService.getConfGlobal();
					cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
					confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
					confGlobal.setPath(path.getText());
					confGlobal.save();
					
					TipoRecebivelDto tipoRecebivelDto = tipoRecebivelService.findAllTipoRecebivel(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto) cbFundo.getSelectedItem()).getIdFundo()).get(0);
					MovimentoDto movimentoDto = movimentoService.findAllMovimentos(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((LayoutEnum)cbLayout.getSelectedItem()).getCdLayout()).get(0);
					
					for (int i = 0; i < Integer.parseInt(quantTitulo.getText()); i++) {

						addTitulo(movimentoDto, tipoRecebivelDto);
					}
					
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
			        
			        cnab = new CnabDto();
			       
					
				} catch (Exception e1) {
					erro.setText(e1.getMessage());
					repaint();
				}
				
				
			}
		};
	}
	
	public void addTitulo(MovimentoDto movimentoDto, TipoRecebivelDto tipoRecebivelDto) {
		
		try {
			gerarNovoSeuNumero();
			gerarNovoNumeroDocumento();
			gerarNovoTermoCessao();
			gerarNovoChaveNfe();
	
			BigDecimal valor = ValorAleatorioUtil.getValorDecimal();
	
			titulo.setValorTitulo(valor);
			titulo.setValorAquisicao(NumericUtils.getValorMenos10PorCento(valor));
			titulo.setDataVencimento(cnab.getDataGravacao().plusDays(20));
			titulo.setEspecie(tipoRecebivelDto.getCdEspecie());
			titulo.setMovimento(movimentoDto);
			titulo.setCedente(getCedenteSelecionado());
			titulo.setSacado(getSacadoSelecionado());
			titulo.setDataVencimento(LocalDateUtils.parseDate(dataVencimento.getText()));
			titulo.setCoobrigacao("Com coobrigação".equals(cbCoobrigacao.getSelectedItem()) ? "01" : "02");
	
			titulo.setNumBanco(cnab.getBanco().getCodigoBanco());
			cnab.getTitulos().add(titulo.getCopy());
			
			titulo = new TituloDto();
		
		}catch(Exception e) {
			erro.setText(e.getMessage());
		}
	}
	
	public void gerarNovoSeuNumero() {
		titulo.setSeuNumero(ValorAleatorioUtil.getValor(25));
	}

	public void gerarNovoNumeroDocumento() {
		titulo.setNumeroDocumento(ValorAleatorioUtil.getValor(10));
	}

	public void gerarNovoTermoCessao() {
		titulo.setTermoCessao(ValorAleatorioUtil.getValor(10));
	}

	public void gerarNovoChaveNfe() {
		titulo.setChaveNfe("31190600006388319890559240000000311006164587");
	}
	
	private CedenteDto getCedenteSelecionado() {
		
		try {
		
			
			if ("Cedente Único".equals(cbCedente.getSelectedItem())) {
				return cedentes.get(0);
			} else {
				return cedentes.get(ValorAleatorioUtil.getValorNumerico(cedentes.size()));
			}
		
		}catch(Exception e) {
			erro.setText(e.getMessage());
			this.repaint();
		}
		
		return null;
	}

	private SacadoDto getSacadoSelecionado() throws Exception {
		
		

		return sacados.get(ValorAleatorioUtil.getValorNumerico(sacados.size()));

	}
		
	private ActionListener getActionCbFundoDto() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(cbFundo != null && cbFundo.getItemCount() > 0) {
					try {
					
						dataGravacao.setText(((FundoDto)cbFundo.getSelectedItem()).getDataFundo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					
						preencherComboOriginador();
						
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
