package com.qtu404.option.controller;

import com.qtu404.option.domain.Option;
import com.qtu404.option.service.OptionService;
import com.qtu404.util.web.ssm.controller.BaseController;
import com.qtu404.util.web.ssm.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RequestMapping("option/")
@Controller("optionController")
public class OptionController extends BaseController<Option> {

    @Resource(name="optionService")
    OptionService optionService;

    @Override
    protected BaseService<Option> getBaseService() {
        return optionService;
    }
}
