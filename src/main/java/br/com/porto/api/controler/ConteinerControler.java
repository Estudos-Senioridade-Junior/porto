package br.com.porto.api.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.porto.api.assembler.ConteinerModelAssembler;
import br.com.porto.api.assembler.ConteinerModelDisassembler;
import br.com.porto.api.model.ConteinerInput;
import br.com.porto.api.model.ConteinerModel;
import br.com.porto.domain.model.Conteiner;
import br.com.porto.domain.service.CadastroConteinerService;

@RestController
@RequestMapping("/conteiner")
public class ConteinerControler {
	
	@Autowired
	private CadastroConteinerService cadastroConteinerService;
	
	@Autowired
	private ConteinerModelAssembler conteinerModelAssembler;
	
	@Autowired
	private ConteinerModelDisassembler conteinerModelDisassembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ConteinerModel salvar (@RequestBody ConteinerInput conteinerInput) {
		Conteiner conteiner = conteinerModelDisassembler
				.toObjectModel(conteinerInput);
		
		return conteinerModelAssembler
				.toModel(cadastroConteinerService.salvar(conteiner));
	}
	
	@GetMapping("/{conteinerId}")
	@ResponseStatus(HttpStatus.OK)
	public ConteinerModel buscar (@PathVariable ("conteinerId") Long conteinerId) {
		return conteinerModelAssembler
				.toModel(cadastroConteinerService.buscar(conteinerId));
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ConteinerModel> listar (){
		return conteinerModelAssembler
				.toCollectModel(cadastroConteinerService.listar());
	}
	
	@PutMapping("{conteinerId}")
	@ResponseStatus(HttpStatus.OK)
	public ConteinerModel atualiza (@RequestBody ConteinerInput conteinerInput, 
			@PathVariable ("conteinerId") Long conteinerId) {
		Conteiner conteiner = cadastroConteinerService.buscarOuFalhar(conteinerId);
		conteinerModelDisassembler.copyToObject(conteinerInput, conteiner);
		return conteinerModelAssembler
				.toModel(cadastroConteinerService.salvar(conteiner));
	}
	
	@DeleteMapping("{conteinerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover (@PathVariable ("conteinerId") Long id) {
		cadastroConteinerService.remover(id);
	}
	
	
	            
	

}
