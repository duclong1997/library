package com.book.library.service;

import com.book.library.entity.User;
import com.book.library.models.BookBrowedUserModel;

import java.util.List;

public interface BookBrowService {
    public void borrowBook(User user, List<BookBrowedUserModel> listBookBrowed);
    public void returnBook(User user, List<BookBrowedUserModel> listBookBrowed);
    public boolean checkTimeBorrow(int idBook);
}
