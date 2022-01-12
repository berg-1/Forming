package me.berg.forming.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.berg.forming.entity.ProjectItem;
import me.berg.forming.mapper.ProjectItemMapper;
import me.berg.forming.service.ProjectItemService;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * 项目表单项(ProjectTemplateItem)表服务实现类
 */
@Service
public class ProjectItemServiceImpl extends ServiceImpl<ProjectItemMapper, ProjectItem>
        implements ProjectItemService {

    @Override
    public List<ProjectItem> listByTemplateKey(String key) {
        return this.list(Wrappers.<ProjectItem>lambdaQuery().eq(ProjectItem::getProjectKey, key));
    }

    @Override
    public Boolean saveItemByProjectKey(ProjectItem item, String key) {
        item.setProjectKey(key);
        return this.save(item);
    }

    @Override
    public Boolean deleteByKey(String projectKey, String userId) {
        return this.remove(Wrappers.<ProjectItem>lambdaQuery()
                .eq(ProjectItem::getProjectKey, projectKey));
    }
}




