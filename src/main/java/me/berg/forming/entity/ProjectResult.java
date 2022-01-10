package me.berg.forming.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import sun.security.x509.SerialNumber;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户提交的结果
 * @TableName project_user_result
 */
@TableName(value = "project_result")
@ApiModel(value = "用户信息类", description = "一条用户信息")
@Data
public class ProjectResult implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 填写表单ID
     */
    @TableId(value = "pur_id", type = IdType.AUTO)
    @ApiModelProperty("填写表单ID")
    private Long prId;

    /**
     * 上传用户ID
     */
    @ApiModelProperty("上传用户ID")
    private String userId;

    /**
     * 对应项目模板ID
     */
    @ApiModelProperty("对应项目模板ID")
    private String projectKey;

    /**
     * 用户填写的表单，JSON化
     */
    @ApiModelProperty("用户填写的表单，JSON化")
    private String data;

    /**
     * 上传时间
     */
    @ApiModelProperty("上传时间")
    private LocalDateTime localDateTime;

    private Long serialNumber;
}
