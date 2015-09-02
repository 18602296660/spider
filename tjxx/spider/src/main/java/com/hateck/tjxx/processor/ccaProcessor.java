package com.hateck.tjxx.processor;

import com.hateck.tjxx.login.CcaLogin;
import com.hateck.tjxx.model.Cca;
import com.hateck.tjxx.pipeline.TousuDaoPipeline;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by apple on 15/5/21.
 */
public class ccaProcessor implements PageProcessor {
    private Site site = Site.me().setTimeOut(60000).setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").addHeader("Host", "ts.cca.org.cn").addHeader("Cookie", "JSESSIONID=3CF739C518542B6CD5D85C4760403209.tomcat0").addHeader("Connection", "keep-alive").addHeader("Cache-Control", "max-age=0").addHeader("Accept-Language", "zh-CN,zh;q=0.8").addHeader("Accept-Encoding", "gzip, deflate, sdch").addHeader("Accept-Encoding", "gzip, deflate, sdch").addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8").setRetryTimes(3).setSleepTime(1000);

    public void process(Page page) {
        page.putField("登记编号", page.getHtml().xpath("//*[@id=\"regino\"]/a/text()").toString());
        page.putField("登记人", page.getHtml().xpath("//*[@id=\"dw\"]/div[2]/div[2]/table/tbody/tr[2]/td[2]/text()").toString());
        page.putField("信息来源", page.getHtml().xpath("//*[@id=\"dw\"]/div[2]/div[2]/table/tbody/tr[2]/td[4]/text()").toString());

        page.putField("接收方式", page.getHtml().xpath("//*[@id=\"dw\"]/div[2]/div[2]/table/tbody/tr[3]/td[2]/text()").toString());
        page.putField("登记时间", page.getHtml().xpath("//*[@id=\"dw\"]/div[2]/div[2]/table/tbody/tr[3]/td[4]/text()").toString());

        page.putField("姓名", page.getHtml().xpath("//*[@id=\"dw\"]/div[4]/div[2]/table/tbody/tr[1]/td[2]/text()").toString());
        page.putField("身份证", page.getHtml().xpath("//*[@id=\"dw\"]/div[4]/div[2]/table/tbody/tr[1]/td[4]/text()").toString());

        page.putField("性别", page.getHtml().xpath("//*[@id=\"dw\"]/div[4]/div[2]/table/tbody/tr[2]/td[2]/text()").toString());
        page.putField("年龄", page.getHtml().xpath("//*[@id=\"dw\"]/div[4]/div[2]/table/tbody/tr[2]/td[4]/text()").toString());

        page.putField("电话", page.getHtml().xpath("//*[@id=\"dw\"]/div[4]/div[2]/table/tbody/tr[3]/td[2]/text()").toString());
        page.putField("邮编", page.getHtml().xpath("//*[@id=\"dw\"]/div[4]/div[2]/table/tbody/tr[3]/td[4]/text()").toString());
        page.putField("地址", page.getHtml().xpath("//*[@id=\"dw\"]/div[4]/div[2]/table/tbody/tr[4]/td[2]/text()").toString());

        page.putField("经营者名称", page.getHtml().xpath("//*[@id=\"dw\"]/div[6]/div[2]/table/tbody/tr[1]/td[2]/text()").toString());
        page.putField("经营者类别", page.getHtml().xpath("//*[@id=\"dw\"]/div[6]/div[2]/table/tbody/tr[1]/td[4]/text()").toString());
        page.putField("联系人", page.getHtml().xpath("//*[@id=\"dw\"]/div[6]/div[2]/table/tbody/tr[2]/td[2]/text()").toString());
        page.putField("联系电话", page.getHtml().xpath("//*[@id=\"dw\"]/div[6]/div[2]/table/tbody/tr[2]/td[4]/text()").toString());
        page.putField("经营者地址", page.getHtml().xpath("//*[@id=\"dw\"]/div[6]/div[2]/table/tbody/tr[3]/td[2]/text()").toString());


        page.putField("涉案金额", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[1]/td[2]/text()").toString());
        page.putField("品牌名称", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[1]/td[4]/text()").toString());
        page.putField("商品类型", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[2]/td[2]/text()").toString());
        page.putField("投诉性质", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[2]/td[4]/text()").toString());

        page.putField("具体问题", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[3]/td[2]/text()").toString());

        page.putField("投诉诉求", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[4]/td[2]/text()").toString());
        page.putField("消费日期", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[5]/td[2]/text()").toString());
        page.putField("受损日期", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[5]/td[4]/text()").toString());

        page.putField("调解结果", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[4]/td[2]/text()").toString());
        page.putField("侵权类型", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[5]/td[2]/text()").toString());
        page.putField("未履行义务", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[5]/td[4]/text()").toString());
        page.putField("欺诈标志", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[4]/td[2]/text()").toString());
        page.putField("挽回损失", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[5]/td[2]/text()").toString());
        page.putField("增加赔偿", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[5]/td[4]/text()").toString());
        page.putField("信函表扬", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[4]/td[2]/text()").toString());
        page.putField("媒体曝光", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[5]/td[2]/text()").toString());
        page.putField("调解说明", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[5]/td[4]/text()").toString());
        page.putField("调解次数", page.getHtml().xpath("//*[@id=\"dw\"]/div[14]/div[2]/table/tbody/tr[6]/td[2]/text()").toString());
        page.putField("结案日期", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[5]/td[2]/text()").toString());
        page.putField("调解人", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[5]/td[4]/text()").toString());
        page.putField("调解方式", page.getHtml().xpath("//*[@id=\"dw\"]/div[8]/div[2]/table/tbody/tr[5]/td[4]/text()").toString());

        if (page.getResultItems().get("登记人")==null){
            page.setSkip(true);
        }

        List<String> links = page.getHtml().xpath("//*[@id=\"data\"]").links().all();

        page.addTargetRequests(links);
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        DruidPlugin druid = new DruidPlugin("jdbc:mysql://127.0.0.1/unidata?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull", "root", "tjhateck");
        druid.start();

        ActiveRecordPlugin arp = new ActiveRecordPlugin(druid);
        arp.addMapping("cca", Cca.class);
        arp.start();
        CcaLogin.login();
        Spider.create(new ccaProcessor()).addPipeline(new TousuDaoPipeline()).addUrl("http://ts.cca.org.cn/queryRegister.do?method=queryAppeal&curPage=1").thread(50).run();
    }
}
