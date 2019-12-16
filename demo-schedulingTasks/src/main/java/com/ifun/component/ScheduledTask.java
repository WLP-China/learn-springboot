package com.ifun.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试 SpringTask定时任务
 *
 * SpringTask是Spring自主研发的轻量级定时任务工具，相比于Quartz更加简单方便，且不需要引入其他依赖即可使用。
 */
@Component
public class ScheduledTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /*
    * 通过在方法上加@Scheduled注解，表明该方法是一个调度任务。
    *
    * @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
    * @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
    * @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
    * @Scheduled(cron=" /5 ") ：通过cron表达式定义规则，什么是cro表达式，自行搜索引擎。
    * */
//    @Scheduled(fixedRate = 5000)
    @Scheduled(cron = "0/10 * * ? * ?")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}