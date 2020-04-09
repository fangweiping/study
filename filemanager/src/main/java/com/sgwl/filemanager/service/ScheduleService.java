package com.sgwl.filemanager.service;

import com.sgwl.filemanager.enums.AlaramTypeEnum;
import com.sgwl.filemanager.enums.AppTypeEnum;
import com.sgwl.filemanager.pojo.ScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduleService {

    /**
     * 使用线程安全的map记录开启的定时任务
     */
    private ConcurrentHashMap<String , Future> taskMap = new ConcurrentHashMap<>();

    /**
     * 维护一个供用户查看的任务列表
     */
    private List<ScheduledTask> taskList;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler ;

    /**
     * 开启越界异常监控
     *
     * @return
     * @param cron
     */
    public String enableScheduleTaskOfYueJie(String cron) {
        //日期表达式
        cron = "";

        //新建任务
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("MyRunnable.run()，" + new Date());
                //调用mapper获取相关计算数据进行业务判断,如有异常生成异常信息发送给mq
                //mq调用内部短信接口报备业主
            }
        };

        //任务调度
        ScheduledFuture<?> schedule = taskScheduler.schedule(task, new CronTrigger(cron));

        //任务名
        String taskName=  AppTypeEnum.WDS_SHUIWEI.getName()+ AlaramTypeEnum.YUEJIE.getName();

        //保存任务
        taskMap.put(taskName,schedule);

        //返回任务名
        return taskName;
    }


    /**
     * 关闭越界异常监控
     * @param taskName
     * @return
     */
    public String stopScheduleTaskOfYueJie(String taskName) {
        //获取任务
        Future task = taskMap.get(taskName);

        //关闭任务
        task.cancel(true);

        // 查看任务是否在正常执行之前结束,正常true
        boolean cancelled = task.isCancelled();
        while (!cancelled) {
            task.cancel(true);
            cancelled=task.isCancelled();
        }
        return taskName+":任务关闭";
    }

    /**
     * 修改任务执行周期
     */
    public String changeScheduleTaskOfYueJie(String taskName, String cron) {
        //获取任务
        Future task = taskMap.get(taskName);

        //关闭任务
        task.cancel(true);

        // 查看任务是否在正常执行之前结束,正常true
        boolean cancelled = task.isCancelled();
        while (!cancelled) {
            task.cancel(true);
        }

        //新建任务
        Runnable newTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("MyRunnable.run()，" + new Date());
                //调用mapper获取相关计算数据进行业务判断,如有异常生成异常信息发送给mq
                //mq调用内部短信接口报备业主
          }
        };

        //任务调度
        ScheduledFuture<?> schedule = taskScheduler.schedule(newTask, new CronTrigger(cron));

        //保存任务
        taskMap.put(taskName,schedule);

        return taskName +"任务执行周期修改成功";

    }
}
