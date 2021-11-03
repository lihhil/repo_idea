package com.liQing.dao;

import com.liQing.domain.Resource;
import com.liQing.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {
    //资源分页及多条件查询
    public List<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
