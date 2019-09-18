package com.book.library.models;

import lombok.Data;

@Data
public class BookUpdateModel {
	private int idBook;
	private String nameBook;
	private int idCategory;
}
