package com.gyhqq.service.system.impl;

import com.gyhqq.common.utils.Encrypt;
import com.gyhqq.dao.system.UserDao;
import com.gyhqq.domain.cargo.Contract;
import com.gyhqq.domain.system.User;
import com.gyhqq.service.system.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageInfo findPageByHelper(Integer page, Integer size, String loginCompanyId) {
        PageHelper.startPage(page, size);
        List<User> list = userDao.findAll(loginCompanyId);
        return new PageInfo(list);
    }

    @Override
    public void save(User user) {
        user.setId(UUID.randomUUID().toString());
        //密码做加密处理,加盐处理
        String password = Encrypt.md5(user.getPassword(), user.getEmail());
        user.setPassword(password);
        userDao.save(user);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public void update(User user) {
        //加密密码,加盐
        String password = Encrypt.md5(user.getPassword(), user.getEmail());
        user.setPassword(password);
        userDao.update(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public void changeRole(String userid, String[] roleIds) {
        //1.根据用户id删除中间表数据
        userDao.deleteByUserId(userid);
        //2.根据roleIds保存
        if (roleIds != null) {
            for (String roleId : roleIds) {
                userDao.saveByUserRole(userid, roleId);
            }
        }
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> findByCondition(Contract contract) {
        Integer degree = 1;
        String companyId = contract.getCompanyId();
        return userDao.findByCondition(degree, companyId);
    }

    @Override
    public String findSaaSEmail(int i) {
        return userDao.findSaaSEmail(i);
    }

    @Override
    public List<User> findAllNotMime(String loginCompanyId, String id) {
        return userDao.findAllNotMime(loginCompanyId, id);

    }

    public User findByOpenId(String openId) {
        User user = null;
        try {
            user = userDao.findByOpenId(openId);
            return user;
        } catch (Exception e) {
            return user;
        }
    }

    public void AddOpenId(String openId, String email) {
        userDao.addOpenId(openId, email);
    }

}
