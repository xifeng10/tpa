package com.chuisha.controller;

import com.chuisha.model.KnowledgePoint;
import com.chuisha.model.QuestionClassify;
import com.chuisha.service.IKnowledgePointService;
import com.chuisha.service.IQuestionClassifyService;
import com.wondersgroup.util.controller.BaseRestSpringController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by xifeng on 2017/4/9.
 */
@Controller
@RequestMapping("/manager/knowledgePoint")
public class KnowledgePointController extends BaseRestSpringController<KnowledgePoint, Long> {
    /*针对每个类型下的空的Parent给添加上默认的-1值*/
    private final static Long KNOWLEDGE_POINT_EMPTY_PARENT_ID=-1l;
    @Autowired
    private IKnowledgePointService knowledgePointService;
    @Autowired
    private IQuestionClassifyService questionClassifyService;
    private final static String KNOWLEDGE_POINT = "manager/knowledgePoint";

    @Override
    public Object index(HttpServletRequest request, HttpServletResponse response, KnowledgePoint model) {
        ModelAndView mv = new ModelAndView(KNOWLEDGE_POINT);
        mv.addObject("classifyId", request.getParameter("classifyId"));
        return mv;
    }

    @RequestMapping(value = "/queryKnowledgePointByClassifyId")
    @ResponseBody
    public Object queryKnowledgePointByClassifyId(Long classifyId) {
        List<KnowledgePoint> points = knowledgePointService.findBy("classifyId", classifyId);
        for (KnowledgePoint p:points){
            if (p.getParentId()==null){
                p.setParentId(KNOWLEDGE_POINT_EMPTY_PARENT_ID);
            }
        }
        QuestionClassify questionClassify = questionClassifyService.get(classifyId);
        KnowledgePoint point = new KnowledgePoint();
        point.setId(KNOWLEDGE_POINT_EMPTY_PARENT_ID);
        point.setName(questionClassify.getName());
        points.add(point);
        return points;
    }

    @Override
    public Object create(HttpServletRequest request, HttpServletResponse response, KnowledgePoint model) throws Exception {
        model.setInsertBy(1l);
        model.setInsertTime(new Date());
        if(KNOWLEDGE_POINT_EMPTY_PARENT_ID.equals(model.getParentId())){
            model.setParentId(null);
        }
        knowledgePointService.save(model);
        return model;
    }

    @Override
    public Object update(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, KnowledgePoint model) throws Exception {
        KnowledgePoint point = knowledgePointService.get(id);
        model.setId(id);
        model.setInsertBy(point.getInsertBy());
        model.setInsertTime(point.getInsertTime());
        model.setUpdateBy(1l);
        model.setUpdateTime(new Date());
        if(KNOWLEDGE_POINT_EMPTY_PARENT_ID.equals(model.getParentId())){
            model.setParentId(null);
        }
        knowledgePointService.update(model);
        return model;
    }

    @Override
    public Object delete(@PathVariable Long id) {
        knowledgePointService.delete(id);
        return SUCCESS;
    }
}
