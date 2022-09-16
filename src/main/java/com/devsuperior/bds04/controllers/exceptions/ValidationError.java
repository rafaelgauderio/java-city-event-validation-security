package com.devsuperior.bds04.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> erros = new ArrayList<FieldMessage> ();

	public List<FieldMessage> getErros() {
		return erros;
	}

	/*
	public void setErros(List<FieldMessage> erros) {
		this.erros = erros;
	}
	*/
	
	public void addErrors (String fieldName, String message) {
		FieldMessage fieldMessage = new FieldMessage(fieldName,message);
		erros.add(fieldMessage);
	}
	
	

}
