package br.com.m3Tech.solucoesFromtis.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.m3Tech.solucoesFromtis.dao.Conexao;
import br.com.m3Tech.solucoesFromtis.dto.BancoDto;
import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.dto.RiscoDto;
import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IBancoService;
import br.com.m3Tech.solucoesFromtis.service.ICedenteService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFilaService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.IGeradorCnab;
import br.com.m3Tech.solucoesFromtis.service.IMovimentoService;
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;
import br.com.m3Tech.solucoesFromtis.service.IRiscoService;
import br.com.m3Tech.solucoesFromtis.service.ISacadoService;
import br.com.m3Tech.solucoesFromtis.service.ITipoRecebivelService;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Botao;
import br.com.m3Tech.solucoesFromtis.telas.componentes.ComboBoxBase;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Label;
import br.com.m3Tech.solucoesFromtis.telas.componentes.Text;
import br.com.m3Tech.solucoesFromtis.util.NumericUtils;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;

@Controller
public class SimularImportacaoPortal extends JPanel {

	private static final long serialVersionUID = 1L;

	private JComboBox<Base> cbBase;
	
	private Text quantTitulo;
	private Text quantArquivos;
	
	private Label erro;
	
	private CnabDto cnab;
	
	private final IFundoService fundoService;
	private final IOriginadorService originadorService;
	private final IBancoService bancoService;
	private final IRiscoService riscoService;
	private final ICedenteService cedenteService;
	private final ISacadoService sacadoService;
	private final IMovimentoService movimentoService;
	private final ITipoRecebivelService tipoRecebivelService;
	private final IConfGlobalService confGlobalService;
	private final IFilaService filaService;
	private final IGeradorCnab geradorCnab;

	@Autowired
	public SimularImportacaoPortal(final IFundoService fundoService,
									  final IOriginadorService originadorService,
									  final IBancoService bancoService,
									  final IRiscoService riscoService,
									  final ICedenteService cedenteService,
									  final ISacadoService sacadoService,
									  final IMovimentoService movimentoService,
									  final ITipoRecebivelService tipoRecebivelService,
									  final IConfGlobalService confGlobalService,
									  final IFilaService filaService,
									  final IGeradorCnab geradorCnab) {
		
		this.fundoService = fundoService;
		this.originadorService = originadorService;
		this.bancoService = bancoService;
		this.riscoService = riscoService;
		this.cedenteService = cedenteService;
		this.sacadoService = sacadoService;
		this.movimentoService = movimentoService;
		this.tipoRecebivelService = tipoRecebivelService;
		this.confGlobalService = confGlobalService;
		this.geradorCnab = geradorCnab;
		this.filaService = filaService;
		
		try {

			cnab = new CnabDto();	
			
			this.setBounds(1, 1, ConfigTela.largura, ConfigTela.altura);
			this.setLayout(null);
			this.setBackground(Color.WHITE);
		
			this.add(new Label("Simular Aquisições Via Portal", 10, 10, 500, 20, 14, Color.BLUE));
		
			this.add(new Label("Selecionar Base", 10, 40, 100, 20, 14, Color.BLACK));
			cbBase = ComboBoxBase.novo(110, 40, 350, 20, getActionCbBase());

			
			this.add(new Label("Qtd de titulos Por Arquivo: ", 10, 150, 200, 20, 14, Color.BLACK));
			quantTitulo = new Text(170, 150, 100, 20, true);
			
			this.add(new Label("Qtd de Arquivos: ", 10, 180, 150, 20, 14, Color.BLACK));
			quantArquivos = new Text(170, 180, 100, 20, true);

			this.add(new Botao("Gerar Cnab", 10, 210, 100, 20, getActionGerarCnab()));
			
			erro = new Label("", 10, 250, 1000, 20, 14, Color.RED);
			this.add(erro);
			
			quantTitulo.setText("1");
			quantArquivos.setText("1");
			
			this.add(cbBase);
			this.add(quantTitulo);
			this.add(quantArquivos);
			this.repaint();
		
		} catch (Exception e) {
			e.printStackTrace();
			this.repaint();
		};
		
		
		
	}
	
	private ActionListener getActionCbBase() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
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
					
					if(StringUtils.EmptyOrNull(quantArquivos.getText())) {
						JOptionPane.showMessageDialog(null, "Quantidade de Arquivos é obrigatório","Erro", 0);
						return;
					}
					
					if(Integer.parseInt(quantTitulo.getText()) > 999997) {
						JOptionPane.showMessageDialog(null, "Quantidade de Títulos máxima é 999.997","Erro", 0);
						return;
					}
					
					List<FundoDto> fundos = fundoService.findAllComDataAtual(Conexao.getConnection((Base)cbBase.getSelectedItem()));
					
					if(fundos.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nenhum fundo encontrado com a data atual","Erro", 0);
						return;
					}
					
					Base base = (Base)cbBase.getSelectedItem();
					
					if(base.getUrl().equals("Selecione")) {
						JOptionPane.showMessageDialog(null, "Obrigatório selecionar uma base","Erro", 0);
						return;
					}
					
					Connection con = Conexao.getConnection(base);
					
					String pathRepositorio = confGlobalService.getPathRepositorio(con);
					
					
					

