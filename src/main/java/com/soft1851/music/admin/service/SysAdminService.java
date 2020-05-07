package com.soft1851.music.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.music.admin.domain.dto.LoginDto;
import com.soft1851.music.admin.domain.dto.UserDto;
import com.soft1851.music.admin.domain.entity.SysAdmin;
import com.soft1851.music.admin.domain.entity.SysMenu;

import java.util.Date;
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
    Map<String, Object> login(LoginDto loginDto);


    /**
     * 根据name查询Admin信息，包含其所有角色
     *
     * @param name
     * @return
     */
    SysAdmin getAdminAndRolesByName(String name);


    /**
     * 为指定的管理员生成token
     *
     * @param adminId
     * @param roles
     * @param secret
     * @param expiresAt
     * @return String
     */
    String getToken(final String adminId, final String roles, final String secret, Date expiresAt);


    /**
     * 根据用户Id获取所有菜单，不过现在不用
     *
     * @param adminId
     * @return
     */
    List<Map<String, List<SysMenu>>> getMenusListByAdminId(String adminId);

    /**
     * 修改个人信息
     *
     * @param userDto
     * @return
     */
    int updateInfo(UserDto userDto);
}
