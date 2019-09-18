package com.book.library.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;

import com.book.library.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.dao.BookDao;
import com.book.library.entity.Book;

@Transactional
@Repository
public class BookDaoImpl implements BookDao{

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Book> getBooks() {
		String sql ="select * from book where enable <> 0 ";
		List<Book> books = entityManager.createNativeQuery(sql, Book.class).getResultList();
		return books;
	}

	@Override
	public void insertBook(Book book) {
		entityManager.persist(book);
	}

	@Override
	public void updateBook(Book book) {
		entityManager.merge(book);
	}

	@Override
	public void deleteBook(Book book) {
		book.setEnable(Common.Book.DELETE);
		entityManager.merge(book);
	}

	@Override
	public Book getBookById(int idBook) {
		return entityManager.find(Book.class, idBook);
	}

	@Override
	public void borrowedBook(Book book) {
		book.setEnable(Common.Book.BORROWED);
		entityManager.merge(book);
	}

	@Override
	public void returnBook(Book book) {
		book.setEnable(Common.Book.RETURN);
		entityManager.merge(book);
	}

	@Override
	public Book getBookBorrowedById(int idBook) {
		String sql ="select * from book where idbook =:idBook and enable =2";
		List<Book> bookList =entityManager.createNativeQuery(sql,Book.class)
				.setParameter("idBook",idBook).getResultList();
		if(bookList.isEmpty())
		{
			return  null;
		}else {
			return bookList.get(0);
		}
	}

}
