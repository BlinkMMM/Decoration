package com.decoration.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.decoration.entity.User;

/**
 * Servlet Filter implementation class SessionFilter
 */
public class SessionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//不过滤的uri
		String[] noFilter = new String[]{"login", "css", "js"};
		//请求的uri
		String uri = request.getRequestURI();
		boolean doFilter = true;
		for(String s : noFilter) {
			if(uri.indexOf(s) != -1) {
				doFilter = false;
				break;
			}
		}
		if(doFilter) {
			//执行过滤,判断loginUser是否在session里
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(loginUser == null) {
				//重定向到登录页
				response.sendRedirect("/user/login");
			} else {
				//放行
				filterChain.doFilter(request, response);
			}
		} else {
			filterChain.doFilter(request, response);
		}
		
	}


}
