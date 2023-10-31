package br.com.jlstudents.services.exceptions;

public class EntityCrudStudentAPINotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityCrudStudentAPINotFoundException(final String message) {
		super(message);
	}
	
	public EntityCrudStudentAPINotFoundException(final Integer id) {
		super("Entidade não foi encontrada na base de dados. [Id + - " + id + "]");
	}
	
	public EntityCrudStudentAPINotFoundException(final String message, final Integer id) {
		super(message + " [Id: " + id + "]");
	}
	
}
