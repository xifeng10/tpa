package com.wondersgroup.tpa.mapper;

import com.wondersgroup.tpa.model.SResource;
import com.wondersgroup.tpa.model.SRole;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SResourceMapper  extends WondersgroupMapper<SResource> {
    /**
     * 根据资源ID获取拥有该资源权限的角色
     * @param roleId
     * @return
     */
    @Select("SELECT\n" +
            "	r.Id,\n" +
            "	type,\n" +
            "	parent_id,\n" +
            "	NAME,\n" +
            "	menu_url,\n" +
            "	security_url,\n" +
            "	method,\n" +
            "	ENABLE,\n" +
            "	is_can_del,\n" +
            "	show_index,\n" +
            "	create_by,\n" +
            "	create_time,\n" +
            "	update_by,\n" +
            "	update_time,\n" +
            "	remark\n" +
            "FROM\n" +
            "	s_resource r\n" +
            "LEFT JOIN s_role_resource rr ON r.ID = rr.RESOURCE_ID\n" +
            "WHERE\n" +
            "	ROLE_ID = #{id}")
    public List<SResource> queryByRoleId(Long roleId);
}