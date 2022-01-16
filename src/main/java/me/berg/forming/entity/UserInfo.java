package me.berg.forming.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
    private Long ui_id;

    /**
    * 用户ID
    */
    @ApiModelProperty("用户ID")
    private String user_id;

    /**
    * 用户信息条目的值
    */
    @ApiModelProperty("用户信息条目的值")
    private String content;

    /**
    * 信息类型
    */
    @ApiModelProperty("信息类型")
    private Integer type;

    public UserInfo(String user_id, String content, Integer type) {
        this.user_id = user_id;
        this.content = content;
        this.type = type;
    }
}
