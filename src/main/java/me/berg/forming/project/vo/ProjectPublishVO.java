package me.berg.forming.project.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.berg.forming.entity.ProjectItem;

import java.util.List;

/**
 * 发布项目（为Project注入Project Item）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPublishVO {

    /**
     * 项目UUID
     */
    private String projectKey;

    /**
     * 项目注入的Items
     */
    private List<ProjectItem> projectItems;

}
