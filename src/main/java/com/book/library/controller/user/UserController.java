package com.book.library.controller.user;

import com.book.library.models.*;
import com.book.library.service.UserService;
import com.book.library.utils.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;

	// update user's information
	@PostMapping("/user/eidt")
	public synchronized Message editUser(@RequestBody UserModel userModel){
		Message message = new Message();
		try{
			// get authentication
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			// check authentication
			if(auth.getPrincipal().equals("anonymousUser")){
				message.setStatus(Common.Status.ERROR_SERVER);
				message.setMessage(Common.User.UNAUTHORIZED);
				return message;
			}else {
				CustomUser cus = (CustomUser) auth.getPrincipal();
				int idUser = cus.getUser().getIdUser();
				userService.editUser(userModel, idUser);
			}
		}catch(Exception ex)
		{
			message.setStatus(Common.Status.ERROR_SERVER);
			message.setMessage(ex.getMessage());
			return message;
		}
		message.setStatus(Common.Status.OK);
		message.setMessage(Common.MessageStatus.UPDATE_SUCCESS);
		return message;
	}

	// get detail user
    @GetMapping("/user/detail")
    public Userdetail getDetailUser()
    {
		Userdetail userdetail = new Userdetail();
		try {
			// get authentication
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			// check authentication
			if (auth.getPrincipal().equals("anonymousUser")) {
				logger.error(Common.User.UNAUTHORIZED);
			} else {
				CustomUser cus = (CustomUser) auth.getPrincipal();
				userdetail.setIdUser(cus.getUser().getIdUser());
				userdetail.setName(cus.getUser().getName());
				userdetail.setDob(cus.getUser().getDob());
				userdetail.setGender(cus.getUser().isGender());
			}
		}
		catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}
        return userdetail;
    }

    // list boob borrowed by user
    @GetMapping("/user/userBorrowedBook")
    public List<UserBorrowBook>  getBookBorrowed(){
		List<UserBorrowBook> userBorrowBooks = new ArrayList<>();
		try {
			// get authentication
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			// check authentication
			if (auth.getPrincipal().equals("anonymousUser")) {
				logger.error(Common.User.UNAUTHORIZED);
			} else {
				CustomUser cus = (CustomUser) auth.getPrincipal();
				userBorrowBooks = userService.getBookBorrowByUser(cus.getUser().getIdUser());
			}
		}catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}
        return  userBorrowBooks;
    }
}
