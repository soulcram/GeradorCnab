package br.com.m3Tech.solucoesFromtis.beanio;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("DETAIL")
public class RegistroRetornoB3Detail {
	// D
	private String tipoRegistro;

	// 000000002
	private String sequencia;

	private String chassi;

	// 1 = Remarcado
	// 2 = Normal
	private Integer tipoChassi;

	private String ufLicenciamento;
	private Integer anoFabricacao;
	private Integer anoModelo;

	// 01 = Arrendamento Mercantil / Leasing
	// 02 = Reserva de Domínio / Outros
	// 03 = Alienação Fiduciária
	// 09 = Penhor
	private String tipoGravame;

	// 1 = Ativo para o próprio Participante (está com restrição financeira em
	// aberto)
	// 2 = Ativo para outro Participante (está com restrição financeiro em aberto)
	// 3 = Não ativo para o próprio Participante (não possui mais restrição
	// financeira, porém já foi financiado no passado)
	// 4 = Não ativo para outro Participante (não possui mais restrição financeira,
	// porém já foi financiado no passado)
	// 5 = Chassi não encontrado (não consta informação na base do SNG)
	// NOTA: os itens 3 e 4 desse campo se referem às restrições financeiras
	// incluídas e excluídas (por baixa ou cancelamento) no mesmo período
	private Integer statusGravame;
	private LocalDate dataInclusaoGravame;

	// D = Incluído pelo Detran
	// F = Incluído pelo Participante
	private String informanteGravame;

	private String numeroGravame;

	// 1 – CPF
	// 2 - CNPJ
	private Integer tipoDocumentoFinanciado;
}
