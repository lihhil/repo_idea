package com.liQing.controller;


import com.liQing.domain.*;
import com.liQing.service.MenuService;
import com.liQing.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /*查询所有角色*/
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> list = roleService.findAllRole(role);
        return new ResponseResult(true,200,"查询成功",list);
    }

    /*查询所有的父子菜单信息(分配菜单的第一个接口)
    * */
    @Autowired
    private MenuService menuService;
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){
        //查询父子级
        List<Menu> list = menuService.findSubMenuListByPid(-1);

        HashMap<String, Object> map = new HashMap<>();

        map.put("parentMenuList",list);

        return new ResponseResult(true,200,"查询成功",map);
    }

    //根据角色id查询关联的菜单信息id
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> list = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true,200,"查询的角色关联信息成功",list);

    }

    //为角色分配菜单
    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);

        return new ResponseResult(true,200,"响应成功",null);
    }

    //删除角色
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);

        return new ResponseResult(true,200,"删除成功",null);
    }
}
