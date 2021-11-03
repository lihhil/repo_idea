package com.liQing.controller;

import com.github.pagehelper.PageInfo;
import com.liQing.domain.ResponseResult;
import com.liQing.domain.Role;
import com.liQing.domain.User;
import com.liQing.domain.UserVo;
import com.liQing.service.UserService;
import jdk.internal.joptsimple.internal.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo allUserByPage = userService.findAllUserByPage(userVo);

        return new ResponseResult(true,200,"分页查询成功",allUserByPage);
    }

    //用户登入
    @RequestMapping("/login")//get请求无需requestBody
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User login = userService.login(user);

        if(login != null){
            //保存用户id及access_toke到session中
            HttpSession session = request.getSession();
            String s = UUID.randomUUID().toString();
            session.setAttribute("access_token",s);
            session.setAttribute("user_id",login.getId());

            HashMap<String, String> map = new HashMap<>();

            map.put("access_token",s);
            map.put("user_id", login.getId().toString());

            return new ResponseResult(true,200,"用户登入成功",map);
        }

        return new ResponseResult(true,400,"用户名密码错误",null);


    }

    //分配角色
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){
        List<Role> list = userService.findUserRelationById(id);

        return new ResponseResult(true,200,"分配角色回显成功",list );
    }

    //分配角色
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);

        return new ResponseResult(true,200,"分配角色成功",null);
    }

    //获取用户权限,进行菜单动态展示
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //获取请求头token
        String authorization = request.getHeader("Authorization");

        //获取session中的token
        String access_token = (String) request.getSession().getAttribute("access_token");

        //判断是否一致
        if(access_token.equals(authorization)){
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            return userService.getUserPermissions(user_id);
        }
        return new ResponseResult(false,400,"获取菜单信息失败",null);
    }

}
