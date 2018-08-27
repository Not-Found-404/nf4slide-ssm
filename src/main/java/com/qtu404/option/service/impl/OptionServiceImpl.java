package com.qtu404.option.service.impl;

import com.qtu404.option.dao.OptionDao;
import com.qtu404.option.domain.Option;
import com.qtu404.option.service.OptionService;
import com.qtu404.util.web.ssm.dao.BaseDao;
import com.qtu404.util.web.ssm.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("optionService")
public class OptionServiceImpl extends BaseServiceImpl<Option> implements OptionService {

    @Resource(name="optionDao")
    OptionDao optionDao;

    @Override
    public int modify(List<Option> optionList){
        int result =0;
        for (Option option: optionList){
             result += optionDao.modify(option);
        }
        return result;
    }
    @Override
    protected BaseDao<Option> getBaseDao() {
        return optionDao;
    }
}
