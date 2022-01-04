package me.berg.forming.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户提交的结果
 * @TableName project_user_result
 */
@TableName(value = "project_user_result")
@ApiModel(value = "用户信息类", description = "一条用户信息")
@Data
public class ProjectUserResult implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 填写表单ID
     */
    @NotNull(message = "[填写表单ID]不能为空")
    @ApiModelProperty("填写表单ID")
    private Long pur_id;

    /**
     * 上传用户ID
     */
    @Size(max = 14, message = "编码长度不能超过14")
    @ApiModelProperty("上传用户ID")
    @Length(max = 14, message = "编码长度不能超过14")
    private String user_id;

    /**
     * 对应项目模板ID
     */
    @NotNull(message = "[对应项目模板ID]不能为空")
    @ApiModelProperty("对应项目模板ID")
    private Integer pr_id;

    /**
     * 用户填写的表单，JSON化
     */
    @ApiModelProperty("用户填写的表单，JSON化")
    private String data;

    /**
     * 上传时间
     */
    @ApiModelProperty("上传时间")
    private LocalDateTime update_time;
}
