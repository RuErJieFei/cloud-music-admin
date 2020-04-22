package com.soft1851.music.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.music.admin.entity.SysMenu;

import javax.management.relation.RoleList;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yy
 * @since 2020-04-21
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 根据id获取菜单
     *
     * @param id
     * @return
     */
    SysMenu getMenuById(String id);

    /**
     * 根据roleId获取不同菜单
     *
     * @param roleId
     * @return
     */
    List<SysMenu> getMenuListByRoleId(String roleId);

}
