package com.sgwl.filemanager.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 定时任务对象
 */
@Data
@AllArgsConstructor
public class ScheduledTask {

    private String taskName;

    private String taskCron;

}
