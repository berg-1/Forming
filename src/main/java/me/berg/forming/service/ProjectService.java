package me.berg.forming.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.berg.forming.entity.Project;

import java.util.List;

/**
 *
 */
public interface ProjectService extends IService<Project> {

    /**
     * 根据key获取
     *
     * @param key 项目Key
     * @return 项目
     */
    Project getByKey(String key, String userId);

    /**
     * 获取用户项目列表
     * @param userId 用户ID
     * @return User的Project的List
     */
    List<Project> listById(String userId);

    /**
     * 根据项目key将项目deleted设为true
     * @param projectKey 项目Key
     * @param userId 用户ID
     * @return 是否删除成功
     */
    boolean deleteByKey(String projectKey, String userId);

    /**
     * 获取回收站中的项目
     * @param userId 用户ID
     * @return 回收站中User的Project的List
     */
    List<Project> listRecycle(String userId);
}
