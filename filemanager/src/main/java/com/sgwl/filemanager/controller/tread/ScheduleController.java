package com.sgwl.filemanager.controller.tread;

import com.sgwl.filemanager.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;


/**
 * 实时监控异常类
 */
@RestController
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    /**
     * 开启越界异常监控
     *
     * @return
     */
    @GetMapping("/yuejie/startCron/{cron}")
    @Scheduled()
    public String enableScheduleTaskOfYueJie(@PathVariable String cron) {
        return   scheduleService.enableScheduleTaskOfYueJie(cron);
    }

    /**
     * 关闭越界异常监控
     *
     * @return
     */
    @GetMapping("/yuejie/stopCron{taskName}")
    @Scheduled()
    public String stopScheduleTaskOfYueJie(@PathVariable String taskName) {
        return scheduleService.stopScheduleTaskOfYueJie(taskName);
    }


    /**
     * 修改监控周期
     *
     * @return
     */
    @GetMapping("/yuejie/stopCron/{taskName}/{cron}")
    @Scheduled()
    public String changeScheduleTaskOfYueJie(@PathVariable String taskName,@PathVariable String cron) {

        return scheduleService.changeScheduleTaskOfYueJie(taskName,cron);
    }


    /**
     * 开启变幅异常监控
     *
     * @return
     */
    @GetMapping("/yuejie/startCron")
    @Scheduled()
    public String enableScheduleTaskOfBianFu() {


        return null;
    }

    /**
     * 开启缺数异常监控
     *
     * @return
     */
    @GetMapping("/yuejie/startCron")
    @Scheduled()
    public String enableScheduleTaskOfQueShu() {

        return null;
    }
}
