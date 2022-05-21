package br.com.porto.domain.enums;

public enum TipoConteiner {

	TIPO20 (20),
	TIPO40 (40);
	
	private int valor;
	
	TipoConteiner(int valor){
		this.valor=valor;
	}
}
