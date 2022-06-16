package br.com.m3Tech.solucoesFromtis.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.m3Tech.solucoesFromtis.dao.Conexao;
import br.com.m3Tech.solucoesFromtis.dto.BancoDto;
import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;
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
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxBancoDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxBase;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxFundoDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxLayoutRemessa;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxMovimentoDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxOriginadorDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Label;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Text;
import br.com.m3Tech.utils.LocalDateUtils;

@Controller
public class GerarCnabCsv extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<Base> cbBase;
	private JComboBox<FundoDto> cbFundo;
	private JComboBox<LayoutEnum> cbLayout;
	private JComboBox<OriginadorDto> cbOriginador;
	private JComboBox<BancoDto> cbBanco;
	private JComboBox<MovimentoDto> cbMovimento;
	
	private Text dataGravacao;
	private Text nomeArquivo;
	private Text path;
	
	
	private Label erro;
	
	private CnabDto cnab;
	
	private File fileCsv;
	
	
	private final IFundoService fundoService;
	private final IOriginadorService originadorService;
	private final IBancoService bancoService;
	private final IMovimentoService movimentoService;
	private final IConfGlobalService confGlobalService;
	private final IGeradorCnab geradorCnab;

	@Autowired
	public GerarCnabCsv(final IFundoService fundoService,
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
			
			this.setBounds(1, 1, ConfigTela.largura, ConfigTela.altura);
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
			
			this.add(new Label("Movimento: ", 10, 210, 100, 20, 14, Color.BLACK));
			cbMovimento = ComboBoxMovimentoDto.novo(110, 210, 350, 20);
			this.add(new Botao("Baixar Modelo Csv", 580, 210, 150, 20, getActionBaixarModeloCsv()));
			
			this.add(new Label("Arquivo: ", 10, 250, 100, 20, 14, Color.BLACK));
			nomeArquivo = new Text(120, 250, 450, 20, false);
			this.add(new Botao("Carregar arquivo", 580, 250, 150, 20, getActionCarregarArquivoCsv()));
			

			
			this.add(new Label("Salvar Cnab em: ", 10, 650, 110, 20, 14, Color.BLACK));
			path = new Text(130, 650, 500, 20, true);
			this.add(new Botao("Gerar Cnab", 650, 650, 100, 20, getActionGerarCnab()));
			
			erro = new Label("", 10, 670, 1000, 20, 14, Color.RED);
			this.add(erro);

			if(!"Selecione".equals(((Base)cbBase.getSelectedItem()).getUrl())) {
				preencherComboFundos();
				preencherComboBanco();
				preencherComboMovimento();
			}
		
			path.setText(confGlobalService.getConfGlobal().getPath());
			
			
			this.add(cbBase);
			this.add(cbFundo);
			this.add(dataGravacao);
			this.add(cbLayout);
			this.add(cbOriginador);
			this.add(cbBanco);
			this.add(cbMovimento);
			this.add(nomeArquivo);
			this.add(path);
			this.repaint();
		
		} catch (Exception e) {
			erro.setText(e.getMessage());
			this.repaint();
		};
		
		
		
	}

	private ActionListener getActionCbBase() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					preencherComboFundos();
					preencherComboBanco();
					preencherComboMovimento();
//					preencherComboTipoRecebivel();
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
					
			        carregarTitulosApartirDoCSv();

			        if(cnab.getTitulos().isEmpty()) {
			        	JOptionPane.showMessageDialog(null, "Nenhum titulo encontrado no csv.");
			        	return;
			        }
			        
			        if(cnab.getTitulos().size() > 999997) {
						JOptionPane.showMessageDialog(null, "Quantidade de Títulos máxima é 999.997","Erro", 0);
						return;
					}
			        
			     
					
					geradorCnab.gerar(cnab, "AQUISICAO", false, path.getText());
			        
			        erro.setText("Cnab Gerado com sucesso");
			        
			        cnab = new CnabDto();
			       
					
				} catch (Exception e1) {
					erro.setText(e1.getMessage());
					repaint();
				}
				
				
			}

		};
	}

	private void carregarTitulosApartirDoCSv() {

		try {
			Path p = Paths.get(fileCsv.toURI());
			List<String> readAllLines = Files.readAllLines(p, StandardCharsets.UTF_8);
			readAllLines.remove(0); // removendo o header
			
			for (String linha : readAllLines) {

				String[] colunas = linha.split(";");
				
				CedenteDto cedente = new CedenteDto(null, 
						colunas[0], //nomeCedente
						colunas[1].replaceAll("[^0-9xX]", ""),  //docCedente
						null
								);
				
				SacadoDto sacado = new SacadoDto(null, 
						colunas[2], //nomeSacado
						colunas[3].replaceAll("[^0-9xX]", ""), //docSacado
						colunas[4], //endereçoSacado
						colunas[5] //cepSacado
						);

				TituloDto titulo = new TituloDto(cedente, 
						sacado, 
						cnab.getBanco().getCodigoBanco(), 
						LocalDateUtils.parseDate(colunas[6]), //dataVencimento
						null, 
						null,
						((MovimentoDto)cbMovimento.getSelectedItem()), 
						null,
						null,
						null,
						colunas[7], //SeuNumero 
						colunas[8], //coobrigacao, 
						colunas[9], //nossoNumero, 
						colunas[10], //numeroDocumento, 
						colunas[11], //especie, 
						colunas[12], //termoCessao, 
						colunas[13], //chaveNfe, 
						null,
						null,
						null,
						null, //valorPago, 
						new BigDecimal(colunas[14].replaceAll(",", ".")), //valorTitulo, 
						new BigDecimal(colunas[15].replaceAll(",", ".")), //valorAquisicao, 
						null,
						null);

				cnab.getTitulos().add(titulo.getCopy());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ActionListener getActionCarregarArquivoCsv() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (*csv)", "csv");
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.setFileFilter(filter);
				int ret = fileChooser.showOpenDialog(GerarCnabCsv.this);
				if (ret == JFileChooser.APPROVE_OPTION) {
					fileCsv = fileChooser.getSelectedFile();
					nomeArquivo.setText(fileCsv.getName());
				}

			}
		};
	}
	
	private ActionListener getActionBaixarModeloCsv() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					URL resource = getClass().getClassLoader().getResource("template.csv");
					
					File caminho = new File(path.getText());
					
					if(!caminho.exists()) {
						caminho.mkdirs();
					}
					
					Path p = Paths.get(resource.toURI());
					List<String> readAllLines = Files.readAllLines(p, StandardCharsets.UTF_8);
					
					File modelo = new File(caminho , "template.csv");
										
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(modelo.getPath()), StandardCharsets.UTF_8));
					
					for(String s : readAllLines) {
						bw.write(s + "\r\n");
					}
					
					bw.close();

					JOptionPane.showMessageDialog(null, "O arquivo foi salvo em: " + caminho.getPath());
					
				} catch (Exception e) {
					e.printStackTrace();
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
	
	private void preencherComboMovimento() throws Exception {
		List<MovimentoDto> movimentos = movimentoService.findAllMovimentosAquisicao(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((LayoutEnum)cbLayout.getSelectedItem()).getCdLayout());
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



}
