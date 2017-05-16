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

import com.decoration.dao.ProjectDao;
import com.decoration.dao.UserDao;
import com.decoration.dao.WorkRecordDao;
import com.decoration.entity.Project;
import com.decoration.entity.User;
import com.decoration.entity.WorkRecord;

import util.Page;

/**
 * @author zhenghan
 * 2017年4月19日 
 * 下午10:35:41
 *
 */
public class TestWorkRecordDao {
	private ApplicationContext ctx;
	private WorkRecordDao recordDao;
	private UserDao userDao;
	private ProjectDao projectDao;
	@Before
	public void setUp(){
		ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		recordDao = ctx.getBean(WorkRecordDao.class);
		userDao = ctx.getBean(UserDao.class);
		projectDao = ctx.getBean(ProjectDao.class);
	}
	
	@Test
	public void testSaveWorkRecord(){
		WorkRecord record = new WorkRecord();
		record.setCheckDate(new Date());
		record.setIsFullWork(1);
		record.setRemark("无");
		User user = userDao.findUserById(2);
		Project project = projectDao.findProByName("项目1");		
		record.setRecordProject(project);
		record.setRecordUser(user);
		boolean isOk = recordDao.saveWorkRecord(record);
		System.out.println("record = " + isOk);
	}
	@Test
	public void testDeleteWorkRecordById(){
		boolean isOk = recordDao.deleteWorkRecordById(1);
		System.out.println("isOk = " + isOk);
	}
	
	@Test
	public void testFindAllWorkRecord() {
		List<WorkRecord> list = recordDao.findAllWorkRecord();
		for(WorkRecord w : list){
			System.out.println(w);
		}
	}
	@Test
	public void testFindUserAllRecordByUserIdAndPage() {
		Page page = new Page(10, 2);
		List<WorkRecord> list = recordDao.findUserAllRecordByUseIdAndByPage(1,page.getStartCode(),page.getPageSize());
		for(WorkRecord w : list){
			System.out.println(w);
		}
	}
	@Test
	public void testFindUserAllRecordByUserId() {
		List<WorkRecord> list = recordDao.findUserAllRecordByUseId(1);
		for(WorkRecord w : list){
			System.out.println(w);
		}
	}
	@Test
	public void testFindRecordByUserIdAndDate() {
		WorkRecord workRecord = 
		recordDao.findRecordById(7);
		
		System.out.println("workRecord = " + workRecord);
		
		workRecord.setCheckDate(workRecord.getCheckDate());
		User user = userDao.findUserById(1);
		
		System.out.println("userId = " + user.getUserId());
		workRecord.setRecordUser(user);
		
		WorkRecord record = recordDao.findRecordByUserIdAndDate(workRecord);
		
		System.out.println("record = " + record);
	}
}
