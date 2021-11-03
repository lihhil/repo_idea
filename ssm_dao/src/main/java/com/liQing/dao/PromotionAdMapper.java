package com.liQing.dao;

import com.liQing.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {
    /*分页查询*/
    public List<PromotionAd> findAllPromotionAdByPage();

    /*广告动态上下线*/
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
