package com.decoration.dao;

import java.util.List;

import com.decoration.entity.User;

/**
 * @author zhenghan
 * 2017年3月25日 
 * 下午10:12:34
 *
 */
public interface UserDao {
	/**
	 * 向用户表里添加一条记录
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user);
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user);
	/**
	 * 根据用户编号查找用户
	 * @param userId
	 * @return User
	 */
	public User findUserById(int userId);
	/**
	 * 根据用户姓名查找用户
	 * @param userName
	 * @return
	 */
	public User findUserByName(String userName);
	
	/**
	 * 查找所有用户
	 * @return
	 */
	public List<User> findAllUser();
}
