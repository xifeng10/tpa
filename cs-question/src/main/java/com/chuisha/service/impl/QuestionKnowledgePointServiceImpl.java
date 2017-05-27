package com.chuisha.service.impl;

import com.chuisha.mapper.QuestionKnowPointMapper;
import com.chuisha.model.QuestionKnowledgePoint;
import com.chuisha.service.IQuestionKnowledgePointService;
import com.wondersgroup.util.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xifeng on 2016/7/3.
 */
@Service
public class QuestionKnowledgePointServiceImpl extends CommonServiceImpl<QuestionKnowledgePoint> implements IQuestionKnowledgePointService {
    @Autowired
    private QuestionKnowPointMapper mapper;

    @Override
    public void saveOrUpdate(Long questionId, List<Long> pointIds) {
        QuestionKnowledgePoint questionKnowledgePoint = new QuestionKnowledgePoint();
        questionKnowledgePoint.setQuestionId(questionId);
        List<QuestionKnowledgePoint> questionKnowledgePoints = mapper.select(questionKnowledgePoint);
        Long pointId;
        for(int i=questionKnowledgePoints.size()-1;i>-1;i--){
            pointId=questionKnowledgePoints.get(i).getPointId();
            if (pointIds.contains(pointId)){
                pointIds.remove(pointId);
                questionKnowledgePoints.remove(i);
            }
        }
        for (QuestionKnowledgePoint point:questionKnowledgePoints){
            mapper.delete(point);
        }
        for(Long id:pointIds){
            questionKnowledgePoint.setId(null);
            questionKnowledgePoint.setPointId(id);
            mapper.insert(questionKnowledgePoint);
        }
    }
}
