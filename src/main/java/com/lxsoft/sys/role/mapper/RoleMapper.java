package com.lxsoft.sys.role.mapper;

import com.lxsoft.sys.resource.entity.Resource;
import com.lxsoft.sys.role.entity.Role;
import com.lxsoft.sys.role.entity.RoleResource;
import com.lxsoft.sys.user.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 * Author: lxs
 * Date: 2018/6/6
 * Time: 17:05
 * Version: v1.0.0
 */
public interface RoleMapper {

    Integer add(Role role);

    Integer delete(Integer id);

    Integer batchDelete(@Param("ids") List<Integer> ids);

    Role load(Integer id);

    Integer update(Role role);

    List<Role> listRole();

    UserRole loadUserRole(@Param("userId") int userId, @Param("roleId") int roleId);

    /**
     * 为单个用户设置单个角色
     * @param userId
     * @param roleId
     * @return
     */
    Integer addUserRole(@Param("userId") int userId,@Param("roleId") int roleId);

    /**
     * 为单个用户设置多个角色
     * @param userId
     * @param roleIds
     * @return
     */
    Integer addUserRoles(@Param("userId") int userId,@Param("roleIds")List<Integer> roleIds);

    Integer deleteUserRole(@Param("userId") int userId,@Param("roleId") int roleId);

    /**
     * 删除某个用户的所有角色
     * @param uid
     */
    Integer deleteUserRoles(int uid);

    Integer batchDeleteRoleResource(@Param("roleIds") List<Integer> roleIds);

    /**
     * 根据角色id获取可以访问的所有资源
     * @param roleId
     * @return
     */
    List<Resource> listRoleResource(int roleId);

    Integer addRoleResource(@Param("roleId") int roleId,@Param("resourceId") int resourceId);

    Integer deleteRoleResource(@Param("roleId")int roleId,@Param("resourceId")int resorceId);

    RoleResource loadResourceRole(@Param("roleId")int roleId, @Param("resourceId") int resorceId);

    Integer deleteRoleAndUser(@Param("ids") List<Integer> ids);
}
