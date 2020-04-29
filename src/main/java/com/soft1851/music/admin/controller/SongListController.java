package com.soft1851.music.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.entity.SongList;
import com.soft1851.music.admin.service.SongListService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yy
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/songList")
public class SongListController {
    @Resource
    private SongListService songListService;

    @GetMapping("/page")
    public ResponseResult selectSongListByPage(@Param("current") int current, @Param("size") int size) {
        Page<SongList> page = new Page<>(current, size);
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.select("song_list_id", "song_list_name", "thumbnail", "type", "song_count", "like_count", "comment_count", "delete_flag", "play_counts");
        return ResponseResult.success(songListService.page(page, wrapper));
    }

    @GetMapping
    public ResponseResult selectSongListById(@Param("id") String id) {
        return ResponseResult.success(songListService.selectSongListById(id));
    }

    @GetMapping("/like")
    public ResponseResult search(@Param("like") String like) {
        QueryWrapper<SongList> wrapper1 = new QueryWrapper<>();
        wrapper1.like("song_list_name", like);
        wrapper1.select("song_list_id", "song_list_name", "thumbnail", "type", "song_count", "like_count", "comment_count", "delete_flag", "play_counts");
        QueryWrapper<SongList> wrapper2 = new QueryWrapper<>();
        wrapper2.like("type", like);
        wrapper2.select("song_list_id", "song_list_name", "thumbnail", "type", "song_count", "like_count", "comment_count", "delete_flag", "play_counts");
        List<SongList> result = Stream.of(songListService.list(wrapper1), songListService.list(wrapper2))
                .flatMap(Collection::stream).distinct().collect(Collectors.toList());
        return ResponseResult.success(result);
    }
}
