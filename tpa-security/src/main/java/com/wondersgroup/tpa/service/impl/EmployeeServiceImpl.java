package com.wondersgroup.tpa.service.impl;

import com.wondersgroup.tpa.mapper.SEmployeeMapper;
import com.wondersgroup.tpa.mapper.SRoleMapper;
import com.wondersgroup.tpa.model.SEmployee;
import com.wondersgroup.tpa.service.IEmployeeService;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import com.wondersgroup.util.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.wondersgroup.tpa.service.impl
 * @Description:
 * @Author: xifeng chenxifeng@wondersgroup.com
 * @Date: 2017-01-20
 * @Time: 11:04
 */
@Service
public class EmployeeServiceImpl extends CommonServiceImpl<SEmployee> implements IEmployeeService, UserDetailsService {
    @Autowired
    private SEmployeeMapper employeeMapper;
    @Autowired
    private SRoleMapper roleMapper;

    @Override
    public WondersgroupMapper<SEmployee> getMapper() {
        return employeeMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SEmployee employee = findByUniqueResult("username", username);
        if (employee != null) {
            employee.setRoles(roleMapper.queryRolesByEmployeeId(employee.getId()));
        } else {
            throw new UsernameNotFoundException("找不到用户：" + username);
        }
        return employee;
    }
}
