package com.wondersgroup.tpa.controller;

import com.wondersgroup.tpa.model.SResource;
import com.wondersgroup.tpa.service.IResourceService;
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
@RequestMapping("system/resource")
public class ResourceController extends BaseRestSpringController<SResource, Long> {
    private static Log log = LogFactory.getLog(ResourceController.class);
    @Autowired
    private IResourceService resourceService;

    @Override
    public Object index(HttpServletRequest request, HttpServletResponse response, SResource model) {
        return new ModelAndView("system/resource");
    }

    @Override
    public Object page(HttpServletRequest request, HttpServletResponse response, Page page, SResource model) {
        if (page == null) {
            page = new Page();
            page.setSizeOfPerPage(Integer.MAX_VALUE);
        }
        return resourceService.queryAllByPage(page);
    }

    @Override
    public Object create(HttpServletRequest request, HttpServletResponse response, SResource model) throws Exception {
        model.setCreateBy(1l);
        model.setCreateTime(new Date());
        resourceService.save(model);
        return SUCCESS;
    }

    @Override
    public Object update(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, SResource model) throws Exception {
        SResource resource = resourceService.get(id);
        model.setId(id);
        model.setCreateTime(resource.getCreateTime());
        model.setCreateBy(resource.getCreateBy());
        model.setUpdateBy(1l);
        model.setUpdateTime(new Date());
        return super.update(id, request, response, model);
    }

    @Override
    public Object delete(@PathVariable Long id) {
        resourceService.delete(id);
        return SUCCESS;
    }
}
