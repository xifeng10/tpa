package com.chuisha.controller;

import com.chuisha.model.Question;
import com.chuisha.model.QuestionClassify;
import com.chuisha.service.IQuestionKnowledgePointService;
import com.chuisha.service.IQuestionService;
import com.chuisha.service.ISelectItemService;
import com.wondersgroup.util.controller.BaseRestSpringController;
import com.wondersgroup.util.util.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xifeng on 2016/7/9.
 */
@Controller
@RequestMapping("/manager/question")
public class QuestionController extends BaseRestSpringController<Question, Long> {
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private ISelectItemService selectItemService;
    @Autowired
    private IQuestionKnowledgePointService questionKnowledgePointService;
    private final static String QUESTION = "manager/question";
    private final static String CHOISE_QUESTION_SHOW = "manager/choiseQuestionShow";

    @RequestMapping(value = "/saveOrUpdateSelectItems")
    @ResponseBody
    public Object saveOrUpdateSelectItems(Question question) {
        selectItemService.saveOrUpdate(question.getSelectItems(), question.getId());
        return SUCCESS;
    }

    @RequestMapping(value = "/saveOrUpdateKnowledgePoint/{questionId}")
    @ResponseBody
    public Object saveOrUpdateKnowledgePoint(@PathVariable Long questionId,String pointIds) {
//        System.out.println(request.getParameter("pointIds"));;
//        System.out.println(request.getParameterValues("pointIds").length);
        String[] strs = pointIds.split(",");
        List<Long> ids = new ArrayList<>(strs.length);
        for(String s:strs){
            ids.add(Long.parseLong(s));
        }
        questionKnowledgePointService.saveOrUpdate(questionId, ids);
        return SUCCESS;
    }

    @Override
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Question model) {
        Page page = new Page<Question>();
//        questionService.q
        page.setSort("desc");
        page.setOrderBy("insert_time");
        String p = request.getParameter("currentPage");
        if(p!=null) {
            page.setCurrentPage(Integer.parseInt(p));
        }
        questionService.queryAllByPage(page);
        ModelAndView mv = new ModelAndView(QUESTION);
        mv.addObject("questions", page.getResults());
        mv.addObject("page", page);
//        System.out.println("2222================="+request.getSession().getAttribute("classifyId"));
        return mv;
    }

    @Override
    public ModelAndView _new(HttpServletRequest request, HttpServletResponse response, Question model) throws Exception {
        return super._new(request, response, model);
    }

    @Override
    public Object show(@PathVariable Long id) throws Exception {
        return questionService.get(id);
    }

    @Override
    public ModelAndView edit(@PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView(QUESTION);
        mv.addObject("question", questionService.get(id));
        return mv;
    }

    @Override
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response, Question question) throws Exception {
        Object obj = request.getSession().getAttribute("classifyId");
        if (obj != null) {
            question.setClassifyId((Long) obj);
        } else {
            question.setClassifyId(1l);
        }
        questionService.save(question);
        return new ModelAndView("redirect:/manager/question");
    }

    @Override
    public ModelAndView update(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, Question question) throws Exception {
//        question.setId(id);
        Question oldQuestion = questionService.get(id);
        if (!StringUtils.isEmpty(question.getQuestion())) {
            oldQuestion.setQuestion(question.getQuestion());
            oldQuestion.setQuestionHtml(question.getQuestionHtml());
        }
        if (!StringUtils.isEmpty(question.getAnalyse())) {
            oldQuestion.setAnalyse(question.getAnalyse());
            oldQuestion.setAnalyseHtml(question.getAnalyseHtml());
        }
        if (!StringUtils.isEmpty(question.getAnswer())) {
            oldQuestion.setAnswer(question.getAnswer());
            oldQuestion.setAnswerHtml(question.getAnswerHtml());
        }
        oldQuestion.setUpdateTime(new Date());
        questionService.update(oldQuestion);
        return new ModelAndView("redirect:/manager/question");
    }

    @Override
    public ModelAndView delete(@PathVariable Long id) {
        try {
            questionService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/manager/question");
    }
}
