package com.soft1851.music.admin.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RoleMenuServiceTest {
    @Resource
    private RoleMenuService roleMenuService;

    @Test
    void getRoleMenuByRoleId() {
        System.out.println(roleMenuService.getRoleMenuByRoleId("1"));
    }
}