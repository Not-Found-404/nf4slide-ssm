package com.qtu404.option.controller;

import com.alibaba.fastjson.JSON;
import com.qtu404.option.domain.Option;
import com.qtu404.option.service.OptionService;
import com.qtu404.util.web.Result;
import com.qtu404.util.web.ssm.controller.BaseController;
import com.qtu404.util.web.ssm.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping("option/")
@Controller
public class OptionController extends BaseController<Option> {

    @Resource(name="optionService")
    OptionService optionService;

    @RequestMapping(value = "addNewOption",method = RequestMethod.POST)
    public void addNewOption(@RequestBody String body, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        Option option = JSON.parseObject(body, Option.class);
        option = optionService.save(option);
        writeResult(response, option);
    }
    @RequestMapping("deleteOption")
    public void deleteOption(@RequestBody String optionId, HttpServletResponse response, HttpServletRequest request) {
        Option option = new Option();
        option.setOptionId(Integer.parseInt(optionId));
        int rst = optionService.delete(option);
        Result result = new Result();
        if (rst == 1) {
            result.setResult("delete Success");
            result.setCode(200);
        } else {
            result.setResult("delete fail");
            result.setCode(500);
        }
        writeResult(response, result);
    }
    @RequestMapping("fetchOptionById")
    public void fetchOptionById(HttpServletResponse response, HttpServletRequest request){
        String optionId = request.getParameter("optionId");
        Option option = optionService.fetchById(Integer.parseInt(optionId));
        writeResult(response, option);
    }
    @Override
    protected BaseService<Option> getBaseService() {
        return optionService;
    }
}
