package com.wondersgroup.tpa.service.impl;

import com.wondersgroup.tpa.mapper.SResourceMapper;
import com.wondersgroup.tpa.mapper.SRoleMapper;
import com.wondersgroup.tpa.mapper.SRoleResourceMapper;
import com.wondersgroup.tpa.model.SResource;
import com.wondersgroup.tpa.model.SRole;
import com.wondersgroup.tpa.model.SRoleResource;
import com.wondersgroup.tpa.service.IRoleService;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import com.wondersgroup.util.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private SRoleResourceMapper roleResourceMapper;
    @Override
    public WondersgroupMapper<SRole> getMapper() {
        return roleMapper;
    }

    @Override
    public List<SRole> queryRolesByEmployeeId(Long employeeId) {
        return roleMapper.queryRolesByEmployeeId(employeeId);
    }

    @Override
    public List<SRole> queryRolesByResourceId(Long resourceId) {
        return roleMapper.queryRolesByResourceId(resourceId);
    }

    @Override
    public void save(SRole model, List<Long> resourceIds) {
        roleMapper.insert(model);
        if(resourceIds!=null){
            saveRoleResource(model.getId(), resourceIds);
        }
    }

    /**
     * 保存角色资源信息
     * @param roleId
     * @param resourceIds
     */
    private void saveRoleResource(Long roleId, List<Long> resourceIds) {
        SRoleResource roleResource;
        for(Long resourceId:resourceIds){
            roleResource=new SRoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(resourceId);
            roleResourceMapper.insert(roleResource);
        }
    }

    @Override
    public void update(SRole role, List<Long> resourceIds) {
        roleMapper.updateByPrimaryKeySelective(role);
        SRoleResource roleResource = new SRoleResource();
        roleResource.setRoleId(role.getId());
        List<SRoleResource> roleResources = roleResourceMapper.select(roleResource);
        for(SRoleResource rr:roleResources){
            if(resourceIds.contains(rr.getResourceId())){
                resourceIds.remove(rr.getResourceId());
            }else{
                roleResourceMapper.delete(rr);
            }
        }
        saveRoleResource(role.getId(), resourceIds);
    }

    @Override
    public List<SRole> queryRolesByMethodId(Long id) {
        return roleMapper.queryRolesByMethodId(id);
    }
}
