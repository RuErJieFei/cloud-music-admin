package com.soft1851.music.admin.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SysRoleServiceTest {
    @Resource
    private SysRoleService sysRoleService;

    @Test
    void getRoleById() {
        System.out.println(sysRoleService.getRoleById("1"));
    }

    @Test
    void getRoleListById() {
        System.out.println(sysRoleService.getRoleListById("DE35D7CC05AF96A21D7ADFC8651E6614"));
    }

    @Test
    void selectRoleById() {
        System.out.println(sysRoleService.selectRoleById(1));
    }
}