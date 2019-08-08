package com.gyhqq.service.system.impl;

import com.gyhqq.dao.system.ModuleDao;
import com.gyhqq.dao.system.UserDao;
import com.gyhqq.domain.system.Module;
import com.gyhqq.domain.system.User;
import com.gyhqq.service.system.ModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleDao moduleDao;

    @Autowired
    private UserDao userDao;

    @Override
    public PageInfo findPageByHelper(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Module> list = moduleDao.findAll();
        return new PageInfo(list);
    }

    @Override
    public void save(Module module) {
        module.setId(UUID.randomUUID().toString());
        System.out.println(module);
        moduleDao.save(module);
    }

    @Override
    public Module findById(String id) {
        return moduleDao.findById(id);
    }

    @Override
    public void update(Module module) {
        moduleDao.update(module);
    }

    @Override
    public void delete(String id) {
        moduleDao.delete(id);
    }

    @Override
    public List<Module> findAll() {
        return moduleDao.findAll();
    }

    @Override
    public List<Module> findByRoleId(String roleId) {

        return moduleDao.findByRoleId(roleId);
    }

    @Override
    public List<Module> findUserModule(User user) {
        //走那三个点,两个管理员的module都是固定的,员工的是变化的
        if (user.getDegree() == 0) {//saas管理员
            return moduleDao.findByBelong(0);//查询belong为0的module
            //也就是说belong为0的模块都是SaaS管理员的
        } else if (user.getDegree() == 1) {//企业管理员
            return moduleDao.findByBelong(1);
            //belong为1的模块都是企业管理员的
        } else {//员工
            return moduleDao.findByUserId(user.getId());
        }
    }

    @Override
    public List<Module> findByUserId(String id) {

        User user = userDao.findById(id);
        Integer degree = user.getDegree();
        if (degree == 0) {
            return moduleDao.findByBelong(0);
        } else if (degree == 1) {
            return moduleDao.findByBelong(1);
        } else {
            return moduleDao.findByUserId(id);
        }
    }

}
