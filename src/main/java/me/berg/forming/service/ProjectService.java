package me.berg.forming.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.berg.forming.entity.Project;

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
    Project getByKey(String key);
}
