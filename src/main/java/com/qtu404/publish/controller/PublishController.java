package com.qtu404.publish.controller;

import com.alibaba.fastjson.JSON;
import com.qtu404.publish.domain.Publish;
import com.qtu404.publish.service.PublishService;
import com.qtu404.question.domain.Question;
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
import java.util.List;

@RequestMapping("publish/")
@Controller
public class PublishController extends BaseController<Publish> {

    @Resource(name="publishService")
    PublishService publishService;

    @RequestMapping(value = "/addPublish", method = RequestMethod.POST)
    public void addPublish(@RequestBody String body,HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        Publish dto = JSON.parseObject(body, Publish.class);
       dto = publishService.save(dto);
        writeResult(response, dto);
    }
    // 测试完成
    @RequestMapping(value = "/fetchPublishById", method = RequestMethod.POST)
    public void fetchPublishById(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        String questionId = request.getParameter("questionId");
        List<Publish> publishList = publishService.findPublishByQuestionId(Integer.parseInt(questionId));
        writeResult(response, publishList);
    }
    @Override
    protected BaseService<Publish> getBaseService() {
        return publishService;
    }
}
