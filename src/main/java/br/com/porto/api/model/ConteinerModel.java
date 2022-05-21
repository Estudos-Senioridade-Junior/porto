package br.com.porto.api.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.porto.domain.enums.Categoria;
import br.com.porto.domain.enums.Status;
import br.com.porto.domain.enums.TipoConteiner;
import br.com.porto.domain.model.Cliente;
import br.com.porto.domain.model.Movimentacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConteinerModel {

	
	private Long id;
	
	private String identificacao;
	
	private ClienteModel cliente;
	
	private TipoConteiner tipo;
	
	private Categoria categoria;
	
	private Status status;
	
	@JsonIgnore
	private List<Movimentacao> movimentacoes = new ArrayList<>();
}
