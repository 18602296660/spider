package com.hateck.topsyapi.jobs;

import com.hateck.topsyapi.models.Keyword;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

/**
 * Created by apple on 15/4/21.
 * 每分钟搜索一次关键字
 */
public class JobB extends SearchJob implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //System.out.println("1min");
        Logger.getLogger(this.getClass()).info("1min");
        List<Keyword> keys = Keyword.dao.find("select * from keyword where state = ?", 1);

        if(keys.size()>=1){
            if(!search(1)){
                search(1);
            }
        }

    }
}
