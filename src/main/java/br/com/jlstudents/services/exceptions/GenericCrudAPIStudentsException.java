package br.com.jlstudents.services.exceptions;

public class GenericCrudAPIStudentsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public GenericCrudAPIStudentsException(final String message) {
		super(message);
	}

}
