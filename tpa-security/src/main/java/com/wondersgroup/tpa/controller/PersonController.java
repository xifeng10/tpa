package com.wondersgroup.tpa.controller;

import com.wondersgroup.tpa.model.SPerson;
import com.wondersgroup.tpa.service.IPersonService;
import com.wondersgroup.util.controller.BaseRestSpringController;
import com.wondersgroup.util.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.wondersgroup.tpa.controller
 * @Description:
 * @Author: xifeng chenxifeng@wondersgroup.com
 * @Date: 2017-01-24
 * @Time: 10:47
 */
@RestController
@RequestMapping("system/person")
public class PersonController extends BaseRestSpringController<SPerson, Long> {
    @Autowired
    private IPersonService personService;

    @Override
    public Object index(HttpServletRequest request, HttpServletResponse response, SPerson model) {
        return new ModelAndView("system/person");
    }

    @RequestMapping("autocomplete")
    public List<SPerson> queryByAutocomplete(String realName) {
        Page page = new Page();
        if (!StringUtils.isEmpty(realName)) {
            Example example = new Example(SPerson.class);
            String likeStr = "%" + realName + "%";
            example.createCriteria().andLike("realName", likeStr);
            example.or().andLike("nameSpell", likeStr);
            example.or().andLike("spellAbbreviation", likeStr);
            example.or().andLike("email", likeStr);
            return personService.queryByExampleByPage(page, example).getResults();
        } else {
            return personService.queryAllByPage(page).getResults();
        }
    }

    @Override
    public Object page(HttpServletRequest request, HttpServletResponse response, Page page, SPerson model) {
        if (model != null && !StringUtils.isEmpty(model.getRealName())) {
            Example example = new Example(SPerson.class);
            example.createCriteria().andLike("realName", "%" + model.getRealName() + "%");
            return personService.queryByExampleByPage(page, example);
        }
        return personService.queryAllByPage(page);
    }

    @Override
    public Object create(HttpServletRequest request, HttpServletResponse response, SPerson model) throws Exception {
        model.setCreateBy(1l);
        model.setCreateTime(new Date());
        personService.save(model);
        return SUCCESS;
    }

    @Override
    public Object update(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, SPerson model) throws Exception {
        SPerson person = personService.get(id);
        model.setCreateTime(person.getCreateTime());
        model.setCreateBy(person.getCreateBy());
        model.setUpdateTime(new Date());
        model.setUpdateBy(1l);
        personService.update(model);
        return SUCCESS;
    }

    @Override
    public Object delete(@PathVariable Long id) {
        personService.delete(id);
        return SUCCESS;
    }
}
