package com.liQing.service;

import com.liQing.domain.Course;
import com.liQing.domain.CourseVo;

import java.util.List;

public interface CourseService {

    //多条件课程列表查询
    public List<Course> findCourseByCondition(CourseVo courseVo);

    //添加课程及讲师信息
    public void saveCourseOrTeacher(CourseVo courseVo);

    /*根据id查询课程信息*/
    public CourseVo findCourseById(Integer id);

    //更新课程及讲师信息
    public void updateCourseOrTeacher(CourseVo courseVo);

    //课程状态变更
    public void updateCourseStatus(int id,int status);
}
