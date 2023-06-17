package br.com.m3Tech.solucoesFromtis.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.m3Tech.solucoesFromtis.dto.ArquivosPorMinutoDto;
import br.com.m3Tech.solucoesFromtis.dto.StatusDto;
import br.com.m3Tech.solucoesFromtis.dto.TempoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.service.IBancoService;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.ICedenteService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFilaService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.IGeradorCnab;
import br.com.m3Tech.solucoesFromtis.service.IIndexadorService;
import br.com.m3Tech.solucoesFromtis.service.IMovimentoService;
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;
import br.com.m3Tech.solucoesFromtis.service.IRiscoService;
import br.com.m3Tech.solucoesFromtis.service.ISacadoService;
import br.com.m3Tech.solucoesFromtis.service.ITipoRecebivelService;
import io.github.bucket4j.Bucket;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class DesempenhoFilasController implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private static final String VOLTAR = "/pages/cadastros/home.xhtml";


	@Autowired
	private IBaseService baseService;
	@Autowired
	private IFundoService fundoService;
	@Autowired
	private IBancoService bancoService;
	@Autowired
	private IOriginadorService originadorService;
	@Autowired
	private ISacadoService sacadoService;
	@Autowired
	private ICedenteService cedenteService;
	@Autowired
	private IMovimentoService movimentoService;
	@Autowired
	private ITipoRecebivelService tipoRecebivelService;
	@Autowired
	private IConfGlobalService confGlobalService;
	@Autowired
	private  IGeradorCnab geradorCnab;
	@Autowired
	private IIndexadorService indexadorService;
	@Autowired
	private IRiscoService riscoService;
	@Autowired
	private IFilaService filaService;
		
	
	
	private Integer baseSelecionada;

	
	private PieChartModel statusPieModelValidacaoPortal;
	private PieChartModel porMinutoPieModelValidacaoPortal;
	private PieChartModel tempoPieModelValidacaoPortal;
	
	private PieChartModel statusPieModelCarregarCnab;
	private PieChartModel porMinutoPieModelCarregarCnab;
	private PieChartModel tempoPieModelCarregarCnab;
	
	private PieChartModel statusPieModelMovimentacao;
	private PieChartModel porMinutoPieModelMovimentacao;
	private PieChartModel tempoPieModelMovimentacao;

	private LocalDate data;
	
	
	private List<Base> bases;
	
	private Bucket bucket;
	
	@PostConstruct
	public void init() {
		
		bases = baseService.findAll();
		
		data = LocalDate.now();
		
		statusPieModelValidacaoPortal = new PieChartModel();
		porMinutoPieModelValidacaoPortal = new PieChartModel();
		tempoPieModelValidacaoPortal = new PieChartModel();
		
		statusPieModelCarregarCnab = new PieChartModel();
		porMinutoPieModelCarregarCnab = new PieChartModel();
		tempoPieModelCarregarCnab = new PieChartModel();
		
		statusPieModelMovimentacao = new PieChartModel();
		porMinutoPieModelMovimentacao = new PieChartModel();
		tempoPieModelMovimentacao = new PieChartModel();

				
	}

	
	public PieChartModel getStatusPieModelValidacaoPortal() {
		try {
			if(baseSelecionada == null) {
				return statusPieModelValidacaoPortal;
			}
			
			Base base = baseService.findById(baseSelecionada);

			StatusDto statusValidacaoDto = filaService.getStatusArquivosFilaValidacao(base, data);

			if(statusValidacaoDto == null || (statusValidacaoDto.getValidado() == null &&
					statusValidacaoDto.getInvalido() == null &&
					statusValidacaoDto.getEnviado() == null &&
					statusValidacaoDto.getAguardando() == null) ) {
				statusPieModelValidacaoPortal = new PieChartModel();
			}else {
				
				statusPieModelValidacaoPortal = new PieChartModel();
				statusPieModelValidacaoPortal.setTitle("Status Validação");
				statusPieModelValidacaoPortal.setLegendPosition("ne");
				
				statusPieModelValidacaoPortal.getData().put("Validos", statusValidacaoDto.getValidado());
				statusPieModelValidacaoPortal.getData().put("Invalidos", statusValidacaoDto.getInvalido());
				statusPieModelValidacaoPortal.getData().put("Aguardando", statusValidacaoDto.getAguardando());
				statusPieModelValidacaoPortal.getData().put("Enviado", statusValidacaoDto.getEnviado());
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return statusPieModelValidacaoPortal;

	}
	
	
	public PieChartModel getPorMinutoPieModelValidacaoPortal() {
		try {
			if(baseSelecionada == null) {
				return porMinutoPieModelValidacaoPortal;
			}
			
			Base base = baseService.findById(baseSelecionada);

			ArquivosPorMinutoDto dto = filaService.getArquivosPorMinutoFilaValidacao(base, data);

			if(dto == null || (dto.getMinimo() == null && 
					dto.getMaximo() == null &&
					dto.getMedia() == null) ) {
				porMinutoPieModelValidacaoPortal = new PieChartModel();
			}else {
				
				porMinutoPieModelValidacaoPortal = new PieChartModel();
				porMinutoPieModelValidacaoPortal.setTitle("Validados por Minuto");
				porMinutoPieModelValidacaoPortal.setLegendPosition("ne");
			
				porMinutoPieModelValidacaoPortal.getData().put("Mínimo", dto.getMinimo());
				porMinutoPieModelValidacaoPortal.getData().put("Máximo", dto.getMaximo());
				porMinutoPieModelValidacaoPortal.getData().put("Média", dto.getMedia());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return porMinutoPieModelValidacaoPortal;

	}

	
	public PieChartModel getTempoPieModelValidacaoPortal() {
		try {
			if(baseSelecionada == null) {
				return tempoPieModelValidacaoPortal;
			}
			
			Base base = baseService.findById(baseSelecionada);

			List<TempoDto> tempos = filaService.getTemposFilaValidacao(base, data);

			if(tempos.isEmpty()) {
				tempoPieModelValidacaoPortal = new PieChartModel();
			}else {
				tempoPieModelValidacaoPortal = new PieChartModel();
				tempoPieModelValidacaoPortal.setTitle("Tempos de Validação");
				tempoPieModelValidacaoPortal.setLegendPosition("ne");
				
				int i = 0;
				Integer quant = 0;
				for(TempoDto t : tempos) {
					if(i < 7) {
						tempoPieModelValidacaoPortal.getData().put( t.getTempo() + " segundos", t.getQuantidade());
					}else if(i > 6 && i < tempos.size() -1) {
						quant += t.getQuantidade();
					}else if(i == tempos.size() -1) {
						tempoPieModelValidacaoPortal.getData().put( "outros", quant);
						tempoPieModelValidacaoPortal.getData().put( t.getTempo() + " segundos", t.getQuantidade());
					}
					i++;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tempoPieModelValidacaoPortal;

	}
	
	public PieChartModel getStatusPieModelCarregarCnab() {
		try {
			if(baseSelecionada == null) {
				return statusPieModelCarregarCnab;
			}
			
			Base base = baseService.findById(baseSelecionada);

			StatusDto statusValidacaoDto = filaService.getStatusArquivosFilaCarregarCnab(base, data);

			if(statusValidacaoDto == null || (statusValidacaoDto.getValidado() == null &&
					statusValidacaoDto.getInvalido() == null &&
					statusValidacaoDto.getEnviado() == null &&
					statusValidacaoDto.getAguardando() == null) ) {
				statusPieModelCarregarCnab = new PieChartModel();
			}else {
				
				statusPieModelCarregarCnab = new PieChartModel();
				statusPieModelCarregarCnab.setTitle("Status Validação");
				statusPieModelCarregarCnab.setLegendPosition("ne");
				
				statusPieModelCarregarCnab.getData().put("Processando", statusValidacaoDto.getProcessando());
				statusPieModelCarregarCnab.getData().put("Finalizado", statusValidacaoDto.getFinalizado());
				statusPieModelCarregarCnab.getData().put("Aguardando", statusValidacaoDto.getAguardando());
				statusPieModelCarregarCnab.getData().put("Erro", statusValidacaoDto.getErro());
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return statusPieModelCarregarCnab;

	}
	
	
	public PieChartModel getPorMinutoPieModelCarregarCnab() {
		try {
			if(baseSelecionada == null) {
				return porMinutoPieModelCarregarCnab;
			}
			
			Base base = baseService.findById(baseSelecionada);

			ArquivosPorMinutoDto dto = filaService.getArquivosPorMinutoFilaCarregarCnab(base, data);

			if(dto == null || (dto.getMinimo() == null && 
					dto.getMaximo() == null &&
					dto.getMedia() == null) ) {
				porMinutoPieModelCarregarCnab = new PieChartModel();
			}else {
				
				porMinutoPieModelCarregarCnab = new PieChartModel();
				porMinutoPieModelCarregarCnab.setTitle("Validados por Minuto");
				porMinutoPieModelCarregarCnab.setLegendPosition("ne");
			
				porMinutoPieModelCarregarCnab.getData().put("Mínimo", dto.getMinimo());
				porMinutoPieModelCarregarCnab.getData().put("Máximo", dto.getMaximo());
				porMinutoPieModelCarregarCnab.getData().put("Média", dto.getMedia());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return porMinutoPieModelCarregarCnab;

	}

	
	public PieChartModel getTempoPieModelCarregarCnab() {
		try {
			if(baseSelecionada == null) {
				return tempoPieModelCarregarCnab;
			}
			
			Base base = baseService.findById(baseSelecionada);

			List<TempoDto> tempos = filaService.getTemposFilaCarregarCnab(base, data);

			if(tempos.isEmpty()) {
				tempoPieModelCarregarCnab = new PieChartModel();
			}else {
				tempoPieModelCarregarCnab = new PieChartModel();
				tempoPieModelCarregarCnab.setTitle("Tempos de Validação");
				tempoPieModelCarregarCnab.setLegendPosition("ne");
			
				int i = 0;
				Integer quant = 0;
				for(TempoDto t : tempos) {
					if(i < 7) {
						tempoPieModelCarregarCnab.getData().put( t.getTempo() + " segundos", t.getQuantidade());
					}else if(i > 6 && i < tempos.size() -1) {
						quant += t.getQuantidade();
					}else if(i == tempos.size() -1) {
						tempoPieModelCarregarCnab.getData().put( "outros", quant);
						tempoPieModelCarregarCnab.getData().put( t.getTempo() + " segundos", t.getQuantidade());
					}
					i++;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return tempoPieModelCarregarCnab;

	}
	
	public PieChartModel getStatusPieModelMovimentacao() {
		try {
			if(baseSelecionada == null) {
				return statusPieModelMovimentacao;
			}
			
			Base base = baseService.findById(baseSelecionada);

			StatusDto statusValidacaoDto = filaService.getStatusArquivosFilaMovimentacao(base, data);

			if(statusValidacaoDto == null || (statusValidacaoDto.getValidado() == null &&
					statusValidacaoDto.getInvalido() == null &&
					statusValidacaoDto.getEnviado() == null &&
					statusValidacaoDto.getAguardando() == null) ) {
				statusPieModelMovimentacao = new PieChartModel();
			}else {
				
				statusPieModelMovimentacao = new PieChartModel();
				statusPieModelMovimentacao.setTitle("Status Validação");
				statusPieModelMovimentacao.setLegendPosition("ne");
				
				statusPieModelMovimentacao.getData().put("Processando", statusValidacaoDto.getProcessando());
				statusPieModelMovimentacao.getData().put("Finalizado", statusValidacaoDto.getFinalizado());
				statusPieModelMovimentacao.getData().put("Aguardando", statusValidacaoDto.getAguardando());
				statusPieModelMovimentacao.getData().put("Erro", statusValidacaoDto.getErro());
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return statusPieModelMovimentacao;

	}
	
	
	public PieChartModel getPorMinutoPieModelMovimentacao() {
		try {
			if(baseSelecionada == null) {
				return porMinutoPieModelMovimentacao;
			}
			
			Base base = baseService.findById(baseSelecionada);

			ArquivosPorMinutoDto dto = filaService.getArquivosPorMinutoFilaMovimentacao(base, data);

			if(dto == null || (dto.getMinimo() == null && 
					dto.getMaximo() == null &&
					dto.getMedia() == null) ) {
				porMinutoPieModelMovimentacao = new PieChartModel();
			}else {
				
				porMinutoPieModelMovimentacao = new PieChartModel();
				porMinutoPieModelMovimentacao.setTitle("Validados por Minuto");
				porMinutoPieModelMovimentacao.setLegendPosition("ne");
			
				porMinutoPieModelMovimentacao.getData().put("Mínimo", dto.getMinimo());
				porMinutoPieModelMovimentacao.getData().put("Máximo", dto.getMaximo());
				porMinutoPieModelMovimentacao.getData().put("Média", dto.getMedia());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return porMinutoPieModelMovimentacao;

	}

	
	public PieChartModel getTempoPieModelMovimentacao() {
		try {
			if(baseSelecionada == null) {
				return tempoPieModelMovimentacao;
			}
			
			Base base = baseService.findById(baseSelecionada);

			List<TempoDto> tempos = filaService.getTemposFilaMovimentacao(base, data);

			if(tempos.isEmpty()) {
				tempoPieModelMovimentacao = new PieChartModel();
			}else {
				tempoPieModelMovimentacao = new PieChartModel();
				tempoPieModelMovimentacao.setTitle("Tempos de Validação");
				tempoPieModelMovimentacao.setLegendPosition("ne");
			
				int i = 0;
				Integer quant = 0;
				for(TempoDto t : tempos) {
					if(i < 7) {
						tempoPieModelMovimentacao.getData().put( t.getTempo() + " segundos", t.getQuantidade());
					}else if(i > 6 && i < tempos.size() -1) {
						quant += t.getQuantidade();
					}else if(i == tempos.size() -1) {
						tempoPieModelMovimentacao.getData().put( "outros", quant);
						tempoPieModelMovimentacao.getData().put( t.getTempo() + " segundos", t.getQuantidade());
					}
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tempoPieModelMovimentacao;

	}
	
	public void selecionandoBase() {
		System.out.println("Selecionando Base " + baseSelecionada );
		
	}

	
	public void atualizarBases() {
		System.out.println("Atualizar Base");
		
		bases = baseService.findAll();
	}

	
	public String voltar() {
		return VOLTAR;
	}

}
