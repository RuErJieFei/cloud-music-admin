package com.soft1851.music.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.entity.Song;
import com.soft1851.music.admin.service.SongService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yy
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/song")
public class SongController {
    @Resource
    private SongService songService;

    @GetMapping
    public ResponseResult page(@Param("size") int size, @Param("current") int current) {
        Page<Song> page = new Page<>(current, size);
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.select("song_name", "sort_id", "singer", "duration", "thumbnail"
                , "url", "lyric", "comment_count", "like_count", "play_count", "delete_flag");
        return ResponseResult.success(songService.page(page));
    }

}
