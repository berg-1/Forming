package me.berg.forming.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目的具体条目（问题）
 * @TableName project_template_item
 */
@TableName(value ="project_item")
@Data
@NoArgsConstructor
public class ProjectItem implements Serializable {
    /**
     * 表项ID
     */
    @TableId(value = "itm_id", type = IdType.AUTO)
    private Long itmId;

    /**
     * 该条目属于哪一个项目
     */
    @TableField(value = "project_key")
    private String projectKey;

    /**
     * 条目的位置
     */
    @TableField(value = "position")
    private Integer position;

    /**
     * 条目类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 问题名称<br/>
     * 表项的描述信息
     */
    @TableField(value = "label")
    private String label;

    /**
     * 表示该条目的JSON
     */
    @TableField(value = "expand")
    private String expand;

    /**
     * 正则表达式匹配条目
     */
    @TableField(value = "regex")
    private String regex;

    /**
     * 自动填写字段 类型值
     */
    @TableField(value = "autofill")
    private Integer autofill;

    /**
     * 是否必填，默认为是
     */
    @TableField(value = "required")
    private Integer required;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public String getPath(){
        return this.position.toString();
    }

}
