package com.soft1851.music.admin.service;

import com.soft1851.music.admin.domain.dto.LoginDto;
import com.soft1851.music.admin.domain.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SysAdminServiceTest {
    @Resource
    private SysAdminService sysAdminService;

    @Test
    void login() {
        LoginDto loginDto = LoginDto.builder().name("1").password("1").verifyCode("1").build();
        sysAdminService.login(loginDto);
    }


    @Test
    void getAdmin() {
//        System.out.println(sysAdminService.getAdmin("1"));
    }

    @Test
    void getAdminAndRolesByName() {
        System.out.println(sysAdminService.getAdminAndRolesByName("1"));
    }

    @Test
    void updateInfo() {
        UserDto userDto = UserDto.builder().userId("2").username("45").build();
        int n = sysAdminService.updateInfo(userDto);
        assertEquals(1, n);
    }
}