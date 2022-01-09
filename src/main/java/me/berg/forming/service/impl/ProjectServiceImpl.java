package me.berg.forming.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.berg.forming.entity.Project;
import me.berg.forming.mapper.ProjectMapper;
import me.berg.forming.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Override
    public Project getByKey(String key, String userId) {
        return this.getOne(Wrappers.<Project>lambdaQuery().eq(Project::getKey, key).eq(Project::getUserId, userId));
    }

    @Override
    public List<Project> listById(String userId) {
        return this.list(Wrappers.<Project>lambdaQuery().eq(Project::getUserId, userId).orderByDesc(Project::getCreateTime));
    }

    @Override
    public boolean deleteByKey(String projectKey, String userId) {
        return this.update(new Project() {{
            setDeleted(Boolean.TRUE);
        }}, Wrappers.<Project>lambdaQuery().eq(Project::getKey, projectKey).eq(Project::getUserId, userId));
    }
}

