/**
 * 
 */
package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.decoration.dao.FlowDao;
import com.decoration.entity.Flow;

/**
 * @author zhenghan
 * 2017年4月19日 
 * 下午10:35:41
 *
 */
public class TestFlowDao {
	private ApplicationContext ctx;
	private FlowDao flowDao;
	@Before
	public void setUp(){
		ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		flowDao = ctx.getBean(FlowDao.class);
	}
	
	@Test
	public void testFindFlowByName(){
		Flow flow = flowDao.findFlowByName("木工");
		System.out.println("flow = " + flow);
	}
}
