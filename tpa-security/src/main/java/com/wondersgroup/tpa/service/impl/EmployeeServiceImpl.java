package com.wondersgroup.tpa.service.impl;

import com.wondersgroup.tpa.mapper.SEmployeeMapper;
import com.wondersgroup.tpa.mapper.SEmployeeRoleMapper;
import com.wondersgroup.tpa.mapper.SRoleMapper;
import com.wondersgroup.tpa.model.SEmployee;
import com.wondersgroup.tpa.model.SEmployeeRole;
import com.wondersgroup.tpa.model.SRoleResource;
import com.wondersgroup.tpa.service.IEmployeeService;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import com.wondersgroup.util.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private SEmployeeRoleMapper employeeRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SEmployee employee = findByUniqueResult("username", username);
        if (employee != null) {
            employee.setRoles(roleMapper.queryRolesByEmployeeId(employee.getId()));
        } else {
            throw new UsernameNotFoundException("找不到用户：" + username);
        }
//        logger.debug(employee.getRoles());
        return employee;
    }

    @Override
    public void save(SEmployee employee, List<Long> roleIds) {
        employeeMapper.insert(employee);
        if(roleIds!=null){
            saveEmployeeRole(employee.getId(),roleIds);
        }
    }

    @Override
    public void update(SEmployee employee, List<Long> roleIds) {
        employeeMapper.updateByPrimaryKeySelective(employee);
        SEmployeeRole employeeRole = new SEmployeeRole();
        employeeRole.setEmployeeId(employee.getId());
        List<SEmployeeRole> employeeRoles = employeeRoleMapper.select(employeeRole);
        for(SEmployeeRole er:employeeRoles){
            if(roleIds.contains(er.getRoleId())){
                roleIds.remove(er.getRoleId());
            }else {
                employeeRoleMapper.delete(er);
            }
        }
        saveEmployeeRole(employee.getId(),roleIds);
    }
    /**
     * 保存角色资源信息
     * @param employeeId
     * @param roleIds
     */
    private void saveEmployeeRole(Long employeeId, List<Long> roleIds) {
        SEmployeeRole employeeRole;
        for(Long roleId:roleIds){
            employeeRole=new SEmployeeRole();
            employeeRole.setEmployeeId(employeeId);
            employeeRole.setRoleId(roleId);
            employeeRoleMapper.insert(employeeRole);
        }
    }

}
