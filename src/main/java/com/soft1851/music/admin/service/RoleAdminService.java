package com.soft1851.music.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.music.admin.entity.RoleAdmin;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yy
 * @since 2020-04-21
 */
public interface RoleAdminService extends IService<RoleAdmin> {
    /**
     *  通过adminId获取角色列表
     * @param adminId
     * @return
     */
    List<RoleAdmin> getRoleListByAdminId(String adminId);

}
