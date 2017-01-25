package com.wondersgroup.tpa.controller;

import com.wondersgroup.tpa.model.SRole;
import com.wondersgroup.tpa.service.IRoleService;
import com.wondersgroup.util.controller.BaseRestSpringController;
import com.wondersgroup.util.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.wondersgroup.tpa.controller
 * @Description:
 * @Author: xifeng chenxifeng@wondersgroup.com
 * @Date: 2017-01-22
 * @Time: 13:39
 */
@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseRestSpringController<SRole, Long> {
    private static Log log = LogFactory.getLog(RoleController.class);
    @Autowired
    private IRoleService roleService;

    @Override
    public Object index(HttpServletRequest request, HttpServletResponse response, SRole model) {
        return new ModelAndView("system/role");
    }

    @Override
    public Object page(HttpServletRequest request, HttpServletResponse response, Page page, SRole model) {
        return roleService.queryAllByPage(page);
    }

    @Override
    public Object show(@PathVariable Long id) {
        return roleService.get(id);
    }

    @Override
    public Object edit(@PathVariable Long id) throws Exception {
        return super.edit(id);
    }

    @Override
    public Object create(HttpServletRequest request, HttpServletResponse response, SRole model) throws Exception {
        model.setCreateBy(1l);
        model.setCreateTime(new Date());
        roleService.save(model);
        return "success";
    }

    @Override
    public Object update(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, SRole model) throws Exception {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String s = parameterNames.nextElement();
            System.out.println(s + "\t-\t" + request.getParameter(s));
        }
        model.setId(id);
        roleService.update(model);
        return new ModelAndView("system/role");
    }

    @Override
    public Object delete(@PathVariable Long id) {
        roleService.delete(id);
        return "success";
    }
}