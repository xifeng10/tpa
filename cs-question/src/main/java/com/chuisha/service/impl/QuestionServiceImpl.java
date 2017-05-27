package com.chuisha.service.impl;

import com.chuisha.mapper.QuestionMapper;
import com.chuisha.mapper.SelectItemMapper;
import com.chuisha.model.Question;
import com.chuisha.model.QuestionKnowledgePoint;
import com.chuisha.model.QuestionType;
import com.chuisha.model.SelectItem;
import com.chuisha.service.IQuestionKnowledgePointService;
import com.chuisha.service.IQuestionService;
import com.chuisha.service.ISelectItemService;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import com.wondersgroup.util.service.impl.CommonServiceImpl;
import com.wondersgroup.util.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xifeng on 2016/7/3.
 */
@Service
public class QuestionServiceImpl extends CommonServiceImpl<Question> implements IQuestionService {
    private static final char[] cs = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','X'};
    @Autowired
    private ISelectItemService selectItemService;
    @Autowired
    private IQuestionKnowledgePointService questionKnowledgePointService;

    @Override
    public int save(Question question) {
//        changeBeforeForSaveOrUpdate(choiseQuestion);
        Date d = new Date();
        question.setInsertTime(d);
        question.setUpdateTime(d);
        return super.save(question);
    }
//
//    private void changeBeforeForSaveOrUpdate(Question choiseQuestion) {
//        for (int i=choiseQuestion.getSelectItems().size()-1;i>-1;i--){
//            if(StringUtils.isEmpty(choiseQuestion.getSelectItems().get(i).getItem())){
//                choiseQuestion.getSelectItems().remove(i);
//            }else{
//                choiseQuestion.getSelectItems().get(i).setChoiseQuestion(choiseQuestion);
//            }
//        }
//    }

    @Override
    public Question get(Serializable id) {
        Question question = super.get(id);
        question.setSelectItems(selectItemService.findBy("questionId", question.getId()));
        return question;
    }

    @Override
    public Page<Question> queryAllByPage(Page<Question> page) {
        super.queryAllByPage(page);
        return changePage(page);
    }

    private Page<Question> changePage(Page<Question> page) {
        List<Question> list = page.getResults();
        List<SelectItem> items=null;
        int index=page.getFirst();
        StringBuffer pointIds = new StringBuffer();
        for(Question q:list) {
            q.setIndex(++index);
            if(QuestionType.SELECT_QUESTION_TYPE==q.getType()) {
                items=selectItemService.findBy("questionId", q.getId());
                q.setSelectItems(items);

                for(int i=0;i<items.size();i++){
                    items.get(i).setIndexChar(cs[i]);
                    if(items.get(i).getIsRight()){
                        q.setAnswer(String.valueOf(cs[i]));
                        q.setAnswerHtml(String.valueOf(cs[i]));
                    }
                }
            }
            List<QuestionKnowledgePoint> points = questionKnowledgePointService.findBy("questionId", q.getId());
            pointIds.setLength(0);
            for (QuestionKnowledgePoint p:points) {
                pointIds.append(",").append(p.getPointId());
            }
            if (pointIds.length()>0){
                q.setPointIds(pointIds.substring(1));
            }
        }
        return page;
    }

    @Override
    public Page<Question> queryByExampleByPage(Page<Question> page, Example example) {
        super.queryByExampleByPage(page, example);
        return changePage(page);
    }

    @Override
    public int delete(Serializable id) {
        SelectItem item=new SelectItem();
        item.setQuestionId((Long) id);
//        item.setIsRight(true);
        selectItemService.delete(item);
        return super.delete(id);
    }

    @Override
    public int update(Question entity) {
//        changeBeforeForSaveOrUpdate(entity);
        entity.setUpdateTime(new Date());
        return super.update(entity);
    }

}
