package com.wondersgroup.util.service.impl;

import com.github.pagehelper.PageHelper;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import com.wondersgroup.util.service.ICommonService;
import com.wondersgroup.util.util.Page;
import com.wondersgroup.util.util.ReflectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * @author 陈希峰 chenxf10@126.com
 * @version V1.0
 * @Title: CommonServicesImpl.java
 * @Package com.teda.services.impl
 * @Description: 公共的Services具体实现方法
 * @date 2012-4-8 上午11:47:23
 */
public abstract class CommonServiceImpl<T> implements ICommonService<T> {
    public static final String LOGGER_FLAG = "common-service";

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public abstract WondersgroupMapper<T> getMapper();

    protected Class<T> entityClass;

    /**
     * 在构造函数中利用反射机制获得参数T的具体类
     */
    public CommonServiceImpl() {
        entityClass = ReflectUtils.getClassGenricType(getClass());
    }

    public T findByUniqueResult(String propertyName, Object value) {
        List<T> list = findBy(propertyName, value);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 使用反射机制把属性填充到对象，然后使用对象进行查询
     *
     * @param propertyName
     * @param value
     * @return
     * @
     */
    public List<T> findBy(String propertyName, Object value) {
//        try {
//            T entity = (T) entityClass.newInstance();
//            Method m = entityClass.getMethod("set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1), value.getClass());
//            if (m != null) {
//                m.invoke(entity, value);
//                return getMapper().select(entity);
//            } else {
//                logger.debug(LOGGER_FLAG, propertyName + "对应的属性类型不正确" + value.getClass());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.debug(LOGGER_FLAG, e.getMessage());
//        }
//
//        return new ArrayList<T>();

        Example example = new Example(entityClass);
        example.createCriteria().andEqualTo(propertyName, value);
        return getMapper().selectByExample(example);
    }

    /**
     * @param entity
     * @return void
     * @Title: save
     * @author 陈希峰 chenxf10@126.com
     * @Description: 新增
     */
    public int save(T entity) {
        return getMapper().insert(entity);
    }

    /**
     * @param entity
     * @return void
     * @Title: delete
     * @author 陈希峰 chenxf10@126.com
     * @Description: 根据主键删除
     */
    public int delete(T entity) {
        return getMapper().delete(entity);
    }

    /**
     * @param id
     * @throws Exception
     * @Title delete
     * @author 陈希峰 chenxf10@126.com
     * @Description 根据主键删除对象
     */
    public int delete(Serializable id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    /**
     * @param id
     * @return T
     * @throws
     * @Title get
     * @author 陈希峰 chenxf10@126.com
     * @Description 根据主键获取唯一的实体
     */
    public T get(Serializable id) {
        return getMapper().selectByPrimaryKey(id);
    }

    /**
     * @param entity
     * @return void
     * @Title: update
     * @author 陈希峰 chenxf10@126.com
     * @Description: 修改
     */
    public int update(T entity) {
        return getMapper().updateByPrimaryKeySelective(entity);
    }

    /**
     * @return
     * @Title queryAll
     * @author 陈希峰 chenxf10@126.com
     * @Description 查询所有
     */
    public List<T> findAll() {
        return getMapper().selectAll();
    }

    /**
     * @param entity
     * @return List<T>
     * @throws
     * @Title findByExample
     * @author 陈希峰 chenxf10@126.com
     * @Description 根据entity查找跟entity属性相同的实体
     */
    public List<T> findByExample(T entity) {
        return getMapper().select(entity);
    }

    /**
     * @param List
     * @return void
     * @throws
     * @Title saveAll
     * @author 陈希峰 chenxf10@126.com
     * @Description 批量添加
     */
    public int saveAll(List<T> List) {
        return getMapper().insertList(List);
    }

    /**
     * @param page
     * @param example
     * @return Page<T>
     * @throws
     * @Title queryAllByPage
     * @author 陈希峰 chenxf10@126.com
     * @Description 安装通用Example进行分页查询
     */
    public Page<T> queryByExampleByPage(Page<T> page, Example example) {

        if (page == null) {
            page = new Page<T>();
        }
        PageHelper.startPage(page.getCurrentPage(), page.getSizeOfPerPage());
        List<T> results = null;
        //通用Example查询
        if (!StringUtils.isEmpty(page.getOrderBy())) {
            example.setOrderByClause(page.getOrderBy() + " " + page.getSort());
        }
        results = getMapper().selectByExample(example);
        page.setResults(results);
        com.github.pagehelper.Page pagehelper = ((com.github.pagehelper.Page) results);
        page.setTotalSize(pagehelper.getTotal());
        return page;
    }

    /**
     * @param page
     * @return Page<T>
     * @throws
     * @Title queryAllByPage
     * @author 陈希峰 chenxf10@126.com
     * @Description 分页查询
     */
    public Page<T> queryAllByPage(Page<T> page) {
        if (page == null) {
            page = new Page<T>();
        }
        PageHelper.startPage(page.getCurrentPage(), page.getSizeOfPerPage());
        List<T> results = null;
        if (!StringUtils.isEmpty(page.getOrderBy())) {
            //通用Example查询
            Example example = new Example(entityClass);
            example.setOrderByClause(page.getOrderBy() + " " + page.getSort());
            results = getMapper().selectByExample(example);
        } else {
            results = getMapper().selectAll();
        }
        page.setResults(results);
        com.github.pagehelper.Page pagehelper = ((com.github.pagehelper.Page) results);
        page.setTotalSize(pagehelper.getTotal());
        return page;

    }

}