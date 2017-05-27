package com.chuisha.model;

import javax.persistence.*;

/**
 * Created by xifeng on 2016/9/7.
 */
@Table(name = "CS_SELECT_ITEM")
public class SelectItem {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;
    private String itemHtml;
    private Boolean isRight ;
    @Transient
    private char indexChar;
    private Long questionId;

    public SelectItem() {
    }

    public SelectItem(String item, String itemHtml, Boolean isRight) {
        this.item = item;
        this.itemHtml = itemHtml;
        this.isRight = isRight;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char getIndexChar() {
        return indexChar;
    }

    public void setIndexChar(char indexChar) {
        this.indexChar = indexChar;
    }

    @Column(name = "ITEM")
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Column(name = "ITEM_HTML")
    public String getItemHtml() {
        return itemHtml;
    }

    public void setItemHtml(String itemHtml) {
        this.itemHtml = itemHtml;
    }

    @Column(name = "IS_RIGHT")
    public Boolean getIsRight() {
        return isRight;
    }

    public void setIsRight(Boolean isRight) {
        this.isRight = isRight;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SelectItem that = (SelectItem) o;

        if (id != that.id) return false;
        if (isRight != that.isRight) return false;
        if (item != null ? !item.equals(that.item) : that.item != null) return false;
        if (itemHtml != null ? !itemHtml.equals(that.itemHtml) : that.itemHtml != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        if(id==null){
            return 0;
        }
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (itemHtml != null ? itemHtml.hashCode() : 0);
        result = 31 * result +  (isRight?1:0);
        return result;
    }

//    @ManyToOne
//    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID", nullable = false)
//    public Question getCsQuestionByQuestionId() {
//        return csQuestionByQuestionId;
//    }
//
//    public void setCsQuestionByQuestionId(Question csQuestionByQuestionId) {
//        this.csQuestionByQuestionId = csQuestionByQuestionId;
//    }
}
