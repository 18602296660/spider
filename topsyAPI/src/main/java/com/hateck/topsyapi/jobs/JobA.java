package com.hateck.topsyapi.jobs;

import com.hateck.topsyapi.models.Keyword;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

/**
 * Created by apple on 15/4/21.
 * 每5分钟搜索一次用户关键字
 */
public class JobA extends SearchJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Logger.getLogger(this.getClass()).info("5min");
        List<Keyword> keys = Keyword.dao.find("select * from keyword where state = ?", 0);
        if(keys.size()>0) {
            search(0);
        }
    }
}
