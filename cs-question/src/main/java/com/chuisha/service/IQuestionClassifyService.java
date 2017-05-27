package com.chuisha.service;

import com.chuisha.model.QuestionClassify;
import com.wondersgroup.util.service.ICommonService;
import com.wondersgroup.util.util.Page;

/**
 * Created by xifeng on 2016/7/10.
 */
public interface IQuestionClassifyService extends ICommonService<QuestionClassify> {
    public Page<QuestionClassify> query(Page<QuestionClassify> page, QuestionClassify classify);
}
