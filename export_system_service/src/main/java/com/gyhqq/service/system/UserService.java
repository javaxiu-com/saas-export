package com.gyhqq.service.system;

import com.gyhqq.domain.cargo.Contract;
import com.gyhqq.domain.system.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

    PageInfo findPageByHelper(Integer page, Integer size, String loginCompanyId);

    void save(User user);

    User findById(String id);

    void update(User user);

    void delete(String id);

    void changeRole(String userid, String[] roleIds);

    User findByEmail(String email);

    List<User> findByCondition(Contract contract);

    String findSaaSEmail(int i);

    List<User> findAllNotMime(String loginCompanyId, String id);

    User findByOpenId(String openId);

    void AddOpenId(String openId, String email);
}
