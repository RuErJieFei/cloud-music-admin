package com.soft1851.music.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.music.admin.dto.LoginDto;
import com.soft1851.music.admin.entity.SysAdmin;
import com.soft1851.music.admin.entity.SysMenu;

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
public interface SysAdminService extends IService<SysAdmin> {

    /**
     * 登录
     *
     * @param loginDto
     * @return boolean
     */
    boolean login(LoginDto loginDto);

    /**
     * 根据name查询Admin
     *
     * @param name
     * @return
     */
    SysAdmin getAdmin(String name);

    /**
     * 根据用户Id获取所有菜单
     *
     * @param adminId
     * @return
     */
    List<Map<String, List<SysMenu>>> getMenusListByAdminId(String adminId);


}
