package me.berg.forming.web.vo;

import lombok.Data;
import me.berg.forming.entity.Project;
import me.berg.forming.entity.ProjectItem;
import me.berg.forming.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回项目填写需要 VO
 */
@Data
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
    private List<UserInfoVO> userInfos;

    public ProjectLoadVO(Project project, List<ProjectItem> projectItems, List<UserInfo> infos) {
        this.project = project;
        this.projectItems = projectItems;
        this.userInfos = new ArrayList<>();
        for (UserInfo info : infos) {
            this.userInfos.add(new UserInfoVO(info.getType(), info.getContent()));
        }

    }
}
