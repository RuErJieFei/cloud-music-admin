package com.soft1851.music.admin.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SongListMapperTest {
    @Resource
    private SongListMapper songListMapper;
    @Test
    void selectSongListById() {
        System.out.println(songListMapper.selectSongListById("2175031759"));
    }
}