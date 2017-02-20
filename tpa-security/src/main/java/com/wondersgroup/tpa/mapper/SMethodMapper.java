package com.wondersgroup.tpa.mapper;

import com.wondersgroup.tpa.model.SMethod;
import com.wondersgroup.tpa.model.SResource;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SMethodMapper extends WondersgroupMapper<SMethod> {
    /**
     * 根据方法ID获取拥有该方法权限的角色
     * @param roleId
     * @return
     */
    @Select(
    "SELECT\n" +
            "	M.ID," +
            "   PACKAGE_NAME," +
            "   CLASS_NAME," +
            "   METHOD_NAME,\n" +
            "   CREATE_BY,\n" +
            "	CREATE_TIME,\n" +
            "	UPDATE_BY,\n" +
            "	UPDATE_TIME,\n" +
            "	REMARK\n" +
            "FROM S_METHOD M\n" +
            "LEFT JOIN S_ROLE_METHOD RM ON M.ID = RM.METHOD_ID\n"+
            "WHERE ROLE_ID = #{ID}")
    public List<SMethod> queryByRoleId(Long roleId);
}