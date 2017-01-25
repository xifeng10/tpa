package com.wondersgroup.tpa.service;

import com.wondersgroup.tpa.model.SEmployee;
import com.wondersgroup.util.service.ICommonService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.wondersgroup.tpa.service
 * @Description:
 * @Author: xifeng chenxifeng@wondersgroup.com
 * @Date: 2017-01-20
 * @Time: 11:03
 */
public interface IEmployeeService extends ICommonService<SEmployee> {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
