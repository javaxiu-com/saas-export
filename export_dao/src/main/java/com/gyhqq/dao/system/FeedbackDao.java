package com.gyhqq.dao.system;

import com.gyhqq.domain.system.Feedback;
import com.gyhqq.domain.system.FeedbackExample;

import java.util.List;

public interface FeedbackDao {

    int deleteByPrimaryKey(String feedbackId);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    List<Feedback> selectByExample(FeedbackExample example);

    Feedback selectByPrimaryKey(String feedbackId);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);
}