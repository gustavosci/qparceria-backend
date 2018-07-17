package br.com.qparceria.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;

import br.com.qparceria.services.exceptions.AuthorizationException;
import br.com.qparceria.services.exceptions.DataIntegrityException;
import br.com.qparceria.services.exceptions.FileException;
import br.com.qparceria.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(), 
											  HttpStatus.NOT_FOUND.value(), 
											  "Não encontrado", 
											  e.getMessage(), 
											  request.getRequestURI());;
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(), 
											  HttpStatus.BAD_REQUEST.value(), 
											  "Integridade de dados", 
											  e.getMessage(), 
											  request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){	
		ValidationError err = new ValidationError(System.currentTimeMillis(), 
												  HttpStatus.UNPROCESSABLE_ENTITY.value(), 
												  "Erro de validação", 
												  e.getMessage(),
												  request.getRequestURI());
		
		for( FieldError x : e.getBindingResult().getFieldErrors() ) {
			err.addError(x.getField(), x.getDefaultMessage());
		}		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(), 
				  							  HttpStatus.FORBIDDEN.value(), 
			  							  	  "Acesso negado", 
			  							  	  e.getMessage(), 
			  							  	  request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(), 
				  							  HttpStatus.BAD_REQUEST.value(), 
			  							  	  "Erro ao importar arquivo", 
			  							  	  e.getMessage(), 
			  							  	  request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.valueOf(e.getErrorCode());
		StandardError err = new StandardError(System.currentTimeMillis(), 
				  							  status.value(),
			  							  	  "Exceção na Amazon", 
			  							  	  e.getMessage(), 
			  							  	  request.getRequestURI());
		return ResponseEntity.status(status.value()).body(err);
	}

	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(), 
				  							  HttpStatus.BAD_REQUEST.value(), 
			  							  	  "Erro na Amazon", 
			  							  	  e.getMessage(), 
			  							  	  request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

}
