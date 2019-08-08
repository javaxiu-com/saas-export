package com.gyhqq.web.shiro;

import com.gyhqq.common.utils.Encrypt;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //转换token 获取登录用户名和密码 获取数据库密码 对登录密码进行加密 比较
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        String email = upToken.getUsername();

        String loginPassword = new String(upToken.getPassword());

        String dbpassword = (String) info.getCredentials();

        if (((UsernamePasswordToken) token).getHost() == null)
            loginPassword = Encrypt.md5(loginPassword, email);

        return loginPassword.equals(dbpassword);
    }
}
