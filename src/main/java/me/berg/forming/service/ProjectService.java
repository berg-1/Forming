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

    List<Project> listById(String userId);
}
