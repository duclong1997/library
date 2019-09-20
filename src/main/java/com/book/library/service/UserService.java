package com.book.library.service;

import com.book.library.entity.User;
import com.book.library.models.UserBorrowBook;
import com.book.library.models.UserModel;
import com.book.library.models.UserRegister;

import java.util.List;

public interface UserService {

	/**
	 * insert user
	 * author: longnd
	 * userregister
	 */
	public void insertUser(UserRegister userRegister);
	/**
	 * insert user 
	 * author: longnd
	 * parameter: username, password
	 */
	public User getUserByUsernamePassword(String username, String password);
	
	/**
	 * edit user 
	 * author: longnd
	 * parameter: Usermodel
	 */
	public void editUser(UserModel userModel, int iduser);

	/**
	 *
	 * @param idUser
	 * @return UserBorrowBook
	 */
	public List<UserBorrowBook> getBookBorrowByUser(int idUser);
}
