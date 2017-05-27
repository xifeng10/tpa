package com.chuisha.service.impl;

import com.chuisha.mapper.QuestionClassifyMapper;
import com.chuisha.mapper.QuestionMapper;
import com.chuisha.model.QuestionClassify;
import com.chuisha.service.IQuestionClassifyService;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import com.wondersgroup.util.service.impl.CommonServiceImpl;
import com.wondersgroup.util.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xifeng on 2016/7/10.
 */
@Service
public class QuestionClassifyServiceImpl extends CommonServiceImpl<QuestionClassify> implements IQuestionClassifyService {

    @Override
    public Page<QuestionClassify> query(Page<QuestionClassify> page, QuestionClassify classify) {
        return null;
    }

}
