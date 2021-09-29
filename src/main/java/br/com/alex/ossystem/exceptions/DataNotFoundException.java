package br.com.alex.ossystem.exceptions;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4839757549065226183L;
	public DataNotFoundException() {
		super("Dado não encontrado");
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataNotFoundException(String message) {
		super(message);
	}
	
	

}
