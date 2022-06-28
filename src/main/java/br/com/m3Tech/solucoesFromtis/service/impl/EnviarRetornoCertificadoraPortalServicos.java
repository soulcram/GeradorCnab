package br.com.m3Tech.solucoesFromtis.service.impl;

import java.rmi.RemoteException;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fromtis.fidc.portal.webservices.Avalista;
import br.com.fromtis.fidc.portal.webservices.CedenteRetorno;
import br.com.fromtis.fidc.portal.webservices.Consultoria;
import br.com.fromtis.fidc.portal.webservices.Custodiante;
import br.com.fromtis.fidc.portal.webservices.Erro;
import br.com.fromtis.fidc.portal.webservices.Fundo;
import br.com.fromtis.fidc.portal.webservices.Operacao;
import br.com.fromtis.fidc.portal.webservices.Parte;
import br.com.fromtis.fidc.portal.webservices.ParteRepresentante;
import br.com.fromtis.fidc.portal.webservices.RetornoCertificacaoDigitalProxy;
import br.com.fromtis.fidc.portal.webservices.RetornoCertificadoDigital;
import br.com.fromtis.fidc.portal.webservices.RetornoProcessamento;
import br.com.fromtis.fidc.portal.webservices.Sacado;
import br.com.fromtis.fidc.portal.webservices.Testemunha;
import br.com.fromtis.fidc.portal.webservices.TipoAssinaturaEnum;
import br.com.fromtis.fidc.portal.webservices.TipoPessoaEnum;
import br.com.fromtis.fidc.portal.webservices.TipoTituloEnum;
import br.com.fromtis.fidc.portal.webservices.TituloRetorno;
import br.com.m3Tech.solucoesFromtis.dto.DadosRetornoCertificadoDigitalDto;
import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;
import br.com.m3Tech.solucoesFromtis.util.FileUtil;

public class EnviarRetornoCertificadoraPortalServicos  {

	private static final Logger logger  = LoggerFactory.getLogger(EnviarRetornoCertificadoraPortalServicos.class);
	
