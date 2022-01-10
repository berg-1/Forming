package me.berg.forming.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.berg.forming.component.RedisService;
import me.berg.forming.entity.ProjectResult;
import me.berg.forming.mapper.ProjectUserResultMapper;
import me.berg.forming.service.ProjectResultService;
import org.springframework.stereotype.Service;

import static me.berg.forming.constants.CommonConstants.ConstantNumber.ONE;
import static me.berg.forming.constants.ProjectRedisKeyConstants.PROJECT_RESULT_NUMBER;

/**
 * 项目表单项(ProjectResult)表服务实现类
 */
@Service
@RequiredArgsConstructor
public class ProjectResultServiceImpl extends ServiceImpl<ProjectUserResultMapper, ProjectResult>
        implements ProjectResultService {

    private final RedisService redisService;

    /**
     * 保存填写结果，SerialNumber保存到Redis
     *
     * @param result 填写结果
     */
    @Override
    public Boolean saveProjectResult(ProjectResult result) {
        String projectKey = result.getProjectKey();
        result.setSerialNumber(redisService.incr(StrUtil.format(PROJECT_RESULT_NUMBER, projectKey), ONE));
        return this.save(result);
    }
}




