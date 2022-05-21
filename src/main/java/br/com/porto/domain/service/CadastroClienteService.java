package br.com.porto.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.porto.domain.exception.ClienteNaoEncontradoException;
import br.com.porto.domain.exception.EntidadeEmUsoException;
import br.com.porto.domain.model.Cliente;
import br.com.porto.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	private static final String CLIENTE_EM_USO=
			"Cliente de código %d não pode ser removido pois está em uso";
	
	public Cliente buscarOuFalhar(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(()-> new ClienteNaoEncontradoException(id));
	}
	
	@Transactional
	public Cliente salvar (Cliente cliente) {
		Cliente clienteNovo = new Cliente();
		clienteNovo = clienteRepository.save(cliente);
		return clienteNovo;
	}
	
	public Cliente buscar (Long id) {
		return buscarOuFalhar(id);
	}
	
	public List<Cliente> listar (){
		return clienteRepository.findAll();
	}
	
	@Transactional
	public void remover (Long id) {
		buscarOuFalhar(id);
		try {
		clienteRepository.deleteById(id);
		clienteRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(CLIENTE_EM_USO, id));
		}
		
	}
	
}
