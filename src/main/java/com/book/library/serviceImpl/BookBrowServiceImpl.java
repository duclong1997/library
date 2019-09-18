package com.book.library.serviceImpl;

import com.book.library.dao.BookBrowDao;
import com.book.library.dao.BookBrowRespository;
import com.book.library.dao.BookDao;
import com.book.library.entity.Book;
import com.book.library.entity.BookBrow;
import com.book.library.entity.User;
import com.book.library.models.BookBrowedUserModel;
import com.book.library.service.BookBrowService;
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
public class BookBrowServiceImpl implements BookBrowService {
    private static final Logger logger = LoggerFactory.getLogger(BookBrowServiceImpl.class);

    @Autowired
    BookBrowDao bookBrowDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    BookBrowRespository bookBrowRespository;

    @Override
    public void borrowBook(User user, List<BookBrowedUserModel> listBookBrowed) {
        logger.info("borrow books");
        List<BookBrow> bookBrows = new ArrayList<>();
        try{
            for (BookBrowedUserModel bookBrowed : listBookBrowed) {
                Book book = new Book();
                book = bookDao.getBookById(bookBrowed.getIdBook());
                // check book !=null and book not borrowed
                if(book !=null && book.getEnable() == Common.Book.RETURN)
                {
                    BookBrow bookBrow = new BookBrow();
                    bookBrow.setBook(book);
                    bookBrow.setUser(user);
                    bookBrow.setStartBrow(bookBrowed.getBeginBrow());
                    bookBrow.setEndBrow(bookBrowed.getEndBrow());
                    bookBrows.add(bookBrow);
                    logger.info("borrowed book");
                    bookDao.borrowedBook(book);
                }
            }
            bookBrowRespository.saveAll(bookBrows);
        }catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void returnBook(User user, List<BookBrowedUserModel> listBookBrowed) {
        logger.info("return books");
        try{
            for (BookBrowedUserModel bookBrowedUserModel : listBookBrowed)
            {
                logger.info("get bookBrow by iduser, idbook");
                BookBrow bookBrow = bookBrowRespository.getBookBrowByIdUserAndIdBook(user.getIdUser(),bookBrowedUserModel.getIdBook());
                Book book = new Book();
                book = bookDao.getBookById(bookBrowedUserModel.getIdBook());
                // book doesn't return, book is borrowed.
                if(bookBrow !=null && book !=null && book.getEnable() ==Common.Book.BORROWED )
                {
                    bookBrowDao.returnBook(bookBrow);
                    logger.info(" return book");
                    bookDao.returnBook(book);
                }
            }
        }catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public boolean checkTimeBorrow(int idBook) {
        try {
            BookBrow checkTime = new BookBrow();
            logger.info("check time end borrowed");
            checkTime = bookBrowRespository.checkTimeBorrowedBook(idBook);
            // check return book late
            if (checkTime != null) {
                checkTime.setEnable(Common.BookBorrow.RETURN_BOOK_LATE);
                bookBrowDao.updateBookBrow(checkTime);
            }
            logger.info("check book borrowed");
            Book book = new Book();
            book = bookDao.getBookBorrowedById(idBook);
            if (book ==null)
            {
                return Common.Book.CHECK_BOOK_NOT_BORROWED;
            }
            else {
                return Common.Book.CHECK_BOOK_BORROWED;
            }

        }catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return Common.Book.CHECK_BOOK_BORROWED;
        }
    }
}
