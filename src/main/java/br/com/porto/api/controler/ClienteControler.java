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

import br.com.porto.api.assembler.ClienteModelAssembler;
import br.com.porto.api.assembler.ClienteModelDisassembler;
import br.com.porto.api.model.ClienteInput;
import br.com.porto.api.model.ClienteModel;
import br.com.porto.domain.model.Cliente;
import br.com.porto.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteControler {

	@Autowired
	private CadastroClienteService cadastroClienteService;
	
	@Autowired
	private ClienteModelAssembler clienteModelAssembler;
	
	@Autowired
	private ClienteModelDisassembler clienteModelDisassembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel salvar (@RequestBody ClienteInput clienteInput) {
		Cliente cliente = clienteModelDisassembler.toObjectModel(clienteInput);
		
		return clienteModelAssembler.toModel(
				cadastroClienteService.salvar(cliente));
	}
	
	@GetMapping("/{clienteId}")
	@ResponseStatus(HttpStatus.OK)
	public ClienteModel buscar (@PathVariable("clienteId")Long id) {
		return clienteModelAssembler
				.toModel(cadastroClienteService.buscar(id));
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ClienteModel> listar (){
		return clienteModelAssembler
				.toCollectModel(cadastroClienteService.listar());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{clienteId}")
	public ClienteModel atualizar (@RequestBody ClienteInput clienteInput,
			@PathVariable ("clienteId") Long clienteId) {
			Cliente cliente = cadastroClienteService.buscarOuFalhar(clienteId);
			clienteModelDisassembler.copyToObject(clienteInput, cliente);
		return clienteModelAssembler.toModel(cadastroClienteService.salvar(cliente));
}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{clienteId}")
	public void remover (@PathVariable ("clienteId") Long clienteId) {
		cadastroClienteService.remover(clienteId);
	}
}
