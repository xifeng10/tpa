package com.wondersgroup.tpa.mapper;

import com.wondersgroup.tpa.model.SRole;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SRoleMapper extends WondersgroupMapper<SRole> {

    /**
     * 根据用户ID获取该用户的所有角色
     * @param employeeId
     * @return
     */
    @Select("SELECT R.ID,R.NAME,R.CREATE_BY,R.CREATE_TIME,R.UPDATE_BY,R.UPDATE_TIME,R.REMARK FROM S_ROLE R LEFT JOIN S_EMPLOYEE_ROLE ER ON R.ID=ER.ROLE_ID WHERE ER.EMPLOYEE_ID=#{id}")
    public List<SRole> queryRolesByEmployeeId(Long employeeId);

    /**
     * 根据资源ID获取拥有该资源权限的角色
     * @param resourceId
     * @return
     */
    @Select("SELECT R.ID,R.NAME,R.CREATE_BY,R.CREATE_TIME,R.UPDATE_BY,R.UPDATE_TIME,R.REMARK FROM S_ROLE R LEFT JOIN S_ROLE_RESOURCE RR ON R.ID=RR.ROLE_ID WHERE RR.RESOURCE_ID=#{id}")
    public List<SRole> queryRolesByResourceId(Long resourceId);
    /**
     * 根据方法ID获取拥有该方法权限的角色
     * @param methodId
     * @return
     */
    @Select("SELECT R.ID,R.NAME,R.CREATE_BY,R.CREATE_TIME,R.UPDATE_BY,R.UPDATE_TIME,R.REMARK FROM S_ROLE R LEFT JOIN S_ROLE_METHOD RM ON R.ID=RM.ROLE_ID WHERE RM.METHOD_ID=#{id}")
    public List<SRole> queryRolesByMethodId(Long methodId);
}