package com.hjsjy.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author hjsjy
 * @create 2018/10/15
 * @since 1.0.0
 */
public class ShiroRealm extends AuthenticatingRealm {



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1. AuthenticationToken转化为 UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //2. 从usernamePasswordToken 中获取用户名
        String username = usernamePasswordToken.getUsername();

        //3. 调用数据库的方法 从数据库中查询username对应的用户记录
        System.out.println("从数据库中获取username"+username+"所对应的信息");
        //4. 若用户不存在，则可以抛出UnknownaccountException异常
        if("unknown".equals(username)){
            throw new UnknownAccountException("用户不存在");
        }
        //5. 根据用户信息的情况，决定是否需要抛出其他的AuthenticationException异常
        if("monster".equals(username)){
            throw new LockedAccountException("用户被锁定");
        }
        //6.根据用户的情况，来构建authenticationInfo 对象并返回

        //1) principals 认证的实体信息，可以是username 也可以是数据表对应的用户的实体类对象
            Object principals=username;

        //2） 从credentials：密码，
        Object credentials = "fc1709d0a95a6be30bc5926fdb7f22f4";
        if("admin".equals(username)){
            credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
        }else if("user".equals(username)){
            credentials = "098d2c478e9c11555ce2823231e02ec1";
        }
        //3） realmName 当前realm对象的name，调用父类的getName()方法即可
        String realmName=getName();
        //4) 盐值 salt
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        simpleAuthenticationInfo = new SimpleAuthenticationInfo(credentials, credentials, credentialsSalt, realmName);
        return simpleAuthenticationInfo;
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("user");
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);

    }
}
