package com.gyhqq.service.system;

import com.gyhqq.domain.system.Module;
import com.gyhqq.domain.system.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {
    PageInfo findPageByHelper(Integer page, Integer size, String loginCompanyId);

    void save(Role role);

    Role findById(String id);

    void update(Role role);

    void delete(String id);

    void updateRoleModule(String roleid, String moduleIds);

    List findAll(String loginCompanyId);

    List<Role> findByUserId(String id);
}
