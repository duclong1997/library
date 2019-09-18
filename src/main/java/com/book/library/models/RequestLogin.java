package com.book.library.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class RequestLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
}
