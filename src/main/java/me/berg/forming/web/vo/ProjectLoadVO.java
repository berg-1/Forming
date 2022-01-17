package me.berg.forming.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.berg.forming.entity.Project;
import me.berg.forming.entity.ProjectItem;
import me.berg.forming.entity.UserInfo;

import java.util.List;

/**
 * 返回项目填写需要 VO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectLoadVO {

    /**
     * 项目 大致描述
     */
    private Project project;

    /**
     * 具体表项
     */
    private List<ProjectItem> projectItems;

    /**
     * 用户信息 用于自动填写
     */
    private List<UserInfo> userInfos;
}
