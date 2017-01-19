package com.wondersgroup.util.service;

import com.wondersgroup.util.util.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: CommonServices.java
 * @Package com.teda.services
 * @Description: 公共的Services方法
 * @author 陈希峰 chenxf10@126.com
 * @date 2012-4-8 上午11:41:29
 * @version V1.0
 */
public interface ICommonService<T> {
	/**
	 *
	 * @Title: save
	 * @author 陈希峰 chenxf10@126.com
	 * @Description: 新增
	 * @param entity
	 * @return void
	 * @throws
	 */
	void save(T entity) throws Exception;

	/**
	 * 
	 * @Title: delete
	 * @author 陈希峰 chenxf10@126.com
	 * @Description: 根据主键删除
	 * @param entity
	 * @return void
	 * @throws
	 */
	void delete(T entity) throws Exception;

	/**
	 * 
	 * @Title: update
	 * @author 陈希峰 chenxf10@126.com
	 * @Description: 修改
	 * @param entity
	 * @return void
	 * @throws
	 */
	void update(T entity) throws Exception;

	/**
	 * @Title findByUniqueResult
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 根据属性查询第一个记录
	 * @param propertyName
	 * @param value
	 * @return T
	 * @throws
	 */
	T findByUniqueResult(String propertyName, Object value);

	/**
	 * @Title findBy
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 根据属性查询
	 * @param propertyName
	 * @param value
	 * @
	 * @return List<T>
	 * @throws
	 */
	List<T> findBy(String propertyName, Object value);

	/**
	 * 
	 * @Title get
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 根据主键获取唯一的实体
	 * @param id
	 * @return T
	 * @throws
	 */
	T get(Serializable id);

	/**
	 * 根据主键删除
	 * @param id
	 * @throws Exception
	 */
	void delete(Serializable id) throws Exception;

	/**
	 * @Title queryAll
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 查询所有
	 * @return Object
	 * @throws
	 */
	List<T> findAll();

	/**
	 * @Title findByExample
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 根据entity查找跟entity属性相同的实体
	 * @param entity
	 * @return List<T>
	 * @throws
	 */
	List<T> findByExample(T entity);

	/**
	 * @Title queryAllByPage
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 分页查询
	 * @param page
	 * @return Page<T>
	 * @throws
	 */
	public Page<T> queryAllByPage(Page<T> page);

	/**
	 * @Title saveAll
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 批量添加
	 * @param list
	 * @return void
	 * @throws
	 */
	public void saveAll(List<T> list) throws Exception;

}
