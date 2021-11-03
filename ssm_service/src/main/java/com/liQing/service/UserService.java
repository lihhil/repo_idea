package com.liQing.service;

import com.github.pagehelper.PageInfo;
import com.liQing.domain.*;

import java.util.List;

public interface UserService {
    /*多条件查询分页*/
    public PageInfo findAllUserByPage(UserVo userVo);

    //用户登入
    public User login(User user) throws Exception;

    //分配角色
    public List<Role> findUserRelationById(Integer id);

    //用户关联角色
    public void userContextRole(UserVo userVO);

    //获取用户权限,进行菜单动态展示
    public ResponseResult getUserPermissions(Integer userId);

}
