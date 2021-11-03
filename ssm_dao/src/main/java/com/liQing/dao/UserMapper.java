package com.liQing.dao;

import com.liQing.domain.*;

import java.util.List;

public interface UserMapper {
    /*用户分页&多条件查询*/
    public List<User> findAllUserByPage(UserVo userVo);

    //用户登入,根据用户名查询具体用户信息
    public User login(User user);

    //根据用户id,查询关联的角色信息
    public List<Role> findUserRelationById(Integer id);


    //清空中间表
    public void deleteUserContextRole(Integer userId);

    //分配角色
    public void userContextRole(User_Role_relation user_role_relation);

    //根据角色id,查询角色所拥有的顶级菜单
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    //根据pid查询子菜单信息
    public  List<Menu> findSubMenuByPid(Integer pid);

    //获取用户拥有的资源权限信息
     public List<Resource> findResourceByRoleId(List<Integer> ids);
}
