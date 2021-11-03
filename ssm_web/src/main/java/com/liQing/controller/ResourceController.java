package com.liQing.controller;

import com.github.pagehelper.PageInfo;
import com.liQing.domain.Resource;
import com.liQing.domain.ResourceVo;
import com.liQing.domain.ResponseResult;
import com.liQing.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/findAllResource")
    public ResponseResult findAllResourceByPage(@RequestBody ResourceVo resourceVo){
        PageInfo<Resource> list = resourceService.findAllResourceByPage(resourceVo);

        return new ResponseResult(true,200,"查询成功",list);
    }
}
