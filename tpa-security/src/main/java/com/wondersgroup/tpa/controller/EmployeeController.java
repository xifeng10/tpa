package com.wondersgroup.tpa.controller;

import com.wondersgroup.tpa.conf.TpaProperties;
import com.wondersgroup.tpa.model.SEmployee;
import com.wondersgroup.tpa.service.IEmployeeService;
import com.wondersgroup.util.controller.BaseRestSpringController;
import com.wondersgroup.util.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.wondersgroup.tpa.controller
 * @Description:
 * @Author: xifeng chenxifeng@wondersgroup.com
 * @Date: 2017-01-20
 * @Time: 11:06
 */
@Controller
@RequestMapping("/system/employee")
public class EmployeeController extends BaseRestSpringController<SEmployee, Long> {

    private static Log log = LogFactory.getLog(EmployeeController.class);

    public static Md5PasswordEncoder ENCODER = new Md5PasswordEncoder();
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private TpaProperties tpaProperties;

    @Override
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, SEmployee model) {
//        UserDetails userDetails = employeeService.loadUserByUsername("xifeng");
//        System.out.println(userDetails.getUsername());
//        System.out.println(userDetails.getPassword());
//        List<SRole> roles = ((SEmployee) userDetails).getRoles();
//        System.out.print("角色有：");
//        for(SRole r:roles) {
//            System.out.print(r.getName()+",");
//        }
//        System.out.println();
        return new ModelAndView("system/employee");
    }

//    @Override
    public Object page(HttpServletRequest request, HttpServletResponse response, Page page, SEmployee model) {
        return employeeService.queryAllByPage(page);
    }

    @Override
    public Object create(HttpServletRequest request, HttpServletResponse response, SEmployee model) throws Exception {
        if (employeeService.get(model.getId())!=null){
            Map<String,Object> returnMap = new HashMap();
            returnMap.put("flag",ERROR);
            returnMap.put("msg","该人员已经存在工作信息！");
//            throw new Exception("该人员已经存在工作信息！");
            return returnMap;
        }else {
            model.setCreateBy(1l);
//            model.setCreateBy(((SEmployee) SecurityContextHolder.getContext().getAuthentication()
//                    .getPrincipal()).getId());

            model.setCreateTime(new Date());
            model.setPassword(ENCODER.encodePassword(tpaProperties.getDefaultPassword(),	model.getUsername()));
            employeeService.save(model);
            return SUCCESS;
        }

    }

    @Override
    public Object update(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, SEmployee model) throws Exception {
        return super.update(id, request, response, model);
    }

    @Override
    public Object delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
