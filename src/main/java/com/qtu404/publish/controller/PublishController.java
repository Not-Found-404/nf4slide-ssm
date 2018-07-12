package com.qtu404.publish.controller;

import com.qtu404.publish.domain.Publish;
import com.qtu404.publish.service.PublishService;
import com.qtu404.util.web.ssm.controller.BaseController;
import com.qtu404.util.web.ssm.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RequestMapping("publish/")
@Controller
public class PublishController extends BaseController<Publish> {

    @Resource(name="publishService")
    PublishService publishService;

    @Override
    protected BaseService<Publish> getBaseService() {
        return publishService;
    }
}
