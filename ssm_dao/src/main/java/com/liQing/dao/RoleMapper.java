package com.liQing.dao;

import com.liQing.domain.Role;
import com.liQing.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    public List<Role> findAllRole(Role role);

    //根据角色id查询该角色关联的菜单信息id
    public List<Integer> findMenuByRoleId(Integer roleId);

    //根据roleId清空中间表
    public void deleteRoleContextMenu(Integer rid);

    //为角色分配菜单信息
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    //删除角色
    public void deleteRole(Integer id);
}
