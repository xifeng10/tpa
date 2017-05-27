package com.chuisha.service.impl;

import com.chuisha.mapper.SelectItemMapper;
import com.chuisha.model.Question;
import com.chuisha.model.SelectItem;
import com.chuisha.service.ISelectItemService;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import com.wondersgroup.util.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xifeng on 2016/7/3.
 */
@Service
public class SelectItemServiceImpl extends CommonServiceImpl<SelectItem> implements ISelectItemService {

    @Override
    public int saveOrUpdate(List<SelectItem> items, Long questionId) {
        List<SelectItem> oldItems = super.findBy("questionId", questionId);
        for (SelectItem item:oldItems)
            super.delete(item);
        for (SelectItem item:items ) {
            item.setQuestionId(questionId);
            super.save(item);
        }
        return items.size();
    }

}
