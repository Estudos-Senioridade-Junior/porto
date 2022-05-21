package br.com.porto.api.model;

import java.util.List;

import br.com.porto.domain.model.Relatorio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelatorioModel {

	private List<Relatorio> relatorio;
	private int totalImportacao;
	private int totalExportacao;
	
}
