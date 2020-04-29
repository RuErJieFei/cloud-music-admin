package com.soft1851.music.admin.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SysAdminMapperTest {
    @Resource
    private SysAdminMapper sysAdminMapper;

    @Test
    void selectByName() {
        System.out.println(sysAdminMapper.selectByName("1"));
    }

    @Test
    void getSysAdminByName() {
        System.out.println(sysAdminMapper.getSysAdminByName("1"));
    }
}