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
 * 项目模板
 *
 * @TableName project_template
 */
@ApiModel(value = "项目模板类", description = "项目模板")
@TableName(value = "project_template")
@Data
public class ProjectTemplate implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 项目模板ID
     */
    @NotNull(message = "[项目模板ID]不能为空")
    @ApiModelProperty("项目模板ID")
    private Integer pt_id;

    /**
     * 项目描述，JSON数据
     */
    @ApiModelProperty("项目描述，JSON数据")
    private Integer describe;

    /**
     * 发布者ID
     */
    @Size(max = 14, message = "编码长度不能超过14")
    @ApiModelProperty("发布者ID")
    @Length(max = 14, message = "编码长度不能超过14")
    private String user_id;

    /**
     * 项目模板创建时间
     */
    @ApiModelProperty("项目模板创建时间")
    private LocalDateTime create_time;
}
