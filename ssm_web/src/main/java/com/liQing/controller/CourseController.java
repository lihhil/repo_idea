package com.liQing.controller;


import com.liQing.domain.Course;
import com.liQing.domain.CourseVo;
import com.liQing.domain.ResponseResult;
import com.liQing.service.CourseService;
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
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo){
        List<Course> list = courseService.findCourseByCondition(courseVo);

        return new ResponseResult(true, 200, "响应成功", list);
    }

    //课程图片上传
    @RequestMapping("/courseUpload")
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


    /*新增课程信息和讲师信息*/
    /*新增与修改方法写在同一个方法中*/
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo){

        String dif;
        if(courseVo.getId() == null){
            courseService.saveCourseOrTeacher(courseVo);
            dif = "新增成功";
        }else{
            courseService.saveCourseOrTeacher(courseVo);
            dif = "修改成功";
        }


        return new ResponseResult(true, 200, dif, null);

    }

    //根据id查询具体的课程信息及关联的讲师信息
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVo courseVo = courseService.findCourseById(id);

        return new ResponseResult(true, 200, "查询成功", courseVo);
    }

    //课程状态管理
    @RequestMapping("/updateCourseStatus ")
    public ResponseResult updateCourseStatus(Integer id,Integer status){
        //调用service,传递参数,完成课程状态的变更
        courseService.updateCourseStatus(id,status);

        HashMap<String, Integer> map = new HashMap<>();

        map.put("status",status);

        return new ResponseResult(true, 200, "变更成功", map);
    }
}
