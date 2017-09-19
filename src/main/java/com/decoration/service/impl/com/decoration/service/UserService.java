/**
 * 
 */
package com.decoration.service;

import com.decoration.entity.User;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zhenghan
 * 2017年4月2日 
 * 上午11:00:11
 *
 */
public interface UserService {
	/**
	 * 注册用户
	 * @param user sth
	 * @return ModelAndView
	 */
	ModelAndView registerUser(User user);
	
	/**
	 * 验证登录
	 * @param userName s
	 * @param password s
	 * @return ModelAndView
	 */
	ModelAndView login(String userName,String password,HttpSession session);
	
	ModelAndView logout(HttpSession session);
	
	ModelAndView updateUser();
	
	/**
	 * 根据用户id查找用户
	 * @param userId sth
	 * @return user
	 */
	User findUserById(int userId);
	/**
	 * 查找所有用户
	 * @return list
	 */
	List<User> findAllUser();
	
	ModelAndView changePass(String oldPass,String newPass,String reNewPass,HttpSession session);
}
