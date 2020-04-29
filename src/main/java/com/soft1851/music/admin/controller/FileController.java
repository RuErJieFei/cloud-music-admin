package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.domain.entity.Song;
import com.soft1851.music.admin.service.FileService;
import com.soft1851.music.admin.util.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/4/22
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/resources")
@Slf4j
public class FileController {
    @Resource
    private FileService fileService;

    @PostMapping(value = "/songList")
    public void export(@RequestBody(required = false) List<Song> list) {
        log.info(list.toString());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletResponse response = attributes.getResponse();
        assert response != null;
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment");
        ExcelUtils.exportExcel(response, list, fileService.exportSongList(), "歌单表");
    }
}
