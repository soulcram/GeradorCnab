package br.com.m3Tech.solucoesFromtis.dto;

import br.com.m3Tech.solucoesFromtis.enuns.TipoMovimentacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportacaoSimuladaDto {

	private Boolean selecionado;
	private TipoMovimentacao tipoMovimentacao;
	private Integer quantArquivos;
	private Integer quantTitulosPorArquivo;
	

}
