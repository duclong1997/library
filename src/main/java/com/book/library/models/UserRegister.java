package com.book.library.models;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserRegister implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String username;
	private String password;
	private String role;
	private boolean gender;
	private Date dob;
}
