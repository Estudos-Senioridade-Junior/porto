package br.com.porto.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import br.com.porto.domain.enums.TipoMovimentacao;
import lombok.Data;

@Data
@Entity
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private TipoMovimentacao tipo;
	
	@CreationTimestamp
	private OffsetDateTime horaInicio;
	
	
	private OffsetDateTime horaFim;
	
	@ManyToOne
	@JoinColumn(name = "conteiner_id")
	private Conteiner conteiner;

}
