package com.hateck.webserver.services;

import com.github.abel533.echarts.LabelLine;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Polar;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.Axis;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.TimeAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.data.MapData;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.*;
import com.github.abel533.echarts.series.event.Detail;
import com.github.abel533.echarts.series.event.Event;
import com.github.abel533.echarts.series.event.Evolution;
import com.github.abel533.echarts.style.ItemStyle;
import com.hateck.webserver.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by apple on 15/4/23.
 */
public class EChartsService {
    private EChartsService() {
    }

    private static final EChartsService single = new EChartsService();

    public static EChartsService getInstance() {
        return single;
    }

    public GsonOption getHot() {
        GsonOption option = new GsonOption();
        Tooltip tooltip = new Tooltip();
        tooltip.show(true);
        option.setTooltip(tooltip);
        Legend legend = new Legend();
        List list = new ArrayList<>();
        list.add("销量");
        legend.data(list);
        option.setLegend(legend);

        List<Axis> axises = new ArrayList<>();

        Axis axis = new CategoryAxis();
        axis.type(AxisType.category);
        axis.data("集会", "游行", "暴动", "周永康", "高瑜", "毕福剑");
        axises.add(axis);

        option.setxAxis(axises);

        List<Axis> axises1 = new ArrayList<>();
        Axis axis1 = new TimeAxis();
        axis1.type(AxisType.value);
        axises1.add(axis1);
        option.setyAxis(axises1);

        Series series = new Bar();
        series.name("数量");
        series.type(SeriesType.bar);

        series.data(5, 20, 40, 10, new java.util.Random().nextInt(50), 20);
        option.series(series);
        return option;
    }



    public GsonOption getEventRiver1() {
        GsonOption option = new GsonOption();

        /*Title title = new Title();
        title.text("热点事件走势图");
        option.setTitle(title);

        Tooltip tooltip = new Tooltip();
        tooltip.trigger(Trigger.item);
        tooltip.enterable(Boolean.TRUE);
        option.setTooltip(tooltip);

        Legend legend = new Legend();
        List list = new ArrayList<>();
        list.add("数量");
        legend.data(list);
        option.setLegend(legend);

        Toolbox toolbox = new Toolbox();
        toolbox.show(Boolean.TRUE);

        Feature feature = new Feature();
        toolbox.feature(feature.mark.show(Boolean.TRUE), feature.restore.show(Boolean.TRUE), feature.saveAsImage.show(Boolean.TRUE));
        option.setToolbox(toolbox);*/

        option.title().text("热点事件走势图");
        option.tooltip().trigger(Trigger.item);
        option.tooltip().enterable(true);
        option.legend("财经事件", "政治事件");
        option.toolbox().show(true).feature(Tool.mark, Tool.restore, Tool.saveAsImage);
        option.xAxis(new TimeAxis().boundaryGap(0.05, 0.1));
        option.toolbox().show(true).feature(Tool.mark, Tool.restore, Tool.saveAsImage);

        List<Axis> axises = new ArrayList<>();
        TimeAxis timeAxis = new TimeAxis();
        timeAxis.type(AxisType.time);
        timeAxis.boundaryGap(0.05, 0.1);
        axises.add(timeAxis);
        option.setxAxis(axises);

        EventRiver eventRiver = new EventRiver("财经事件", 123);
        EventRiver eventRiver1 = new EventRiver("财经事件", 123);
        Event event = new Event();
        Evolution evolution = new Evolution();
        Detail detail = new Detail();
        detail.link("http://www.baidu.com");
        detail.text("百度指数");
        detail.img("http://echarts.baidu.com/doc/asset/ico/favicon.png");
        evolution.detail(detail);

        evolution.time("2015-04-24");
        evolution.value(20);
        event.name("aaa");
        event.weight(123);
        event.evolution(evolution);

        Event event1 = new Event();
        Evolution evolution1 = new Evolution();
        Detail detail1 = new Detail();
        detail1.link("http://www.baidu.com");
        detail1.text("百度指数1");
        detail1.img("http://echarts.baidu.com/doc/asset/ico/favicon.png");
        evolution1.detail(detail1);

        evolution1.time("2015-04-27");
        evolution1.value(50);
        event1.name("bbb");
        event1.weight(123);
        event1.evolution(evolution1);

        Event event2 = new Event();
        Evolution evolution2 = new Evolution();
        Detail detail2 = new Detail();
        detail2.link("http://www.baidu.com");
        detail2.text("百度指数1");
        detail2.img("http://echarts.baidu.com/doc/asset/ico/favicon.png");
        evolution2.detail(detail2);

        evolution2.time("2015-05-29");
        evolution2.value(130);
        event2.name("bbb");
        event2.weight(123);
        event2.evolution(evolution2);


        eventRiver.data(event);
        eventRiver1.data(event1, event2);
        option.series(eventRiver, eventRiver1);
        return option;
    }

