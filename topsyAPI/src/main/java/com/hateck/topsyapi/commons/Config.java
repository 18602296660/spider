package com.hateck.topsyapi.commons;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.plugin.quartz.QuartzPlugin;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.plugin.druid.DruidPlugin;

/**
 * Created by apple on 15/4/21.
 */
public class Config extends JFinalConfig {
    @Override
    public void configConstant(Constants me) {
        loadPropertyFile("init.properties");
        me.setDevMode(getPropertyToBoolean("devMode", false));
    }

    @Override
    public void configRoute(Routes me) {
    }

    @Override
    public void configPlugin(Plugins me) {
        DruidPlugin druid = new DruidPlugin("jdbc:mysql://127.0.0.1/ajyqv1", "root", "tjhateck");
        me.add(druid);
        AutoTableBindPlugin atbp = new AutoTableBindPlugin(druid);
        atbp.setShowSql(getPropertyToBoolean("showSql", false));
        me.add(atbp);
        QuartzPlugin quartzPlugin = new QuartzPlugin("job.properties");
        me.add(quartzPlugin);
    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }

    public static void main(String[] args) throws Exception {
        JFinal.start("src/main/webapp", 8080, "/", 5);
    }
}
