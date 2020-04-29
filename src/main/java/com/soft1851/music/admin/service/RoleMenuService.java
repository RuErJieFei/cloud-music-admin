package com.soft1851.music.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.music.admin.domain.entity.RoleMenu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yy
 * @since 2020-04-21
 */
public interface RoleMenuService extends IService<RoleMenu> {
    /**
     * 根据roleId获取不同的权限
     *
     * @param roleId
     * @return
     */
    List<RoleMenu> getRoleMenuByRoleId(String roleId);

}
