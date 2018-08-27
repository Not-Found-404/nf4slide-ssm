package com.qtu404.option.service;

import com.qtu404.option.domain.Option;
import com.qtu404.util.web.ssm.service.BaseService;

import java.util.List;

public interface OptionService extends BaseService<Option> {
    int modify(List<Option> optionList);
}
