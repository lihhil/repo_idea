package com.liQing.service;

import com.github.pagehelper.PageInfo;
import com.liQing.domain.PromotionAd;
import com.liQing.domain.PromotionAdVo;

import java.util.List;

public interface PromotionAdService {
    /*分页查询广告信息*/
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo);

    /*广告动态上下线*/
    public void updatePromotionAdStatus(int id,int status);
}
