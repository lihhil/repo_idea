package com.liQing.controller;

import com.liQing.domain.ResourceCategory;
import com.liQing.domain.ResponseResult;
import com.liQing.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resourceCategory")
public class ResourceCategoryController {
    @Autowired
    private ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> list = resourceCategoryService.findAllResourceCategory();

        return new ResponseResult(true,200,"查询分类成功",list);
    }
}
