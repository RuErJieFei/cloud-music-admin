package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.dto.LoginDto;
import com.soft1851.music.admin.entity.SysAdmin;
import com.soft1851.music.admin.service.SysAdminService;
import com.soft1851.music.admin.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yy
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/sysAdmin")
@Slf4j
public class SysAdminController {
    @Resource
    private SysAdminService sysAdminService;

    /**
     * 获取所有不同角色的不同菜单
     *
     * @param adminId
     * @return
     */
    @PostMapping("/menu")
    public ResponseResult getMenus(@Param("adminId") String adminId) {
        return ResponseResult.success(sysAdminService.getMenusListByAdminId(adminId));
    }

    @PostMapping("/admin")
    public ResponseResult getAdmin(@Param("adminName") String adminName) {
        return ResponseResult.success(sysAdminService.getAdmin(adminName));
    }

    /**
     * 登录
     *
     * @return String
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginDto loginDto) {
        boolean login = sysAdminService.login(loginDto);
        if (login) {
            SysAdmin admin = sysAdminService.getAdmin(loginDto.getName());
            return ResponseResult.success(JwtTokenUtil.getToken(admin.getId(), "admin", new Date(System.currentTimeMillis() + 60L * 1000L)));
        } else {
            return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
        }
    }
}
