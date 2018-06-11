package com.lxsoft.sys.user.service;

import com.lxsoft.common.util.ShiroKit;
import com.lxsoft.sys.resource.entity.Resource;
import com.lxsoft.sys.role.entity.Role;
import com.lxsoft.sys.role.mapper.RoleMapper;
import com.lxsoft.sys.user.entity.User;
import com.lxsoft.sys.user.mapper.UserMapper;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserService
 *
 * @author lxs
 * @version v1.0.0
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 返回新插入用户数据的主键
     * @param user
     * @return
     */
    
    public User add(User user) {
        // 使用用户名作为盐值，MD5 算法加密
        user.setPassword(ShiroKit.md5(user.getPassword(),user.getUsername()));
        userMapper.add(user);
        return user;
    }

    /**
     * 为单个用户设置多个角色
     * @param user
     * @param rids
     */
    @Transactional
    
    public User add(User user, List<Integer> rids) {
        Integer userId = this.add(user).getId();
        roleMapper.addUserRoles(userId,rids);
        return user;
    }

    /**
     * 根据 user_id 删除用户数据
     * @param id
     */
    
    public void delete(int id) {
        if(id==1){
            throw new RuntimeException("不能删除管理员用户");
        }
        userMapper.delete(id);
    }

    
    @Transactional
    public void deleteUserAndRole(List<Integer> ids) {
        if(ids.contains(1)){
            throw new RuntimeException("不能删除管理员用户");
        }
        // 删除用户列表
        userMapper.batchDelete(ids);
        // 依次删除这些用户所绑定的角色
        for(Integer userId:ids){
            roleMapper.deleteUserRoles(userId);
        }
    }

    /**
     *
     * 更新用户数据
     * 1、更新用户基本信息
     * 2、更新用户所属角色
     *    （1）先删除所有的角色
     *    （2）再添加绑定的角色
     * @param user
     * @param rids
     */
    @Transactional
    
    public User update(User user, List<Integer> rids) {
        Integer userId = user.getId();
        roleMapper.deleteUserRoles(userId);
        roleMapper.addUserRoles(userId,rids);
        this.update(user);
        return user;
    }

    /**
     * 更新单个用户信息
     * @param user
     * @return
     */
    
    public User update(User user) {
        String password = user.getPassword();
        if(password!=null){
            user.setPassword(ShiroKit.md5(user.getPassword(),user.getUsername()));
        }
        userMapper.update(user);
        return user;
    }

    /**
     * 根据主键 id 加载用户对象
     * @param id
     * @return
     */
    
    public User load(int id) {
        return userMapper.load(id);
    }

    /**
     * 根据用户名加载用户对象（用于登录使用）
     * @param username
     * @return
     */
    
    public User loadByUsername(String username) {
        return userMapper.loadByUserName(username);
    }

    /**
     * 登录逻辑
     * 1、先根据用户名查询用户对象
     * 2、如果有用户对象，则继续匹配密码
     * 如果没有用户对象，则抛出异常
     * @param username
     * @param password
     * @return
     */
    
    public User login(String username, String password) {
        User user = userMapper.loadByUserName(username);
        // 密码匹配的工作交给 Shiro 去完成
        if(user == null ){
            // 因为缓存切面的原因,在这里就抛出用户名不存在的异常
            logger.error("用户名不存在(生产环境中应该写:用户名和密码的组合不正确)");
            throw new UnknownAccountException("用户名不存在(生产环境中应该写:用户名和密码的组合不正确)");
        }else if(user.getStatus() == 0){
            logger.error("用户已经被禁用，请联系管理员启用该账号");
            throw new LockedAccountException("用户已经被禁用，请联系管理员启用该账号");
        }
        return user;
    }

    /**
     * 查询所有的用户对象列表
     * @return
     */
    
    public List<User> list() {
        return userMapper.listUser();
    }

    /**
     * 根据角色 id 查询是这个角色的所有用户
     * @param id
     * @return
     */
    
    public List<User> listByRole(int id) {
        return userMapper.listByRole(id);
    }

    /**
     * 查询指定用户 id 所拥有的权限
     * @param uid
     * @return
     */
    
    public List<Resource> listAllResource(int uid) {
        return userMapper.listAllResources(uid);
    }

    /**
     * 查询指定用户所指定的角色字符串列表
     * @param uid
     * @return
     */
    
    public List<String> listRoleSnByUser(int uid) {
        return userMapper.listRoleSnByUser(uid);
    }

    /**
     * 查询指定用户所绑定的角色列表
     * @param uid
     * @return
     */
    
    public List<Role> listUserRole(int uid) {
        return userMapper.listUserRole(uid);
    }

}
