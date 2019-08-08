package com.gyhqq.service.system;

import com.gyhqq.domain.system.Feedback;
import com.gyhqq.domain.system.FeedbackExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FeedbackService {
    void deleteByPrimaryKey(String feedbackId);

    void insertSelective(Feedback record);

    List<Feedback> selectByExample(FeedbackExample example);

    Feedback selectByPrimaryKey(String feedbackId);

    void updateByPrimaryKeySelective(Feedback record);

    PageInfo findPageByHelper(Integer page, Integer size, FeedbackExample example);
}
