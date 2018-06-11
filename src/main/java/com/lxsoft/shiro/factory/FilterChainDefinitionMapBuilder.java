package com.lxsoft.shiro.factory;

import java.util.LinkedHashMap;

/**
 * FilterChainDefinitionMapBuilder
 *
 * @author lxs
 * @version v1.0.0
 */
public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String,String> buildFilterChainDefinitionMap(){
        LinkedHashMap<String,String> map=new LinkedHashMap<>();

        //从数据库中拿数据

        map.put("/login.jsp","anon");
        map.put("/shiro/login","anon");
        map.put("/shiro/logout","logout");
        map.put("/user.jsp","authc,roles[user]");//经过认证的且具有user角色的
        map.put("/admin.jsp","authc,roles[admin]");
        map.put("/list.jsp","user");//可以通过记住我登陆进行访问




        map.put("/**","authc");

        return map;
    }

}
