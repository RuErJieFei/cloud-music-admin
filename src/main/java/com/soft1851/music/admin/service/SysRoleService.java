package com.soft1851.music.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.music.admin.domain.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yy
 * @since 2020-04-21
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 根据角色id获取角色信息（基础信息和该角色所有菜单）
     *
     * @param roleId
     * @return
     */
    Map<String, Object> selectRoleById(int roleId);

    /**
     * 检查roleId是否在roles中存在
     *
     * @param roles
     * @param roleId
     * @return boolean
     */
    boolean checkRole(List<SysRole> roles, int roleId);

    /**
     * 根据id查角色
     *
     * @param id
     * @return
     */
    SysRole getRoleById(String id);

    /**
     * 根据adminId获取不同角色
     *
     * @param adminId
     * @return
     */
    List<SysRole> getRoleListById(String adminId);

}
