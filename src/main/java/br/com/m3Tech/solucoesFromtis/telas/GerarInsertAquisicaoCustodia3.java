package br.com.m3Tech.solucoesFromtis.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.m3Tech.solucoesFromtis.beanio.CnabDetail;
import br.com.m3Tech.solucoesFromtis.beanio.CnabHeader;
import br.com.m3Tech.solucoesFromtis.beanio.CnabTrailler;
import br.com.m3Tech.solucoesFromtis.dao.Conexao;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.service.IArquivoCustodia3Service;
import br.com.m3Tech.solucoesFromtis.service.IBancoService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Botao;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxBase;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxFundoDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxOriginadorDto;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Label;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Text;

@Controller
public class GerarInsertAquisicaoCustodia3 extends JPanel {

private static final long serialVersionUID = 1L;


	@Autowired
	private IArquivoCustodia3Service  arquivoCustodia3Service;
	
	private JComboBox<Base> cbBase;
	private JComboBox<FundoDto> cbFundo;
	private JComboBox<OriginadorDto> cbOriginador;
	private JComboBox<MovimentoDto> cbMovimento;
	
	private Text nomeArquivo;
	
	
	private Label erro;
	
	private File fileCnab;
	
	
	private final IFundoService fundoService;
	private final IOriginadorService originadorService;
	private final IBancoService bancoService;

	@Autowired
	public GerarInsertAquisicaoCustodia3(final IFundoService fundoService,
			final IOriginadorService originadorService,
			final IBancoService bancoService) {

		this.fundoService = fundoService;
		this.originadorService = originadorService;
		this.bancoService = bancoService;
		
		try {
			
			this.setBounds(1, 1, ConfigTela.largura, ConfigTela.altura);
			this.setLayout(null);
			this.setBackground(Color.WHITE);
		
			this.add(new Label("Inserir Cnab na TB_IM_STG_ARQ do custodia 3", 10, 10, 500, 20, 14, Color.BLUE));
		
			this.add(new Label("Selecionar Base", 10, 40, 100, 20, 14, Color.BLACK));
			cbBase = ComboBoxBase.novo(110, 40, 350, 20, getActionCbBase());
			
			this.add(new Label("Fundo: ", 10, 70, 100, 20, 14, Color.BLACK));
			cbFundo = ComboBoxFundoDto.novo(110, 70, 350, 20, getActionCbFundoDto());
			
			this.add(new Label("Originador: ", 10, 100, 100, 20, 14, Color.BLACK));
			cbOriginador = ComboBoxOriginadorDto.novo(110, 100, 350, 20);

			
			this.add(new Label("Arquivo: ", 10, 130, 100, 20, 14, Color.BLACK));
			nomeArquivo = new Text(120, 130, 450, 20, true);
			this.add(new Botao("Carregar arquivo", 580, 130, 150, 20, getActionCarregarArquivo()));

			this.add(new Botao("Inserir Cnab", 10, 160, 150, 20, getActionInserirCnabNaBase()));
			
			erro = new Label("", 10, 200, 1000, 20, 14, Color.RED);
			this.add(erro);

			if(!"Selecione".equals(((Base)cbBase.getSelectedItem()).getUrl())) {
				preencherComboFundos();
			}
		
			
			
			this.add(cbBase);
			this.add(cbFundo);
			this.add(cbOriginador);
			this.add(cbMovimento);
			this.add(nomeArquivo);
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
//					preencherComboTipoRecebivel();
				} catch (Exception e1) {
					erro.setText(e1.getMessage());
				}
				
				
			}
		};
	}
	
	private ActionListener getActionInserirCnabNaBase() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Path p = Paths.get(fileCnab.toURI());
					List<String> readAllLines = Files.readAllLines(p, StandardCharsets.UTF_8);
					
					
					arquivoCustodia3Service.processar(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto)cbFundo.getSelectedItem()),  fileCnab.getName(), readAllLines);
					

					System.out.println("Cnab inserido na base com sucesso");
					JOptionPane.showMessageDialog(null, "Cnab inserido na base com sucesso");

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}

		};
	}

	private void importarSemValidacao() {
		try {
			
			StreamFactory factory = StreamFactory.newInstance();
	        
			factory.loadResource("beanio.xml");
			
			BeanReader reader = factory.createReader("cnab500RemessaCredSystem",	
					new InputStreamReader(new ByteArrayInputStream( Files.readAllBytes(fileCnab.toPath()))));
			
			Object record;
			
			CnabHeader header = new CnabHeader();
			List<CnabDetail> details = new ArrayList<>();
			CnabTrailler trailler = new CnabTrailler();
			
			while ((record =  reader.read()) != null) {
				if(record instanceof CnabHeader) {
					header = (CnabHeader) record;
				}else if(record instanceof CnabDetail) {
					details.add((CnabDetail)record);
				}else if(record instanceof CnabTrailler) {
					trailler = (CnabTrailler)record;
				}
			}
			
			arquivoCustodia3Service.processar(Conexao.getConnection((Base)cbBase.getSelectedItem()), ((FundoDto)cbFundo.getSelectedItem()), ((OriginadorDto)cbOriginador.getSelectedItem()), fileCnab.getName(), header, details, trailler);
			

			System.out.println("Cnab inserido na base com sucesso");

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	
	private ActionListener getActionCarregarArquivo() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (*csv)", "csv");
				fileChooser.setAcceptAllFileFilterUsed(false);
//				fileChooser.setFileFilter(filter);
				int ret = fileChooser.showOpenDialog(GerarInsertAquisicaoCustodia3.this);
				if (ret == JFileChooser.APPROVE_OPTION) {
					fileCnab = fileChooser.getSelectedFile();
					nomeArquivo.setText(fileCnab.getName());
					repaintTela();
				}
				
				

			}
		};
	}
	
	private void repaintTela() {
		this.repaint();
	}
	
	private ActionListener getActionCbFundoDto() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(cbFundo != null && cbFundo.getItemCount() > 0) {
					try {
					
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





}
