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
    Boolean recycleByKey(String projectKey, String userId);

    /**
     * 彻底删除
     * @param projectKey 项目Key
     * @param userId 用户I
     * @return 是否彻底删除成功
     */
    Boolean removeAnyway(String projectKey, String userId);

    /**
     * 获取回收站中的项目
     * @param userId 用户ID
     * @return 回收站中User的Project的List
     */
    List<Project> listRecycle(String userId);

    /**
     * 回收项目
     * @param projectKey 项目ID
     * @param userId 用户ID
     * @return 是否删除成功
     */
    Boolean restoreByKey(String projectKey, String userId);

    /**
     * 彻底删除项目(从回收站移除)
     * @param projectKey 项目ID
     * @param userId 用户ID
     * @return 是否彻底删除成功
     */
    Boolean deleteByKey(String projectKey, String userId);
}
