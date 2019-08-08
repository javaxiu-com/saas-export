package com.gyhqq.service.system.impl;

import com.gyhqq.dao.system.RoleDao;
import com.gyhqq.domain.system.Role;
import com.gyhqq.service.system.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public PageInfo findPageByHelper(Integer page, Integer size, String loginCompanyId) {
        PageHelper.startPage(page, size);
        List<Role> list = roleDao.findAll(loginCompanyId);
        return new PageInfo(list);
    }

    @Override
    public void save(Role role) {
        role.setId(UUID.randomUUID().toString());
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void delete(String id) {
        roleDao.delete(id);
    }

    @Override
    public void updateRoleModule(String roleid, String moduleIds) {
        String[] split = moduleIds.split(",");
        roleDao.deleteRoleModule(roleid);
        for (String moduleId : split) {
            roleDao.saveRoleModule(roleid, moduleId);
        }

    }

    @Override
    public List findAll(String loginCompanyId) {
        return roleDao.findAll(loginCompanyId);
    }

    @Override
    public List<Role> findByUserId(String id) {
        return roleDao.findByUserId(id);
    }

}
