package com.gyhqq.web.shiro;

import com.gyhqq.domain.system.Module;
import com.gyhqq.domain.system.User;
import com.gyhqq.service.system.ModuleService;
import com.gyhqq.service.system.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //查找登录用户的权限,将权限名称存入set集合中,再将set存入返回值中
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<Module> userModule = moduleService.findUserModule(user);
        Set set = new HashSet<>();
        for (Module module : userModule) {
            set.add(module.getName());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(set);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String email = upToken.getUsername();
        String pasword = new String(upToken.getPassword());
        User user = userService.findByEmail(email);
        if (user != null) {
            return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        } else {

            return null;
        }

    }
}
