package br.com.porto.api.model;

import java.time.OffsetDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import br.com.porto.domain.enums.TipoMovimentacao;
import br.com.porto.domain.model.Conteiner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoInput {

	
	@NotNull
	private TipoMovimentacao tipo;
	
	@Valid
	private ConteinerIdInput conteiner;
	
}
