package com.chuisha.controller;

import com.chuisha.model.Question;
import com.chuisha.service.IQuestionService;
import com.wondersgroup.util.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xifeng on 2017/5/8.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IQuestionService questionService;

    @RequestMapping(value = "/{type}",method = RequestMethod.GET)
    public ModelAndView index(@PathVariable Integer type,HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("student/student");
        Page page = new Page<Question>();
        page.setSort("desc");
        page.setOrderBy("insert_time");
        String p = request.getParameter("currentPage");
        if(p!=null) {
            page.setCurrentPage(Integer.parseInt(p));
        }
        Example example = new Example(Question.class);
        example.createCriteria().andEqualTo("classifyId",type);
        questionService.queryByExampleByPage(page,example);
        mv.addObject("questions", page.getResults());
        mv.addObject("page", page);
        mv.addObject("type",type);
        return mv;
    }

}
