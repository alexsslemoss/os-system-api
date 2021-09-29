package br.com.alex.ossystem.exceptions;

public class DataIntegratyViolationException extends RuntimeException {

	private static final long serialVersionUID = -4839757549065226183L;
	public DataIntegratyViolationException() {
		super("Informação já cadastrada");
	}

	public DataIntegratyViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegratyViolationException(String message) {
		super(message);
	}
	
	

}
