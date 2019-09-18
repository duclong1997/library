package com.book.library.models;

import java.util.Date;

import lombok.Data;

@Data
public class UserModel {
	private String name;
	private boolean gender;
	private Date dob;
}
