package com.soft1851.music.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author mq_xu
 * @since 2020-04-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_admin")
public class SysAdmin extends Model<SysAdmin> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private String id;

    /**
     * 用户名
     */
    @TableField("name")
    @NotNull(message = "用户名不可以为空")
    private String name;

    /**
     * 密码
     */
    @JsonIgnore
    @TableField("password")
    @NotNull(message = "密码不可以为空")
    private String password;

    /**
     * 加密盐
     */
    @JsonIgnore
    @TableField("salt")
    private String salt;

    /**
     * 头像
     */
    @TableField("avatar")
    @Pattern(regexp = "/^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$/", message = "头像格式错误")
    private String avatar;

    /**
     * 创建时间
     */
    @JsonIgnore
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonIgnore
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 账户状态：0 禁用 1 启用
     */
    @JsonIgnore
    @TableField("status")
    private Integer status;
    private List<SysRole> roles;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
