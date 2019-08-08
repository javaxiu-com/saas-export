package com.gyhqq.service.system;

import com.gyhqq.domain.system.Chat;
import com.gyhqq.domain.system.ChatExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ChatService {

    void deleteByPrimaryKey(String chatId);

    void insertSelective(Chat record);

    List<Chat> selectByExample(ChatExample example);

    Chat selectByPrimaryKey(String chatId);

    void updateByPrimaryKeySelective(Chat record);

    PageInfo findAll(Integer page, Integer size, ChatExample chatExample);
}
