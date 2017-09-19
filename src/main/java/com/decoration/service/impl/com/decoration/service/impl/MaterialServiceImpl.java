package com.decoration.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import util.Page;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhenghan 2017年4月14日 下午11:03:46
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

    //
    // ===========购买材料========================================================================================

    public ModelAndView findAllMatBean() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<MaterialBean> list = materialDao.findMatBean(map);
        ModelAndView mv = new ModelAndView();
        mv.addObject("matData", list);
        return mv;
    }

    /**
     * 分页查询购买材料
     */

    public ModelAndView findMatBeanByPage(Page page, String searchName) {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> map = this.checkSearchNameForBuy(searchName);
        List<MaterialBean> list = materialDao.findMatBean(map);
        page = new Page(list.size(), page.getCurrentPageCode());
        session.setAttribute("matPage", page);

        List<MaterialBean> pageList = materialDao.findMatBeanByPage(page, map);
        mv.addObject("matPageData", pageList);
        return mv;
    }


    public ModelAndView saveMaterialBean(MaterialBean matBean) {
        ModelAndView mv = new ModelAndView();
        String matName = matBean.getMatName();
        String proName = matBean.getMatProject().getProjectName();
        String flowName = matBean.getMatFlow().getFlowName();
        Project project = proDao.findProByName(proName);
        Flow flow = flowDao.findFlowByName(flowName);
        Date buyDate = matBean.getMatBuyDate();
        User user = (User) session.getAttribute("loginUser");

        if (project != null && flow != null && user != null) {
            Material mat = new Material();
            mat.setMaterialName(matName);
            mat.setMatProjectId(project.getProjectId());

            if (this.checkMatIsExistByNameAndProductId(mat)) {
                mv.addObject("result", false);
                mv.addObject("reason", "材料已存在！！");
                mv.addObject("page", "addInfo");
            } else {
                if (utilService.checkDateIsValid(buyDate)) {
                    matBean.setMatProject(project);
                    matBean.setMatFlow(flow);
                    matBean.setMatUser(user);
                    materialDao.saveMatBean(matBean);
                    mv = this.findMatByPageAfterOperation(mv);
                    mv.addObject("page", "buy");
                } else {
                    mv.addObject("result", false);
                    mv.addObject("reason", "日期不能迟于当前日期！");
                    mv.addObject("page", "addInfo");
                }
            }
        } else {
            mv.addObject("result", false);
            mv.addObject("reason", "材料信息错误！");
            mv.addObject("page", "errorInfo");
        }
        return mv;
    }


    public ModelAndView deleteMatById(int matId) {
        ModelAndView mv = new ModelAndView();
        materialDao.deleteMatById(matId);
        mv = this.findMatByPageAfterOperation(mv);
        return mv;
    }


    public ModelAndView updateMatBean(MaterialBean matBean) {
        ModelAndView mv = new ModelAndView();

        String matName = matBean.getMatName();
        String proName = matBean.getMatProject().getProjectName();
        String flowName = matBean.getMatFlow().getFlowName();
        Date buyDate = matBean.getMatBuyDate();

        Project project = proDao.findProByName(proName);
        Flow flow = flowDao.findFlowByName(flowName);
        User user = (User) session.getAttribute("loginUser");

        if (project != null && flow != null && user != null) {
            Material mat = new Material();
            mat.setMaterialName(matName);
            mat.setMatProjectId(project.getProjectId());
            mat = materialDao.findMatByNameAndProjectId(mat);

            MaterialBean matBean2 = materialDao.findMatBeanById(matBean.getMatId());

            if (mat != null && matBean2.getMatId() != mat.getMaterialId()) {
                mv.addObject("result", false);
                mv.addObject("reason", "您想要修改的材料已存在,无法修改！！");
                mv.addObject("page", "errorInfo");
            } else {
                if (utilService.checkDateIsValid(buyDate)) {
                    matBean.setMatProject(project);
                    matBean.setMatFlow(flow);
                    matBean.setMatUser(user);
                    materialDao.updateMatBean(matBean);
                    mv = this.findMatByPageAfterOperation(mv);
                    mv.addObject("page", "buy");
                } else {
                    mv.addObject("result", false);
                    mv.addObject("reason", "输入的日期有误！");
                    mv.addObject("page", "errorInfo");
                }
            }
        } else {
            mv.addObject("result", false);
            mv.addObject("reason", "输入的信息有误，请重新输入！");
            mv.addObject("page", "updateInfo");
        }
        return mv;
    }


    public ModelAndView findMatBeanById(int matId) {
        ModelAndView mv = new ModelAndView();
        MaterialBean matBean = materialDao.findMatBeanById(matId);
        mv.addObject("matBean", matBean);
        return mv;
    }
    // ==============材料进场=====================================================================


    public ModelAndView findAllMatEnter() {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();
        List<MaterialEnter> enterList = materialDao.findAllMatEnter(map);
        mv.addObject("enterData", enterList);
        return mv;
    }


    public ModelAndView findAllMatEnterByPage(Page page, String searchName) {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> map = this.checkSearchNameForEnter(searchName);
        List<MaterialEnter> list = materialDao.findAllMatEnter(map);
        page = new Page(list.size(), page.getCurrentPageCode());
        session.setAttribute("enterPage", page);

        List<MaterialEnter> pageList = materialDao.findAllMatEnterByPage(page, map);
        mv.addObject("enterPageData", pageList);
        return mv;
    }


    public ModelAndView saveMatEnter(MaterialEnter matEnter) {
        ModelAndView mv = new ModelAndView();

        String enterName = matEnter.getEnterMat().getMatName();
        String projectName = matEnter.getEnterProject().getProjectName();

        User user = (User) session.getAttribute("loginUser");
        Project project = proDao.findProByName(projectName);
        Date enterDate = matEnter.getEnterDate();

        if (user != null && project != null) {
            Material material = new Material();
            material.setMaterialName(enterName);
            material.setMatProjectId(project.getProjectId());

            material = materialDao.findMatByNameAndProjectId(material);

            if (material == null) {
                mv.addObject("result", false);
                mv.addObject("reason", "材料不存在！");
                mv.addObject("page", "errorInfo");
            } else if (matEnter.getEnterNum() > material.getMaterialNum()) {
                mv.addObject("result", false);
                mv.addObject("reason", "进场材料大于库存，有误！");
                mv.addObject("page", "errorInfo");
            } else {
                if (utilService.checkDateIsValid(enterDate)) {
                    matEnter.setEnterMat(materialDao.findMatBeanById(material.getMaterialId()));
                    matEnter.setEnterProject(project);
                    matEnter.setEnterUser(user);
                    materialDao.saveMatEnter(matEnter);
                    mv = this.findMatEnterByPageAfterOperation(mv);
                    mv.addObject("page", "enter");
                } else {
                    mv.addObject("result", false);
                    mv.addObject("reason", "日期输入有误！");
                    mv.addObject("page", "errorInfo");
                }

            }
        } else {
            mv.addObject("result", false);
            mv.addObject("reason", "输入信息有误，请重新输入！");
            mv.addObject("page", "errorInfo");
        }
        return mv;
    }


    public ModelAndView deleteEnterMatById(int enterId) {
        ModelAndView mv = new ModelAndView();
        materialDao.deleteMatEnterById(enterId);
        mv = this.findMatEnterByPageAfterOperation(mv);
        return mv;
    }


    public ModelAndView updateMatEnter(MaterialEnter matEnter) {
        ModelAndView mv = new ModelAndView();

        String enterName = matEnter.getEnterMat().getMatName();
        String projectName = matEnter.getEnterProject().getProjectName();
        Date enterDate = matEnter.getEnterDate();

        Project project = proDao.findProByName(projectName);
        User user = (User) session.getAttribute("loginUser");

        if (project != null && user != null) {
            Material material = new Material();
            material.setMaterialName(enterName);
            material.setMatProjectId(project.getProjectId());

            material = materialDao.findMatByNameAndProjectId(material);

            if (material == null) {
                mv.addObject("result", false);
                mv.addObject("reason", "材料不存在！！");
                mv.addObject("page", "errorInfo");
            } else if (matEnter.getEnterNum() > material.getMaterialNum()) {
                mv.addObject("result", false);
                mv.addObject("reason", "进场材料大于库存，有误！！");
                mv.addObject("page", "errorInfo");
            } else {
                if (utilService.checkDateIsValid(enterDate)) {
                    matEnter.setEnterMat(materialDao.findMatBeanById(material.getMaterialId()));
                    matEnter.setEnterProject(project);
                    matEnter.setEnterUser(user);
                    materialDao.updateMatEnter(matEnter);
                    mv = this.findMatEnterByPageAfterOperation(mv);
                    mv.addObject("page", "enter");
                } else {
                    mv.addObject("result", false);
                    mv.addObject("reason", "日期输入有误！");
                    mv.addObject("page", "errorInfo");
                }
            }
        } else {
            mv.addObject("result", false);
            mv.addObject("reason", "输入的信息有误，请重新输入！");
            mv.addObject("page", "errorInfo");
        }
        return mv;
    }


    public ModelAndView findMatEnterById(int enterId) {
        ModelAndView mv = new ModelAndView();
        MaterialEnter matEnter = materialDao.findMatEnterById(enterId);
        mv.addObject("matEnter", matEnter);
        Map<String, Object> map = new HashMap<String, Object>();
        List<MaterialBean> list = materialDao.findMatBean(map);
        mv.addObject("matData", list);
        return mv;
    }
    // ===========材料使用=============================================================================

    public ModelAndView findAllMatUse() {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();
        List<MaterialUse> useList = materialDao.findAllMatUse(map);
        mv.addObject("useData", useList);
        return mv;
    }

    /**
     * 分页查询使用材料
     */

    public ModelAndView findAllMatUseByPage(Page page, String searchName) {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> map = this.checkSearchNameForUse(searchName);
        List<MaterialUse> list = materialDao.findAllMatUse(map);
        page = new Page(list.size(), page.getCurrentPageCode());
        session.setAttribute("usePage", page);

        List<MaterialUse> pageList = materialDao.findAllMatUseByPage(page, map);
        mv.addObject("usePageData", pageList);
        return mv;
    }


    public ModelAndView saveMatUse(MaterialUse matUse) {
        ModelAndView mv = new ModelAndView();

        String matName = matUse.getUseMat().getMatName();
        String proName = matUse.getUseProject().getProjectName();
        Date useDate = matUse.getUseDate();

        Project project = proDao.findProByName(proName);
        User user = (User) session.getAttribute("loginUser");

        if (project != null && user != null) {
            Material material = new Material();
            material.setMatProjectId(project.getProjectId());
            material.setMaterialName(matName);
            material = materialDao.findMatByNameAndProjectId(material);

            if (material == null) {
                mv.addObject("result", false);
                mv.addObject("reason", "材料不存在！！");
                mv.addObject("page", "errorInfo");

            } else if (matUse.getUseNum() > material.getMaterialNum()) {
                mv.addObject("result", false);
                mv.addObject("reason", "使用材料大于进场材料，有误！");
                mv.addObject("page", "errorInfo");
            } else {
                if (utilService.checkDateIsValid(useDate)) {
                    matUse.setUseMat(materialDao.findMatBeanById(material.getMaterialId()));
                    matUse.setUseProject(project);
                    matUse.setUseUser(user);
                    materialDao.saveMatUse(matUse);
                    mv = this.findMatUseByPageAfterOperation(mv);
                    mv.addObject("page", "use");
                } else {
                    mv.addObject("result", false);
                    mv.addObject("reason", "日期输入有误！");
                    mv.addObject("page", "errorInfo");
                }
            }
        } else {
            mv.addObject("result", false);
            mv.addObject("reason", "输入信息有误，请重新输入！");
            mv.addObject("page", "errorInfo");
        }
        return mv;
    }


    public ModelAndView deleteUseMatById(int useId) {
        ModelAndView mv = new ModelAndView();
        materialDao.deleteMatUseById(useId);
        mv = this.findMatUseByPageAfterOperation(mv);
        return mv;
    }


    public ModelAndView updateMatUse(MaterialUse matUse) {
        ModelAndView mv = new ModelAndView();

        String matName = matUse.getUseMat().getMatName();
        String proName = matUse.getUseProject().getProjectName();
        Date useDate = matUse.getUseDate();

        Project project = proDao.findProByName(proName);
        User useUser = (User) session.getAttribute("loginUser");

        if (project != null && useUser != null) {
            Material material = new Material();
            material.setMatProjectId(project.getProjectId());
            material.setMaterialName(matName);
            material = materialDao.findMatByNameAndProjectId(material);

            if (material == null) {
                mv.addObject("result", false);
                mv.addObject("reason", "材料不存在！！");
                mv.addObject("page", "errorInfo");
            } else if (matUse.getUseNum() > material.getMaterialNum()) {
                mv.addObject("result", false);
                mv.addObject("reason", "进场材料大于库存，有误！！");
                mv.addObject("page", "errorInfo");
            } else {
                if (utilService.checkDateIsValid(useDate)) {
                    matUse.setUseMat(materialDao.findMatBeanById(material.getMaterialId()));
                    matUse.setUseProject(project);
                    matUse.setUseUser(useUser);
                    materialDao.updateMatUse(matUse);
                    mv = this.findMatUseByPageAfterOperation(mv);
                    mv.addObject("page", "use");
                } else {
                    mv.addObject("result", false);
                    mv.addObject("reason", "日期输入有误！");
                    mv.addObject("page", "errorInfo");
                }
            }
        } else {
            mv.addObject("result", false);
            mv.addObject("reason", "输入信息有误，请重新输入！");
            mv.addObject("page", "errorInfo");
        }
        return mv;
    }


    public ModelAndView findMatUseById(int useId) {
        ModelAndView mv = new ModelAndView();
        MaterialUse matUse = materialDao.findMatUseById(useId);
        mv.addObject("matUse", matUse);
        Map<String, Object> map = new HashMap<String, Object>();
        List<MaterialEnter> enterList = materialDao.findAllMatEnter(map);
        mv.addObject("enterData", enterList);
        return mv;
    }


    // ========================================================================================

    /**
     * 根据材料名称和项目编号检查材料是否已经存在
     *
     * @param material for sth
     * @return boolean
     */
    private boolean checkMatIsExistByNameAndProductId(Material material) {
        return null == materialDao.findMatByNameAndProjectId(material);
    }

    /**
     * @param mv for
     * @return ModelAndView
     */
    private ModelAndView findMatByPageAfterOperation(ModelAndView mv) {
        Map<String, Object> map = new HashMap<String, Object>();
        int currentPage;
        List<MaterialBean> list = materialDao.findMatBean(map);
        Page page = (Page) session.getAttribute("matPage");
        Page page2 = new Page(list.size(), 1);
        if (page.getTotalPages() == page2.getTotalPages()) {
            currentPage = page.getTotalPages();
        } else {
            currentPage = page2.getTotalPages();
        }
        page.setCurrentPageCode(currentPage);
        mv = this.findMatBeanByPage(page, "");
        return mv;
    }

    /**
     * 在材料进场模块执行操作后分页查出数据
     *
     * @param mv for
     */
    private ModelAndView findMatEnterByPageAfterOperation(ModelAndView mv) {
        int currentPage;
        Map<String, Object> map = new HashMap<String, Object>();
        List<MaterialEnter> list = materialDao.findAllMatEnter(map);
        Page page = (Page) session.getAttribute("enterPage");
        Page page2 = new Page(list.size(), 1);
        if (page.getTotalPages() == page2.getTotalPages()) {
            currentPage = page.getTotalPages();
        } else {
            currentPage = page2.getTotalPages();
        }
        page.setCurrentPageCode(currentPage);
        mv = this.findAllMatEnterByPage(page, "");
        return mv;
    }

    /**
     * 在材料使用模块执行操作后分页查出数据
     *
     * @param mv for
     */
    private ModelAndView findMatUseByPageAfterOperation(ModelAndView mv) {
        Map<String, Object> map = new HashMap<String, Object>();
        int currentPage;
        List<MaterialUse> list = materialDao.findAllMatUse(map);
        Page page = (Page) session.getAttribute("usePage");
        Page page2 = new Page(list.size(), 1);
        if (page.getTotalPages() == page2.getTotalPages()) {
            currentPage = page.getTotalPages();
        } else {
            currentPage = page2.getTotalPages();
        }
        page.setCurrentPageCode(currentPage);
        mv = this.findAllMatUseByPage(page, "");
        return mv;
    }

    private Map<String, Object> checkSearchNameForBuy(String searchName) {
        Map<String, Object> map = new HashMap<String, Object>();


        Map<String, Object> matMap = new HashMap<String, Object>();
        matMap.put("matName", searchName);
        List<MaterialBean> matList = materialDao.findMatBean(matMap);

        Map<String, Object> flowMap = new HashMap<String, Object>();
        flowMap.put("flowName", searchName);
        List<MaterialBean> matList2 = materialDao.findMatBean(flowMap);

        if (matList.size() != 0 && matList2.size() == 0) {
            map.put("matName", searchName);
        } else if (matList.size() == 0 && matList2.size() != 0) {
            map.put("flowName", searchName);
        } else {
            map.put("matName", searchName);
            map.put("flowName", searchName);
        }
        return map;
    }

    private Map<String, Object> checkSearchNameForEnter(String searchName) {
        Map<String, Object> map = new HashMap<String, Object>();


        Map<String, Object> matMap = new HashMap<String, Object>();
        matMap.put("matName", searchName);
        List<MaterialEnter> matList = materialDao.findAllMatEnter(matMap);

        Map<String, Object> flowMap = new HashMap<String, Object>();
        flowMap.put("flowName", searchName);
        List<MaterialEnter> matList2 = materialDao.findAllMatEnter(flowMap);

        if (matList.size() != 0 && matList2.size() == 0) {
            map.put("matName", searchName);
        } else if (matList.size() == 0 && matList2.size() != 0) {
            map.put("flowName", searchName);
        } else {
            map.put("matName", searchName);
            map.put("flowName", searchName);
        }
        return map;
    }

    private Map<String, Object> checkSearchNameForUse(String searchName) {
        Map<String, Object> map = new HashMap<String, Object>();


        Map<String, Object> matMap = new HashMap<String, Object>();
        matMap.put("matName", searchName);
        List<MaterialUse> matList = materialDao.findAllMatUse(matMap);

        Map<String, Object> flowMap = new HashMap<String, Object>();
        flowMap.put("flowName", searchName);
        List<MaterialUse> matList2 = materialDao.findAllMatUse(flowMap);

        if (matList.size() != 0 && matList2.size() == 0) {
            map.put("matName", searchName);
        } else if (matList.size() == 0 && matList2.size() != 0) {
            map.put("flowName", searchName);
        } else {
            map.put("matName", searchName);
            map.put("flowName", searchName);
        }
        return map;
    }
}
