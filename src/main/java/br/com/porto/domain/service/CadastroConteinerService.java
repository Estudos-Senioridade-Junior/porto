package br.com.porto.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.porto.api.assembler.ConteinerModelAssembler;
import br.com.porto.api.assembler.ConteinerModelDisassembler;
import br.com.porto.api.model.ConteinerInput;
import br.com.porto.api.model.ConteinerModel;
import br.com.porto.domain.exception.ClienteNaoEncontradoException;
import br.com.porto.domain.exception.ConteinerNaoEncontradoException;
import br.com.porto.domain.exception.EntidadeEmUsoException;
import br.com.porto.domain.model.Cliente;
import br.com.porto.domain.model.Conteiner;
import br.com.porto.domain.repository.ClienteRepository;
import br.com.porto.domain.repository.ConteinerRepository;

@Service
public class CadastroConteinerService {

	@Autowired
	private ConteinerRepository conteinerRepository;
	
	@Autowired
	private CadastroClienteService cadastroClienteService;
	
	private static final String CONTEINER_EM_USO=
			"Conteiner de código %d não pode ser removido pois está em uso";
	
	public Conteiner buscarOuFalhar(Long id) {
		return conteinerRepository.findById(id)
				.orElseThrow(()-> new ConteinerNaoEncontradoException(id));
	}
	
	@Transactional
	public Conteiner salvar (Conteiner conteiner) {
		
		Cliente cliente = cadastroClienteService.buscarOuFalhar(conteiner.getCliente().getId());
		conteiner.setCliente(cliente);
		
		return conteinerRepository.save(conteiner);
	}
	
	public Conteiner buscar (Long id) {
		return buscarOuFalhar(id);
	}
	
	public List<Conteiner> listar (){
		return conteinerRepository.findAll();
	}
	
	@Transactional
	public void remover (Long id) {
		buscarOuFalhar(id);
		try {
		conteinerRepository.deleteById(id);
		conteinerRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(CONTEINER_EM_USO, id));
		}
	}
	
	public int getImportacao() {
		return conteinerRepository.consultaPorImportacao();
	}
	
	public int getExportacao() {
		return conteinerRepository.consultaPorExportacao();
	}
}
