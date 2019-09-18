package com.book.library.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.book.library.dao.UserDao;
import com.book.library.entity.User;
import com.book.library.models.CustomUser;
import com.book.library.utils.Common;


public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserDao userDAO;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(Common.TOKEN_HEADER);
		if (jwtService.validateTokenLogin(authToken)) {
			try {
				String username = jwtService.getUsernameFromToken(authToken);
				User user = userDAO.getUserByUsername(username);

				if (user != null) {
					CustomUser customeUser = new CustomUser();
					customeUser.setUser(user);
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							customeUser, null, customeUser.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} catch (Exception ex) {
				HttpServletResponse response1 = (HttpServletResponse) response;
				response1.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response1.getWriter().write(Common.User.UNAUTHORIZED);
			}
		}
		chain.doFilter(request, response);
	}
}
