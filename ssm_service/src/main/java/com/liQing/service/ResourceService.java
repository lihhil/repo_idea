package com.liQing.service;

import com.github.pagehelper.PageInfo;
import com.liQing.domain.Resource;
import com.liQing.domain.ResourceVo;

import java.util.List;

public interface ResourceService {
    //资源分页及多条件查询
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
