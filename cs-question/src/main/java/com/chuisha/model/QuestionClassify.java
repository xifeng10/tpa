package com.chuisha.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xifeng on 2016/9/7.
 */
@Table(name = "CS_QUESTION_CLASSIFY")
public class QuestionClassify {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String treePath;
    private Long insertBy;
    private Date insertTime;
    private Long updateBy;
    private Date updateTime;
    private Long parentId;

    public QuestionClassify(long id) {
        this.id = id;
    }

    public QuestionClassify() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "TREE_PATH")
    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    @Column(name = "INSERT_BY")
    public Long getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(Long insertBy) {
        this.insertBy = insertBy;
    }

    @Column(name = "INSERT_TIME")
    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    @Column(name = "UPDATE_BY")
    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    @Column(name = "UPDATE_TIME")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionClassify that = (QuestionClassify) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (treePath != null ? !treePath.equals(that.treePath) : that.treePath != null) return false;
        if (insertBy != null ? !insertBy.equals(that.insertBy) : that.insertBy != null) return false;
        if (insertTime != null ? !insertTime.equals(that.insertTime) : that.insertTime != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (treePath != null ? treePath.hashCode() : 0);
        result = 31 * result + (insertBy != null ? insertBy.hashCode() : 0);
        result = 31 * result + (insertTime != null ? insertTime.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

}
