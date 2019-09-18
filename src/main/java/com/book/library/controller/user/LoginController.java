package com.book.library.controller.user;

import com.book.library.entity.User;
import com.book.library.models.Message;
import com.book.library.models.RequestLogin;
import com.book.library.security.JwtService;
import com.book.library.service.UserService;
import com.book.library.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtService jwtService;
	
	// login
	@PostMapping("/login")
	public Message Login(@RequestBody RequestLogin request)
	{
		Message message = new Message();
		User user = userService.getUserByUsernamePassword(request.getUsername(), request.getPassword());
		if(user==null || user.getEnable() !=1)
		{
			message.setMessage(Common.User.MESSAGE_LOGIN_FALSE);
			message.setStatus(Common.Status.BAD_REQUEST);
			return message;
		}
		else{
			// response token to user
			String token = jwtService.generateTokenLogin(user.getUsername());
			message.setMessage(token);
			message.setStatus(Common.Status.OK);
			return message;
		}
	}

}
