package com.book.library.controller.user;

import com.book.library.models.UserRegister;
import com.book.library.service.UserService;
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
	public String registerUser(@RequestBody UserRegister userRegister)
	{
		try{
		userService.insertUser(userRegister);
		}catch(Exception ex)
		{
			return ex.getMessage();
		}
		return "register successfullly!";
	}
}