	public String executar(ParametrosCadastrosAutomaticos parametros, DadosRetornoCertificadoDigitalDto dadosRetornoDto ) {
		
		try {
			
			logger.info("Iniciando Aprovação Consultoria | {}", dadosRetornoDto);
		
			String endpoint = "/portal/retornoCertificacaoDigital?wsdl";
			
			logger.info("Criando Serviço | {} ", parametros.getUrl() + endpoint);
			RetornoCertificacaoDigitalProxy service = new RetornoCertificacaoDigitalProxy(parametros.getUrl() + endpoint, parametros.getUsuario(), parametros.getSenha());
			
			Erro[] erros = {new Erro("1", "1")};
			
			RetornoProcessamento retornoProcessamento = new RetornoProcessamento("00", erros);
	        Operacao operacao = new Operacao();
	        Custodiante custodiante = new Custodiante();
	        Fundo fundo = new Fundo();
	        Consultoria consultoria = new Consultoria();
	        CedenteRetorno cedente = new CedenteRetorno();
			
			FileUtil.copyProperties(dadosRetornoDto.getOperacao(), operacao);
			
			FileUtil.copyProperties(dadosRetornoDto.getCustodiante(), custodiante);
			
			FileUtil.copyProperties(dadosRetornoDto.getFundo(), fundo);
			
			List<Parte> partes = dadosRetornoDto.getFundo().getPartes().stream().map(p -> new Parte(p.getNome(), p.getCpf(), p.getEmail(), p.getPapel(), TipoAssinaturaEnum.fromString(p.getTipoAssinaturaEnum()))).collect(Collectors.toList());
			fundo.setPartes(partes.toArray(new Parte[partes.size()]));
			
			List<Testemunha> testemunhas = dadosRetornoDto.getFundo().getTestemunhas().stream().map(p -> new Testemunha(p.getNome(), p.getCpf(), p.getEmail())).collect(Collectors.toList());
			fundo.setTestemunhas(testemunhas.toArray(new Testemunha[testemunhas.size()]));
			
			FileUtil.copyProperties(dadosRetornoDto.getConsultoria(), consultoria);
			
			List<Parte> partesConsultoria = dadosRetornoDto.getConsultoria().getPartes().stream().map(p -> new Parte(p.getNome(), p.getCpf(), p.getEmail(), p.getPapel(), TipoAssinaturaEnum.fromString(p.getTipoAssinaturaEnum()))).collect(Collectors.toList());
			consultoria.setPartes(partesConsultoria.toArray(new Parte[partesConsultoria.size()]));
			
			FileUtil.copyProperties(dadosRetornoDto.getCedente(), cedente);
			
			List<ParteRepresentante> partesRepresentante = dadosRetornoDto.getCedente().getPartes().stream().map(p -> new ParteRepresentante(p.getNome(), p.getCpf(), p.getEmail(), p.getPapel(),p.isAssinaIsoladamente(),p.isEmiteDuplicata(),p.isAssinaPorEndosso(),p.isAssinaTermoCessao())).collect(Collectors.toList());
			cedente.setPartes(partesRepresentante.toArray(new ParteRepresentante[partesRepresentante.size()]));

			List<Avalista> avalistas = dadosRetornoDto.getCedente().getAvalistas().stream().map(p -> new Avalista(p.getNome(), p.getCpf(), p.getEmail(), p.getPapel())).collect(Collectors.toList());
			cedente.setAvalistas(avalistas.toArray(new Avalista[avalistas.size()]));

			List<TituloRetorno> titulos = dadosRetornoDto.getCedente().getTitulos().stream().map(p -> {
				
				ParteRepresentante[] parteSacado = {};
				
				Sacado sacado = new Sacado(p.getSacado().getNomeSacado(), p.getSacado().getDocSacado(), p.getSacado().getEndereco(), p.getSacado().getNumero(), 
						p.getSacado().getComplemento(), p.getSacado().getEmail(), TipoPessoaEnum.fromString(p.getSacado().getTipoPessoaEnum()), p.getSacado().getBairro(), p.getSacado().getCidade(), p.getSacado().getUF(), p.getSacado().getCep(), p.getSacado().getPais(), parteSacado);
				
				Calendar calendarEmissao = Calendar.getInstance();
				calendarEmissao.setTime(Date.from(p.getDataDeEmissaoDuplicata().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				
				Calendar calendarVencimento = Calendar.getInstance();
				calendarVencimento.setTime(Date.from(p.getDataDeVencimentoDuplicata().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				
				return new TituloRetorno(p.getNumero(), p.getNumeroControleParticipante(), calendarEmissao, 
						calendarVencimento, p.getValorBruto(), p.getValorLiquido(), p.getNumeroDaNotaFiscal(), p.getSerieDaNotaFiscal(), p.getNomePkcs7(), p.getChaveNfe(), sacado, false, TipoTituloEnum.fromString(p.getTipoTituloEnum()));
			}).collect(Collectors.toList());
				
			
			cedente.setTitulos(titulos.toArray(new TituloRetorno[titulos.size()]));

			List<TituloRetorno> titulosRecompra = dadosRetornoDto.getCedente().getTitulos().stream().map(p -> {
				
				Sacado sacado = new Sacado();
				
				FileUtil.copyProperties(p.getSacado(), sacado);
				
				Calendar calendarEmissao = Calendar.getInstance();
				calendarEmissao.setTime(Date.from(p.getDataDeEmissaoDuplicata().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				
				Calendar calendarVencimento = Calendar.getInstance();
				calendarVencimento.setTime(Date.from(p.getDataDeVencimentoDuplicata().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				
				return new TituloRetorno(p.getNumero(), p.getNumeroControleParticipante(), calendarEmissao, 
						calendarVencimento, p.getValorBruto(), p.getValorLiquido(), p.getNumeroDaNotaFiscal(), p.getSerieDaNotaFiscal(), p.getNomePkcs7(), p.getChaveNfe(), sacado, false, TipoTituloEnum.fromString(p.getTipoTituloEnum()));
			}).collect(Collectors.toList());
			cedente.setTitulosRecompra(titulosRecompra.toArray(new TituloRetorno[titulosRecompra.size()]));
		
			RetornoCertificadoDigital retornoAquisicao = new RetornoCertificadoDigital(retornoProcessamento, 
					operacao, 
					custodiante, 
					fundo, 
					consultoria, 
					cedente, 
					dadosRetornoDto.getId());
			
			RetornoProcessamento retorno = service.retorno(retornoAquisicao);
			logger.info("Retorno | {} ", retorno.getStatus());
						
			if(retorno.getErros().length > 0) {
				
				for(Erro e : retorno.getErros()) {
					logger.info("Erro retorno Certificadora | Codigo: {} | Mensagem: {} ", e.getCodigo(), e.getMensagem());
				}
				
			}
			
		
			return retorno.getStatus();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return "";

	}

}
