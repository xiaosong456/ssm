package com.lxsoft.common.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * ShiroKit
 *
 * @author lxs
 * @version v1.0.0
 */
public class ShiroKit {

    public static String md5(String originPassword,String salt){
        return new Md5Hash(originPassword,salt).toString();
    }

    public static void main(String[] args) {
        System.out.println(md5("123456","admin"));
    }
}
