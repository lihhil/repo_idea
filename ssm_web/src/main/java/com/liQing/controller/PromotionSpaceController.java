package com.liQing.controller;

import com.liQing.domain.PromotionSpace;
import com.liQing.domain.ResponseResult;
import com.liQing.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/promotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> allPromotionSpace = promotionSpaceService.findAllPromotionSpace();
        return new ResponseResult(true,200,"查询成功",allPromotionSpace);
    }

    /*添加广告位*/
    @RequestMapping("/saveOrUpdatePromotionSpace ")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if(promotionSpace.getId() == null){
            promotionSpaceService.savePromotionSpace(promotionSpace);

            return new ResponseResult(true,200,"新增广告位成功",null);
        }else{
            promotionSpaceService.updatePromotionSpace(promotionSpace);

            return new ResponseResult(true,200,"更新广告位成功",null);
        }

    }

    //根据id查询广告位
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id){
        PromotionSpace result = promotionSpaceService.findPromotionSpaceById(id);
        return new ResponseResult(true,200,"查询成功",result);

    }
}
