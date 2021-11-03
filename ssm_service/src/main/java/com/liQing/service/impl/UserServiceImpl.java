package com.liQing.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.utils.Md5;
import com.liQing.dao.UserMapper;
import com.liQing.domain.*;
import com.liQing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);

        PageInfo<User> userPageInfo = new PageInfo<>(allUserByPage);
        return userPageInfo;
    }

    //用户登录
    @Override
    public User login(User user) throws Exception {
        User login = userMapper.login(user);
        if(login != null && Md5.verify(user.getPassword(),"lagou",login.getPassword())){
            return login;
        }
        return null;
    }

    //分配角色回显
    @Override
    public List<Role> findUserRelationById(Integer id) {
        List<Role> list = userMapper.findUserRelationById(id);

        return list;
    }

    @Override
    public void userContextRole(UserVo userVO) {
        //根据用户id清空中间表关联关系
        userMapper.deleteUserContextRole(userVO.getUserId());
        //建立关联关系
        for (Integer roleId : userVO.getRoleIdList()) {
            //封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVO.getUserId());
            user_role_relation.setRoleId(roleId);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);

            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }

    }

    //获取用户权限信息
    @Override
    public ResponseResult getUserPermissions(Integer userId) {
         //获取当前用户所拥有的角色
        List<Role> roleList = userMapper.findUserRelationById(userId);

        //获取角色id,保存到list集合中
        ArrayList<Integer> roleIds = new ArrayList<>();

        for (Role role : roleList) {
            roleIds.add(role.getId());
        }

        //根据角色id查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        //查询封装的父菜单关联的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());

            menu.setSubMenuList(subMenu);
        }
        //获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        //封装数据
        HashMap<Object, Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"获取成功",map);
    }
}
