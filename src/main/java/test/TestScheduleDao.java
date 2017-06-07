/**
 * 
 */
package test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.decoration.dao.FlowDao;
import com.decoration.dao.ProjectDao;
import com.decoration.dao.ScheduleDao;
import com.decoration.dao.UserDao;
import com.decoration.entity.CheckSchedule;
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
		Flow flow = flowDao.findFlowByName("电工类");
		User user = userDao.findUserById(1);
		Project project = proDao.findProByName("项目1");
		Schedule schedule = new Schedule(20, 10, 0.5, "做椅子",new Date(), project, flow, user);
		
		boolean isOk = scheduleDao.saveSchedule(schedule);
		System.out.println("isOk = " + isOk);
	}
	
	@Test
	public void testFindAllScedule(){
		Map<String,Object> map = new HashMap<String, Object>();
		//map.put("projectName", "项目2");
		map.put("flowName", "贴砖");
		List<Schedule> list = scheduleDao.findAllSchedule(map);
		for(Schedule s : list){
			System.out.println(s);
		}
	}
	
	@Test
	public void testDeleteSchedule(){
		boolean isOk = scheduleDao.deleleScheduleById(16);
		System.out.println("isOk = " + isOk);
	}
	
	@Test
	public void testSaveCheckSchedule(){
		CheckSchedule checkSchedule = new CheckSchedule();
		checkSchedule.setCheckState(2);
		checkSchedule.setCheckUser(userDao.findUserById(7));
		checkSchedule.setCheckDate(new Date());
		checkSchedule.setReason("做的不好");
		checkSchedule.setResponsibleParty("施工方责任");
		checkSchedule.setScheduleId(1);
		boolean isOk = scheduleDao.saveCheckSchedule(checkSchedule);
		System.out.println("isOk = " + isOk);
	}
	@Test
	public void testUpdateCheckSchedule(){
		CheckSchedule checkSchedule = new CheckSchedule();
		checkSchedule.setCheckState(2);
		checkSchedule.setCheckUser(userDao.findUserById(6));
		checkSchedule.setCheckDate(new Date());
		checkSchedule.setReason("做的太不好了");
		checkSchedule.setResponsibleParty("是施工方责任");
		checkSchedule.setScheduleId(1);
		checkSchedule.setCheckId(4);
		boolean isOk = scheduleDao.updateCheckSchedule(checkSchedule);
		System.out.println("isOk = " + isOk);
	}
	@Test
	public void testFindCheckSchedule(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("checkId", 1);
		List<CheckSchedule> list = scheduleDao.findCheckSchedule(map);
		System.out.println(list.size());
		//System.out.println(list.get(0));
	}
	
	@Test
	public void testFindLastSchedule(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("projectId", 1);
		map.put("flowId", 1);
		List<Schedule> list = scheduleDao.findLastSchedule(map);
		System.out.println(list.get(0));
		//System.out.println(list.get(0));
	}
}
