package com.wondersgroup.tpa.service;

import com.wondersgroup.tpa.model.SEmployee;
import com.wondersgroup.util.service.ICommonService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.wondersgroup.tpa.service
 * @Description:
 * @Author: xifeng chenxifeng@wondersgroup.com
 * @Date: 2017-01-20
 * @Time: 11:03
 */
public interface IEmployeeService extends ICommonService<SEmployee>,UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     *  带角色ID保存用户信息
     * @param model
     * @param roleIds
     */
    void save(SEmployee model, List<Long> roleIds);
    /**
     *  带角色ID修改用户信息
     * @param model
     * @param roleIds
     */
    @PreAuthorize("hasAuthority('USER_QUERY')")
    void update(SEmployee model, List<Long> roleIds);
}
