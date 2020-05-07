package com.soft1851.music.admin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @ClassName LoginDto
 * @Description TODO
 * @Author mq_xu
 * @Date 2020/4/21
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotNull(message = "用户名不可以为空")
    private String name;

    @NotNull(message = "密码不可以为空")
    @Pattern(regexp = "^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$", message = "密码只能含有数字英文和下划线")
    private String password;

    @NotNull(message = "验证码不可以为空")
    @Pattern(regexp = "^[0-9a-zA-Z]+$", message = "验证码只能是字母和数字")
    private String verifyCode;
}
