package com.wondersgroup.tpa.service.impl;

import com.wondersgroup.tpa.mapper.SResourceMapper;
import com.wondersgroup.tpa.model.SResource;
import com.wondersgroup.tpa.service.IResourceService;
import com.wondersgroup.tpa.service.resource.FilterInvocationSecurityMetadataSourceService;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import com.wondersgroup.util.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.wondersgroup.tpa.service.impl
 * @Description:
 * @Author: xifeng chenxifeng@wondersgroup.com
 * @Date: 2017-01-24
 * @Time: 10:17
 */
@Service
public class ResourceServiceImpl extends CommonServiceImpl<SResource> implements IResourceService {
    @Autowired
    private SResourceMapper resourceMapper;

    @Autowired
    private FilterInvocationSecurityMetadataSourceService metadataSourceService;

    @Override
    public List<SResource> findAll() {
        Example example = new Example(SResource.class);
        example.setOrderByClause("parent_id , show_index ");
        return resourceMapper.selectByExample(example);
    }

    @Override
    public List<SResource> queryByRoleId(Long roleId) {
        return resourceMapper.queryByRoleId(roleId);
    }

    @Override
    public int save(SResource entity) {
        int change = super.save(entity);
        metadataSourceService.loadResourceDefine();
        return change;
    }

    @Override
    public int delete(SResource entity) {
        int change = super.delete(entity);
        metadataSourceService.loadResourceDefine();
        return change;
    }

    @Override
    public int delete(Serializable id) {
        int change = super.delete(id);
        metadataSourceService.loadResourceDefine();
        return change;
    }

    @Override
    public int update(SResource entity) {
        int change = super.update(entity);
        metadataSourceService.loadResourceDefine();
        return change;
    }

    @Override
    public int saveAll(List<SResource> List) {
        int change = super.saveAll(List);
        metadataSourceService.loadResourceDefine();
        return change;
    }
}
