package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.service.SysMenuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yy
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    @PostMapping("/menu")
    public ResponseResult getMenus(@Param("roleId") String roleId) {
        return ResponseResult.success(sysMenuService.getMenuListByRoleId(roleId));
    }
}
