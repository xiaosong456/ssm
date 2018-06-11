package com.lxsoft.shiro.realms;

import com.lxsoft.sys.resource.entity.Resource;
import com.lxsoft.sys.user.entity.User;
import com.lxsoft.sys.user.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;

import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * MyRealm
 *
 * @author lxs
 * @version v1.0.0
 */
public class MyRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private UserService userService;

    //登录授权的方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
     // 1、 从 PrincipalCollection 中获取登录用户的信息
        User user=(User) principals.getPrimaryPrincipal();

     // 2、 利用登录的用户的信息来分配当前用户的角色和权限（可能需要查数据库）
        // 获取用户所有的权限
        List<Resource> resourceList=userService.listAllResource(user.getId());
        // 获取用户的所有角色
        List<String> roleList=userService.listRoleSnByUser(user.getId());

        List<String> resStrList=new ArrayList<>();
        for (Resource resource:resourceList){
            resStrList.add(resource.getUrl());
        }

     // 3、创建SimpleAuthorizationInfo，并设置其reles属性。
        SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();
        info.setRoles(new HashSet<>(roleList));
        info.setStringPermissions(new HashSet<>(resStrList));

     // 4、 以上完成了动态的对用户授权 返回SimpleAuthorizationInfo 对象
        logger.debug("roles => " + roleList);
        logger.debug("permissions  => " + resStrList);
        return info;
    }

    //登录认证的方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("MyRealm---doGetAuthenticationInfo");

        //1、把AuthenticationToken 转换成UsernamePasswordToken
        UsernamePasswordToken upToken=(UsernamePasswordToken)token;

        //2、从UsernamePasswordToken中来获取username和password,密码的校验交给shiro来完成
        String username=upToken.getUsername();
        String password=String.valueOf(upToken.getPassword());

        //3、调用数据库方法，从数据库中查询对应的用户信息
        User user=userService.login(username,password);

        //4、根据用户的情况，来构建AuthenticationInfo对象并返回 通常使用的实现类为：SimpleAuthorizationInfo。
        if(user!=null){
            //1、principal：认证的实体信息，可以是username，也可以是数据表对应的用户的实体对象
            Object principal=user;
            //2、credentials:密码
            //Object credentials=fixedPssword(username,password);
            Object credentials=user.getPassword();
            //3、realmName:当前realm对象的name，调用父类的getName()方法即可
            String realmName=user.getUsername();
            //4、盐值
            ByteSource credentialsSalt=ByteSource.Util.bytes(username);
            //5、最后加密之后的密文，此处只做展示
            String ciphertext=fixedPssword(username,password);

            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);

            return info;
        }

        return null;
    }


    /**
     * 由用户名和密码获取最后加密过的密码密文
     * @author: lixiaosong
     * @param username
     * @param pasword
     * @date: 2018/6/6 17:57
     * @version: v1.0.0
     */
    public String fixedPssword(String username,String pasword){
        //加密算法
        String hashAlogorithmName="MD5";
        //用户输入的密码字符串
        Object credentials=pasword;
        //实用盐值加密
        Object salt=ByteSource.Util.bytes(username);
        //加密次数
        int HashIterations=1024;
        //经过层层设防 最终结果
        Object result=new SimpleHash(hashAlogorithmName,credentials,salt,HashIterations);

        return result.toString();
    }


}
