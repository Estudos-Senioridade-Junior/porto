package br.com.porto.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class IdentificacaoConteinerValidation implements ConstraintValidator<IdentificacaoConteiner, String>{
	
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean sinal=true;
		boolean continua=true;
		
		//Valida se os 4 primeiros chars são letras
		for (int i=0; i<4; i++) {
			char c = value.charAt(i);
			if (!(Character.isLetter(c))) {
				sinal=false;
				continua=false;
			}
		}
			
		//Valida se os 7 ultimos chars são numeros
		for (int i=4; i<11; i++) {
			char c = value.charAt(i);
			if (!Character.isDigit(c)) {
			sinal=false;
			continua=false;
			}
			
		if (value.length()>=12) {
			sinal=false;
		}
		}
		return sinal;
	}
		

}
