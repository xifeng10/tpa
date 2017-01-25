package com.wondersgroup.tpa.mapper;

import com.wondersgroup.tpa.model.SRole;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SRoleMapper extends WondersgroupMapper<SRole> {

    @Select("SELECT R.ID,R.NAME,R.CREATE_BY,R.CREATE_TIME,R.UPDATE_BY,R.UPDATE_TIME,R.REMARK FROM S_ROLE R LEFT JOIN S_EMPLOYEE_ROLE ER ON R.ID=ER.ROLE_ID WHERE ER.EMPLOYEE_ID=#{id}")
    public List<SRole> queryRolesByEmployeeId(Long employeeId);
}