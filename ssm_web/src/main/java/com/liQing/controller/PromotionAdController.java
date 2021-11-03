package com.liQing.controller;

import com.github.pagehelper.PageInfo;
import com.liQing.domain.PromotionAd;
import com.liQing.domain.PromotionAdVo;
import com.liQing.domain.ResponseResult;
import com.liQing.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/promotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService promotionAdService;

    /*分页查询广告一览*/
    @RequestMapping("/findALlPromotionAdByPage")
    public ResponseResult findALlPromotionAdByPage(@RequestBody PromotionAdVo promotionAdVo){
        PageInfo<PromotionAd> list = promotionAdService.findAllPromotionAdByPage(promotionAdVo);

        return new ResponseResult(true,200,"广告分页查询成功",list);

    }

    @RequestMapping("/promotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //判断接收到的上传文件是否为空
        if(file.isEmpty()){
            throw new RuntimeException();
        }

        //获取项目部署路径
        //D:\Java\apache-tomcat-8.5.69\webapps\ssm_web
        String realPath = request.getServletContext().getRealPath("/");
        //D:\Java\apache-tomcat-8.5.69\webapps\
        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));

        //获取原文件名
        String originalFilename = file.getOriginalFilename();

        //生成新文件名
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //文件上传
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        //如果目录不存在就创建目录
        if(!filePath.getParentFile().exists())
        {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录");
        }

        //图片真正上传
        file.transferTo(filePath);

        HashMap<String, String> map = new HashMap<>();

        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);


        ResponseResult result = new ResponseResult(true,200,"响应成功",map);

        return result;

    }

    /*广告动态上下线*/
    @RequestMapping("updatePromotionStatus")
    public ResponseResult updatePromotionStatus(Integer id,Integer status){
        promotionAdService.updatePromotionAdStatus(id,status);
        return new ResponseResult(true,200,"更改成功 ",null);
    }

}
