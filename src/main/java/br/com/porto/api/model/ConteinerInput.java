package br.com.porto.api.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.porto.core.validation.IdentificacaoConteiner;
import br.com.porto.domain.enums.Categoria;
import br.com.porto.domain.enums.Status;
import br.com.porto.domain.enums.TipoConteiner;
import br.com.porto.domain.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConteinerInput {


	@IdentificacaoConteiner
	private String identificacao;
	
	@Valid
	private ClienteIdInput cliente;
	
	@NotNull
	private TipoConteiner tipo;
	
	@NotNull
	private Categoria categoria;
	
	@NotNull
	private Status status;
	
}
