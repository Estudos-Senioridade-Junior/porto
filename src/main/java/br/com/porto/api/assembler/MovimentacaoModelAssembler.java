package br.com.porto.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.porto.api.model.MovimentacaoModel;
import br.com.porto.domain.model.Movimentacao;

@Component
public class MovimentacaoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public MovimentacaoModel toModel (Movimentacao movimentacao) {
		return modelMapper
				.map(movimentacao, MovimentacaoModel.class);
	}
	
	public List<MovimentacaoModel> toCollectModel (List<Movimentacao> movimentacoes){
		return movimentacoes.stream()
				.map(movimentacao -> toModel(movimentacao))
				.collect(Collectors.toList());
	}
}
