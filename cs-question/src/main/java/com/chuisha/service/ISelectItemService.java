package com.chuisha.service;

import com.chuisha.model.SelectItem;
import com.wondersgroup.util.service.ICommonService;

import java.util.List;

/**
 * Created by xifeng on 2016/7/3.
 */
public interface ISelectItemService extends ICommonService<SelectItem> {
    int saveOrUpdate(List<SelectItem> items, Long questionId);
}