    public GsonOption getRealm() {
        GsonOption option = new GsonOption();
        option.tooltip().trigger(Trigger.axis);

        option.legend().orient(Orient.vertical);
        option.legend().x(X.right);
        option.legend().y(Y.bottom);

        List data = new ArrayList();
        List<Realm> realms = Realm.dao.find("select * from realm");

        List<Polar> polars = new ArrayList<Polar>();
        List indicators = new ArrayList<>();

        String sql = "select count(bb.id) as c from week as aa " +
                "left join information as bb on DATE_FORMAT(bb.itime,'%w') = aa.week " +
                "and realmId=? " +
                "and bb.itime between subdate(curdate(),date_format(curdate(),'%w')-1) and subdate(curdate(),date_format(curdate(),'%w')-8) " +
                "group by aa.week order by aa.id";

        long max = Information.dao.findFirst("select count(bb.id) as c from week as aa " +
                "left join information as bb on DATE_FORMAT(bb.itime,'%w') = aa.week " +
                "and bb.itime between subdate(curdate(),date_format(curdate(),'%w')-1) and subdate(curdate(),date_format(curdate(),'%w')-8) \n" +
                "group by aa.id , bb.realmId order by c desc").getLong("c");

        List<Week> weeks = Week.dao.find("select weekCN from week");
        for (Week week : weeks) {
            HashMap hashMap = new HashMap();
            hashMap.put("text", week.getStr("weekCN"));
            hashMap.put("max", max);
            indicators.add(hashMap);
        }

        List<Data> datas = new ArrayList<>();

        for (Realm realm : realms) {
            String realmName = realm.getStr("name");
            data.add(realmName);
            List<Information> infos = Information.dao.find(sql, realm.getInt("id"));
            Data data1 = new Data();

            List v = new ArrayList<>();
            for (Information info : infos) {
                v.add(info.getLong("c"));
            }
            data1.name(realmName);
            data1.value(v);
            datas.add(data1);
        }

        option.legend().data(data);//添加legend 中所有领域

        Polar polar = new Polar();
        polar.indicator(indicators);
        polars.add(polar);
        option.setPolar(polars);

        Series series = new Radar();
        series.type(SeriesType.radar);

        series.setData(datas);
        option.series(series);

        return option;
    }

    public GsonOption getCountFor24() {
        GsonOption option = new GsonOption();
        option.tooltip().trigger(Trigger.axis);
        option.calculable(Boolean.TRUE);
        Axis axis = new CategoryAxis();
        axis.type(AxisType.category);
        List time = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            time.add(i + ":00");
        }
        axis.setData(time);

        option.xAxis(axis);
        Axis yaxis = new ValueAxis();
        yaxis.type(AxisType.value);
        yaxis.position(Position.right);
        option.yAxis(yaxis);

        option.legend().data("tweet", "链接", "图片", "视频", "其他");

        Bar series = new Bar();
        series.type(SeriesType.bar);
        //series.name("aa");
        List<Information> infos = Information.dao.find("select count(*) as c, hour(itime) as h, info.type from information as info where TO_DAYS(itime) = TO_DAYS(now()) GROUP BY hour(itime), info.type");

        Bar tweet = new Bar();
        tweet.name("tweet");
        tweet.type(SeriesType.bar);
        //tweet.tooltip().trigger(Trigger.item);
        tweet.stack("search");
        List tweetdata = new ArrayList<>();

        Bar link = new Bar();
        link.name("链接");
        link.type(SeriesType.bar);
        //link.tooltip().trigger(Trigger.item);
        link.stack("search");
        List linkdata = new ArrayList<>();

        Bar image = new Bar();
        image.name("图片");
        image.type(SeriesType.bar);
        //image.tooltip().trigger(Trigger.item);
        image.stack("search");
        List imagedata = new ArrayList<>();

        Bar video = new Bar();
        video.name("视频");
        video.type(SeriesType.bar);
        //video.tooltip().trigger(Trigger.item);
        video.stack("search");
        List videodata = new ArrayList<>();

        Bar other = new Bar();
        other.name("其他");
        other.type(SeriesType.bar);
        //other.tooltip().trigger(Trigger.item);
        other.stack("search");
        List otherdata = new ArrayList<>();

