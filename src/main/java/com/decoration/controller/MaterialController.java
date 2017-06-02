/**
 * 
 */
package com.decoration.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.decoration.bean.MaterialBean;
import com.decoration.service.MaterialService;
import com.decoration.service.UserService;
import com.decoration.service.UtilService;

import util.DictionaryItems;
import util.Page;

/**
 * @author zhenghan
 * 2017年4月14日 
 * 下午10:23:49
 *
 */
@RequestMapping("/material")
@Scope("prototype")
@Controller
public class MaterialController {
	@Autowired
	private MaterialService matService;
	@Autowired
	private UtilService utilService;
	@Autowired
	private HttpSession session;
	/**
	 * 跳转到展示购买材料页面
	 * @return
	 */
	@RequestMapping(value="/buy",method = RequestMethod.GET)
	public ModelAndView showBuy(Page page){
		page.setCurrentPageCode(1);
		ModelAndView mv = matService.findMatBeanByPage(page,"");
		mv.addObject("page", "buy");
		mv.setViewName("/home");
		return mv;
	}
	@RequestMapping(value="/",method = RequestMethod.GET)
	public ModelAndView showBuyByPage(Page page){
		page = (Page)session.getAttribute("matPage");
		utilService.choosePage(page,"from");
		ModelAndView mv = matService.findMatBeanByPage(page,"");
		mv.addObject("page", "buy");
		mv.setViewName("/home");
		return mv;
	}
	
	@RequestMapping(value="/pageNumber",method = RequestMethod.POST)
	public ModelAndView showBuyByPageNumBer(Integer currentPageCode){
		Page page = (Page)session.getAttribute("matPage");
		page.setCurrentPageCode(currentPageCode);
		ModelAndView mv = matService.findMatBeanByPage(page,"");
		mv.addObject("page", "buy");
		mv.setViewName("/home");
		return mv;
	}
	
	@RequestMapping(value="/matSearch",method = RequestMethod.POST)
	public ModelAndView showMatBySearch(String searchName){
		Page page = (Page)session.getAttribute("matPage");
		page.setCurrentPageCode(1);
		ModelAndView mv = matService.findMatBeanByPage(page, searchName);
		mv.addObject("page", "buy");
		mv.setViewName("/home");
		return mv;
	}
	
	/**
	 * 跳转到展示材料进场页面
	 * @return
	 */
	@RequestMapping(value="/enter",method = RequestMethod.GET)
	public ModelAndView showEnter(Page page){
		page.setCurrentPageCode(1);
		ModelAndView mv = matService.findAllMatEnterByPage(page,"");
		mv.addObject("page", "enter");
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 跳转到材料使用界面
	 * @return
	 */
	@RequestMapping(value="/use",method = RequestMethod.GET)
	public ModelAndView showUse(Page page){
		page.setCurrentPageCode(1);
		ModelAndView mv = matService.findAllMatUseByPage(page,"");
		mv.addObject("page", "use");
		mv.setViewName("/home");
		return mv;
	}
	
	//===========================================================================
	/**
	 * 跳转到新添材料输入界面
	 * @return
	 */
	@RequestMapping(value="/addInfo",method = RequestMethod.GET)
	public ModelAndView showAddInfo(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("page","addInfo");
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 执行增加购买材料操作并跳转到展示购买材料页面
	 * @param matBean
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addMaterial(MaterialBean matBean) {
		ModelAndView mv = matService.saveMaterialBean(matBean);
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 执行删除操作
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/delete/{matId}", method = RequestMethod.GET)
	public ModelAndView deleteMatPurById(@PathVariable int matId) {
		ModelAndView mv = matService.deleteMatById(matId);
		mv.addObject("page", "buy");
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 跳转到修改材料页面
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateInfo/{matId} ", method = RequestMethod.GET)
	public ModelAndView updateMatPurchase(@PathVariable int matId) {
		ModelAndView mv = new ModelAndView();
		mv = matService.findMatBeanById(matId);
		mv.addObject("page", "updateInfo");
		mv.addObject("updateId", matId); 
		mv.setViewName("/home");
		return mv;
	}
	/**
	 * 执行修改操作并跳转到展示材料页面
	 * @param matBean
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public ModelAndView updateMatBean(MaterialBean matBean,@PathVariable int id) {
		ModelAndView mv = new ModelAndView();
		matBean.setMatId(id);
		mv = matService.updateMatBean(matBean);
		//mv = matService.findAllMatBean();
		mv.addObject("page", "buy");
		mv.setViewName("/home");
		return mv;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
