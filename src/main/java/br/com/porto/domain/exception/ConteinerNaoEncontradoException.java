package br.com.porto.domain.exception;

public class ConteinerNaoEncontradoException extends EntidadeNaoEncontradaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConteinerNaoEncontradoException (String mensagem) {
		super (mensagem);
	}
	
	public ConteinerNaoEncontradoException (Long id) {
		this(String.format("Não existe conteiner de código %d", id));
	}

}
