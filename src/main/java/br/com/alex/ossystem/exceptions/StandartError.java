package br.com.alex.ossystem.exceptions;

import java.io.Serializable;

public class StandartError implements Serializable {

	private static final long serialVersionUID = -5377495891679451049L;

	private Long timestamp;

	private Integer status;

	private String error;

	public StandartError() {
		super();
	}

	public StandartError(Long timestamp, Integer status, String error) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
