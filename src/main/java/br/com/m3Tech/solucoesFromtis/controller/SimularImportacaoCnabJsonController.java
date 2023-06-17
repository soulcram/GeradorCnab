package br.com.m3Tech.solucoesFromtis.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.m3Tech.solucoesFromtis.certificadora.utils.ControllerUtils;
import br.com.m3Tech.solucoesFromtis.dto.ArquivoJSONDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.ImportacaoSimuladaDto;
import br.com.m3Tech.solucoesFromtis.dto.OperacaoAzumi;
import br.com.m3Tech.solucoesFromtis.enuns.TipoMovimentacao;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
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
import br.com.m3Tech.solucoesFromtis.service.ISimularImportacaoJson;
import br.com.m3Tech.solucoesFromtis.service.ISimularImportacaoPortal;
import br.com.m3Tech.solucoesFromtis.service.ITipoRecebivelService;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class SimularImportacaoCnabJsonController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SimularImportacaoCnabJsonController.class);

	private static final String VOLTAR = "/pages/cadastros/home.xhtml";

	@Autowired
	private List<? extends ISimularImportacaoJson> processes;
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
		

	private List<FundoDto> fundos;
	private List<ImportacaoSimuladaDto> simulacoes;
	private Base base;
	private ConfGlobal confGlobal;
	private Integer fundoSelecionado;
	
	@PostConstruct
	public void init() {
		this.confGlobal = confGlobalService.getConfGlobal();
		fundos = new ArrayList<>();
		base = baseService.findById(confGlobal.getIdBasePadrao());
		simulacoes = iniciarSimulacoes();
		atualizarFundos();
	}

	public void gerar(ImportacaoSimuladaDto item) {
		try {
			if(fundoSelecionado == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Obrigatório Selecionar um Fundo "));
				return;
			}
			for(ImportacaoSimuladaDto impl : simulacoes) {
				if(impl.getTipoMovimentacao().equals(item.getTipoMovimentacao())) {
					try {
						for(ISimularImportacaoJson executor: processes) {
							executor.gerar(impl, fundoSelecionado);
						}
					}catch(Exception e) {
						logger.error("Erro na " + item.getTipoMovimentacao().getNome() + ". Msg: " + e.getMessage());
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Erro na " + item.getTipoMovimentacao().getNome() + ". Msg: " + e.getMessage()));
					}
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void download(ImportacaoSimuladaDto item) {
		if(fundoSelecionado == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Obrigatório Selecionar um Fundo "));
			return;
		}
		
		try {
			
			for(ImportacaoSimuladaDto impl : simulacoes) {
				if(impl.getTipoMovimentacao().equals(item.getTipoMovimentacao())) {
					try {
						for(ISimularImportacaoJson executor: processes) {
							ArquivoJSONDto arquivoJSONDto = executor.gerarUnico(item, fundoSelecionado);
							
							Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
							
							final String json = gson.toJson(arquivoJSONDto);
							
							ControllerUtils.downloadAsAttachment(
									arquivoJSONDto.getNomeArquivo(),
									new ByteArrayInputStream(json.getBytes()));
						}
					}catch(Exception e) {
						logger.error("Erro na " + item.getTipoMovimentacao().getNome() + ". Msg: " + e.getMessage());
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Erro na " + item.getTipoMovimentacao().getNome() + ". Msg: " + e.getMessage()));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ControllerUtils.addMessageInfo("Ocorreu um erro inesperado.");
		}
	}
	
	
	public List<ImportacaoSimuladaDto> iniciarSimulacoes() {
		
		List<ImportacaoSimuladaDto>  retorno = Lists.newArrayList();
		
		for(TipoMovimentacao item : TipoMovimentacao.values()) {
			retorno.add(new ImportacaoSimuladaDto(false, item, 1, 1));
		}
		
		return retorno;
	}
	
	public void atualizarFundos() {
		
		try {

			fundos = fundoService.findAll(base);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	public String voltar() {
		return VOLTAR;
	}

}
