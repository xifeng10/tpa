package com.chuisha.service;

import com.chuisha.model.QuestionKnowledgePoint;
import com.wondersgroup.util.service.ICommonService;

import java.util.List;

/**
 * Created by xifeng on 2016/7/3.
 */
public interface IQuestionKnowledgePointService extends ICommonService<QuestionKnowledgePoint> {
    void saveOrUpdate(Long questionId, List<Long> pointIds);
}
