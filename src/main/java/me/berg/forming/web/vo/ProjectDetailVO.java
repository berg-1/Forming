package me.berg.forming.web.vo;

import lombok.NoArgsConstructor;
import me.berg.forming.entity.ProjectItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import me.berg.forming.entity.Project;

import java.util.List;

/**
 * 返回项目模板细节 VO (Value Object)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetailVO {

    /**
     * 项目 大致描述
     */
    private Project project;

    /**
     * 具体表项
     */
    private List<ProjectItem> projectItems;

}
