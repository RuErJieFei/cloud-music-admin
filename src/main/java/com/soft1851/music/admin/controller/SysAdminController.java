package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.dto.LoginDto;
import com.soft1851.music.admin.domain.dto.UserDto;
import com.soft1851.music.admin.service.SysAdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
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
@RequestMapping("/sysAdmin")
@Validated
@Slf4j
public class SysAdminController {
    @Resource
    private SysAdminService sysAdminService;


    @PostMapping("/admin")
    public ResponseResult getAdmin(@Param("adminName") String adminName) {
        return ResponseResult.success(sysAdminService.getAdminAndRolesByName(adminName));
    }

    /**
     * 登录
     *
     * @return String
     */
    @PostMapping("/login")
    public Map login(@Valid @RequestBody LoginDto loginDto) {
        log.info(loginDto.toString());
        return sysAdminService.login(loginDto);
    }

    @PostMapping("/update")
    public ResponseResult update(@Valid @RequestBody UserDto userDto) {
        sysAdminService.updateInfo(userDto);
        return ResponseResult.success(sysAdminService.getAdminAndRolesByName(userDto.getUsername()));
    }
}