        for (int i = 0; i < 24; i++) {//24小时
            tweetdata.add(0);
            linkdata.add(0);
            imagedata.add(0);
            videodata.add(0);
            otherdata.add(0);

            for (Information info : infos) {//结果集
                if (info.getInt("h") == i) {
                    String type = info.getStr("type");
                    if ("tweet".equals(type)) {
                        tweetdata.set(i, info.getLong("c"));
                        continue;
                    } else if ("link".equals(type)) {
                        linkdata.set(i, info.getLong("c"));

                        continue;
                    } else if ("image".equals(type)) {
                        imagedata.set(i, info.getLong("c"));
                        continue;
                    } else if ("video".equals(type)) {
                        videodata.set(i, info.getLong("c"));
                        continue;
                    } else if ("unknown".equals(type)) {
                        otherdata.set(i, info.getLong("c"));
                        continue;
                    }
                }else{
                    continue;
                }
            }

        }
        tweet.setData(tweetdata);
        link.setData(linkdata);
        image.setData(imagedata);
        video.setData(videodata);
        other.setData(otherdata);

        Pie pie = new Pie();
        pie.type(SeriesType.pie);
        pie.tooltip().trigger(Trigger.item);
        pie.tooltip().formatter("{a} <br/>{b} : {c} ({d}%)");

        pie.center("15%", "15%");
        pie.radius(0, 60);

        ItemStyle itemStyle = new ItemStyle();

        LabelLine line = new LabelLine();
        line.length(5);
        itemStyle.normal().borderColor("#fff");
        itemStyle.normal().labelLine(line);
        pie.itemStyle(itemStyle);

        List list = new ArrayList<>();

        List<Information> infos1 = Information.dao.find("select count(*) as c, type from information as info where TO_DAYS(itime) = TO_DAYS(now()) GROUP BY type");

        for(Information info : infos1) {
            Data data = new Data();
            String type = info.getStr("type");
            String name;
            switch (type){
                case "tweet":
                    name = "tweet";
                    break;
                case "image":
                    name= "图片";
                    break;
                case "video":
                    name = "视频";
                    break;
                case "link":
                    name = "链接";
                    break;
                default:
                    name = "其他";
                    break;
            }
            data.name(name);
            data.value(info.getLong("c"));
            list.add(data);
        }

        pie.setData(list);

        //series.data(series);

