package br.com.m3Tech.solucoesFromtis.controller;

import java.io.Serializable;
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

import br.com.m3Tech.solucoesFromtis.dto.ImportacaoSimuladaDto;
import br.com.m3Tech.solucoesFromtis.enuns.TipoMovimentacao;
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
import br.com.m3Tech.solucoesFromtis.service.ISimularImportacaoPortal;
import br.com.m3Tech.solucoesFromtis.service.ITipoRecebivelService;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class SimularImportacaoCnabPortalController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SimularImportacaoCnabPortalController.class);

	private static final String VOLTAR = "/pages/cadastros/home.xhtml";

	@Autowired
	private List<? extends ISimularImportacaoPortal> processes;
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
		

	private List<ImportacaoSimuladaDto> simulacoes;
	
	
	@PostConstruct
	public void init() {
		simulacoes = iniciarSimulacoes();
	}

	public void gerar() {
		try {
			
			for(ImportacaoSimuladaDto item : simulacoes) {
				if(item.getSelecionado()) {
					try {
						for(ISimularImportacaoPortal executor: processes) {
							executor.gerar(item);
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
	
	
	public List<ImportacaoSimuladaDto> iniciarSimulacoes() {
		
		List<ImportacaoSimuladaDto>  retorno = Lists.newArrayList();
		
		for(TipoMovimentacao item : TipoMovimentacao.values()) {
			retorno.add(new ImportacaoSimuladaDto(false, item, 1, 1));
		}
		
		return retorno;
	}
	
	public String voltar() {
		return VOLTAR;
	}

}
