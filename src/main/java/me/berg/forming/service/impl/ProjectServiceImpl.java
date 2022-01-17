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
    public Project getByKey(String key) {
        return this.getOne(Wrappers.<Project>lambdaQuery().eq(Project::getKey, key));
    }

    @Override
    public List<Project> listById(String userId) {
        return this.list(Wrappers.<Project>lambdaQuery()
                .eq(Project::getUserId, userId)
                .eq(Project::getDeleted, false)
                .orderByDesc(Project::getCreateTime));
    }

    @Override
    public Boolean recycleByKey(String projectKey, String userId) {
        return this.update(new Project() {{
            setDeleted(Boolean.TRUE);
        }}, Wrappers.<Project>lambdaQuery().eq(Project::getKey, projectKey).eq(Project::getUserId, userId));
    }

    @Override
    public Boolean removeAnyway(String projectKey, String userId) {
        return this.remove(Wrappers.<Project>lambdaQuery().eq(Project::getUserId, userId)
                .eq(Project::getKey, projectKey));
    }

    @Override
    public List<Project> listRecycle(String userId) {
        return this.list(Wrappers.<Project>lambdaQuery()
                .eq(Project::getUserId, userId)
                .eq(Project::getDeleted, true)
                .orderByDesc(Project::getCreateTime));
    }

    @Override
    public Boolean restoreByKey(String projectKey, String userId) {
        return this.update(new Project() {{
            setDeleted(Boolean.FALSE);
        }}, Wrappers.<Project>lambdaQuery().eq(Project::getKey, projectKey).eq(Project::getUserId, userId));
    }

    @Override
    public Boolean deleteByKey(String projectKey, String userId) {
        return this.remove(Wrappers.<Project>lambdaQuery().eq(Project::getUserId, userId)
                .eq(Project::getDeleted, Boolean.TRUE)
                .eq(Project::getKey, projectKey));
    }
}

