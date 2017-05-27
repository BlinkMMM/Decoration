 /**
 * 
 */
package com.decoration.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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
import com.decoration.service.MaterialService;
import com.decoration.service.UtilService;

import util.DictionaryItems;
import util.Page;

/**
 * @author zhenghan 2017年4月14日 下午11:03:46
 *
 */
@Service(value = "materialService")
@Scope("prototype")
public class MaterialServiceImpl implements MaterialService {
	@Autowired
	private MaterialDao materialDao;
	@Autowired
	private ProjectDao proDao;
	@Autowired
	private FlowDao flowDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UtilService utilService;
	@Autowired
	private HttpSession session;

	// ===========购买材料========================================================================================
	@Override
	public ModelAndView findAllMatBean() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<MaterialBean> list = materialDao.findMatBean(map);
		ModelAndView mv = new ModelAndView();
		mv.addObject("matData", list);
		return mv;
	}

	/**
	 * 分页查询购买材料
	 */
	@Override
	public ModelAndView findMatBeanByPage(Page page,String searchName) {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> map = this.checkSearchNameForBuy(searchName);
		List<MaterialBean> list = materialDao.findMatBean(map);
		page = new Page(list.size(),page.getCurrentPageCode());
		session.setAttribute("matPage", page);
		
		List<MaterialBean> pageList = materialDao.findMatBeanByPage(page,map);
		mv.addObject("matPageData",pageList);
		return mv;
	}

	@Override
	public ModelAndView saveMaterialBean(MaterialBean matBean) {
		ModelAndView mv = new ModelAndView();
		String matName = matBean.getMatName();
		String proName = matBean.getMatProject().getProjectName();
		String flowName = matBean.getMatFlow().getFlowName();
		Project project = proDao.findProByName(proName);
		Flow flow = flowDao.findFlowByName(flowName);
		Date buyDate = matBean.getMatBuyDate();
		utilService.checkDateIsValid(buyDate);
		User user = (User)session.getAttribute("loginUser");
		if (project != null && flow != null && user != null) {
			matBean.setMatProject(project);
			matBean.setMatFlow(flow);
			matBean.setMatUser(user);
			Material mat = new Material();
			mat.setMaterialName(matName);
			mat.setMatProjectId(project.getProjectId());
			boolean matIsExist = this.checkMatIsExistByNameAndProductId(mat);
			if (matIsExist == true) {
				mv.addObject("result", false);
				mv.addObject("reason", "材料已存在！！");
				mv.addObject("page", "addInfo");
			} else {
				materialDao.saveMatBean(matBean);
				mv = this.findMatByPageAfterOperation(mv);
				mv.addObject("page", "buy");
			}
		} else {
			mv.addObject("result", false);
			mv.addObject("reason", "输入的信息有误，请重新输入！");
			mv.addObject("page", "addInfo");
		}
		return mv;
	}

	@Override
	public ModelAndView deleteMatById(int matId) {
		ModelAndView mv = new ModelAndView();
		materialDao.deleteMatById(matId);
		mv = this.findMatByPageAfterOperation(mv);
		return mv;
	}

	@Override
	public ModelAndView updateMatBean(MaterialBean matBean) {
		ModelAndView mv = new ModelAndView();
		String matName = matBean.getMatName();
		String proName = matBean.getMatProject().getProjectName();
		String flowName = matBean.getMatFlow().getFlowName();
		Project project = proDao.findProByName(proName);
		Flow flow = flowDao.findFlowByName(flowName);
		if (project != null && flow != null) {
			matBean.setMatProject(project);
			matBean.setMatFlow(flow);
			Material mat = new Material();
			mat.setMaterialName(matName);
			mat.setMatProjectId(project.getProjectId());
			boolean matIsExist = this.checkMatIsExistByNameAndProductId(mat);
			if (matIsExist == true) {
				mv.addObject("result", false);
				mv.addObject("reason", "材料已存在！！");
			} else {
				materialDao.updateMatBean(matBean);
				mv = this.findMatByPageAfterOperation(mv);
			}
		} else {
			mv.addObject("result", false);
			mv.addObject("reason", "输入的信息有误，请重新输入！");
		}
		return mv;
	}
	// ==============材料进场=====================================================================

	@Override
	public ModelAndView findAllMatEnter() {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> map =  new HashMap<String,Object>();
		List<MaterialEnter> enterList = materialDao.findAllMatEnter(map);
		mv.addObject("enterData", enterList);
		return mv;
	}
	
	@Override
	public ModelAndView findAllMatEnterByPage(Page page,String searchName) {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> map =  this.checkSearchNameForEnter(searchName);
		List<MaterialEnter> list = materialDao.findAllMatEnter(map);
		page = new Page(list.size(),page.getCurrentPageCode());
		session.setAttribute("enterPage", page);
		
		List<MaterialEnter> pageList = materialDao.findAllMatEnterByPage(page,map);
		mv.addObject("enterPageData",pageList);
		return mv;
	}

	@Override
	public ModelAndView saveMatEnter(MaterialEnter matEnter) {
		ModelAndView mv = new ModelAndView();

		String enterName = matEnter.getEnterMat().getMatName();
		String projectName = matEnter.getEnterProject().getProjectName();

		User user = (User)session.getAttribute("loginUser");
		Project project = proDao.findProByName(projectName);
		if (user != null && project != null) {
			Material material = new Material();
			material.setMaterialName(enterName);
			material.setMatProjectId(project.getProjectId());
			material = materialDao.findMatByNameAndProjectId(material);

			matEnter.setEnterMat(materialDao.findMatBeanById(material.getMaterialId()));
			matEnter.setEnterProject(project);
			matEnter.setEnterUser(user);

			boolean matIsExist = this.checkMatIsExistByNameAndProductId(material);
			if (matIsExist == false) {
				mv.addObject("result", false);
				mv.addObject("reason", "材料不存在！！");
				mv.addObject("page", "enterAddInfo");
			} else if (matEnter.getEnterNum() > material.getMaterialNum()) {
				mv.addObject("result", false);
				mv.addObject("reason", "进场材料大于库存，有误！！");
				mv.addObject("page", "enterAddInfo");
			} else {
				materialDao.saveMatEnter(matEnter);
				mv = this.findMatEnterByPageAfterOperation(mv);
				mv.addObject("page", "enter");
			}
		} else {
			mv.addObject("result", false);
			mv.addObject("reason", "输入信息有误，请重新输入！");
			mv.addObject("page", "enterAddInfo");
		}
		return mv;
	}

	@Override
	public ModelAndView deleteEnterMatById(int enterId) {
		ModelAndView mv = new ModelAndView();
		materialDao.deleteMatEnterById(enterId);
		mv = this.findMatEnterByPageAfterOperation(mv);
		return mv;
	}

	@Override
	public ModelAndView updateMatEnter(MaterialEnter matEnter) {
		ModelAndView mv = new ModelAndView();
		String enterName = matEnter.getEnterMat().getMatName();
		String projectName = matEnter.getEnterProject().getProjectName();
		Project project = proDao.findProByName(projectName);
		if (project != null) {
			Material material = new Material();
			material.setMaterialName(enterName);
			material.setMatProjectId(project.getProjectId());
			material = materialDao.findMatByNameAndProjectId(material);

			matEnter.setEnterMat(materialDao.findMatBeanById(material.getMaterialId()));
			matEnter.setEnterProject(project);

			boolean matIsExist = this.checkMatIsExistByNameAndProductId(material);
			if (matIsExist == false) {
				mv.addObject("result", false);
				mv.addObject("reason", "材料不存在！！");
			} else if (matEnter.getEnterNum() > material.getMaterialNum()) {
				mv.addObject("result", false);
				mv.addObject("reason", "进场材料大于库存，有误！！");
			} else {
				materialDao.updateMatEnter(matEnter);
				mv = this.findMatEnterByPageAfterOperation(mv);
			}
		} else {
			mv.addObject("message", "输入的信息有误，请重新输入！");
		}
		return mv;
	}

	// ===========材料使用=============================================================================
	@Override
	public ModelAndView findAllMatUse() {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> map = new HashMap<String,Object>();
		List<MaterialUse> useList = materialDao.findAllMatUse(map);
		mv.addObject("useData",useList);
		return mv;
	}
	/**
	 * 分页查询使用材料
	 */
	@Override
	public ModelAndView findAllMatUseByPage(Page page,String searchName) {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> map = this.checkSearchNameForUse(searchName);
		List<MaterialUse> list = materialDao.findAllMatUse(map);
		page = new Page(list.size(),page.getCurrentPageCode());
		session.setAttribute("usePage", page);
		
		List<MaterialUse> pageList = materialDao.findAllMatUseByPage(page,map);
		mv.addObject("usePageData",pageList);
		return mv;
	}

	@Override
	public ModelAndView saveMatUse(MaterialUse matUse) {
		ModelAndView mv = new ModelAndView();
		double useNum = matUse.getUseNum();
		String matName = matUse.getUseMat().getMatName();
		String proName = matUse.getUseProject().getProjectName();
		System.out.println("matName = " + matName);
		System.out.println("proName = " + proName);
		Project project = proDao.findProByName(proName);
		User user = (User)session.getAttribute("loginUser");
		
		if(project != null && user != null){
			Material material = new Material();
			material.setMatProjectId(project.getProjectId());
			material.setMaterialName(matName);
			material = materialDao.findMatByNameAndProjectId(material);
			System.out.println("material = " + material);
			matUse.setUseMat(materialDao.findMatBeanById(material.getMaterialId()));
			matUse.setUseProject(project);
			matUse.setUseUser(user);
			
			boolean matIsExist = this.checkMatIsExistByNameAndProductId(material);
			if (matIsExist == false) {
				mv.addObject("result", false);
				mv.addObject("reason", "材料不存在！！");
				mv.addObject("page", "useAddInfo");
			} else if (useNum > material.getMaterialNum()) {
				mv.addObject("result", false);
				mv.addObject("reason", "进场材料大于库存，有误！！");
				mv.addObject("page", "useAddInfo");
			} else {
				System.out.println(matUse);
				materialDao.saveMatUse(matUse);
				mv = this.findMatUseByPageAfterOperation(mv);
				mv.addObject("page", "use");
			}
		} else {
			mv.addObject("result", false);
			mv.addObject("reason", "输入信息有误，请重新输入！");
			mv.addObject("page", "useAddInfo");
		}
		return mv;
	}

	@Override
	public ModelAndView deleteUseMatById(int useId) {
		ModelAndView mv = new ModelAndView();
		materialDao.deleteMatUseById(useId);
		mv = this.findMatUseByPageAfterOperation(mv);
		return mv;
	}

	@Override
	public ModelAndView updateMatUse(MaterialUse matUse) {
		ModelAndView mv = new ModelAndView();
		double useNum = matUse.getUseNum();
		String matName = matUse.getUseMat().getMatName();
		String proName = matUse.getUseProject().getProjectName();
		
		Project project = proDao.findProByName(proName);
		if(project != null){
			Material material = new Material();
			material.setMatProjectId(project.getProjectId());
			material.setMaterialName(matName);
			material = materialDao.findMatByNameAndProjectId(material);
			
			matUse.setUseMat(materialDao.findMatBeanById(material.getMaterialId()));
			matUse.setUseProject(project);
			
			boolean matIsExist = this.checkMatIsExistByNameAndProductId(material);
			if (matIsExist == false) {
				mv.addObject("result", false);
				mv.addObject("reason", "材料不存在！！");
			} else if (useNum > material.getMaterialNum()) {
				mv.addObject("result", false);
				mv.addObject("reason", "进场材料大于库存，有误！！");
			} else {
				materialDao.updateMatUse(matUse);
				mv = this.findMatUseByPageAfterOperation(mv);
			}
		} else {
			mv.addObject("result", false);
			mv.addObject("reason", "输入信息有误，请重新输入！");
		}
		return mv;
	}

	// ========================================================================================
	/**
	 * 根据材料名称和项目编号检查材料是否已经存在
	 * 
	 * @param material
	 * @return
	 */
	public boolean checkMatIsExistByNameAndProductId(Material material) {
		material = materialDao.findMatByNameAndProjectId(material);
		if (material == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 在购买材料模块执行操作后分页查出数据
	 * @param mv
	 */
	public ModelAndView findMatByPageAfterOperation(ModelAndView mv){
		Map<String,Object> map = new HashMap<String,Object>();

		List<MaterialBean> list = materialDao.findMatBean(map);
		Page page = (Page)session.getAttribute("matPage");
		Page page2 = new Page(list.size(), 1);
		int currentPage = 0;
		if(page.getTotalPages() == page2.getTotalPages()){
			currentPage = page.getTotalPages();
		}else{
			currentPage = page2.getTotalPages();
		}
		page.setCurrentPageCode(currentPage);
		mv = this.findMatBeanByPage(page,"");
		return mv;
	}
	/**
	 * 在材料进场模块执行操作后分页查出数据
	 * @param mv
	 */
	public ModelAndView findMatEnterByPageAfterOperation(ModelAndView mv){
		Map<String,Object> map = new HashMap<>();
		List<MaterialEnter> list = materialDao.findAllMatEnter(map);
		Page page = (Page)session.getAttribute("enterPage");
		Page page2 = new Page(list.size(), 1);
		int currentPage = 0;
		if(page.getTotalPages() == page2.getTotalPages()){
			currentPage = page.getTotalPages();
		}else{
			currentPage = page2.getTotalPages();
		}
		page.setCurrentPageCode(currentPage);
		mv = this.findAllMatEnterByPage(page,"");
		return mv;
	}
	/**
	 * 在材料使用模块执行操作后分页查出数据
	 * @param mv
	 */
	public ModelAndView findMatUseByPageAfterOperation(ModelAndView mv){
		Map<String,Object> map = new HashMap<String,Object>();
		List<MaterialUse> list = materialDao.findAllMatUse(map);
		Page page = (Page)session.getAttribute("usePage");
		Page page2 = new Page(list.size(), 1);
		int currentPage = 0;
		if(page.getTotalPages() == page2.getTotalPages()){
			currentPage = page.getTotalPages();
		}else{
			currentPage = page2.getTotalPages();
		}
		page.setCurrentPageCode(currentPage);
		mv = this.findAllMatUseByPage(page,"");
		return mv;
	}
	
	public Map<String,Object> checkSearchNameForBuy(String searchName){
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		Map<String,Object> matMap = new HashMap<String,Object>();
		matMap.put("matName", searchName);
		List<MaterialBean> matList = materialDao.findMatBean(matMap);
		
		Map<String,Object> flowMap = new HashMap<String,Object>();
		flowMap.put("flowName", searchName);
		List<MaterialBean> matList2 = materialDao.findMatBean(flowMap);
		
		if(matList.size()!=0 && matList2.size()==0){
			map.put("matName", searchName);
		}else if(matList.size()==0 && matList2.size()!=0){
			map.put("flowName", searchName);
		}else{
			map.put("matName",searchName);
			map.put("flowName",searchName);
		}
		return map;
	}
	public Map<String,Object> checkSearchNameForEnter(String searchName){
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		Map<String,Object> matMap = new HashMap<String,Object>();
		matMap.put("matName", searchName);
		List<MaterialEnter> matList = materialDao.findAllMatEnter(matMap);
		
		Map<String,Object> flowMap = new HashMap<String,Object>();
		flowMap.put("flowName", searchName);
		List<MaterialEnter> matList2 = materialDao.findAllMatEnter(flowMap);
		
		if(matList.size()!=0 && matList2.size()==0){
			map.put("matName", searchName);
		}else if(matList.size()==0 && matList2.size()!=0){
			map.put("flowName", searchName);
		}else{
			map.put("matName",searchName);
			map.put("flowName",searchName);
		}
		return map;
	}
	public Map<String,Object> checkSearchNameForUse(String searchName){
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		Map<String,Object> matMap = new HashMap<String,Object>();
		matMap.put("matName", searchName);
		List<MaterialUse> matList = materialDao.findAllMatUse(matMap);
		
		Map<String,Object> flowMap = new HashMap<String,Object>();
		flowMap.put("flowName", searchName);
		List<MaterialUse> matList2 = materialDao.findAllMatUse(flowMap);
		
		if(matList.size()!=0 && matList2.size()==0){
			map.put("matName", searchName);
		}else if(matList.size()==0 && matList2.size()!=0){
			map.put("flowName", searchName);
		}else{
			map.put("matName",searchName);
			map.put("flowName",searchName);
		}
		return map;
	}

}
