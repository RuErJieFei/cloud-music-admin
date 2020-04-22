package com.soft1851.music.admin.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RoleAdminServiceTest {
    @Resource
    private RoleAdminService roleAdminService;

    @Test
    void getRoleListByAdminId() {
        System.out.println(roleAdminService.getRoleListByAdminId("DE35D7CC05AF96A21D7ADFC8651E6614"));
    }
}