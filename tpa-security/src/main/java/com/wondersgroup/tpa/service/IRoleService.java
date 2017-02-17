package com.wondersgroup.tpa.service;

import com.wondersgroup.tpa.model.SRole;
import com.wondersgroup.util.service.ICommonService;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.wondersgroup.tpa.service
 * @Description:
 * @Author: xifeng chenxifeng@wondersgroup.com
 * @Date: 2017-01-22
 * @Time: 13:41
 */
public interface IRoleService extends ICommonService<SRole>{
    public List<SRole> queryRolesByEmployeeId(Long employeeId);

    public List<SRole> queryRolesByResourceId(Long resourceId);

    /**
     * 保存角色，并添加角色的资源信息
     * @param model
     * @param resourceIds
     */
    void save(SRole model, List<Long> resourceIds);
    /**
     * 修改角色，并修改角色的资源信息
     * @param model
     * @param resourceIds
     */
    void update(SRole model, List<Long> resourceIds);

    List<SRole> queryRolesByMethodId(Long id);
}
