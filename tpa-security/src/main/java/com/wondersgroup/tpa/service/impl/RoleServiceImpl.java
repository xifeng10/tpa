package com.wondersgroup.tpa.service.impl;

import com.wondersgroup.tpa.mapper.SRoleMapper;
import com.wondersgroup.tpa.model.SRole;
import com.wondersgroup.tpa.service.IRoleService;
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
 * @Date: 2017-01-22
 * @Time: 13:42
 */

@Service
public class RoleServiceImpl extends CommonServiceImpl<SRole> implements IRoleService {
    @Autowired
    private SRoleMapper roleMapper;
    @Override
    public WondersgroupMapper<SRole> getMapper() {
        return roleMapper;
    }
}
