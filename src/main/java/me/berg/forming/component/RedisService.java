package me.berg.forming.component;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.support.ConnectionPoolSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.berg.forming.service.SyncCommandCallback;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;

/**
 * RedisService 使用此类提供的方法 get 或 set Redis 内的数据
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisService {

    final RedisClient redisClient;

    /**
     * 引入了Commons Pool的一个对象池，用于缓存Redis连接。
     * 注意:因为Lettuce本身是基于Netty的异步驱动，在异步访问时并不需要创建连接池，但基于Servlet模型的同步访问时，连接池是有必要的。
     * 连接池在@PostConstruct方法中初始化，在@PreDestroy方法中关闭。
     */
    GenericObjectPool<StatefulRedisConnection<String, String>> redisConnectionPool;

    @PostConstruct
    public void init() {
        GenericObjectPoolConfig<StatefulRedisConnection<String, String>> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(20);
        poolConfig.setMaxIdle(5);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        this.redisConnectionPool = ConnectionPoolSupport.createGenericObjectPool(redisClient::connect, poolConfig);
    }

    @PreDestroy
    public void shutdown() {
        this.redisConnectionPool.close();
        this.redisClient.shutdown();
    }

    /**
     * 编写executeSync方法，在该方法中，获取Redis连接，利用callback操作Redis，最后释放连接，并返回操作结果：
     * 常用命令可以提供方法接口，如果要执行任意复杂的操作，就可以通过executeSync(SyncCommandCallback<T>)来完成。
     *
     * @param callback 回调函数接口,利用callback操作Redis
     * @param <T>      泛型
     * @return 操作结果
     */
    public <T> T executeSync(SyncCommandCallback<T> callback) {
        try (StatefulRedisConnection<String, String> connection = redisConnectionPool.borrowObject()) {
            connection.setAutoFlushCommands(true);
            RedisCommands<String, String> commands = connection.sync();
            return callback.doInConnection(commands);
        } catch (Exception e) {
            log.warn("executeSync redis failed.", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置键值对到Redis,存在则覆盖
     *
     * @param key   键
     * @param value 值
     * @return 插入状态/结果
     */
    public String set(String key, String value) {
        return executeSync(commands -> commands.set(key, value));
    }

    /**
     * 从Redis中取值
     *
     * @param key 键
     * @return 查询结果
     */
    public String get(String key) {
        return executeSync(commands -> commands.get(key));
    }

    /**
     * 设置哈希表
     *
     * @param key   哈希表名
     * @param field 键
     * @param value 值
     * @return 插入状态
     */
    public boolean hSet(String key, String field, String value) {
        return executeSync(commands -> commands.hset(key, field, value));
    }

    /**
     * 查询哈希表
     *
     * @param key   哈希表名
     * @param field 字段
     * @return 查询结果
     */
    public String hGet(String key, String field) {
        return executeSync(commands -> commands.hget(key, field));
    }

    /**
     * 取得哈希表中的所有键值对
     *
     * @param key 哈希表名
     * @return 键值对的Map
     */
    public Map<String, String> hGetAll(String key) {
        return executeSync(commands -> commands.hgetall(key));
    }

    /**
     * 在Redis中递增
     *
     * @param key 键
     * @param num 增加多少
     * @return 增加结果
     */
    public Long incr(String key, Integer num) {
        if (num < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return executeSync(commands -> commands.incr(key));
    }
}
