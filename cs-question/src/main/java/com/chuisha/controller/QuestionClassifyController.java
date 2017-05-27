package com.chuisha.controller;

import com.chuisha.model.QuestionClassify;
import com.chuisha.service.IQuestionClassifyService;
import com.wondersgroup.util.controller.BaseRestSpringController;
import com.wondersgroup.util.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xifeng on 2016/7/10.
 */
@Controller
@RequestMapping("/manager/classify")
public class QuestionClassifyController extends BaseRestSpringController<QuestionClassify,Long> {
    private static final String TRUE_FALSE = "manager/classify";
    private static final String SELECT_CLASSIFY = "select-classify";
    private static final String TRUE_FALSE_SHOW = "classify-show";
    private static final String TRUE_FALSE_LIST = "manager/classify-list";
    @Autowired
    private IQuestionClassifyService questionClassifyService;

    @Override
    public Object page(HttpServletRequest request, HttpServletResponse response, Page page, QuestionClassify model) {
//        return questionClassifyService.queryAllByPage(page,model);
        if (model != null) {
            Example example = new Example(QuestionClassify.class);
//            example.createCriteria().andLike("realName", "%" + model.getRealName() + "%");
            return questionClassifyService.queryByExampleByPage(page, example);
        }
        return questionClassifyService.queryAllByPage(page);
    }

    @Override
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, QuestionClassify model) {
        return new ModelAndView(TRUE_FALSE_LIST);
    }

    @Override
    public ModelAndView show(@PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView(TRUE_FALSE);
        mv.addObject("classify",questionClassifyService.get(id));
        return mv;
    }

    @Override
    public ModelAndView edit(@PathVariable Long id) throws Exception {
        return show(id);
    }

    @Override
    public Object create(HttpServletRequest request, HttpServletResponse response, QuestionClassify model) throws Exception {
//        if (model.getParentClassify()!=null&&model.getParentClassify().getId()==null){
//            model.setParentClassify(null);
//        }
        questionClassifyService.save(model);
        return SUCCESS;
    }

    @Override
    public Object update(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, QuestionClassify model) throws Exception {
        model.setId(id);
        questionClassifyService.update(model);
        return SUCCESS;
    }

    @Override
    public Object delete(@PathVariable Long id) {
        try {
            questionClassifyService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    @RequestMapping(value = "/selectClassify")
    @ResponseBody
    public Map<String,Object> selectClassify(Long classifyId, HttpSession session){
        session.setAttribute("classifyId",classifyId);
        Map<String,Object> mv = new HashMap<String,Object>();
        mv.put("flag",true);
//        System.out.println("1111================="+session.getAttribute("classifyId"));
        return mv;
    }
}
