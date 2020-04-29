package com.soft1851.music.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.entity.SongList;
import com.soft1851.music.admin.service.SongListService;
import com.soft1851.music.admin.service.SongTypeService;
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
@RequestMapping("/songType")
public class SongTypeController {
    @Resource
    private SongTypeService songTypeService;
    @Resource
    private SongListService songListService;

    @GetMapping("/list")
    public ResponseResult getSongListBySongType(@Param("typeName") String typeName) {
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.eq("type", typeName);
        wrapper.select("song_list_id", "song_list_name", "thumbnail", "type", "song_count", "like_count", "comment_count", "delete_flag", "play_counts");
        return ResponseResult.success(songListService.list(wrapper));
    }


    @GetMapping("/all")
    public ResponseResult getAll() {
        return ResponseResult.success(songTypeService.list());
    }

}
