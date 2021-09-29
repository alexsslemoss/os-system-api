package br.com.alex.ossystem.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	private static final long serialVersionUID = -9209076647375269332L;

	private String nameField;

	private String message;

	public FieldMessage() {
		super();
	}

	public FieldMessage(String nameField, String message) {
		super();
		this.nameField = nameField;
		this.message = message;
	}

	public String getNameField() {
		return nameField;
	}

	public void setNameField(String nameField) {
		this.nameField = nameField;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
