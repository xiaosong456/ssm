package com.lxsoft.sys.role.service;

import com.lxsoft.sys.resource.entity.Resource;
import com.lxsoft.sys.role.entity.Role;
import com.lxsoft.sys.role.entity.RoleResource;
import com.lxsoft.sys.role.mapper.RoleMapper;
import com.lxsoft.sys.user.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * RoleService
 *
 * @author lxs
 * @version v1.0.0
 */
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    
    public Integer add(Role role) {
        return roleMapper.add(role);
    }

    
    public Integer delete(int id) {
        return roleMapper.delete(id);
    }

    @Transactional
    public void deleteRoleAndResource(List<Integer> ids) {
        roleMapper.batchDelete(ids);
        roleMapper.batchDeleteRoleResource(ids);
    }

    
    public Role load(int id) {
        return roleMapper.load(id);
    }

    
    public List<Role> list() {
        return roleMapper.listRole();
    }

    
    public Integer update(Role role) {
        return roleMapper.update(role);
    }

    
    public List<Role> listRole() {
        return roleMapper.listRole();
    }

    
    public UserRole loadUserRole(int uid, int roleId) {
        return roleMapper.loadUserRole(uid, roleId);
    }

    
    public void addUserRole(int uid, int roleId) {
        roleMapper.addUserRole(uid, roleId);
    }

    
    public void deleteUserRole(int uid, int roleId) {
        roleMapper.deleteUserRole(uid, roleId);
    }

    
    public void deleteUserRoles(int uid) {
        roleMapper.deleteUserRoles(uid);
    }

    
    public List<Resource> listRoleResource(int roleId) {
        return roleMapper.listRoleResource(roleId);
    }

    
    public void addRoleResource(int roleId, int resId) {
        roleMapper.addRoleResource(roleId, resId);
    }

    
    public void deleteRoleResource(int roleId, int resId) {
        roleMapper.deleteRoleResource(roleId, resId);
    }

    
    public RoleResource loadResourceRole(int roleId, int resId) {
        return roleMapper.loadResourceRole(roleId, resId);
    }

    
    public Integer deleteRoleAndUser(List<Integer> ids) {
        return roleMapper.deleteRoleAndUser(ids);
    }
}
