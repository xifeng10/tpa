package com.wondersgroup.tpa.service.impl;

import com.wondersgroup.tpa.mapper.SPersonMapper;
import com.wondersgroup.tpa.model.SPerson;
import com.wondersgroup.tpa.service.IPersonService;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import com.wondersgroup.util.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.wondersgroup.tpa.service.impl
 * @Description:
 * @Author: xifeng chenxifeng@wondersgroup.com
 * @Date: 2017-01-24
 * @Time: 10:46
 */
@Service
public class PersonServiceImpl extends CommonServiceImpl<SPerson> implements IPersonService{
}