					BancoDto banco = bancoService.findOneByNumBanco(con, "001");
					List<RiscoDto> riscos = riscoService.findAll(con);
					
					Integer quantidadeTitulos = Integer.parseInt(quantTitulo.getText());
					Integer quantidadeArquivos = Integer.parseInt(quantArquivos.getText());
					
					for (int j = 0; j < quantidadeArquivos; j++) {
						
						FundoDto fundoAtual = fundos.get(ValorAleatorioUtil.getValorNumerico(fundos.size()));
						
						StringBuilder path = new StringBuilder();
						
						path.append(pathRepositorio)
							.append(File.separator)
							.append("VALIDACAO_ARQUIVO")
							.append(File.separator)
							.append(fundoAtual.getCodigoIsin())
							.append(File.separator)
							.append(fundoAtual.getDataFundo().format(DateTimeFormatter.ofPattern("dd_MM_yyyy")))
							.append(File.separator)
							.append("AGUARDANDO")
							.append(File.separator);
						
						List<CedenteDto> cedentes = cedenteService.findAll(con, fundoAtual.getIdFundo(), base);
						if(cedentes.isEmpty()) {
							System.out.println("Nenhum Cedente encontrado para o fundo " + fundoAtual.getNomeFundo());
							return;
						}
						List<SacadoDto> sacados = sacadoService.findAll(con, fundoAtual.getIdFundo());
						if(sacados.isEmpty()) {
							System.out.println("Nenhum Sacado encontrado para o fundo " + fundoAtual.getNomeFundo());
							return;
						}
						List<OriginadorDto> originadores = originadorService.findAll(con, fundoAtual.getIdFundo());
						if(originadores.isEmpty()) {
							System.out.println("Nenhum Originador encontrado para o fundo " + fundoAtual.getNomeFundo());
							return;
						}
						List<MovimentoDto> movimentos = movimentoService.findAllMovimentosAquisicao(con, fundoAtual.getLayoutAquisicao());
						if(movimentos.isEmpty()) {
							System.out.println("Nenhum Movimento encontrado para o layout " + fundoAtual.getLayoutAquisicao());
							return;
						}
						
						cnab.setBanco(banco);
						cnab.setDataGravacao(LocalDate.now());
						cnab.setFundo(fundoAtual);
						cnab.setLayout(LayoutEnum.parse(fundoAtual.getLayoutAquisicao()));
						cnab.setOriginador(originadores.get(ValorAleatorioUtil.getValorNumerico(originadores.size())) );
						
						CedenteDto cedenteAtual = cedentes.get(ValorAleatorioUtil.getValorNumerico(cedentes.size()));
						
						for (int i = 0; i < quantidadeTitulos; i++) {
			
							addTitulo(fundoAtual, 
									  cedenteAtual, 
									  sacados.get(ValorAleatorioUtil.getValorNumerico(sacados.size())),
									  movimentos.stream().filter(c -> c.getCdOcorrencia().equals("1")).findFirst().get(),
									  riscos.get(0)
									  );
						}
						
						ConfGlobal confGlobal = confGlobalService.getConfGlobal();
						cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
						confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
						confGlobal.save();
						
						File file = geradorCnab.gerar(cnab, "AQUISICAO_PORTAL", false, path.toString());
						
						filaService.inserirArquivoValidacao(con, filaService.inserirFilaImportacaoArquivo(con, fundoAtual), file, fundoAtual.getLayoutAquisicao());
						
						cnab = new CnabDto();
					}
					
					
			        
			        erro.setText("Cnab Gerado com sucesso");
			       
					
				} catch (Exception e1) {
					erro.setText(e1.getMessage());
					repaint();
				}
				
				
			}
		};
	}
	
	public void addTitulo(FundoDto fundoAtual, CedenteDto cedente, SacadoDto sacado, MovimentoDto movimento, RiscoDto risco) {
		
		try {
	
			TituloDto titulo = new TituloDto();
			
			BigDecimal valor = ValorAleatorioUtil.getValorDecimal(null,null);
	
			titulo.setValorTitulo(valor);
			titulo.setValorAquisicao(NumericUtils.getValorMenos20PorCento(valor));
			titulo.setTaxaJuros(ValorAleatorioUtil.getTaxaDecimal());
			titulo.setEspecie("01");
			titulo.setMovimento(movimento);
			titulo.setCedente(cedente);
			titulo.setSacado(sacado);
			titulo.setDataVencimento(fundoAtual.getDataFundo().plusDays(45));
			titulo.setSeuNumero(ValorAleatorioUtil.getValor(25));
			titulo.setNumeroDocumento(ValorAleatorioUtil.getValor(10));
			titulo.setTermoCessao(ValorAleatorioUtil.getValor(10));
			titulo.setChaveNfe("31190600006388319890559240000000311006164587");
			titulo.setCoobrigacao(cedente.getCoobrigacao().equalsIgnoreCase("N") ? "02" : "01");
			titulo.setRisco(risco);
	
			titulo.setNumBanco(cnab.getBanco().getCodigoBanco());
			cnab.getTitulos().add(titulo.getCopy());
			
			titulo = new TituloDto();
		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Erro", 0);
		}
	}
	

}
