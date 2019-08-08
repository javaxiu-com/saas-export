package com.gyhqq.web.controller.system;

import com.gyhqq.domain.system.Feedback;
import com.gyhqq.domain.system.FeedbackExample;
import com.gyhqq.service.system.FeedbackService;
import com.gyhqq.service.system.UserService;
import com.gyhqq.web.activemq.EmailProducer;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/feedback")
public class FeedbackController extends BaseController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private EmailProducer emailProducer;
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer size) {

        FeedbackExample feedbackExample = new FeedbackExample();
        if (getLoginUser().getDegree() != 0) {
            FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
            criteria.andCompanyIdEqualTo(getLoginCompanyId());
        }
        PageInfo pageInfo = feedbackService.findPageByHelper(page, size, feedbackExample);
        model.addAttribute("page", pageInfo);
        return "system/feedback/feedback-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/system/feedback/feedback-add";
    }

    @RequestMapping("/edit")
    public String edit(Feedback feedback) {
        feedback.setCompanyId(getLoginCompanyId());
        feedback.setCompanyName(getLoginCompanyName());

        if (StringUtils.isEmpty(feedback.getFeedbackId())) {
            //增加
            feedback.setCreateBy(getLoginUser().getId());
            feedback.setCreateDept(getLoginUser().getDeptId());
            feedback.setCreateTime(new Date());
            feedbackService.insertSelective(feedback);
            //获取saas管理员邮箱
            String saasEmail = userService.findSaaSEmail(0);
            //发送邮件
            Map<String, String> map = new HashMap<>();
            map.put("receive", saasEmail);
            map.put("subject", "用户反馈");
            map.put("content", "您好,您有新的反馈,请及时处理");
            emailProducer.sendMessage(map);
        } else {
            //修改
            feedback.setUpdateBy(getLoginUser().getId());
            feedback.setUpdateTime(new Date());
            feedbackService.updateByPrimaryKeySelective(feedback);
        }
        return "redirect:/system/feedback/list.do";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Model model, String id) {
        Feedback feedback = feedbackService.selectByPrimaryKey(id);
        model.addAttribute("feedback", feedback);
        return "/system/feedback/feedback-update";

    }

    @RequestMapping("/delete")
    public String delete(String id) {
        feedbackService.deleteByPrimaryKey(id);
        return "redirect:/system/feedback/list.do";
    }

    //如果为0;去查询反馈数据未处理的条数(未处理的状态为0),并显示在铃铛位置,以及将状态改为2让处理按钮显示
    @RequestMapping("/remind")
    public @ResponseBody
    int remind() {
        FeedbackExample feedbackExample = new FeedbackExample();
        FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
        criteria.andStateEqualTo("0");
        List<Feedback> feedbacks = feedbackService.selectByExample(feedbackExample);
        /*for (Feedback feedback : feedbacks) {
            feedback.setState("2");
            feedbackService.updateByPrimaryKeySelective(feedback);
        }*/
        return feedbacks.size();
    }

    @RequestMapping("/toProcess")
    public String toProcess(String id, Model model) {
        Feedback feedback = feedbackService.selectByPrimaryKey(id);
        model.addAttribute("feedback", feedback);
        return "system/feedback/feedback-process";
    }

    //点击处理跳转的地方
    //处理完之后去到首页
    @RequestMapping("/updateFeedback")
    public String updateFeedback(Feedback feedback, Model model) {
        feedback.setState("1");
        feedbackService.updateByPrimaryKeySelective(feedback);
        return "redirect:/system/feedback/list.do";
    }

    //改变反馈的状态为2并转跳到list
/*    @RequestMapping("/changeState")
    public String changeState(){
        FeedbackExample feedbackExample = new FeedbackExample();
        FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
        criteria.andStateEqualTo("0");
        List<Feedback> feedbacks = feedbackService.selectByExample(feedbackExample);
        for (Feedback feedback : feedbacks) {
            feedback.setState("2");
            feedbackService.updateByPrimaryKeySelective(feedback);
        }
        return "redirect:/system/feedback/list.do";
    }*/

    //返回主页
    @RequestMapping("/home")
    public String home() {
        return "home/main";
    }

    @RequestMapping("/toView")
    public String toView(String id, Model model) {
        Feedback feedback = feedbackService.selectByPrimaryKey(id);
        model.addAttribute("feedback", feedback);
        return "system/feedback/feedback-view";
    }

}
