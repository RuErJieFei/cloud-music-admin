package com.soft1851.music.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.music.admin.domain.entity.SongList;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yy
 * @since 2020-04-21
 */
public interface SongListService extends IService<SongList> {
    /**
     * 根据songListId来查找歌单，包含歌曲
     *
     * @param songListId
     * @return
     */
    SongList selectSongListById(String songListId);
}
