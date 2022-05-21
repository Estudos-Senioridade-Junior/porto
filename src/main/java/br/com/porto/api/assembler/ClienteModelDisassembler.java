package br.com.porto.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.porto.api.model.ClienteInput;
import br.com.porto.domain.model.Cliente;

@Component
public class ClienteModelDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cliente toObjectModel (ClienteInput clienteInput) {
		return modelMapper
				.map(clienteInput, Cliente.class);
	}
	
	public void copyToObject (ClienteInput clienteInput, Cliente cliente) {
		modelMapper
		.map(clienteInput, cliente);
	}
}
