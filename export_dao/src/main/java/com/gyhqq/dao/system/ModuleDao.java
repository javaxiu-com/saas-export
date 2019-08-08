package com.gyhqq.dao.system;

import com.gyhqq.domain.system.Module;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */

public interface ModuleDao {

    //根据id查询
    Module findById(String moduleId);

    //根据id删除
    int delete(String moduleId);

    //添加用户
    int save(Module module);

    //更新用户
    int update(Module module);

    //查询全部
    List<Module> findAll();

    List<Module> findByRoleId(String roleId);

    List<Module> findByBelong(int belong);

    List<Module> findByUserId(String userId);
}