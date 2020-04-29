package com.soft1851.music.admin.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft1851.music.admin.domain.entity.Song;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SongMapperTest {
    @Resource
    private SongMapper songMapper;

    @Test
    void selectSong() {
        Page<Song> page = new Page<>(1, 5);
        songMapper.selectSongsByPage(page, 0).getRecords().forEach(System.out::println);
    }
}