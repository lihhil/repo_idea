package com.liQing.dao;

import com.liQing.domain.Course;
import com.liQing.domain.CourseVo;
import com.liQing.domain.Teacher;

import java.util.List;

public interface CourseMapper {
    /*
    * 多条件查询
    * */
    public List<Course> findCourseByCondition(CourseVo courseVo);

    /*新增课程信息*/
    public void saveCourse(Course course);

    //新增讲师信息
    public void saveTeacher(Teacher teacher );

    //回显课程信息
    public CourseVo findCourseById(Integer id);

    //更新课程信息
    public void updateCourse(Course course);

    //更新讲师信息
    public void updateTeacher(Teacher teacher);

    /*
    * 课程状态管理
    * */
    public void updateCourseStatus(Course course);
}
