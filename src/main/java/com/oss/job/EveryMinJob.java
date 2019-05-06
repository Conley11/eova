package com.oss.job;

import org.quartz.JobExecutionContext;

/**
 * 每分钟执行
 *
 * @author czhang
 * @date 2018-7-7
 */
public class EveryMinJob extends AbsJob {

	@Override
	protected void process(JobExecutionContext context) {
		System.out.println("每分钟任务");
		// context.getJobDetail().getJobDataMap().get("xx参数");
	}
}
