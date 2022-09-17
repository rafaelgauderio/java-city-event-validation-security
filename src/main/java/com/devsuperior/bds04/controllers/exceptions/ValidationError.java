package com.devsuperior.bds04.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{


	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<FieldMessage>();

	public List<FieldMessage> getErrors() {
		return this.errors;
	}

	
	public void addError(String message, String fieldName) {
		FieldMessage fieldMessage = new FieldMessage(message,fieldName);
		errors.add(fieldMessage);
	}

	
	

}
