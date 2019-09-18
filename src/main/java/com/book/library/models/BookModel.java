package com.book.library.models;

import lombok.Data;

@Data
public class BookModel {
	private int idBook;
	private String nameBook;
	private String nameCategory;
	private  int idCategory;
	private int checkBrow;
}
