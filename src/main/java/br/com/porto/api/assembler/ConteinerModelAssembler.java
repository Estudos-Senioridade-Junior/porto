package br.com.porto.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.porto.api.model.ConteinerInput;
import br.com.porto.api.model.ConteinerModel;
import br.com.porto.domain.model.Conteiner;

@Component
public class ConteinerModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	
	public ConteinerModel toModel (Conteiner conteiner) {
		return modelMapper
				.map(conteiner, ConteinerModel.class);
	}
	
	public List<ConteinerModel> toCollectModel (List<Conteiner> conteineres){
		return conteineres.stream()
				.map(conteiner -> toModel(conteiner))
				.collect(Collectors.toList());
	}
}
