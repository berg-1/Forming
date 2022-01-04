package me.berg.forming.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
* 用户信息表
*/
@TableName(value ="user_info")
@ApiModel(value = "用户信息类", description = "一条用户信息")
@Data
public class UserInfo implements Serializable {

    /**
    * 用户信息条目ID
    */
    @NotNull(message="[用户信息条目ID]不能为空")
    @ApiModelProperty("用户信息条目ID")
    @TableId(value = "ui_id", type = IdType.AUTO)
    private Long ui_id;

    /**
    * 用户ID
    */
    @Size(max= 14,message="编码长度不能超过14")
    @ApiModelProperty("用户ID")
    @Length(max= 14,message="编码长度不能超过14")
    private String user_id;

    /**
    * 用户信息条目的值
    */
    @Size(max= 128,message="编码长度不能超过128")
    @ApiModelProperty("用户信息条目的值")
    @Length(max= 128,message="编码长度不能超过128")
    private String content;

    /**
    * 信息类型（如身份证、手机号、住址）
    */
    @Size(max= 128,message="编码长度不能超过128")
    @ApiModelProperty("信息类型（如身份证、手机号、住址）")
    @Length(max= 128,message="编码长度不能超过128")
    private String type;

}
