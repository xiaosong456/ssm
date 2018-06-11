package com.lxsoft.sys.resource.mapper;

import com.lxsoft.sys.resource.entity.Resource;

import java.util.List;

/**
 * Description:
 * Author: lxs
 * Date: 2018/6/6
 * Time: 17:06
 * Version: v1.0.0
 */
public interface ResourceMapper {
    Integer add(Resource res);

    Integer update(Resource res);

    Integer delete(int id);

    Resource load(int id);

    List<Resource> listResource();
}
