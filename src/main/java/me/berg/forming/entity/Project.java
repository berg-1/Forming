package me.berg.forming.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 项目模板
 *
 * @TableName project_template
 */
@ApiModel(value = "项目模板类", description = "项目模板")
@TableName(value = "project")
@Data
public class Project implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 项目KEY UUID
     */
    @TableId(value = "`key`", type = IdType.AUTO)
    @ApiModelProperty("Key")
    private String key;

    /**
     * 项目名称
     */
    @ApiModelProperty("项目名称")
    @TableField("name")
    private String name;

    /**
     * 项目描述
     */
    @ApiModelProperty("项目描述")
    @TableField("`describe`")
    private String describe;

    /**
     * 构建表单的JSON数据
     */
    @ApiModelProperty("构建表单的JSON数据")
    @TableField("content")
    private String content;

    /**
     * 发布者ID
     */
    @ApiModelProperty("发布者ID")
    @TableField("user_id")
    private String userId;

    /**
     * 项目模板创建时间
     */
    @ApiModelProperty("项目模板创建时间")
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 是否被移入回收站
     */
    @ApiModelProperty("是否被移入回收站")
    @TableField("deleted")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Boolean deleted;


}
