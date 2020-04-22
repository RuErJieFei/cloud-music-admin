package com.soft1851.music.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName LoginDto
 * @Description TODO
 * @Author xiaobinggan
 * @Date 2020/4/21 2:14 下午
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String name;
    private String password;
    private String verifyCode;
}
