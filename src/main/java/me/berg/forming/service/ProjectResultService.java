package me.berg.forming.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.berg.forming.entity.ProjectResult;

/**
 *
 */
public interface ProjectResultService extends IService<ProjectResult> {

    /**
     * 保存结果
     * @param result 填写结果
     */
    Boolean saveProjectResult(ProjectResult result);

}
