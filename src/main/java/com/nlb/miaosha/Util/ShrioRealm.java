package com.nlb.miaosha.Util;

import com.nlb.miaosha.domain.user;
import com.nlb.miaosha.service.userServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class ShrioRealm extends AuthorizingRealm {
    @Autowired
    userServiceImpl userService;

    @Override//授权的逻辑
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        user user  = (user) subject.getPrincipal();
        authorizationInfo.addStringPermission(user.getPerms());
        return authorizationInfo;
    }



    @Override//认证的逻辑
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        user user = userService.selectUserService(token.getUsername());
        if(user==null) {
            return null;//就是抛出用户名不存在的那个异常
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
