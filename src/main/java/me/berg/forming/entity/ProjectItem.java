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
     * 表示该条目的JSON
     */
    @TableField(value = "expand")
    private String expand;

    /**
     * 正则表达式匹配条目
     */
    @TableField(value = "regex")
    private String regex;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProjectItem other = (ProjectItem) that;
        return (this.getItmId() == null ? other.getItmId() == null : this.getItmId().equals(other.getItmId()))
            && (this.getProjectKey() == null ? other.getProjectKey() == null : this.getProjectKey().equals(other.getProjectKey()))
            && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getExpand() == null ? other.getExpand() == null : this.getExpand().equals(other.getExpand()))
            && (this.getRegex() == null ? other.getRegex() == null : this.getRegex().equals(other.getRegex()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getItmId() == null) ? 0 : getItmId().hashCode());
        result = prime * result + ((getProjectKey() == null) ? 0 : getProjectKey().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getExpand() == null) ? 0 : getExpand().hashCode());
        result = prime * result + ((getRegex() == null) ? 0 : getRegex().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itmId=").append(itmId);
        sb.append(", projectKey=").append(projectKey);
        sb.append(", position=").append(position);
        sb.append(", type=").append(type);
        sb.append(", expand=").append(expand);
        sb.append(", regex=").append(regex);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
