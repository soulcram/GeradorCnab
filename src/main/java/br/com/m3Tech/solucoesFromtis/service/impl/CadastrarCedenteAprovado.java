package br.com.m3Tech.solucoesFromtis.service.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedente;
import br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteAprovadoProxy;
import br.com.fromtis.fidc.portal.servicos.consulta.soap.ContaCorrenteCadCedente;
import br.com.fromtis.fidc.portal.servicos.consulta.soap.FundoCadCedente;
import br.com.fromtis.fidc.portal.servicos.consulta.soap.RepresentateCadCedente;
import br.com.fromtis.fidc.portal.servicos.consulta.soap.RetornoCadastroCedente;
import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;
import br.com.m3Tech.solucoesFromtis.service.ICadastroAutomatizado;

public class CadastrarCedenteAprovado implements ICadastroAutomatizado {

	@Override
	public String executar(ParametrosCadastrosAutomaticos parametros) {
		
		try {
		
			String endpoint = "/portal-servicos/servicos/soap/cadastroCedenteAprovado?wsdl";
			
			CadastroCedenteAprovadoProxy service = new CadastroCedenteAprovadoProxy(parametros.getUrl() + endpoint, parametros.getUsuario(), parametros.getSenha());
	
			GeradorNomeFake nomeFake = new GeradorNomeFake();
			GeradorCpfCnpjRgFake gerarDoc = new GeradorCpfCnpjRgFake();
	
			
			
			List<CadastroCedente> cs = new ArrayList<>();
			
			ContaCorrenteCadCedente cc = new ContaCorrenteCadCedente("001", "0001", "32145", "CC", "SIM", "NAO");
			ContaCorrenteCadCedente[] contas = {cc};
	
			for (int i = 0; i < parametros.getRepeticoes(); i++) {
				
				
				
				String nomeRepresentante = nomeFake.gerarNomeCompleto();
				RepresentateCadCedente representante = new RepresentateCadCedente(nomeRepresentante, 
						gerarDoc.cpf(false) ,//cpfRepresentante, 
						nomeRepresentante.replaceAll(" ", "") + "@gmail.com",//emailRepresentante, 
						 "11987453256",//telefoneRepresentante, 
						 "SIM" ,//assinaIsoladamente, 
						 "SIM" ,//emiteDuplicata, 
						 "SIM" ,//assinaPorEndosso, 
						 "SIM" //assinaTermoCessao
						 );
				RepresentateCadCedente[] representantes = {representante};
				
				String nomeCedente = nomeFake.gerarNomeCompleto();
				CadastroCedente cedente = new CadastroCedente(new FundoCadCedente("95523683000178", "Fromtis Serviços"), //fundo, 
						"CNPJ", 
						gerarDoc.cnpj(false), 
						nomeCedente  ,//nome, 
						nomeCedente.replaceAll(" ", "") + "@gmail.com", //email, 
						"1", 
						"", 
						"1234", 
						"1", 
						"AGRÍCOLA", 
						"LTDA", //tipodeSociedade, 
						"123745", //faturamentoAnual, 
						"1", //conglomeradoEconomico, 
						"1",//classRisco, 
						"1",//autorizacao, 
						"Rua irmã pia",//endereco, 
						"1050",//numEndereco, 
						null,//compEndereco, 
						"04547855",//cep, 
						"Jaguaré",//bairro, 
						"sao paulo",//cidade, 
						"SP",//uf, 
						"",//dataContrato, 
						"11954785214",//telefone, 
						"123454",//limiteCredito, 
						"",//fax, 
						"1",//minAprovacao, 
						"A",//codigoCoobrigacao, 
						contas,//contaCorrentes, 
						null,//contatos, 
						representantes,//representantes, 
						null,//avalistas, 
						null,//partesRelacionadas, 
						null//instituicaoFinanceira
						);
				
				cs.add(cedente);
	
			}
		
		
			RetornoCadastroCedente[] cadastroCedenteAprovado = service.cadastroCedenteAprovado(cs.toArray(new CadastroCedente[0]));
			
			return cadastroCedenteAprovado[0].getMensagemRetorno().getDescricaoRetorno();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";

	}

}
