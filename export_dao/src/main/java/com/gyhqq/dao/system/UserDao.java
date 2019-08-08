package com.gyhqq.dao.system;

import com.gyhqq.domain.system.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao {

    List<User> findAll(String loginCompanyId);

    void save(User user);

    User findById(String id);

    void update(User user);

    void delete(String id);

    void deleteByUserId(String userid);

    void saveByUserRole(@Param("usreid") String userid, @Param("roleId") String roleId);

    User findByEmail(String email);

    List<User> findByCondition(@Param("degree") Integer degree, @Param("companyId") String companyId);

    String findSaaSEmail(int i);

    List<User> findAllNotMime(@Param("loginCompanyId") String loginCompanyId, @Param("id") String id);

    User findByOpenId(String openId);

    void addOpenId(@Param("openId") String openId, @Param("email") String email);
}
