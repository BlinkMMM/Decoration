/**
 * 
 */
package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Test
	public void testFindAllProject(){
		List<Project> list = proDao.findAllProject();
		for(Project p: list){
			System.out.println(p);
		}
	}
	@Test
	public void testFindAllProjectByCondition(){
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("projectName", "项目2");
		map.put("customer", "项目");
		List<Project> list = proDao.findAllProjectByCondition(map);
		for(Project p: list){
			System.out.println(p);
		}
	}
}
