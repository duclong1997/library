package com.book.library.serviceImpl;

import com.book.library.dao.BookBrowRespository;
import com.book.library.dao.UserDao;
import com.book.library.entity.BookBrow;
import com.book.library.entity.User;
import com.book.library.models.CustomUser;
import com.book.library.models.UserBorrowBook;
import com.book.library.models.UserModel;
import com.book.library.models.UserRegister;
import com.book.library.service.BookBrowService;
import com.book.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDao userdao;

	@Autowired
	BookBrowRespository bookBrowRespository;

	@Autowired
	BookBrowService bookBrowService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("get user");
		try {
			User user = userdao.getUserByUsername(username);
			if (user == null) {
				return null;
			} else {
				CustomUser customUser = new CustomUser();
				customUser.setUser(user);
				return customUser;
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}

	@Override
	public void insertUser(UserRegister userRegister) {
		try {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			logger.info("register user");
			User user = new User();
			user.setName(userRegister.getName());
			user.setUsername(userRegister.getUsername());
			user.setPassword(bCryptPasswordEncoder.encode(userRegister.getPassword()));
			user.setRole(userRegister.getRole());
			user.setDob(userRegister.getDob());
			user.setGender(userRegister.isGender());
			userdao.insertUser(user);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

	@Override
	public User getUserByUsernamePassword(String username, String password) {
		try{
			logger.info("get user by username and password");
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			User user = new User();
			user = userdao.getUserByUsername(username);
			if(user !=null)
			{
				if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
					return user;
				}
				else {
					return null;
				}
			}
			else{
				return null;
			}
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return null;
		}
	}

	@Override
	public void editUser(UserModel userModel, int idUser) {
		User user = new User();
		try{
			logger.info(" update information user");
			logger.info("get user by iduser");
			user = userdao.getUserById(idUser);
			if(user !=null){
				user.setDob(userModel.getDob());
				user.setGender(userModel.isGender());
				user.setName(userModel.getName());
				userdao.updateUser(user);
			}
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
	}

	@Override
	public List<UserBorrowBook> getBookBorrowByUser(int idUser) {
		List<UserBorrowBook> userBorrowBooks = new ArrayList<>();
		try{
			logger.info("get list books borrowed by user");
			List<BookBrow> bookBrows = new ArrayList<>();
			bookBrows = bookBrowRespository.getBookBrowByIdUser(idUser);
			for (BookBrow bookBrow: bookBrows) {
				UserBorrowBook userBorrowBook = new UserBorrowBook();
				userBorrowBook.setIdUser(idUser);
				userBorrowBook.setBorrowTime(bookBrow.getStartBrow());
				userBorrowBook.setIdBook(bookBrow.getBook().getId());
				// check return late book
				logger.info("check time out borrowed by idbook");
				bookBrowService.checkTimeBorrow(bookBrow.getBook().getId());
				userBorrowBook.setName(bookBrow.getUser().getName());
				userBorrowBook.setReturnTime(bookBrow.getEndBrow());
				userBorrowBook.setNameBook(bookBrow.getBook().getNameBook());
				userBorrowBook.setCheckBorrowed(bookBrow.getEnable());
				userBorrowBooks.add(userBorrowBook);
			}
		}catch (Exception ex)
		{
			logger.error(ex.getMessage());
			return  null;
		}
		return userBorrowBooks;
	}
}
