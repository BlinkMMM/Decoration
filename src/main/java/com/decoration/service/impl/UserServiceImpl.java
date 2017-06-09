/**
 * 
 */
package com.decoration.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.decoration.dao.UserDao;
import com.decoration.entity.User;
import com.decoration.service.UserService;
import com.decoration.service.UtilService;

/**
 * @author zhenghan
 * 2017年4月2日 
 * 下午12:00:38
 *
 */
@Service(value = "userService")
@Scope("prototype")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private UtilService utilService;

	public ModelAndView registerUser(User user) {
		// 返回结果
				ModelAndView mv = new ModelAndView();
				// 根据用户名查重
				User existUser = userDao.findUserByName(user.getUserName());
				if (existUser != null) {
					// 用户名已存在
					mv.addObject("result", false);
					mv.addObject("reason", "用户名已存在");
					mv.addObject("existUser", existUser);
					mv.setViewName("/user/register");
					
				} else {
 					boolean result = userDao.saveUser(user);
					mv.addObject("result", result);
					mv.setViewName("/user/login");
					if (result == false){
						mv.addObject("reason", "注册用户失败");
						mv.setViewName("/user/register");
					}
				}
				return mv;
	}
	
	public ModelAndView login(String userName, String password,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User user = userDao.findUserByName(userName);
		if(user!=null&& !userName.equals("") && password!=null && !password.equals("")){
			if(user.getPassword().equals(password)){
				// 登录成功
				mv.addObject("result", true);
				mv.addObject("reason", "登陆成功！");
				utilService.initChooseProjectAndFlow();
				mv.addObject("loginUser", user);
				mv.addObject("page","first");
				mv.setViewName("/home");
			} else {
				mv.addObject("result", false);
				mv.addObject("reason", "用户名或密码错误!");
				mv.setViewName("/user/login");
			}
		}else{
			mv.addObject("result", false);
			mv.addObject("reason", "用户名或密码错误!");
			mv.setViewName("/user/login");
		}
		return mv;
	}
	
	/**
	 * 退出登录
	 */
	public ModelAndView logout(HttpSession session){
		ModelAndView mv = new ModelAndView();
		session.invalidate();
		mv.setViewName("/user/login");
		return mv;
	}
	/**
	 * 修改密码
	 */
	public ModelAndView changePass(String oldPass,String newPass,String reNewPass,HttpSession session){
		ModelAndView mv = new ModelAndView();
		User sessionUser = (User)session.getAttribute("loginUser");
		String userName = sessionUser.getUserName();
		
		User user = userDao.findUserByName(userName);
		if(userName!=null&& !userName.equals("") && oldPass!=null && !oldPass.equals("")){
			if(user.getPassword().equals(oldPass)){
				User newUser = new User();
				newUser = user;
				if(newPass.equals(reNewPass)){
					newUser.setPassword(newPass);
					userDao.updateUser(newUser);
					mv.addObject("result",true);
					mv.addObject("reason","密码修改成功！");
					session.removeAttribute("user");
					mv.setViewName("/user/login");
				}else{
					mv.addObject("reason", "两次密码输入不一致！");
					mv.addObject("page","changePass");
					mv.setViewName("/home");
				}
			} else {
				mv.addObject("reason","原密码输入不正确！");
				mv.addObject("page","changePass");
				mv.setViewName("/home");
			}
		}else{
			mv.addObject("result", false);
			mv.addObject("reason", "密码不能为空!");
			mv.addObject("page","changePass");
			mv.setViewName("/home");
		}
		return mv;
	}

	public ModelAndView updateUser() {
		// TODO Auto-generated method stub
		return null;
	}


	public User findUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return null;
	}




}
