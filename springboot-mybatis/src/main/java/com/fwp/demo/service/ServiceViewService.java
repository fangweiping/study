package com.fwp.demo.service;

import com.fwp.demo.entity.ServiceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


@Service
public class ServiceViewService {

    @Autowired
    private Jedis jedis;

    private ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);

    public void  update(ServiceView serviceView) {
        String businessKey = "serviceView:" + serviceView.getId();
        Thread thread = Thread.currentThread();
        String threadId = String.valueOf(thread.getId() + System.currentTimeMillis());
        ScheduledFuture watchDog = null;
        try {
            if ("OK".equals(jedis.set(businessKey, threadId, "NX", "EX", 3))) {//加锁3秒
                //通过定时任务为当前线程持有的锁续期，2s后执行，每2s执行一次
                watchDog = schedule.scheduleAtFixedRate(() -> {
                    if (threadId.equals(jedis.get(businessKey))) {
                        //重新续期3s
                        jedis.expire(businessKey, 3);
                    }
                }, 2, 2, TimeUnit.SECONDS);
                //业务处理

            }
        } catch (Exception e) {
            //异常处理

        } finally {
            //取消续期任务
            if (watchDog != null) {
                watchDog.cancel(true);
            }
            //释放锁
            jedis.del(businessKey);
        }
    }
}
