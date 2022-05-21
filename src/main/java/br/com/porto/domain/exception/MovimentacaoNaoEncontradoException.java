package br.com.porto.domain.exception;

public class MovimentacaoNaoEncontradoException extends EntidadeNaoEncontradaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MovimentacaoNaoEncontradoException (String mensagem) {
		super (mensagem);
	}
	
	public MovimentacaoNaoEncontradoException (Long id) {
		this(String.format("Não existe movimentação de código %d", id));
	}

}
