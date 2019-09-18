package com.book.library.dao;

import com.book.library.entity.User;

public interface UserDao {
	public User getUserByUsername(String username);
	
	public void insertUser(User user);
	
	public void updateUser(User user);
	
	public User getUserById(int idUser);
	
}
