package com.fwp.study.config.nosql.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class RedisConfig {

    @Resource
    private RedisClusterProperties redisClusterProperties;

    private String redisAddressTemp = "redis://%s:%s";

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisClusterConfiguration config = new RedisClusterConfiguration();
        config.setClusterNodes(getRedisNode());
        return new LettuceConnectionFactory(config);
    }

    /**
     * redisTemplate 和 stringRedisTemplate 设置相同的序列化方式实现数据相互访问
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

//    @Bean
//    public RedissonClient redissonClient() {
//        Config config = new Config();
////        config.useSingleServer().setAddress("redis://" + host + ":" + port);
//        config.useClusterServers().addNodeAddress(getNodeAddress());
//        return Redisson.create(config);
//    }

    public List<RedisNode> getRedisNode() {
        return redisClusterProperties.getNodes().stream()
                .map(node -> new RedisNode(node.split(":")[0], Integer.valueOf(node.split(":")[1])))
                .collect(Collectors.toList());
    }

    public String[] getNodeAddress() {
        return redisClusterProperties.getNodes().stream()
                .map(node -> String.format(redisAddressTemp, node.split(":")[0], node.split(":")[1])).toArray(String[]::new);
    }
}
