package com.wondersgroup.util.service;

import com.wondersgroup.util.util.Page;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * @author 陈希峰 chenxf10@126.com
 * @version V1.0
 * @Title: CommonServices.java
 * @Package com.teda.services
 * @Description: 公共的Services方法
 * @date 2012-4-8 上午11:41:29
 */
public interface ICommonService<T> {
    /**
     * @param entity
     * @return void
     * @throws
     * @Title: save
     * @author 陈希峰 chenxf10@126.com
     * @Description: 新增
     */
    int save(T entity) throws Exception;

    /**
     * @param entity
     * @return void
     * @throws
     * @Title: delete
     * @author 陈希峰 chenxf10@126.com
     * @Description: 根据主键删除
     */
    int delete(T entity) throws Exception;

    /**
     * @param entity
     * @return void
     * @throws
     * @Title: update
     * @author 陈希峰 chenxf10@126.com
     * @Description: 修改
     */
    int update(T entity) throws Exception;

    /**
     * @param propertyName
     * @param value
     * @return T
     * @throws
     * @Title findByUniqueResult
     * @author 陈希峰 chenxf10@126.com
     * @Description 根据属性查询第一个记录
     */
    T findByUniqueResult(String propertyName, Object value);

    /**
     * @param propertyName
     * @param value
     * @return List<T>
     * @throws
     * @Title findBy
     * @author 陈希峰 chenxf10@126.com
     * @Description 根据属性查询
     * @
     */
    List<T> findBy(String propertyName, Object value);

    /**
     * @param id
     * @return T
     * @throws
     * @Title get
     * @author 陈希峰 chenxf10@126.com
     * @Description 根据主键获取唯一的实体
     */
    T get(Serializable id);

    /**
     * 根据主键删除
     *
     * @param id
     * @throws Exception
     */
    int delete(Serializable id);

    /**
     * @return Object
     * @throws
     * @Title queryAll
     * @author 陈希峰 chenxf10@126.com
     * @Description 查询所有
     */
    List<T> findAll();

    /**
     * @param entity
     * @return List<T>
     * @throws
     * @Title findByExample
     * @author 陈希峰 chenxf10@126.com
     * @Description 根据entity查找跟entity属性相同的实体
     */
    List<T> findByExample(T entity);

    /**
     * @param page
     * @return Page<T>
     * @throws
     * @Title queryAllByPage
     * @author 陈希峰 chenxf10@126.com
     * @Description 分页查询
     */
    Page<T> queryAllByPage(Page<T> page);

    /**
     * 根据Example分页查询
     * @param page
     * @return
     */
    Page<T> queryByExampleByPage(Page<T> page, Example example);

    /**
     * @param list
     * @return void
     * @Title saveAll
     * @author 陈希峰 chenxf10@126.com
     * @Description 批量添加
     */
    int saveAll(List<T> list);

}
