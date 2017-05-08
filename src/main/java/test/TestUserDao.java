/**
 * 
 */
package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.decoration.dao.UserDao;
import com.decoration.entity.User;

/**
 * @author zhenghan
 * 2017年4月16日 
 * 上午12:14:06
 *
 */
public class TestUserDao {
	private ApplicationContext ctx;
	private UserDao userDao;
	
	@Before
	public void setUp(){
		ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		userDao = ctx.getBean(UserDao.class);
	}
	
	@Test
	public void saveUser(){
		User user = new User("张三","123","工人","18388888888","qqq@123.com");
		boolean isOk = userDao.saveUser(user);
		System.out.println("isOk = " + isOk);
	}
	@Test
	public void updateUser(){
		User user = new User("张三","123","工人","18388888888","qqq@123.com");
		boolean isOk = userDao.updateUser(user);
		System.out.println("isOk = " + isOk);
	}
	@Test
	public void findUserByID(){
		User user = userDao.findUserById(2);
		System.out.println("user = " + user);
	}
	@Test
	public void findUserByName(){
		User user = userDao.findUserByName("张三");
		System.out.println("user = " + user);
	}
	@Test
	public void findAllUser(){
		List<User> userList = userDao.findAllUser();
		for(User u:userList){
			System.out.println(u);
		}
	}
	/**
	 * 
	 */
}
