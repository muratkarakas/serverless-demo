package com.eteration.demo.cloud.event;

import java.io.Serializable;

import io.quarkus.runtime.annotations.RegisterForReflection;
@RegisterForReflection
public class CustomEvent implements Serializable {

	private static final long serialVersionUID = -6596373309617774130L;

	private String message;

	public CustomEvent(String message) {
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



} 
