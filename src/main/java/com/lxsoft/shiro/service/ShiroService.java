package com.lxsoft.shiro.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * ShiroService
 *
 * @author lxs
 * @version v1.0.0
 */
@Service
public class ShiroService {

    @RequiresRoles({"admin"})
    public void testMethod(){
        System.out.println("testMethod,time:"+new Date());

        Session session= SecurityUtils.getSubject().getSession();
        Object val=session.getAttribute("key");

        System.out.println("Service Sessionval:"+val);
    }

}
