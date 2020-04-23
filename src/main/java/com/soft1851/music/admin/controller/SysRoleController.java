package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.service.SysRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yy
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    @PostMapping(value = "/role")
    public ResponseResult getRoles(@Param("adminId") String adminId) {
        return ResponseResult.success(sysRoleService.getRoleListById(adminId));
    }

    @GetMapping()
    public Map getRoleById(@Param("roleId") String roleId) {
        return sysRoleService.selectRoleById(Integer.parseInt(roleId));
    }
}
