package br.com.porto.api.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConteinerIdInput {
	
	@NotNull
	private Long id;

}
