package com.soft1851.music.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.music.admin.domain.entity.SongList;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yy
 * @since 2020-04-21
 */
public interface SongListMapper extends BaseMapper<SongList> {
    /**
     * 根据歌单id查询歌单，包含歌曲
     *
     * @param songListId
     * @return
     */
    SongList selectSongListById(String songListId);
}
