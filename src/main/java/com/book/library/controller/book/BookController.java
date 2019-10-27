package com.book.library.controller.book;

import com.book.library.models.BookModel;
import com.book.library.models.BookRegisterModel;
import com.book.library.models.BookUpdateModel;
import com.book.library.models.Message;
import com.book.library.service.BookBrowService;
import com.book.library.service.BookService;
import com.book.library.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apiv2")
public class BookController {
	
	@Autowired
	BookService bookService;

	@Autowired
	BookBrowService bookBrowService;

	// get list books
	@GetMapping("/book")
	public List<BookModel> getBook()
	{
		List<BookModel> bookModels = new ArrayList<BookModel>();
		bookModels = bookService.getBooks();
		return bookModels;
	}
	
	// get book by idbook
	@GetMapping("/book/{idBook}")
	public BookModel getBook(@PathVariable(name = "idBook") int idBook)
	{
		BookModel bookModel = new BookModel();
		bookModel = bookService.getBookById(idBook);
		return bookModel;
	}
	
	// add book
	@PostMapping("/book/add")
	public Message addBook(@RequestBody BookRegisterModel bookRegisterModel)
	{
		Message message = new Message();
		try{
			bookService.addBook(bookRegisterModel);
		}catch(Exception ex){
			message.setStatus(Common.Status.BAD_REQUEST);
			message.setMessage(ex.getMessage());
			return message;
		}
		message.setStatus(Common.Status.OK);
		message.setMessage(Common.MessageStatus.ADD_SUCCESS);
		return message;
	}
	
	// edit book
	@PutMapping("/book/edit")
	public Message editBook(@RequestBody BookUpdateModel bookUpdateModel)
	{
		Message message = new Message();
		try{	
			bookService.editBook(bookUpdateModel);	
		}
		catch(Exception ex)
		{
			message.setStatus(Common.Status.BAD_REQUEST);
			message.setMessage(ex.getMessage());
			return message;
		}
		message.setStatus(Common.Status.OK);
		message.setMessage(Common.MessageStatus.UPDATE_SUCCESS);
		return message;
	}

	// delete book
	@DeleteMapping("/book/delete/{idBook}")
	public Message deleteBook(@PathVariable(name="idBook") int idBook)
	{
		Message message = new Message();
		try{
			bookService.deleteBook(idBook);
			
		}catch(Exception ex)
		{
			message.setStatus(Common.Status.BAD_REQUEST);
			message.setMessage(ex.getMessage());
			return message;
		}
		message.setStatus(Common.Status.OK);
		message.setMessage(Common.MessageStatus.DELETE_SUCCESS);
		return message;
	}

	// check book borrowed.
	@GetMapping("/book/checkBorrowed/{idBook}")
	public Message checkBorrowed(@PathVariable(name = "idBook") int idBook){
		Message message = new Message();
		try{
			boolean checkBorrowed = bookBrowService.checkTimeBorrow(idBook);
			if(checkBorrowed == Common.Book.CHECK_BOOK_BORROWED)
			{
				message.setStatus(Common.Status.OK);
				message.setMessage(Common.Book.BOOK_BORROWED);
				return message;
			}
			else {
				message.setStatus(Common.Status.OK);
				message.setMessage(Common.Book.BOOK_RETURN);
				return message;
			}
		}catch (Exception ex)
		{
			message.setStatus(Common.Status.BAD_REQUEST);
			message.setMessage(ex.getMessage());
			return message;
		}
	}

	// insert multi book
	@PostMapping("/book/multiBook")
    public  Message getMultiBook(@RequestBody List<BookRegisterModel> bookRegisterModels){
        Message message =new Message();
        try{
            bookService.addMultiBook(bookRegisterModels);
            message.setStatus(Common.Status.OK);
            message.setMessage(Common.MessageStatus.ADD_SUCCESS);
            return message;

        }catch (Exception ex)
        {
            message.setStatus(Common.Status.BAD_REQUEST);
            message.setMessage(ex.getMessage());
            return message;
        }
    }
}
