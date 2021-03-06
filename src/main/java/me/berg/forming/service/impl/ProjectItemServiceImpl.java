package me.berg.forming.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.berg.forming.entity.Project;
import me.berg.forming.entity.ProjectItem;
import me.berg.forming.mapper.ProjectItemMapper;
import me.berg.forming.service.ProjectItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目表单项(ProjectTemplateItem)表服务实现类
 */
@Service
public class ProjectItemServiceImpl extends ServiceImpl<ProjectItemMapper, ProjectItem>
        implements ProjectItemService {

    @Override
    public List<ProjectItem> listByKey(String key) {
        return this.list(Wrappers.<ProjectItem>lambdaQuery().eq(ProjectItem::getProjectKey, key));
    }

    @Override
    public List<ProjectItem> listAutoFill(String key) {
        return this.list(Wrappers.<ProjectItem>lambdaQuery()
                .eq(ProjectItem::getProjectKey, key)
                .ne(ProjectItem::getAutofill, 0));
    }

    @Override
    public List<Integer> listAutoFillKeys(String key) {
        List<ProjectItem> list = this.list(Wrappers.<ProjectItem>lambdaQuery()
                .eq(ProjectItem::getProjectKey, key)
                .ne(ProjectItem::getAutofill, 0));
        return list.stream().map(ProjectItem::getAutofill).collect(Collectors.toList());
    }

    @Override
    public Boolean saveItemByProjectKey(ProjectItem item, String key) {
        item.setProjectKey(key);
        boolean success;
        try {
            success = this.save(item);
        } catch (Exception e) {
            log.debug("Project item 上传失败!");
            success = false;
        }
        return success;
    }

    @Override
    public Boolean deleteByKey(String projectKey, String userId) {
        return this.remove(Wrappers.<ProjectItem>lambdaQuery().eq(ProjectItem::getProjectKey, projectKey));
    }
}




