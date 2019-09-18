package com.book.library.models;

import java.sql.Timestamp;

import lombok.Data;
@Data
public class BookBrowedUserModel {
	private int idBook;
	private String nameBook;
	private Timestamp beginBrow;
	private Timestamp endBrow;
}
