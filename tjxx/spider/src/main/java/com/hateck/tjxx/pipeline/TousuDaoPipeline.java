package com.hateck.tjxx.pipeline;

import com.hateck.tjxx.model.Cca;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;


public class TousuDaoPipeline implements Pipeline {

    public void process(ResultItems resultItems, Task task) {
        Cca cca = new Cca();
        cca.set("id", resultItems.get("登记编号"));
        cca.set("dengjiren",resultItems.get("登记人"));
        cca.set("xinxilaiyuan",resultItems.get("信息来源"));
        cca.set("jieshoufangshi",resultItems.get("接收方式"));
        cca.set("dengjishijian",resultItems.get("登记时间")==null?new Date():resultItems.get("登记时间"));
        cca.set("xingming",resultItems.get("姓名"));
        cca.set("shenfenzheng",resultItems.get("身份证"));
        cca.set("xingbie",resultItems.get("性别"));
        cca.set("nianling",resultItems.get("年龄"));
        cca.set("lianxidianhuaxfz",resultItems.get("电话"));
        cca.set("youbian",resultItems.get("邮编"));
        cca.set("dizhixfz",resultItems.get("地址"));
        cca.set("jingyingzhemingcheng",resultItems.get("经营者名称"));
        cca.set("jingyingzheleibie",resultItems.get("经营者类别"));
        cca.set("lianxiren",resultItems.get("联系人"));
        cca.set("lianxidianhuajyz",resultItems.get("联系电话"));
        cca.set("dizhijyz",resultItems.get("经营者地址"));
        cca.set("sheanjine",resultItems.get("涉案金额"));
        cca.set("pinpaimingcheng",resultItems.get("品牌名称"));
        cca.set("shangpinleixing",resultItems.get("商品类型"));
        cca.set("tousuxingzhi",resultItems.get("投诉性质"));
        cca.set("jutiwenti",resultItems.get("具体问题"));
        cca.set("toususuqiu",resultItems.get("投诉诉求"));
        cca.set("xiaofeiriqi",resultItems.get("消费日期")==null?new Date():resultItems.get("消费日期"));
        cca.set("shousunriqi",resultItems.get("受损日期")==null?new Date():resultItems.get("受损日期"));
        cca.set("tiaojiejieguo", resultItems.get("调解结果"));
        cca.set("qinquanleixing",resultItems.get("侵权类型"));
        cca.set("weilvxingyiwu",resultItems.get("未履行义务"));
        cca.set("qizhabiaozhi",resultItems.get("欺诈标志"));
        cca.set("wanhuisunshi",resultItems.get("挽回损失"));
        cca.set("zengjiapeichang",resultItems.get("增加赔偿"));
        cca.set("xinhanbiaoyang",resultItems.get("信函表扬"));
        cca.set("meitibaoguang",resultItems.get("媒体曝光"));
        cca.set("tiaojieshuoming",resultItems.get("调解说明"));
        cca.set("tiaojiecishu",resultItems.get("调解次数"));
        cca.set("jieanriqi",resultItems.get("结案日期")==null?new Date():resultItems.get("结案日期"));
        cca.set("tiaojieren", resultItems.get("调解人"));
        cca.set("tiaojiefangshi",resultItems.get("调解方式"));
        if(cca.find("select id from cca where id = ?", cca.get("id")).size()==0) {
            cca.save();
        }
        System.out.println(resultItems.toString());
    }
}
