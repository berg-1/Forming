package me.berg.forming.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.berg.forming.entity.ProjectItem;

import java.util.List;

/**
 * 项目表单项(ProjectTemplateItem)表服务接口
 */
public interface ProjectItemService extends IService<ProjectItem> {

    List<ProjectItem> listByTemplateKey(String key);

    boolean saveItemByProjectKey(ProjectItem item,String projectKey);
}
