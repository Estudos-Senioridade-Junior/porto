package br.com.porto.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.porto.api.model.ConteinerInput;
import br.com.porto.domain.model.Cliente;
import br.com.porto.domain.model.Conteiner;

@Component
public class ConteinerModelDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	
	public Conteiner toObjectModel (ConteinerInput conteinerInput) {
		return modelMapper
				.map(conteinerInput, Conteiner.class);
	}
	
	public void copyToObject (ConteinerInput conteinerInput, Conteiner conteiner) {
		conteiner.setCliente(new Cliente());
		modelMapper.map(conteinerInput, conteiner);
	}
}
