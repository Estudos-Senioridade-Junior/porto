package br.com.porto.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.porto.core.validation.IdentificacaoConteiner;
import br.com.porto.domain.enums.Categoria;
import br.com.porto.domain.enums.Status;
import br.com.porto.domain.enums.TipoConteiner;
import lombok.Data;

@Entity
@Data
public class Conteiner {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@IdentificacaoConteiner
	private String identificacao;
	
	@Valid
	@ManyToOne
	@JoinColumn(name ="cliente_id")
	@NotNull
	private Cliente cliente;
	
	@NotNull
	private TipoConteiner tipo;
	
	@NotNull
	private Categoria categoria;
	
	@NotNull
	private Status status;
	
	@OneToMany(mappedBy = "conteiner")
	private List<Movimentacao> movimentacoes = new ArrayList<>();
	
	
}
