package com.wondersgroup.tpa.service.impl;

import com.wondersgroup.tpa.mapper.SMethodMapper;
import com.wondersgroup.tpa.mapper.SResourceMapper;
import com.wondersgroup.tpa.model.SMethod;
import com.wondersgroup.tpa.model.SResource;
import com.wondersgroup.tpa.service.IMethodService;
import com.wondersgroup.tpa.service.IResourceService;
import com.wondersgroup.tpa.service.resource.MethodSecurityMetadataSourceService;
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
public class MethodServiceImpl extends CommonServiceImpl<SMethod> implements IMethodService {
    @Autowired
    private SMethodMapper methodMapper;
    @Autowired
    private MethodSecurityMetadataSourceService methodSecurityMetadataSourceService;

    @Override
    public WondersgroupMapper<SMethod> getMapper() {
        return methodMapper;
    }

    @Override
    public List<SMethod> findAll() {
        return methodMapper.selectAll();
    }

    @Override
    public List<SMethod> queryByRoleId(Long roleId) {
        return methodMapper.queryByRoleId(roleId);
    }

    @Override
    public int save(SMethod entity) {
        int changeNum = super.save(entity);
        methodSecurityMetadataSourceService.loadMethodAuthConfig();
        return changeNum;
    }

    @Override
    public int delete(SMethod entity) {
        int changeNum = super.delete(entity);
        methodSecurityMetadataSourceService.loadMethodAuthConfig();
        return changeNum;
    }

    @Override
    public int delete(Serializable id) {
        int changeNum = super.delete(id);
        methodSecurityMetadataSourceService.loadMethodAuthConfig();
        return changeNum;
    }

    @Override
    public int update(SMethod entity) {
        int changeNum = super.update(entity);
        methodSecurityMetadataSourceService.loadMethodAuthConfig();
        return changeNum;
    }

    @Override
    public int saveAll(List<SMethod> List) {
        int changeNum = super.saveAll(List);
        methodSecurityMetadataSourceService.loadMethodAuthConfig();
        return changeNum;
    }
}
