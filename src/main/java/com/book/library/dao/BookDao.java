package com.book.library.dao;

import java.util.List;

import com.book.library.entity.Book;

public interface BookDao {
	/**
	 * get books
	 * @return list books
	 */
	public List<Book> getBooks();

	/**
	 *	insert book
	 * @return
	 */

	public void insertBook(Book book);
	/**
	 * update book
	 * @return
	 */
	public void updateBook(Book book);
	
	/**
	 * delete book
	 * @return 
	 */
	public void deleteBook(Book book);
	
	/**
	 * get id book
	 * @return book 
	 */
	public Book getBookById(int idBook);

	/**
	 * book borrowed
	 */
	public  void borrowedBook(Book book);

	/**
	 *  return book
	 */
	public void returnBook(Book book);

	public Book getBookBorrowedById( int idBook);
}
