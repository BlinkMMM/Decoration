/**
 * 
 */
package com.decoration.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.decoration.entity.User;
import com.decoration.service.UserService;
import com.decoration.service.UtilService;

/**
 * @author zhenghan
 * 2017年4月2日 
 * 上午11:58:14
 *
 */
@SessionAttributes(value={"loginUser"})
@RequestMapping("/user")
@Scope("prototype")
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView login(String userName,String password,HttpSession session){
		ModelAndView mv = userService.login(userName, password,session);
		return mv;
	}
	
	@RequestMapping(value="/register" , method = RequestMethod.POST)
	public ModelAndView register(User user){
		ModelAndView mv = userService.registerUser(user);
		return mv;
	}
	@RequestMapping(value="/logout" , method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session){
		ModelAndView mv = userService.logout(session);
		return mv;
	}
	@RequestMapping(value="/changePassInfo" , method = RequestMethod.GET)
	public ModelAndView showChangePassInfo(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("page","changePass");
		mv.setViewName("/home");
		return mv;
	}
	@RequestMapping(value="/changePass" , method = RequestMethod.POST)
	public ModelAndView changePass(String oldPass,String newPass,String reNewPass,HttpSession session){
		ModelAndView mv = userService.changePass(oldPass, newPass, reNewPass, session);
		return mv;
	}
	
	@RequestMapping(value="/reLogin",method = RequestMethod.GET)
	public ModelAndView reLogin(HttpSession session){
		ModelAndView mv = new ModelAndView();
		session.invalidate();
		mv.setViewName("/user/login");
		return mv;
	}
	
}
