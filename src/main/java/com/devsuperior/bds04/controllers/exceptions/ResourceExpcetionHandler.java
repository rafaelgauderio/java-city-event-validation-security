package com.devsuperior.bds04.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ResourceExpcetionHandler {
	
	
	public ResponseEntity<ValidationError> validationMethod(MethodArgumentNotValidException ErrorException, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError erro = new ValidationError();
		
		erro.setTimestamp(Instant.now());
		erro.setStatus(status.value());
		erro.setError("Erro de validação. Objeto não foi criado ou atualizada no banco de dados");
		erro.setMessage(erro.getMessage());
		erro.setPath(request.getRequestURI());
		
		
		for(FieldError field : ErrorException.getBindingResult().getFieldErrors()) {
			
			erro.addErrors(field.getField(),field.getDefaultMessage());
			
		}
		
		return ResponseEntity.status(status).body(erro);
		
	}
	

}
