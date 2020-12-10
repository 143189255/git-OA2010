package com.ssm.shiro;

import com.ssm.pojo.Emp;
import com.ssm.pojo.Perssion;
import com.ssm.service.PerssionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// Realm 数据源  负责访问数据库中的安全数据
// 为Shiro数据源 根据数据库提供数据源

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    PerssionService perssionService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("负责用户的权限");
         Session session = SecurityUtils.getSubject().getSession();
         Emp emp = (Emp) session.getAttribute("loginEmp");
         if(emp!=null){
             List<Perssion> perssions = perssionService.getPermissions(emp.getId());
             List<String> rights = new ArrayList<String>();
             for (Perssion perssion : perssions) {
                   rights.add(perssion.getPUrl());
             }
             System.out.println("该用户的权限为:"+rights);
             // 查到权限数据，返回授权信息(要包括 上边的permissions)
             SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
             // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
             simpleAuthorizationInfo.addStringPermissions(rights);
             return simpleAuthorizationInfo;
         }else{
             return null;
         }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("负责用户的登录---用户认证");
        // 获取登录的用户信息
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) authenticationToken;
        //简单的判断一下有没有生成passwordToken！
        if (passwordToken.getUsername() != null && passwordToken.getUsername().length() > 0) {
            return new SimpleAuthenticationInfo(passwordToken.getUsername(), passwordToken.getPassword(), getName());
        } else {
            return null;
        }

    }
    /**
     * 清空缓存--- 如果需要人工刷新缓存，调用给你这个方法！！！
     *
     * 自动清空：
     *    注销！
     *    程序重启！
     */
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
