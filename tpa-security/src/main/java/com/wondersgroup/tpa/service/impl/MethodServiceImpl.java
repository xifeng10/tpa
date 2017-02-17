package com.wondersgroup.tpa.service.impl;

import com.wondersgroup.tpa.mapper.SMethodMapper;
import com.wondersgroup.tpa.mapper.SResourceMapper;
import com.wondersgroup.tpa.model.SMethod;
import com.wondersgroup.tpa.model.SResource;
import com.wondersgroup.tpa.service.IMethodService;
import com.wondersgroup.tpa.service.IResourceService;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import com.wondersgroup.util.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
}