        option.series(tweet,link,image,video,other,pie);
        return option;
    }

    public GsonOption getServer() {
        GsonOption option = new GsonOption();
        List<Server> servers = Server.dao.find("select * from server order by type");
        option.setBackgroundColor("#1b1b1b");
        //-------------title设置-------------//
        //option.title().text("采集点");
        //option.title().x(X.center);
        //option.title().textStyle().color("#fff");
        //-------------tooltip设置-------------//
        option.tooltip().trigger(Trigger.item);
        //-------------tooltip设置-------------//
        //option.legend().orient(Orient.vertical);
        //option.legend().x(X.left);

        option.legend().data("天津", "纽约", "洛杉矶", "法兰克福", "圣保罗", "悉尼", "新加坡", "东京", "俄罗斯");
        option.legend().selectedMode(SelectedMode.multiple);
        option.legend().textStyle().color("#fff");
        //-------------toolbox设置-------------//
        option.toolbox().show(Boolean.FALSE);

        option.dataRange().min(0);
        option.dataRange().max(100);
        option.dataRange().calculable(Boolean.TRUE);
        option.dataRange().color("#ff3333", "orange", "yellow", "lime", "aqua");
        option.dataRange().textStyle().color("#fff");

        List snames = new ArrayList<>();
        List<Series> maps = new ArrayList<Series>();
        for (Server server : servers) {
            Map series = new Map();
            snames.add(server.getStr("name"));
            if (server.getInt("type") == 0) {
                series.name(server.getStr("name"));
                series.type(SeriesType.map);
                series.hoverable(Boolean.FALSE);
                series.setMapType("world");

                ItemStyle itemStyle = new ItemStyle();
                itemStyle.normal().areaStyle().color("#1b1b1b");
                itemStyle.normal().borderWidth(1);
                itemStyle.normal().borderColor("rgba(100,149,237,1)");

                series.itemStyle(itemStyle);


                GeoCoord geoCoord = new GeoCoord();
                for (Server server1 : servers) {
                    geoCoord.put(server1.getStr("name"), server1.getStr("lng"), server1.getStr("lat"));
                    series.setGeoCoord(geoCoord);
                }

                series.markLine().smooth(Boolean.TRUE);
                series.markLine().effect().show(Boolean.TRUE);
                series.markLine().effect().scaleSize(1);
                series.markLine().data();

                series.markLine().itemStyle().normal().borderWidth(1);
                series.markLine().itemStyle().normal().label().show(Boolean.FALSE);
                series.markLine().itemStyle().normal().lineStyle().type(LineType.solid);
                series.markLine().itemStyle().normal().lineStyle().shadowBlur(10);
                series.data();
                maps.add(series);
            } else {
                series.name(server.getStr("name"));
                series.type(SeriesType.map);
                series.setMapType("world");
                series.data();


                series.markLine().smooth(Boolean.TRUE);
                series.markLine().effect().show(Boolean.TRUE);
                series.markLine().effect().scaleSize(1);
                //series.markLine().effect().color("#fff");
                series.markLine().itemStyle().normal().borderWidth(1);
                series.markLine().itemStyle().normal().label().show(Boolean.FALSE);
                series.markLine().itemStyle().normal().lineStyle().type(LineType.solid);
                series.markLine().itemStyle().normal().lineStyle().shadowBlur(10);
                //series.markLine().itemStyle().normal().lineStyle().color("green");
                List list = new ArrayList<>();
                List list1 = new ArrayList<>();
                series.markLine().smoothness(0.05);

                MapData mapData;
                MapData mapData1;
                if (server.getInt("state") < 0) {
                    mapData = new MapData("天津", 100);
                    mapData1 = new MapData(server.getStr("name"), 100);
                } else {
                    mapData = new MapData("天津", 20);
                    mapData1 = new MapData(server.getStr("name"), 20);
                }
                list.add(mapData);
                list.add(mapData1);
                list1.add(mapData1);
                list1.add(mapData);


                series.markLine().data(list, list1);
                maps.add(series);
            }
        }
        option.legend().setData(snames);


        option.setSeries(maps);

        return option;
    }

    public GsonOption getEventRiver() {
        GsonOption option = new GsonOption();

        option.tooltip().trigger(Trigger.item);
        option.tooltip().enterable(Boolean.FALSE);
        option.tooltip().formatter("{a}:{b}");
        List<Axis> axises = new ArrayList<>();
        TimeAxis axis = new TimeAxis();
        axis.type(AxisType.time);
        axis.boundaryGap(0.02, 0.1);
        axises.add(axis);
        option.xAxis(axises);

        ItemStyle itemStyle = new ItemStyle();
        itemStyle.normal().label().show(Boolean.FALSE);
        itemStyle.emphasis().label().show(Boolean.FALSE);

        List<Information> realmCount = Information.dao.find("select realmId as rid from information group by realmId");

        List<Series> series = new ArrayList<>();
        for (int i = 0; i < realmCount.size(); i++) {//遍历领域
            int rid = realmCount.get(i).getInt("rid");
            Realm realm = Realm.dao.findById(rid);

            EventRiver eventRiver = new EventRiver();//series

            List<Information> keyCount = Information.dao.find("select keyId as kid from information where realmId = ? group by keyId", rid);
            for (int j = 0; j < keyCount.size(); j++) {   //遍历关键字
                int kid = keyCount.get(j).getInt("kid");
                Event event = new Event();//data

                List<Information> infos = Information.dao.find("select count(stime) as c, DATE_FORMAT(stime,'%Y-%m-%d') as t from information where realmId = ? and keyId = ? GROUP BY DATE_FORMAT(stime,'%Y-%m-%d') ", rid, kid);
                for (Information info : infos) {
                    Evolution evolution = new Evolution();//
                    Detail detail = new Detail();
                    detail.text("");
                    detail.link("");
                    detail.img("");
                    evolution.detail(detail);
                    evolution.setValue(Integer.parseInt(String.valueOf(info.getLong("c"))));

                    evolution.setTime(info.getStr("t"));
                    event.evolution(evolution);
                }

                event.weight(j+10);
                event.name(Keyword.dao.findById(kid).getStr("keyword"));
                eventRiver.data(event);
                eventRiver.itemStyle(itemStyle);
                eventRiver.name(realm.getStr("name"));
                eventRiver.weight(j+100);

            }
            series.add(eventRiver);

        }

        option.series(series);

        return option;

       /* EventRiver eventRiver= new EventRiver();//series
        Event event = new Event();//data
        Evolution evolution = new Evolution();//
        Evolution evolution1 = new Evolution();//
        Detail detail = new Detail();
        detail.text("");
        detail.link("");
        detail.img("");
        evolution.detail(detail);
        evolution1.detail(detail);

        evolution.value(100);
        evolution.time("2015-4-28");
        event.evolution(evolution);

        evolution1.value(300);
        evolution1.time("2015-4-27");
        event.evolution(evolution1);

        event.name("aaaaaa");
        event.weight(1);

        eventRiver.weight(10);
        eventRiver.name("关键字");
        eventRiver.type(SeriesType.eventRiver);

        eventRiver.data(event);

        eventRiver.itemStyle(itemStyle);*/
    }
}
