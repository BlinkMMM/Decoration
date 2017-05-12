/**
 * 
 */
package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.decoration.bean.WageCostBean;
import com.decoration.dao.CostDao;
import com.decoration.dao.FlowDao;
import com.decoration.dao.MaterialDao;
import com.decoration.dao.ProjectDao;
import com.decoration.dao.UserDao;

/**
 * @author zhenghan
 * 2017年4月15日 
 * 下午10:58:08
 *
 */
public class TestCostDao {
	private ApplicationContext ctx;
	private MaterialDao matDao;
	private ProjectDao proDao;
	private FlowDao flowDao;
	private UserDao userDao;
	private CostDao costDao;
	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		matDao = ctx.getBean(MaterialDao.class);
		proDao = ctx.getBean(ProjectDao.class);
		flowDao = ctx.getBean(FlowDao.class);
		userDao = ctx.getBean(UserDao.class);
		costDao = ctx.getBean(CostDao.class);
	}
	@Test
	public void testFindWageCost(){
		List<WageCostBean> list = costDao.findWageCost();
		for(WageCostBean w : list){
			System.out.println(w);
		}
	}
}
