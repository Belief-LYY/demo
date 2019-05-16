package com.example.demo.util;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.BaseService;
import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws UnknownAccountException {
        SqlSession session = BaseService.factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.selectByAccount((String) token.getPrincipal());
        session.close();
        if (user == null) {
            throw new UnknownAccountException();
        } else {
            return new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(), "UserRealm");
        }
    }

}