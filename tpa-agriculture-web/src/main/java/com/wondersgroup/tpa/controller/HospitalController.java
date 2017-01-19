package com.wondersgroup.tpa.controller;

import com.wondersgroup.tpa.model.TpaHospital;
import com.wondersgroup.tpa.service.IHospitalService;
import com.wondersgroup.util.controller.BaseRestSpringController;
import com.wondersgroup.util.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.wondersgroup.tpa.controller
 * @Description:
 * @Author: xifeng chenxifeng@wondersgroup.com
 * @Date: 2017-01-17
 * @Time: 13:41
 */
@Controller
@RequestMapping(value = "/hospital")
public class HospitalController extends BaseRestSpringController<TpaHospital, Long> {

    @Autowired
    private IHospitalService hospitalService;

//    @Override
    public ModelAndView index(HttpServletRequest request,
                              HttpServletResponse response, TpaHospital hospital) {
        ModelAndView result = new ModelAndView("hospital/index");
        return result;
    }

//    @Override
    public Object page(HttpServletRequest request, HttpServletResponse response, Page page, TpaHospital model) {
        return hospitalService.queryAllByPage(page);
    }

}
