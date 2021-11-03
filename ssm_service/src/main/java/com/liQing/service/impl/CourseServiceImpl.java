package com.liQing.service.impl;

import com.liQing.dao.CourseMapper;
import com.liQing.domain.Course;
import com.liQing.domain.CourseVo;
import com.liQing.domain.Teacher;
import com.liQing.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {
        List<Course> list = courseMapper.findCourseByCondition(courseVo);
        return list;
    }

    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) {
        try {
            //封装课程信息
            Course course = new Course();
            BeanUtils.copyProperties(course, courseVo);
            //补全信息
            Date date = new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);
            //保存课程
            courseMapper.saveCourse(course);
            //获取新插入数据的id
            int id = course.getId();
            //封装讲师信息
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher, courseVo);
            //补全信息
            teacher.setCourseId(id);
            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            //保存讲师信息
            courseMapper.saveTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CourseVo findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) {
        Course course = new Course();
        try {
            BeanUtils.copyProperties(course,courseVo);
            Date date = new Date();
            course.setUpdateTime(date);

            //更新课程信息
            courseMapper.updateCourse(course);

            //封装讲师信息
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher,courseVo);

            //补全讲师信息
            teacher.setCourseId(course.getId());
            teacher.setUpdateTime(date);

            //更新教师信息
            courseMapper.updateTeacher(teacher);

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void updateCourseStatus(int id, int status) {
        //封装数据
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());
        //调用mapper
        courseMapper.updateCourseStatus(course);
    }
}
