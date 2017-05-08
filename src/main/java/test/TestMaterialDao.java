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

import com.decoration.bean.MaterialBean;
import com.decoration.dao.FlowDao;
import com.decoration.dao.MaterialDao;
import com.decoration.dao.ProjectDao;
import com.decoration.dao.UserDao;
import com.decoration.entity.Flow;
import com.decoration.entity.Material;
import com.decoration.entity.MaterialEnter;
import com.decoration.entity.MaterialUse;
import com.decoration.entity.Project;
import com.decoration.entity.User;

/**
 * @author zhenghan
 * 2017年4月15日 
 * 下午10:58:08
 *
 */
public class TestMaterialDao {
	private ApplicationContext ctx;
	private MaterialDao matDao;
	private ProjectDao proDao;
	private FlowDao flowDao;
	private UserDao userDao;
	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		matDao = ctx.getBean(MaterialDao.class);
		proDao = ctx.getBean(ProjectDao.class);
		flowDao = ctx.getBean(FlowDao.class);
		userDao = ctx.getBean(UserDao.class);
	}
	/**
	 * material CRUD
	 */
	@Test
	public void saveMaterial(){
		Material mat = new Material("木板",10,"根",20,"xx",1,2,3,new Date());
		boolean isOk = matDao.saveMaterial(mat);
		System.out.println("isOk="+isOk);
	}
	
	@Test
	public void deleteMatById(){
		int matId = 2;
		boolean isOk = matDao.deleteMatById(matId);
		System.out.println("isOk = " + isOk);
	}
	@Test
	public void updateMaterial() {
		Material mat = new Material("da木板",10,"w3根",2770,"xx",1,2,3,new Date());
		mat.setMaterialId(1);
		boolean isOk = matDao.updateMaterial(mat);
		System.out.println("isOk = " + isOk);
	}
	
	@Test
	public void findMatById(){
		int matId = 1;
		Material mat = matDao.findMatById(matId);
		System.out.println(mat);
	}
	@Test
	public void findMatByName(){
		Material mat = matDao.findMatByName("da木板");
		System.out.println(mat);
	}
	@Test
	public void findMatByNameAndProjectId(){
		Material mat = new Material();
		mat.setMaterialName("水管");
		mat.setMatProjectId(2);
		mat = matDao.findMatByNameAndProjectId(mat);
		System.out.println(mat);
	}
	@Test
	public void findAllMat(){
		List<Material> matList = matDao.findAllMat();
		for(Material mat:matList){
			System.out.println(mat);
		}
	}
	
	/**
	 * materialEnter CRUD
	 */
	@Test
	public void saveMatEnter(){
		MaterialBean bean = matDao.findMatBeanById(3);
		User user = userDao.findUserById(1);
		Project project = proDao.findProByName("项目1");
		Flow flow = flowDao.findFlowByName("木工");
		bean.setMatFlow(flow);
		bean.setMatProject(project);
		System.out.println("bean = " + bean);
		MaterialEnter matEnter = new MaterialEnter(bean, 23, new Date(), user,project);
	
		boolean isOk = matDao.saveMatEnter(matEnter);
		System.out.println("isOk = " + isOk);
	}
	
	@Test
	public void deleteMatEnterById(){
		int matEnterId = 1;
		boolean isOk = matDao.deleteMatEnterById(matEnterId);
		System.out.println("isOk = " + isOk);
	}
	@Test
	public void updateMatEnter() {
		MaterialEnter matEnter = new MaterialEnter();
		matEnter.setEnterId(1);
		boolean isOk = matDao.updateMatEnter(matEnter);
		System.out.println("isOk = " + isOk);
	}
	
	@Test
	public void findMatEnterById(){
		int matEnterId = 3;
		MaterialEnter matEnter = matDao.findMatEnterById(matEnterId);
		System.out.println(matEnter);
	}
	@Test
	public void findAllMatEnter(){
		List<MaterialEnter> matList = matDao.findAllMatEnter();
		for(MaterialEnter mat:matList){
			System.out.println(mat);
		}
	}
	
	/**
	 * materialUse CRUD
	 */
	@Test
	public void saveMatUse(){
		MaterialBean bean = matDao.findMatBeanById(3);
		Project project = proDao.findProByName("项目1");
		User user = userDao.findUserByName("张三");
		MaterialUse matUse = new MaterialUse(bean, 20, 0.8, new Date(), project, user);
		boolean isOk = matDao.saveMatUse(matUse);
		System.out.println("isOk = " + isOk);
	}
	
	@Test
	public void deleteMatUseById(){
		int matUseId = 3;
		boolean isOk = matDao.deleteMatUseById(matUseId);
		System.out.println("isOk = " + isOk);
	}
	@Test
	public void updateMatUse() {
		MaterialBean bean = matDao.findMatBeanById(3);
		Project project = proDao.findProByName("项目1");
		User user = userDao.findUserByName("张三");
		MaterialUse matUse = new MaterialUse(bean, 1000, 0.9, new Date(), project, user);
		matUse.setUseId(3);
		boolean isOk = matDao.updateMatUse(matUse);
		System.out.println("isOk = " + isOk);
	}
	
	@Test
	public void findMatUseById(){
		int matUseId = 2;
		MaterialUse matUse = matDao.findMatUseById(matUseId);
		System.out.println(matUse);
	}
	@Test
	public void findAllMatUse(){
		List<MaterialUse> matList = matDao.findAllMatUse();
		for(MaterialUse mat:matList){
			System.out.println(mat);
		}
	}
	/**
	 * 查找MaterialBean
	 */
	@Test
	public void findMat(){
		List<MaterialBean> list = matDao.findMatBean();
		for(MaterialBean bean:list){
			System.out.println(bean);
		}
	}
	/**
	 * 模糊查找MaterialBean
	 */
	@Test
	public void findMatByCondition(){
		MaterialBean matBean = new MaterialBean();
		Project project = new Project();
		project = proDao.findProByName("项目1");
		Flow flow = new Flow();
		flow = flowDao.findFlowByName("木工");
		matBean.setMatProject(project);
		matBean.setMatFlow(flow);
		List<MaterialBean> list = matDao.findMatBeanByCondition(matBean);
		for(MaterialBean bean:list){
			System.out.println(bean);
		}
	}
	
	@Test
	public void saveMaterialBean(){
		Material mat = new Material("33木板",130,"根",20,"xfx",1,2,3,new Date());
		boolean isOk = matDao.saveMaterial(mat);
		System.out.println("isOk="+isOk);
	}
	@Test
	public void updateMaterialBean(){
		MaterialBean bean = matDao.findMatBeanById(8);
		bean.setMatName("xxx");
		boolean isOk = matDao.updateMatBean(bean);
		System.out.println("isOk = " + isOk);
	}
	@Test
	public void findMaterialBeanById(){
		MaterialBean bean = matDao.findMatBeanById(8);
		System.out.println("bean = " + bean);
		
	}
}
