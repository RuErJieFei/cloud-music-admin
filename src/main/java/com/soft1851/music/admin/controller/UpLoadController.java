package com.soft1851.music.admin.controller;

import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.util.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author wl
 * @ClassNameUpDownController
 * @Description 图片上传
 * @Date 2019/12/4
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class UpLoadController {

    /**
     * 文件上传
     */
    @PostMapping("/img")
    ResponseResult uploadSingle(@RequestPart("file") MultipartFile sourceFile) {
        // 获取文件名
        String fileName = sourceFile.getOriginalFilename();
        //uuid生成主文件名
        String prefix = UUID.randomUUID().toString();
        assert fileName != null;
        //源文件的扩展名
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //创建File类型的临时文件
        File tempFile;
        try {
            tempFile = File.createTempFile(prefix, suffix);
            sourceFile.transferTo(tempFile);
            String url = AliOssUtil.upload(tempFile);
            log.info(url);
            return ResponseResult.success(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.failure(ResultCode.FILE_UPLOAD_FAILURE);
    }


    @RequestMapping(value = "/multipleSave", method = RequestMethod.POST)
    public @ResponseBody
    String multipleSave(@RequestParam("file") MultipartFile[] files) {
        String fileName = null;
        String msg = "";
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                try {
                    fileName = files[i].getOriginalFilename();
                    byte[] bytes = files[i].getBytes();
                    BufferedOutputStream buffStream =
                            new BufferedOutputStream(new FileOutputStream(new File("/Users/xiaobinggan/Downloads/" + fileName)));
                    buffStream.write(bytes);
                    buffStream.close();
                    msg += "You have successfully uploaded " + fileName;
                } catch (Exception e) {
                    return "You failed to upload " + fileName + ": " + e.getMessage();
                }
            }
            return msg;
        } else {
            return "Unable to upload. File is empty.";
        }
    }
}


