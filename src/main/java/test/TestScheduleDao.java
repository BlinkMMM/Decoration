/**
 * 
 */
package test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.decoration.dao.FlowDao;
import com.decoration.dao.ProjectDao;
import com.decoration.dao.ScheduleDao;
import com.decoration.dao.UserDao;
import com.decoration.entity.Flow;
import com.decoration.entity.Project;
import com.decoration.entity.Schedule;
import com.decoration.entity.User;

/**
 * @author zhenghan
 * 2017年4月19日 
 * 下午10:35:41
 *
 */
public class TestScheduleDao {
	private ApplicationContext ctx;
	private ScheduleDao scheduleDao;
	private ProjectDao proDao;
	private FlowDao flowDao;
	private UserDao userDao;
	@Before
	public void setUp(){
		ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		scheduleDao = ctx.getBean(ScheduleDao.class);
		proDao = ctx.getBean(ProjectDao.class);
		flowDao = ctx.getBean(FlowDao.class);
		userDao = ctx.getBean(UserDao.class);
	}
	
	@Test
	public void testSaveSchedule(){
		Flow flow = flowDao.findFlowByName("电工");
		User user = userDao.findUserById(1);
		Project project = proDao.findProByName("项目1");
		Schedule schedule = new Schedule(20, 10, 0.5, new Date(), project, flow, user);
		
		boolean isOk = scheduleDao.saveSchedule(schedule);
		System.out.println("isOk = " + isOk);
	}
	
	@Test
	public void testFindAllScedule(){
		List<Schedule> list = scheduleDao.findAllSchedule();
		for(Schedule s : list){
			System.out.println(s);
		}
	}
	
	@Test
	public void testDeleteSchedule(){
		boolean isOk = scheduleDao.deleleScheduleById(3);
		System.out.println("isOk = " + isOk);
	}
}
