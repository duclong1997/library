package com.book.library.controller.user;

import com.book.library.models.Message;
import com.book.library.models.UserRegister;
import com.book.library.service.UserService;
import com.book.library.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public synchronized Message registerUser(@RequestBody UserRegister userRegister)
	{
		Message message = new Message();
		try{
		userService.insertUser(userRegister);
		}catch(Exception ex)
		{
			message.setStatus(Common.Status.BAD_REQUEST);
			message.setMessage(ex.getMessage());
			return message;
		}
		message.setStatus(Common.Status.OK);
		message.setMessage(Common.User.MESSAGE_REGISTER_SUCCESS);
		return message;
	}
}
