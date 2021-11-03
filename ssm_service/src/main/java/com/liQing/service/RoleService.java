package com.liQing.service;

import com.liQing.domain.Role;
import com.liQing.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {
    /*查询所有角色*/
    public List<Role> findAllRole(Role role);

    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
    为角色分配菜单
    * */
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    //删除角色
    public void deleteRole(Integer roleId);
}
