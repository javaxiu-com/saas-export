package com.gyhqq.service.system.impl;

import com.gyhqq.dao.system.FeedbackDao;
import com.gyhqq.domain.system.Feedback;
import com.gyhqq.domain.system.FeedbackExample;
import com.gyhqq.service.system.FeedbackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public void deleteByPrimaryKey(String feedbackId) {
        feedbackDao.deleteByPrimaryKey(feedbackId);
    }

    @Override
    public void insertSelective(Feedback record) {
        record.setState("0");
        record.setFeedbackId(UUID.randomUUID().toString());
        feedbackDao.insert(record);
    }

    @Override
    public List<Feedback> selectByExample(FeedbackExample example) {
        return feedbackDao.selectByExample(example);
    }

    @Override
    public Feedback selectByPrimaryKey(String feedbackId) {
        return feedbackDao.selectByPrimaryKey(feedbackId);
    }

    @Override
    public void updateByPrimaryKeySelective(Feedback record) {
        feedbackDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo findPageByHelper(Integer page, Integer size, FeedbackExample example) {
        PageHelper.startPage(page, size);
        List<Feedback> feedbacks = feedbackDao.selectByExample(example);
        return new PageInfo(feedbacks);
    }
}
