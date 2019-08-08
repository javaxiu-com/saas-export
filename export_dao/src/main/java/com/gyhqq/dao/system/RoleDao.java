package com.gyhqq.dao.system;

import com.gyhqq.domain.system.Module;
import com.gyhqq.domain.system.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RoleDao {

    List<Role> findAll(String loginCompanyId);

    void save(Role role);

    Role findById(String id);

    void update(Role role);

    void delete(String id);

    void saveRoleModule(@Param("roleid") String roleid, @Param("moduleId") String moduleId);

    void deleteRoleModule(String roleid);

    List<Role> findByUserId(String id);
}
