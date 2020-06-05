package com.blog.pro.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.blog.pro.utils.JwtUtils;
import com.blog.pro.entity.User;
import com.blog.pro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    /**
     * @Description: 为了让realm支持jwt的凭证校验
     * @param: [token]
     * @return: boolean
     * @Author: yaoyp
     * @date: 2020/6/3 0003
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * @Description: 权限校验
     * @param: [principals]
     * @return: org.apache.shiro.authz.AuthorizationInfo
     * @Author: yaoyp
     * @date: 2020/6/3 0003
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {


        return null;
    }

    /**
     * @Description: 登录认证校验
     * @param: [token]
     * @return: org.apache.shiro.authc.AuthenticationInfo
     * @Author: yaoyp
     * @date: 2020/6/3 0003
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        JwtToken jwt = (JwtToken) token;
        log.info("jwt----------------->{}", jwt);
        String userId = jwtUtils.getClaimByToken((String) jwt.getPrincipal()).getSubject();
        User user = userService.getById(Long.parseLong(userId));
        if(user == null) {
            throw new UnknownAccountException("账户不存在！");
        }
        if(user.getStatus() == -1) {
            throw new LockedAccountException("账户已被锁定！");
        }
        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user, profile);
        log.info("profile----------------->{}", profile.toString());
        return new SimpleAuthenticationInfo(profile, jwt.getCredentials(), getName());
    }
}
