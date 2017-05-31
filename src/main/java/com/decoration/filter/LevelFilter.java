/**
 * 
 */
package com.decoration.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.decoration.entity.User;

/**
 * @author zhenghan
 * 2017年5月28日 
 * 下午9:28:58
 *
 */
public class LevelFilter extends OncePerRequestFilter{
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("loginUser");
		String jobType = user.getJobType();
		switch(jobType){
		case "项目经理":
			
		case "施工人员":
			
		case "采购员":
			
		case "财务":
		}
		
	}

}
