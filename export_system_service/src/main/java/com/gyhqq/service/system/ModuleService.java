package com.gyhqq.service.system;

import com.gyhqq.domain.system.Module;
import com.gyhqq.domain.system.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ModuleService {

    PageInfo findPageByHelper(Integer page, Integer size);

    void save(Module module);

    Module findById(String id);

    void update(Module module);

    void delete(String id);

    List<Module> findAll();

    List<Module> findByRoleId(String roleId);

    List<Module> findUserModule(User user);

    List<Module> findByUserId(String id);
}
