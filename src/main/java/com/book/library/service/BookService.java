package com.book.library.service;

import java.util.List;

import com.book.library.models.BookModel;
import com.book.library.models.BookRegisterModel;
import com.book.library.models.BookUpdateModel;

public interface BookService {
	
	public List<BookModel> getBooks();

	public void addBook(BookRegisterModel bookRegisterModel);
	
	public void editBook(BookUpdateModel bookRegisterModel);
	
	public void deleteBook(int idBook);
	
	public BookModel getBookById(int idBook);

	public void addMultiBook(List<BookRegisterModel> bookRegisterModels);
}
