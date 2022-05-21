package br.com.porto.domain.model;

import br.com.porto.domain.enums.TipoMovimentacao;

public interface Relatorio {
	
	Long getCliente();
	TipoMovimentacao getTipo();
	int getTotal();
}
