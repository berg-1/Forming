package me.berg.forming.service;

import io.lettuce.core.api.sync.RedisCommands;

/**
 * 在RedisService中添加Redis访问方法。
 * 为了简化代码，我们仿照JdbcTemplate.execute(ConnectionCallback)方法，
 * 传入回调函数，可大幅减少样板代码。
 *
 * @param <T> 方便函数编写
 */
@FunctionalInterface
public interface SyncCommandCallback<T> {
    T doInConnection(RedisCommands<String, String> commands);
}
