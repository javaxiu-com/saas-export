package com.gyhqq.service.system.impl;

import com.gyhqq.dao.system.ChatDao;
import com.gyhqq.domain.system.Chat;
import com.gyhqq.domain.system.ChatExample;
import com.gyhqq.service.system.ChatService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatDao chatDao;

    @Override
    public void deleteByPrimaryKey(String chatId) {
        chatDao.deleteByPrimaryKey(chatId);
    }

    @Override
    public void insertSelective(Chat record) {
        record.setChatId(UUID.randomUUID().toString());
        record.setSendTime(new Date());
        record.setState(0);
        chatDao.insertSelective(record);

    }

    @Override
    public List<Chat> selectByExample(ChatExample example) {
        return chatDao.selectByExample(example);
    }

    @Override
    public Chat selectByPrimaryKey(String chatId) {

        Chat chat = chatDao.selectByPrimaryKey(chatId);
        //修改信息的状态为已读
        /*chat.setState(1);
        chatDao.updateByPrimaryKeySelective(chat);*/
        return chat;
    }

    @Override
    public void updateByPrimaryKeySelective(Chat record) {
        chatDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo findAll(Integer page, Integer size, ChatExample chatExample) {
        PageHelper.startPage(page, size);
        List<Chat> list = chatDao.selectByExample(chatExample);

        return new PageInfo(list);
    }
}
