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
* 用户信息表
*/
@TableName(value ="user_info")
@ApiModel(value = "用户信息类", description = "一条用户信息")
@Data
@AllArgsConstructor
public class UserInfo implements Serializable {

    /**
    * 用户信息条目ID
    */
    @ApiModelProperty("用户信息条目ID")
    @TableId(value = "ui_id", type = IdType.AUTO)
    private Long uiId;

    /**
    * 用户ID
    */
    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private String userId;

    /**
    * 用户信息条目的值
    */
    @ApiModelProperty("用户信息条目的值")
    @TableField("content")
    private String content;

    /**
    * 信息类型
    */
    @ApiModelProperty("信息类型")
    @TableField("type")
    private Integer type;

    public UserInfo(String user_id, String content, Integer type) {
        this.userId = user_id;
        this.content = content;
        this.type = type;
    }
}
