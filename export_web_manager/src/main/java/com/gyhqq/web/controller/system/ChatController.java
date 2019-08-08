package com.gyhqq.web.controller.system;

import com.gyhqq.domain.system.Chat;
import com.gyhqq.domain.system.ChatExample;
import com.gyhqq.domain.system.User;
import com.gyhqq.service.system.ChatService;
import com.gyhqq.service.system.UserService;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/system/chat")
public class ChatController extends BaseController {

    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer size) {

        ChatExample chatExample = new ChatExample();
        ChatExample.Criteria criteria = chatExample.createCriteria();
        //根据登录人的公司Id,和收件人的Id进行查询
        criteria.andCompanyIdEqualTo(getLoginCompanyId());
        criteria.andReceiveIdEqualTo(getLoginUser().getId());
        PageInfo pageInfo = chatService.findAll(page, size, chatExample);
        model.addAttribute("page", pageInfo);
        return "system/chat/chat-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        //根据公司id查询所有用户,自己除外
        List<User> userList = userService.findAllNotMime(getLoginCompanyId(), getLoginUser().getId());
        model.addAttribute("userList", userList);
        return "system/chat/chat-add";
    }

    @RequestMapping("/edit")
    public String edit(Chat chat) {
        chat.setSendId(getLoginUser().getId());
        chat.setSendName(getLoginUser().getUserName());
        chat.setSendDept(getLoginUser().getDeptName());
        chat.setCompanyId(getLoginCompanyId());
        chat.setCompanyName(getLoginCompanyName());
        chatService.insertSelective(chat);
        return "redirect:/system/chat/list.do";
    }

    @RequestMapping("/toView")
    public String toView(Model model, String id) {
        //修改信息的状态为已读

        Chat chat = chatService.selectByPrimaryKey(id);

        /*Chat chat1 = new Chat();
        chat1.setChatId(id);
        chat1.setState(1);*/
        chat.setState(1);
        chatService.updateByPrimaryKeySelective(chat);

        model.addAttribute("chat", chat);
        return "system/chat/chat-view";
    }

    @RequestMapping("/msgNumber")
    public @ResponseBody
    int msgNumber() {
        //根据公司id,根据登录用户id,根据状态为0的查询
        ChatExample chatExample = new ChatExample();
        ChatExample.Criteria criteria = chatExample.createCriteria();
        criteria.andCompanyIdEqualTo(getLoginCompanyId());
        criteria.andReceiveIdEqualTo(getLoginUser().getId());
        criteria.andStateEqualTo(0);
        List<Chat> chats = chatService.selectByExample(chatExample);
        return chats.size();
    }

    @RequestMapping("/sentList")
    public String sentList(Model model, @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "5") Integer size) {

        ChatExample chatExample = new ChatExample();
        ChatExample.Criteria criteria = chatExample.createCriteria();
        //根据登录人的公司Id,和发件人的Id进行查询
        criteria.andCompanyIdEqualTo(getLoginCompanyId());
        criteria.andSendIdEqualTo(getLoginUser().getId());
        PageInfo pageInfo = chatService.findAll(page, size, chatExample);
        model.addAttribute("page", pageInfo);
        return "system/chat/chat-list";
    }

    @RequestMapping("/reply")
    public String reply(Model model, String id) {
        //根据公司id查询所有用户,自己除外
        List<User> userList = userService.findAllNotMime(getLoginCompanyId(), getLoginUser().getId());
        model.addAttribute("userList", userList);
        //根据聊天id查询聊天记录
        Chat chat = chatService.selectByPrimaryKey(id);
        model.addAttribute("chat", chat);
        return "system/chat/chat-reply";
    }

    @RequestMapping("/delete")
    public String delete(String id) {
        chatService.deleteByPrimaryKey(id);
        return "redirect:/system/chat/list.do";
    }
}
