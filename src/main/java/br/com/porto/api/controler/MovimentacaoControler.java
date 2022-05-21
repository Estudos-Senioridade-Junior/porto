package br.com.porto.api.controler;

import java.time.OffsetDateTime;
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

import br.com.porto.api.assembler.MovimentacaoModelAssembler;
import br.com.porto.api.assembler.MovimentacaoModelDisassembler;
import br.com.porto.api.model.MovimentacaoInput;
import br.com.porto.api.model.MovimentacaoModel;
import br.com.porto.domain.model.Movimentacao;
import br.com.porto.domain.service.CadastroMovimentacaoService;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoControler {

	@Autowired
	private MovimentacaoModelAssembler movimentacaoModelAssembler;
	
	@Autowired
	private MovimentacaoModelDisassembler movimentacaoModelDisassembler;
	
	@Autowired
	private CadastroMovimentacaoService cadastroMovimentacaoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MovimentacaoModel salvar (@RequestBody MovimentacaoInput movInput) {
		Movimentacao movimentacao = movimentacaoModelDisassembler.toObjectModel(movInput);
		return movimentacaoModelAssembler
				.toModel(cadastroMovimentacaoService.salvar(movimentacao));
		
	}
	
	@GetMapping("/{movimentacaoId}")
	@ResponseStatus(HttpStatus.OK)
	public MovimentacaoModel buscar (@PathVariable ("movimentacaoId") Long id) {
		return movimentacaoModelAssembler
				.toModel(cadastroMovimentacaoService.buscarOuFalhar(id));
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<MovimentacaoModel> listar (){
		return movimentacaoModelAssembler
				.toCollectModel(cadastroMovimentacaoService.listar());
	}
	
	@PutMapping("{movimentacaoId}")
	@ResponseStatus(HttpStatus.OK)
	public MovimentacaoModel atualizar(@PathVariable ("movimentacaoId") Long id,
			@RequestBody MovimentacaoInput movimentacaoInput) {
		Movimentacao movimentacao = cadastroMovimentacaoService.buscarOuFalhar(id);
		movimentacaoModelDisassembler.copyToObject(movimentacaoInput, movimentacao);
		return movimentacaoModelAssembler
				.toModel(cadastroMovimentacaoService.salvar(movimentacao));
	}
	
	@DeleteMapping("{movimentacaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover (@PathVariable ("movimentacaoId") Long id) {
		cadastroMovimentacaoService.remover(id);
	}
	
	@PutMapping("/finalizacaomovimentacao/{movimentacaoId}")
	@ResponseStatus(HttpStatus.OK)
	public MovimentacaoModel finalizaMovimentacao (@PathVariable ("movimentacaoId") Long id) {
		Movimentacao movimentacao = cadastroMovimentacaoService.buscarOuFalhar(id);
		movimentacao.setHoraFim(OffsetDateTime.now());
		return movimentacaoModelAssembler
				.toModel(cadastroMovimentacaoService.salvar(movimentacao));
	}
}
