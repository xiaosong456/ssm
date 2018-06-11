package com.lxsoft.sys.resource.service;

import com.lxsoft.sys.resource.entity.Resource;
import com.lxsoft.sys.resource.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ResourceService
 *
 * @author lxs
 * @version v1.0.0
 */
@Service
public class ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    
    public Integer add(Resource res) {
        return resourceMapper.add(res);
    }

    
    public Integer update(Resource res) {
        return resourceMapper.update(res);
    }

    public Integer delete(int id) {
        return resourceMapper.delete(id);
    }

    public Resource load(int id) {
        return resourceMapper.load(id);
    }

    public List<Resource> listResource() {
        return resourceMapper.listResource();
    }
}
