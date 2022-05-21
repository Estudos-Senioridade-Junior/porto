package br.com.porto.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {

	@NotBlank
	private String nome;
}
