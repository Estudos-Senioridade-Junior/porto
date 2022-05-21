package br.com.porto.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.porto.domain.exception.ConteinerNaoEncontradoException;
import br.com.porto.domain.exception.EntidadeEmUsoException;
import br.com.porto.domain.exception.MovimentacaoNaoEncontradoException;
import br.com.porto.domain.model.Conteiner;
import br.com.porto.domain.model.Movimentacao;
import br.com.porto.domain.model.Relatorio;
import br.com.porto.domain.repository.MovimentacaoRepository;

@Service
public class CadastroMovimentacaoService {
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private CadastroConteinerService cadastroConteinerService;
	
	private static final String MOVIMENTACAO_EM_USO=
			"Movimentação de código %d não pode ser removida pois está em uso";

	public Movimentacao buscarOuFalhar(Long id) {
		return movimentacaoRepository.findById(id)
				.orElseThrow(()-> new MovimentacaoNaoEncontradoException(id));
	}
	
	@Transactional
	public Movimentacao salvar (Movimentacao movimentacao) {
		Conteiner conteiner = cadastroConteinerService.buscarOuFalhar(movimentacao.getConteiner().getId());
		movimentacao.setConteiner(conteiner);
		return movimentacaoRepository.save(movimentacao);
	}
	
	public List<Movimentacao> listar (){
		return movimentacaoRepository.findAll();
	}
	
	@Transactional
	public void remover(Long id) {
		buscarOuFalhar(id);
		try {
		movimentacaoRepository.deleteById(id);
		movimentacaoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MOVIMENTACAO_EM_USO, id));
		}
	}
	
	public List<Relatorio> getRelatorioResumoMovimentacao(){
		return movimentacaoRepository.relatorioResumoMovimentacao();
	}
	
	
	
}
