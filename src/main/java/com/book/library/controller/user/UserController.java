package com.book.library.controller.user;

import com.book.library.models.*;
import com.book.library.service.UserService;
import com.book.library.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	// update user's information
	@PostMapping("/user/eidt")
	public Message editUser(@RequestBody UserModel userModel){
		Message message = new Message();
		
		// get authentication
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUser cus = (CustomUser) auth.getPrincipal();
		
		int idUser = cus.getUser().getIdUser();
		try{
		userService.editUser(userModel, idUser);
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
        // get authentication
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUser cus = (CustomUser) auth.getPrincipal();
        Userdetail userdetail = new Userdetail();
        userdetail.setIdUser(cus.getUser().getIdUser());
        userdetail.setName(cus.getUser().getName());
        userdetail.setDob(cus.getUser().getDob());
        userdetail.setGender(cus.getUser().isGender());
        return userdetail;
    }

    // list boob borrowed by user
    @GetMapping("/user/userBorrowedBook")
    public List<UserBorrowBook> getBookBorrowed(){
        // get authentication
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUser cus = (CustomUser) auth.getPrincipal();
        List<UserBorrowBook> userBorrowBooks = new ArrayList<>();
        userBorrowBooks = userService.getBookBorrowByUser(cus.getUser().getIdUser());
        return  userBorrowBooks;
    }
}
