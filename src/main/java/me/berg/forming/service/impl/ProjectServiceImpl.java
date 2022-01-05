package me.berg.forming.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.berg.forming.entity.Project;
import me.berg.forming.mapper.ProjectMapper;
import me.berg.forming.service.ProjectService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project>
        implements ProjectService {

    @Override
    public Project getByKey(String key) {
        return this.getOne(Wrappers.<Project>lambdaQuery().eq(Project::getKey, key));
    }
}




