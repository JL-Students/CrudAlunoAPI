package br.com.jlstudents.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.jlstudents.services.exceptions.DatabaseException;
import br.com.jlstudents.services.exceptions.EntityCrudStudentAPINotFoundException;
import br.com.jlstudents.services.exceptions.GenericCrudAPIStudentsException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(EntityCrudStudentAPINotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityCrudStudentAPINotFoundException ex, HttpServletRequest request) {
		String message = "Entidade não encontrada.";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError erroPersonalizado = new StandardError(Instant.now(), status.value(), message, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erroPersonalizado);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException ex, HttpServletRequest request) {
		String message = "Erro ao tentar executar a operação na base de dados.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erroPersonalizado = new StandardError(Instant.now(), status.value(), message, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erroPersonalizado);
	}
	
	@ExceptionHandler(GenericCrudAPIStudentsException.class)
	public ResponseEntity<StandardError> genericError(GenericCrudAPIStudentsException ex, HttpServletRequest request) {
		String message = "Erro ao tentar executar a requisição solicitada.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erroPersonalizado = new StandardError(Instant.now(), status.value(), message, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erroPersonalizado);
	}
	
}
