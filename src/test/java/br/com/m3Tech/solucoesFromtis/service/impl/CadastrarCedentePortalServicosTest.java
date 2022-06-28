package br.com.m3Tech.solucoesFromtis.service.impl;

import java.net.MalformedURLException;

import javax.xml.rpc.ServiceException;

import org.junit.jupiter.api.Test;

public class CadastrarCedentePortalServicosTest {
	
	@Test
	void deve_cadastrar_cedente() throws ServiceException, MalformedURLException {
//		ICadastroAutomatizado cadastroAutomatizado = new CadastrarCedentePortalServicos();
//		
//		ParametrosCadastrosAutomaticos param = new ParametrosCadastrosAutomaticos("http://localhost:8080", 
//				"soulcram", 
//				"p4r4tud0", 
//				new FundoDto(1, null, null, null, null, null),
//				1);
//		
//		cadastroAutomatizado.executar(param);
//		
//		CadastroCedenteAprovadoProxy p = new CadastroCedenteAprovadoProxy(param.getUrl() + "/portal-servicos/servicos/soap/cadastroCedenteAprovado?wsdl",param.getUsuario(),param.getSenha());
//
//
//		
////		CadastroCedenteAprovadoServiceLocator l = new CadastroCedenteAprovadoServiceLocator();
////		
////		CadastroCedenteAprovado portBinding = l.getCadastroCedenteAprovadoPortBinding();
////		
////		BindingProvider bp =(BindingProvider)portBinding;
////		
////		bp.
//		
////		Authenticator myAuth = new Authenticator() 
////		{
////		    @Override
////		    protected PasswordAuthentication getPasswordAuthentication()
////		    {
////		        return new PasswordAuthentication("soulcram", "p4r4tud0".toCharArray());
////		    }
////		};
////
////		Authenticator.setDefault(myAuth);
//		
//
////		 p.
//		 
////		URL url = new URL("http://localhost:8080/portal-servicos/servicos/soap/cadastroCedenteAprovado?wsdl");
////		
////		QName qname = new QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "CadastroCedenteAprovadoService");
////				
////		Service service = Service.create(url, qname);
////		
////		 Map<String, List<String>> credentials = new HashMap<String,List<String>>();
////
////		 credentials.put("username", Collections.singletonList("username"));
////		 credentials.put("password", Collections.singletonList("password"));
////
////		 ((BindingProvider)service).getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, credentials);
////		
////		
////		
////
////		
////				
//		GeradorCpfCnpjRgFake doc = new GeradorCpfCnpjRgFake();
//		
//		GeradorNomeFake geradorNomeFake = new GeradorNomeFake();
//		
//
//		
//		String nomeCedente = geradorNomeFake.gerarNomeCompleto();
//		
//		CadastroCedente c = new CadastroCedente(new FundoCadCedente("95523683000178", "Fromtis Serviços"), //fundo, 
//				"CNPJ", 
//				doc.cnpj(false), 
//				new GeradorNomeFake().gerarNomeCompleto()  ,//nome, 
//				nomeCedente.replaceAll(" ", "") + "@gmail.com", //email, 
//				"1", 
//				"", 
//				"1234", 
//				"1", 
//				"AGRÍCOLA", 
//				"LTDA", //tipodeSociedade, 
//				"123745", //faturamentoAnual, 
//				"1", //conglomeradoEconomico, 
//				"1",//classRisco, 
//				"1",//autorizacao, 
//				"Rua irmã pia",//endereco, 
//				"1050",//numEndereco, 
//				null,//compEndereco, 
//				"04547855",//cep, 
//				"Jaguaré",//bairro, 
//				"sao paulo",//cidade, 
//				"SP",//uf, 
//				"",//dataContrato, 
//				"11954785214",//telefone, 
//				"123454",//limiteCredito, 
//				"",//fax, 
//				"1",//minAprovacao, 
//				"A",//codigoCoobrigacao, 
//				contas,//contaCorrentes, 
//				null,//contatos, 
//				null,//representantes, 
//				null,//avalistas, 
//				null,//partesRelacionadas, 
//				null//instituicaoFinanceira
//				);
//		
//		
//		
//		
//		cs.add(c);
//		
//		try {
//			
//			RetornoCadastroCedente[] cadastroCedenteAprovado = p.cadastroCedenteAprovado(cs.toArray(new CadastroCedente[0]));
//			
//			System.out.println(cadastroCedenteAprovado[0].getMensagemRetorno().getDescricaoRetorno());
//			ErroValidacaoCedCedente[] errosValidacao = cadastroCedenteAprovado[0].getErrosValidacao();
//			for(ErroValidacaoCedCedente e : errosValidacao) {
//				System.out.println(e.getDescricao());
//			}
//			
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		
	}

}
