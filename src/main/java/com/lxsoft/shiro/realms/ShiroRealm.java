package com.lxsoft.shiro.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ShiroRealm
 *
 * @author lxs
 * @version v1.0.0
 */
public class ShiroRealm extends AuthenticatingRealm{

    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("ShiroRealm---doGetAuthenticationInfo");

        //1、把AuthenticationToken 转换成UsernamePasswordToken
        UsernamePasswordToken upToken=(UsernamePasswordToken)token;

        //2、从UsernamePasswordToken中来获取username
        String username=upToken.getUsername();

        //3、调用数据库方法，从数据库中查询username对应的记录
        System.out.println("从数据库中获取username："+username+",所对应的信息");

        //4、若用户不存在，则可以抛出UnknownAccountException异常
        if("unknow".equals(username)){
            throw new UnknownAccountException("用户不存在！");
        }

        //5、根据用户信息的情况，决定是否抛出以他的AuthenticationException异常。
        if("monster".equals(username)){
            throw new LockedAccountException("用户被锁定！");
        }

        //6、根据用户的情况，来构建AuthenticationInfo对象并返回 通常使用的实现类为：SimpleAuthorizationInfo。
        //以下信息是从数据库中获取的
        //1、principal：认证的实体信息，可以是username，也可以是数据表对应的用户的实体对象
        Object principal=username;
        //2、credentials:密码
//        Object credentials="fc1709d0a95a6be30bc5926fdb7f22f4";
        Object credentials=null;
        if("admin".equals(username)){
            credentials="ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
        }else if("user".equals(username)){
            credentials="073d4c3ae812935f23cb3f2a71943f49e082a718";
        }
        //3、realmName:当前realm对象的name，条用弗雷德getName()方法即可
        String realmName=getName();
        //4、盐值
        ByteSource credentialsSalt=ByteSource.Util.bytes(username);

//        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(principal,credentials,realmName);
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);

        logger.error("doGetAuthenticationInfo:"+token);
        return info;
    }


    public static void main(String[] args){
        String hashAlogorithmName="SHA1";
        Object credentials="123456";
        Object salt=ByteSource.Util.bytes("admin");
        int HashIterations=1024;
        Object result=new SimpleHash(hashAlogorithmName,credentials,salt,HashIterations);
        System.out.println(result);
    }


}
