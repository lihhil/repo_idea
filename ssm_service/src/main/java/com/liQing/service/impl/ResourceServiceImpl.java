package com.liQing.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liQing.dao.ResourceMapper;
import com.liQing.domain.Resource;
import com.liQing.domain.ResourceVo;
import com.liQing.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;
    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo) {
        //分页
        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());

        List<Resource> allResourceByPage = resourceMapper.findAllResourceByPage(resourceVo);

        PageInfo<Resource> pageInfo = new PageInfo<>(allResourceByPage);

        return pageInfo;
    }
}
