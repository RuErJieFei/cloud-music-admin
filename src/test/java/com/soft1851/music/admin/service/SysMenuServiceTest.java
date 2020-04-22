package com.soft1851.music.admin.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SysMenuServiceTest {
    @Resource
    private SysMenuService sysMenuService;

    @Test
    void getMenuById() {
        System.out.println(sysMenuService.getMenuById("1"));
    }

    @Test
    void getMenuListByRoleId() {
        sysMenuService.getMenuListByRoleId("1").forEach(System.out::println);
    }
}