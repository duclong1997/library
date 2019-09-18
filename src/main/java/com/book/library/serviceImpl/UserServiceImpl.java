package com.book.library.serviceImpl;

import com.book.library.dao.UserDao;
import com.book.library.entity.User;
import com.book.library.models.CustomUser;
import com.book.library.models.UserModel;
import com.book.library.models.UserRegister;
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

@Transactional
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDao userdao;

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
}
