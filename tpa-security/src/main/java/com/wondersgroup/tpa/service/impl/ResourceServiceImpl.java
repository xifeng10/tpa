package com.wondersgroup.tpa.service.impl;

import com.wondersgroup.tpa.mapper.SResourceMapper;
import com.wondersgroup.tpa.model.SResource;
import com.wondersgroup.tpa.service.IResourceService;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import com.wondersgroup.util.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public WondersgroupMapper<SResource> getMapper() {
        return resourceMapper;
    }
}