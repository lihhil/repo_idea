package com.liQing.service.impl;

import com.liQing.dao.RoleMapper;
import com.liQing.domain.Role;
import com.liQing.domain.RoleMenuVo;
import com.liQing.domain.Role_menu_relation;
import com.liQing.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> list = roleMapper.findAllRole(role);
        return list;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        List<Integer> list = roleMapper.findMenuByRoleId(roleId);
        return list;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        //清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());
        //为角色分配菜单
        for (Integer integer : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(integer);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            roleMapper.roleContextMenu(role_menu_relation);
        }


        //
    }

    @Override
    public void deleteRole(Integer roleId) {

        //调用根据roleid清空中间表关系
        roleMapper.deleteRoleContextMenu(roleId);
        roleMapper.deleteRole(roleId);
    }
}
