package com.lxsoft.sys.user.mapper;

import com.lxsoft.sys.resource.entity.Resource;
import com.lxsoft.sys.role.entity.Role;
import com.lxsoft.sys.user.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 * Author: lxs
 * Date: 2018/6/6
 * Time: 16:57
 * Version: v1.0.0
 */
public interface UserMapper {
    Integer add(User user);

    Integer update(User user);

    Integer delete(Integer id);

    Integer batchDelete(@Param("ids") List<Integer> ids);

    User load(Integer id);

    List<User> listUser();

    User loadByUserName(String username);

    /**
     * 根据角色 id 查询所有是该角色的用户列表
     * @param rid
     * @return
     */
    List<User> listByRole(Integer rid);

    List<Resource> listAllResources(Integer uid);

    List<String> listRoleSnByUser(Integer uid);

    List<Role> listUserRole(Integer uid);
}
