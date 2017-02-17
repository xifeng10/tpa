package com.wondersgroup.tpa.controller;

import com.wondersgroup.tpa.model.SMethod;
import com.wondersgroup.tpa.model.SResource;
import com.wondersgroup.tpa.service.IMethodService;
import com.wondersgroup.tpa.service.IResourceService;
import com.wondersgroup.util.controller.BaseRestSpringController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.wondersgroup.tpa.controller
 * @Description:
 * @Author: xifeng chenxifeng@wondersgroup.com
 * @Date: 2017-01-24
 * @Time: 10:15
 */
@RestController
@RequestMapping("system/method")
public class MethodController extends BaseRestSpringController<SMethod, Long> {
    private static Log log = LogFactory.getLog(MethodController.class);
    @Autowired
    private IMethodService methodService;

    @Override
    public Object index(HttpServletRequest request, HttpServletResponse response, SMethod model) {
        return new ModelAndView("system/method");
    }

    @RequestMapping(value = "/queryByRoleId")
    @ResponseBody
    public Object queryByRoleId(Long roleId) {
        return methodService.queryByRoleId(roleId);
    }

    @RequestMapping(value = "/queryAll",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object queryAll() {
        return methodService.findAll();
    }

    @Override
    public Object create(HttpServletRequest request, HttpServletResponse response, SMethod model) throws Exception {
        model.setCreateBy(1l);
        model.setCreateTime(new Date());
        methodService.save(model);
        return model;
    }

    @Override
    public Object update(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, SMethod model) throws Exception {
        SMethod method = methodService.get(id);
        model.setId(id);
        model.setCreateTime(method.getCreateTime());
        model.setCreateBy(method.getCreateBy());
        model.setUpdateBy(1l);
        model.setUpdateTime(new Date());
        methodService.update(model);
        return model;
    }

    @Override
    public Object delete(@PathVariable Long id) {
        methodService.delete(id);
        return SUCCESS;
    }
}
