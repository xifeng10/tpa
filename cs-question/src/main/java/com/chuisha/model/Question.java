package com.chuisha.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by xifeng on 2016/9/7.
 */
@Table(name = "CS_QUESTION")
public class Question {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long classifyId;
    private Integer type;
    private String question;
    private String questionHtml;
    private String answer;
    private String answerHtml;
    private String analyse;
    private String analyseHtml;
    private Long insertBy;
    private Date insertTime;
    private Long updateBy;
    private Date updateTime;
    private Long parentId;
    @Transient
    private Question parentQuestion;
    @Transient
    private List<SelectItem> selectItems;
    @Transient
    private String pointIds;
    @Transient
    private Long index;

    public Question() {
    }

    public Question(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionHtml() {
        return questionHtml;
    }

    public void setQuestionHtml(String questionHtml) {
        this.questionHtml = questionHtml;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerHtml() {
        return answerHtml;
    }

    public void setAnswerHtml(String answerHtml) {
        this.answerHtml = answerHtml;
    }

    public String getAnalyse() {
        return analyse;
    }

    public void setAnalyse(String analyse) {
        this.analyse = analyse;
    }

    public String getAnalyseHtml() {
        return analyseHtml;
    }

    public void setAnalyseHtml(String analyseHtml) {
        this.analyseHtml = analyseHtml;
    }

    public Long getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(Long insertBy) {
        this.insertBy = insertBy;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        if (id != question1.id) return false;
        if (question != null ? !question.equals(question1.question) : question1.question != null) return false;
        if (questionHtml != null ? !questionHtml.equals(question1.questionHtml) : question1.questionHtml != null)
            return false;
        if (answer != null ? !answer.equals(question1.answer) : question1.answer != null) return false;
        if (answerHtml != null ? !answerHtml.equals(question1.answerHtml) : question1.answerHtml != null) return false;
        if (analyse != null ? !analyse.equals(question1.analyse) : question1.analyse != null) return false;
        if (analyseHtml != null ? !analyseHtml.equals(question1.analyseHtml) : question1.analyseHtml != null)
            return false;
        if (insertBy != null ? !insertBy.equals(question1.insertBy) : question1.insertBy != null) return false;
        if (insertTime != null ? !insertTime.equals(question1.insertTime) : question1.insertTime != null) return false;
        if (updateBy != null ? !updateBy.equals(question1.updateBy) : question1.updateBy != null) return false;
        if (updateTime != null ? !updateTime.equals(question1.updateTime) : question1.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (questionHtml != null ? questionHtml.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (answerHtml != null ? answerHtml.hashCode() : 0);
        result = 31 * result + (analyse != null ? analyse.hashCode() : 0);
        result = 31 * result + (analyseHtml != null ? analyseHtml.hashCode() : 0);
        result = 31 * result + (insertBy != null ? insertBy.hashCode() : 0);
        result = 31 * result + (insertTime != null ? insertTime.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    public Question getParentQuestion() {
        return parentQuestion;
    }

    public void setParentQuestion(Question parentQuestion) {
        this.parentQuestion = parentQuestion;
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SelectItem> selectItems) {
        this.selectItems = selectItems;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public String getPointIds() {
        return pointIds;
    }

    public void setPointIds(String pointIds) {
        this.pointIds = pointIds;
    }
}
