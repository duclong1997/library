package com.book.library.controller.user;

import com.book.library.models.CustomUser;
import com.book.library.models.Message;
import com.book.library.models.UserModel;
import com.book.library.service.UserService;
import com.book.library.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
