package com.chuisha.model;

import javax.persistence.Table;

@Table(name = "CS_QUESTION_KNOW_POINT")
public class QuestionKnowledgePoint extends BaseKey {
    private Long questionId;

    private Long pointId;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getPointId() {
        return pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }
}