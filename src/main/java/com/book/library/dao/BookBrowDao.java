package com.book.library.dao;

import com.book.library.entity.BookBrow;

public interface BookBrowDao {
    public  void borrowBook(BookBrow bookBrow);

    public void returnBook(BookBrow bookBrow);

    public BookBrow getBookBrowByIdUserAndIdBook(int idUser, int idBook);

    public BookBrow checkTimeBorrowedBook(int idBook);

    public void updateBookBrow(BookBrow bookBrow);
}
