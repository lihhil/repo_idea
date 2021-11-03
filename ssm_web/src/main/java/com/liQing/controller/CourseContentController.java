package com.liQing.controller;


import com.liQing.domain.Course;
import com.liQing.domain.CourseSection;
import com.liQing.domain.ResponseResult;
import com.liQing.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/courseContent")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        return new ResponseResult(true,200,"响应成功",list);
    }

    /*回显章节信息*/
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);

        return new ResponseResult(true,200,"查询成功",course);
    }

    /*新增&更新章节信息*/
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        //判断是否携带章节id
        if(courseSection.getId() == null){
            //新增
            courseContentService.saveSection(courseSection);

            return new ResponseResult(true,200,"新增章节成功",null);
        }else{
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true,200,"更新章节成功",null);
        }


    }

    /*修改章节状态*/
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status){
        courseContentService.updateSectionStatus(id,status);

        HashMap<String, Integer> content = new HashMap<>();
        content.put("status",status);



        return new ResponseResult(true,200,"变更成功",content);
    }
}
