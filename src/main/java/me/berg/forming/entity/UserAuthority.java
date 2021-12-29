package me.berg.forming.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户权限实体类
 */
@ApiModel("用户权限实体类")
@TableName(value ="user_authority")
@Data
@AllArgsConstructor
public class UserAuthority implements Serializable {
    /**
     * 授权id
     */
    @ApiModelProperty(value = "授权ID", required = true)
    @TableId(value = "auth_id", type = IdType.AUTO)
    private Integer authId;

    /**
     * 对应用户ID
     */
    @ApiModelProperty(value = "对应用户ID", required = true)
    @TableField(value = "user_id")
    private String userId;

    /**
     * 用户权限
     */
    @ApiModelProperty(value = "用户权限", required = true, notes = "ROLE_USER / ROLE_ADMIN")
    @TableField(value = "role")
    private String role;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
