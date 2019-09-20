package com.book.library.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class UserModel {
	private String name;
	private boolean gender;
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	private Date dob;
}
