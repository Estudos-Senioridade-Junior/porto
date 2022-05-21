package br.com.porto.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import br.com.porto.domain.exception.*;

@ControllerAdvice //Faz com que capture excessões em todo o projeto
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	private static final String MSG_ERRO_USUARIO_FINAL
		= "Ocorreu um erro interno inesperado do sistema. Tente novamente e se o "
				+ "problema persistir, entre em contato com o administrador do sistema";
	
	private static final String MSG_DADOS_INVALIDOS
		="Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
	
	private static final String MSG_ERRO_VALIDACAO
	="Há um erro de validação na requisição. Por favor corrija.";
	
	
	private Problem.ProblemBuilder createProblemBuilder (HttpStatus status,
			ProblemType problemType, String detail){
		
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail)
				.timestamp(LocalDateTime.now());
	}
	
	//Concatenar o caminho do erro com . (ponto)
	private String joinPath(List<Reference> references) {
		return references.stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(
			EntidadeEmUsoException e, WebRequest request){
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		
		return handleExceptionInternal(e, problem, new HttpHeaders(),
				status, request);		
		
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> handleValidationException(
			Exception e, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_VALIDACAO;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_VALIDACAO)
				.build();
		
		
		return handleExceptionInternal(e, problem, new HttpHeaders(),
				status, request);		
		
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEstadoNaoEncontradoException(
			EntidadeNaoEncontradaException e, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		
		return handleExceptionInternal(e, problem, new HttpHeaders(),
				HttpStatus.NOT_FOUND, request);
		
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(
			EntidadeNaoEncontradaException e, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		
		return handleExceptionInternal(e, problem, new HttpHeaders(),
				status, request);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, 
			Object body, HttpHeaders headers,HttpStatus status, WebRequest request) {
		
		if (body == null) {
		body = Problem.builder()
				.title(status.getReasonPhrase())//Pequena descrição que descreve o status que está vindo
				.status(status.value())
				.userMessage(MSG_ERRO_USUARIO_FINAL)
				.timestamp(LocalDateTime.now())
				.build();
		} else if (body instanceof String) {
			body = Problem.builder()
					.title((String)body)
					.status(status.value())
					.userMessage(MSG_ERRO_USUARIO_FINAL)
					.timestamp(LocalDateTime.now())
					.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	};
	
	
	@ExceptionHandler(Exception.class)
	private ResponseEntity<?>handleUncaught (Exception e, WebRequest request) {
		
		ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String detail = MSG_ERRO_USUARIO_FINAL;
		e.printStackTrace();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_USUARIO_FINAL)
				.build();	
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);	
	}
		
}
