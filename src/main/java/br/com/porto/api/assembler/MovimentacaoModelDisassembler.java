package br.com.porto.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.porto.api.model.MovimentacaoInput;
import br.com.porto.domain.model.Conteiner;
import br.com.porto.domain.model.Movimentacao;

@Component
public class MovimentacaoModelDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Movimentacao toObjectModel (MovimentacaoInput movimentacaoInput) {
		return modelMapper
				.map(movimentacaoInput, Movimentacao.class);
	}
	
	public void copyToObject (MovimentacaoInput movimentacaoInput, Movimentacao movimentacao) {
		movimentacao.setConteiner(new Conteiner());
		modelMapper.map(movimentacaoInput, movimentacao);
	}
}
