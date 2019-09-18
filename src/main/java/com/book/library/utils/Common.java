package com.book.library.utils;

public class Common {
	// comomn for jwt
	public static final String USERNAME = "username";
	public static final String SECRET_KEY = "motHain@43SvN&djdlksawedj2^2csq3";
	public static final int EXPIRE_TIME = 86400000;
	public final static String TOKEN_HEADER = "Authorization";
	
	// comment status http
	public static final class Status{
		public static final int OK =200;
		public static final int BAD_REQUEST =400;
		public static final int NOT_FOUND =401;
		public static final int ERROR_SERVER =500;
		public static final int ACCESS_DENIED =403;
	}
	// common message
	public static final class MessageStatus{
		public static final String ADD_SUCCESS = "Thêm thành công!";
		public static final String UPDATE_SUCCESS = "Thay đổi thành công!";
		public static final String DELETE_SUCCESS = "Xoá thành công!";
	}

	// common book
	public static final  class  Book{
		public  static  final int CHECK_BORROWED =1;
		public  static  final  int CHECK_NOT_BORROWED =0;
		public  static  final  int BORROWED =2;
		public  static  final  int RETURN=1;
		public  static final int DELETE =0;
		public static  final boolean CHECK_BOOK_BORROWED = true;
		public static final boolean CHECK_BOOK_NOT_BORROWED = false;
		public  static final String BOOK_BORROWED ="Sách đã đươc mượn";
		public static final String BOOK_RETURN ="Sách chưa được mượn";
	}

	// common book borrow
	public static final class BookBorrow{
		public static final int RETURN_BOOK=0;
		public  static  final int RETURN_BOOK_LATE=2;
	}

	// common user
	public  static final class User{
		public static final String MESSAGE_LOGIN_FALSE="Username hoặc password không đúng!";
		public static final String MESSAGE_REGISTER_SUCCESS="register successfully!";
		public static final String UNAUTHORIZED ="Unauthorized";
		public static final String ACCESS_DENIED ="Access Denied!";
	}
}
