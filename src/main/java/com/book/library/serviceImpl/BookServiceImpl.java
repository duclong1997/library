package com.book.library.serviceImpl;

import com.book.library.dao.BookDao;
import com.book.library.dao.BookRespository;
import com.book.library.dao.CategoryDao;
import com.book.library.entity.Book;
import com.book.library.entity.Category;
import com.book.library.models.BookModel;
import com.book.library.models.BookRegisterModel;
import com.book.library.models.BookUpdateModel;
import com.book.library.service.BookService;
import com.book.library.utils.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class BookServiceImpl implements BookService {

	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	BookDao bookDao;
	
	@Autowired
	CategoryDao categoryDao;

	@Autowired
	BookRespository bookRespository;

	@Override
	public List<BookModel> getBooks() {
		List<BookModel> bookModels = new ArrayList<>();
		try {
			logger.info(" get list books");
			List<Book> books = new ArrayList<Book>();
			books = bookDao.getBooks();
			for (Book book : books) {
				BookModel bookModel = new BookModel();
				bookModel.setIdBook(book.getId());
				bookModel.setNameBook(book.getNameBook());
				bookModel.setNameCategory(book.getCategory().getNameCategory());
				bookModel.setIdCategory(book.getCategory().getIdCategory());
				// check book borrowed
				if(book.getEnable()== Common.Book.BORROWED)
				{
					bookModel.setCheckBrow(Common.Book.CHECK_BORROWED);
				}else if(book.getEnable()== Common.Book.RETURN)
				{
					bookModel.setCheckBrow(Common.Book.CHECK_NOT_BORROWED);
				}
				bookModels.add(bookModel);
			}
			return bookModels;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}

	}

	@Override
	public void addBook(BookRegisterModel bookRegisterModel) {
		try {
			logger.info("Add book");
			Book book = new Book();
			logger.info("get category by idcategory");
			Category category = new Category();
			category = categoryDao.getCategoryById(bookRegisterModel.getIdCategory());
			if(category !=null)
			{
				book.setNameBook(bookRegisterModel.getNameBook());
				book.setCategory(category);
				bookDao.insertBook(book);
			}
		}catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}
	}

	@Override
	public void editBook(BookUpdateModel bookUpdateModel) {
		try {
			logger.info("update book");
			logger.info("get category by idcategory");
			Category category = new Category();
			category = categoryDao.getCategoryById(bookUpdateModel.getIdCategory());
			
			logger.info("get book by idBook");
			Book book = new Book();
			book = bookDao.getBookById(bookUpdateModel.getIdBook());
			
			if(category !=null && book !=null)
			{
				book.setNameBook(bookUpdateModel.getNameBook());
				book.setCategory(category);
				bookDao.updateBook(book);
			}
		}catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}
	}

	@Override
	public void deleteBook(int idBook) {
		try{
			logger.info("delete book");
			logger.info("get book id book");
			Book book = new Book();
			book = bookDao.getBookById(idBook);
			if(book !=null){
				bookDao.deleteBook(book);
			}
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
	}

	@Override
	public BookModel getBookById(int idBook) {
		BookModel bookModel = new BookModel();
		try{
			Book book = new Book();
			logger.info("get book by idBook");
			book = bookDao.getBookById(idBook);
			bookModel.setIdBook(book.getId());
			bookModel.setNameBook(book.getNameBook());
			bookModel.setIdCategory(book.getCategory().getIdCategory());
			bookModel.setNameCategory(book.getCategory().getNameCategory());
			// check book borrowed
			if(book.getEnable()== Common.Book.BORROWED)
			{
				bookModel.setCheckBrow(Common.Book.CHECK_BORROWED);
			}else if(book.getEnable()== Common.Book.RETURN)
			{
				bookModel.setCheckBrow(Common.Book.CHECK_NOT_BORROWED);
			}
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return null;
		}
		return bookModel;
	}

	@Override
	public void addMultiBook(List<BookRegisterModel> bookRegisterModels) {
		try {
			logger.info("Add books");
			List<Book> books = new ArrayList<>();
			for (BookRegisterModel bookRegisterModel : bookRegisterModels) {
				Category category;
				logger.info("get category by idcategory");
				category = categoryDao.getCategoryById(bookRegisterModel.getIdCategory());
				Book book = new Book();
				if (category != null) {
					book.setNameBook(bookRegisterModel.getNameBook());
					book.setCategory(category);
					books.add(book);
				}
			}
			logger.info("insert books");
			bookRespository.saveAll(books);
		}catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}
	}
}
