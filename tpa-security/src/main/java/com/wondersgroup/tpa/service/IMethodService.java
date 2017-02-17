package com.wondersgroup.tpa.service;

import com.wondersgroup.tpa.model.SMethod;
import com.wondersgroup.tpa.model.SResource;
import com.wondersgroup.util.service.ICommonService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.wondersgroup.tpa.service
 * @Description:
 * @Author: xifeng chenxifeng@wondersgroup.com
 * @Date: 2017-01-24
 * @Time: 10:16
 */
public interface IMethodService extends ICommonService<SMethod> {
    /**
     * 根据角色ID查找所有的资源信息
     * @param roleId
     * @return
     */
    public List<SMethod> queryByRoleId(Long roleId);

}
