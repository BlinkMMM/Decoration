/**
 * 
 */
package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.decoration.dao.ProjectDao;
import com.decoration.entity.Project;

/**
 * @author zhenghan
 * 2017年4月19日 
 * 下午10:35:41
 *
 */
public class TestProjectDao {
	private ApplicationContext ctx;
	private ProjectDao proDao;
	@Before
	public void setUp(){
		ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		proDao = ctx.getBean(ProjectDao.class);
	}
	
	@Test
	public void testFindProByName(){
		Project pro = proDao.findProByName("项目1");
		System.out.println("pro = " + pro);
	}
}
