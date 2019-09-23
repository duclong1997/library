package com.book.library.controller.user;

import com.book.library.models.BookBrowedUserModel;
import com.book.library.models.CustomUser;
import com.book.library.models.Message;
import com.book.library.service.BookBrowService;
import com.book.library.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BrowBookController {

	@Autowired
	BookBrowService bookBrowService;

	// brow list book
//	@Async
	@PostMapping("/user/userBrow")
	public synchronized Message getBrowBook(@RequestBody List<BookBrowedUserModel> listBookBrowed){
		Message message = new Message();
		try {
			// get authentication
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			// check authentication
			if(auth.getPrincipal().equals(Common.User.ANOUNYMOUS_USER)){
				message.setStatus(Common.Status.ERROR_SERVER);
				message.setMessage(Common.User.UNAUTHORIZED);
				return message;
			}else {
				CustomUser cus = (CustomUser) auth.getPrincipal();
				bookBrowService.borrowBook(cus.getUser(), listBookBrowed);
			}
		}catch (Exception ex){
				message.setStatus(Common.Status.BAD_REQUEST);
				message.setMessage(ex.getMessage());
				return message;
		}
		message.setStatus(Common.Status.OK);
		message.setMessage(Common.BookBorrow.BORROWED_SUCCESSFULLY);
		return message;
	}

	// return list books
	@PostMapping("/user/userReturn")
	public synchronized Message getReturnBook(@RequestBody List<BookBrowedUserModel> listBookBrowed){
		Message message = new Message();
		try {
			// get authentication
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			// check authentication
			if(auth.getPrincipal().equals(Common.User.ANOUNYMOUS_USER)){
				message.setStatus(Common.Status.ERROR_SERVER);
				message.setMessage(Common.User.UNAUTHORIZED);
				return message;
			}else {
				CustomUser cus = (CustomUser) auth.getPrincipal();
				bookBrowService.returnBook(cus.getUser(), listBookBrowed);
			}
		}catch (Exception ex){
			message.setStatus(Common.Status.BAD_REQUEST);
			message.setMessage(ex.getMessage());
			return message;
		}
		message.setStatus(Common.Status.OK);
		message.setMessage(Common.BookBorrow.RETURNED_SUCCESSFULLY);
		return message;
	}
}
