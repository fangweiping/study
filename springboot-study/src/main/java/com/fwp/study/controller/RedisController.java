//package com.fwp.study.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.*;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Set;
//
//@RestController
//@RequestMapping("redis")
//public class RedisController {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//
//    /**
//     * redisTemplate操作5种数据结构api
//     * redis种的外层key都是string类型,这里的数据结构
//     * 是指value的数据结构
//     */
//    public void demo() {
//        //1.直接通过redisTemplate获取不同数据结构的操作对象
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        ListOperations listOperations = redisTemplate.opsForList();
//        SetOperations setOperations = redisTemplate.opsForSet();
//        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
//        HashOperations hashOperations = redisTemplate.opsForHash();
//
//        //2.指定key直接获取对应的操作绑定对象
//        BoundValueOperations string = redisTemplate.boundValueOps("key");
//        BoundListOperations list = redisTemplate.boundListOps("key");
//        BoundSetOperations set = redisTemplate.boundSetOps("key");
//        BoundZSetOperations zset = redisTemplate.boundZSetOps("key");
//        BoundHashOperations hash = redisTemplate.boundHashOps("key");
//    }
//
//    /**
//     * 操作字符串类型
//     */
//    public void valueOperations() {
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        valueOperations.append("key","s");//追加
//        valueOperations.getAndSet("key","s");//替换
//    }
//
//    /**
//     * 操作列表类型
//     */
//    public void listOperations() {
//        ListOperations listOperations = redisTemplate.opsForList();
//        listOperations.leftPush("key",1);//头部添加
//        listOperations.leftPop("key");//头部获取并删除
//        listOperations.rightPush("key",1);//尾部添加
//        listOperations.rightPop("key");//尾部获取并删除
//
//        Long size = listOperations.size("key");//获取列表长度
//        List list = listOperations.range("key", 0, -1);//获取所有元素
//    }
//
//    /**
//     * 操作set类型
//     */
//    public void setOperations() {
//        SetOperations setOperations = redisTemplate.opsForSet();
//    }
//
//    /**
//     * 操作zset类型  对应java中sortedset
//     */
//    @GetMapping("zset")
//    public void zsetOperations() {
//        ZSetOperations zsetOperations = redisTemplate.opsForZSet();
//        Set key = zsetOperations.range("key", 0, -1);
//    }
//}
