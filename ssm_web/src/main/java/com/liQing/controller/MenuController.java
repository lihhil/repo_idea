package com.liQing.controller;

import com.liQing.domain.Menu;
import com.liQing.domain.ResponseResult;
import com.liQing.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    //查询所有
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenu();

        return new ResponseResult(true,200,"查询所有菜单信息成功",allMenu);
    }

    //回显菜单信息
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        //判断根据id当前是更新该死添加操作,判断id是否为-1
        if(id == -1)
        {
            //添加操作
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装数据
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);

            return new ResponseResult(true,200,"添加回显成功",map);
        }else {
            Menu menu = menuService.findMenuById(id);
            //添加操作
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装数据
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",subMenuListByPid);

            return new ResponseResult(true,200,"修改 回显成功",map);
        }
    }
}
