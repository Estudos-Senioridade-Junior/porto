package br.com.porto.api.model;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotNull;

import br.com.porto.domain.enums.TipoMovimentacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoModel {
	
	private Long id;
	
	
	private TipoMovimentacao tipo;
	
	
	private OffsetDateTime horaInicio;
	
	
	private OffsetDateTime horaFim;
	
	
	private ConteinerModel conteiner;

}
