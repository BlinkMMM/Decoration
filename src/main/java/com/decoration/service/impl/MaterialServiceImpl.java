 /**
 * 
 */
package com.decoration.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		List<MaterialBean> list = materialDao.findMatBean();
		ModelAndView mv = new ModelAndView();
		mv.addObject("matData", list);
		return mv;
	}

	/**
	 * 分页查询材料
	 */
	@Override
	public ModelAndView findMatBeanByPage(Page page) {
		ModelAndView mv = new ModelAndView();
		List<MaterialBean> list = materialDao.findMatBean();
		page = new Page(list.size(),page.getCurrentPageCode());
		session.setAttribute("matPage", page);
		
		int totalPages = page.getTotalPages();
		List<Integer> totalPagesList = new ArrayList<Integer>();
		for(int i=0;i<totalPages;i++){
			totalPagesList.add(i);
		}
		System.out.println("totalPagesList = " + totalPagesList);
		session.setAttribute("matTotalPage", totalPagesList);

		
		List<MaterialBean> pageList = materialDao.findMatBeanByPage(page);
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
				mv.addObject("message", "材料已存在！！");
				mv.addObject("page", "addInfo");
			} else {
				materialDao.saveMatBean(matBean);
				mv = this.findAllMatBean();
				mv.addObject("page", "buy");
			}
		} else {
			mv.addObject("message", "输入的信息有误，请重新输入！");
			mv.addObject("page", "addInfo");
		}
		return mv;
	}

	@Override
	public ModelAndView deleteMatById(int matId) {
		ModelAndView mv = new ModelAndView();
		materialDao.deleteMatById(matId);
		return mv;
	}

	@Override
	public ModelAndView updateMatBean(MaterialBean matBean) {
		ModelAndView mv = new ModelAndView();
		String matName = matBean.getMatName();
		String proName = matBean.getMatProject().getProjectName();
		String flowName = matBean.getMatFlow().getFlowName();
		String userName = matBean.getMatUser().getUserName();
		Project project = proDao.findProByName(proName);
		Flow flow = flowDao.findFlowByName(flowName);
		User user = userDao.findUserByName(userName);
		if (project != null && flow != null && user != null) {
			matBean.setMatProject(project);
			matBean.setMatFlow(flow);
			matBean.setMatUser(user);
			Material mat = new Material();
			mat.setMaterialName(matName);
			mat.setMatProjectId(project.getProjectId());
			boolean matIsExist = this.checkMatIsExistByNameAndProductId(mat);
			if (matIsExist == true) {
				mv.addObject("message", "材料已存在！！");
			} else {
				materialDao.updateMatBean(matBean);
			}
		} else {
			mv.addObject("message", "输入的信息有误，请重新输入！");
		}
		return mv;
	}
	// ==============材料进场=====================================================================

	@Override
	public ModelAndView findAllMatEnter() {
		ModelAndView mv = new ModelAndView();
		List<MaterialEnter> enterList = materialDao.findAllMatEnter();
		mv.addObject("enterData", enterList);
		return mv;
	}

	@Override
	public ModelAndView saveMatEnter(MaterialEnter matEnter) {
		ModelAndView mv = new ModelAndView();

		String enterName = matEnter.getEnterMat().getMatName();
		String userName = matEnter.getEnterUser().getUserName();
		String projectName = matEnter.getEnterProject().getProjectName();

		User user = userDao.findUserByName(userName);
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
				mv.addObject("message", "材料不存在！！");
				mv.addObject("page", "enterAddInfo");
			} else if (matEnter.getEnterNum() > material.getMaterialNum()) {
				mv.addObject("message", "进场材料大于库存，有误！！");
				mv.addObject("page", "enterAddInfo");
			} else {
				materialDao.saveMatEnter(matEnter);
				mv = this.findAllMatEnter();
				mv.addObject("page", "enter");
			}
		} else {
			mv.addObject("message", "输入信息有误，请重新输入！");
			mv.addObject("page", "enterAddInfo");
		}
		return mv;
	}

	@Override
	public ModelAndView deleteEnterMatById(int enterId) {
		ModelAndView mv = new ModelAndView();
		materialDao.deleteMatEnterById(enterId);
		return mv;
	}

	@Override
	public ModelAndView updateMatEnter(MaterialEnter matEnter) {
		ModelAndView mv = new ModelAndView();
		String enterName = matEnter.getEnterMat().getMatName();
		String userName = matEnter.getEnterUser().getUserName();
		String projectName = matEnter.getEnterProject().getProjectName();
		User user = userDao.findUserByName(userName);
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
				mv.addObject("message", "材料不存在！！");
			} else if (matEnter.getEnterNum() > material.getMaterialNum()) {
				mv.addObject("message", "进场材料大于库存，有误！！");
			} else {
				materialDao.updateMatEnter(matEnter);
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
		List<MaterialUse> useList = materialDao.findAllMatUse();
		mv.addObject("useData",useList);
		return mv;
	}

	@Override
	public ModelAndView saveMatUse(MaterialUse matUse) {
		ModelAndView mv = new ModelAndView();
		double useNum = matUse.getUseNum();
		String matName = matUse.getUseMat().getMatName();
		String proName = matUse.getUseProject().getProjectName();
		String userName = matUse.getUseUser().getUserName();
		
		Project project = proDao.findProByName(proName);
		User user =userDao.findUserByName(userName);
		
		if(project != null && user != null){
			Material material = new Material();
			material.setMatProjectId(project.getProjectId());
			material.setMaterialName(matName);
			material = materialDao.findMatByNameAndProjectId(material);
			
			matUse.setUseMat(materialDao.findMatBeanById(material.getMaterialId()));
			matUse.setUseProject(project);
			matUse.setUseUser(user);
			
			boolean matIsExist = this.checkMatIsExistByNameAndProductId(material);
			if (matIsExist == false) {
				mv.addObject("message", "材料不存在！！");
				mv.addObject("page", "useAddInfo");
			} else if (useNum > material.getMaterialNum()) {
				mv.addObject("message", "进场材料大于库存，有误！！");
				mv.addObject("page", "useAddInfo");
			} else {
				materialDao.saveMatUse(matUse);
				mv = this.findAllMatUse();
				mv.addObject("page", "use");
			}
		} else {
			mv.addObject("message", "输入信息有误，请重新输入！");
			mv.addObject("page", "useAddInfo");
		}
		return mv;
	}

	@Override
	public ModelAndView deleteUseMatById(int useId) {
		ModelAndView mv = new ModelAndView();
		materialDao.deleteMatUseById(useId);
		return mv;
	}

	@Override
	public ModelAndView updateMatUse(MaterialUse matUse) {
		ModelAndView mv = new ModelAndView();
		double useNum = matUse.getUseNum();
		String matName = matUse.getUseMat().getMatName();
		String proName = matUse.getUseProject().getProjectName();
		String userName = matUse.getUseUser().getUserName();
		
		Project project = proDao.findProByName(proName);
		User user =userDao.findUserByName(userName);
		
		if(project != null && user != null){
			Material material = new Material();
			material.setMatProjectId(project.getProjectId());
			material.setMaterialName(matName);
			material = materialDao.findMatByNameAndProjectId(material);
			
			matUse.setUseMat(materialDao.findMatBeanById(material.getMaterialId()));
			matUse.setUseProject(project);
			matUse.setUseUser(user);
			
			boolean matIsExist = this.checkMatIsExistByNameAndProductId(material);
			if (matIsExist == false) {
				mv.addObject("message", "材料不存在！！");
			} else if (useNum > material.getMaterialNum()) {
				mv.addObject("message", "进场材料大于库存，有误！！");
			} else {
				materialDao.updateMatUse(matUse);
			}
		} else {
			mv.addObject("message", "输入信息有误，请重新输入！");
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
}
