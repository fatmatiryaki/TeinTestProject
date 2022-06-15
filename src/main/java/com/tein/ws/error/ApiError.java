package com.tein.ws.error;

import java.util.Date;
import java.util.Map;

import lombok.Data;

@Data
public class ApiError {
	
	private int status;
	
	private String message;
	
	private String path;
	
	private long timestamp = new Date().getTime();
	
	@SuppressWarnings("unused")
	private final Map<String, String> validationErrors;
	
	public ApiError(int status, String message, String path) {
		this.validationErrors = null;
		this.setStatus(status);
		this.setMessage(message);
		this.setPath(path);
	}

	public void setValidationErrors(Map<String, String> validationErrors) {
		// TODO Auto-generated method stub
		
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}

