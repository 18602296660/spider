package com.hateck.spider.jobs;

import com.alibaba.fastjson.JSON;
import com.hateck.spider.models.Api;
import com.hateck.spider.models.Information;
import com.hateck.spider.models.Keyword;
import com.hateck.spider.topsy.beans.info;
import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Security;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by apple on 15/4/21.
 */
public class SearchJob {
    public boolean search(int state) {
        List<Keyword> keywords = Keyword.dao.find("select * from keyword where state = ? order by id desc", state);
        try {
            Logger.getLogger(this.getClass()).info("keyword count :" + keywords.size());
            int count=0;
            for (Keyword keyword : keywords) {
                Logger.getLogger(this.getClass()).info("keyword:"+keyword.getStr("keyword")+"-->start");
                Api api = Api.dao.findFirst("select * from api where name = ?", "topsy");
                String apiUrl = api.getStr("url");
                String ukey = api.getStr("ukey");
                HashMap<String, String> querys = new HashMap<String, String>();
                querys.put("apikey", ukey);
                querys.put("perpage", api.getStr("perpage"));
                querys.put("sort", "date");
                querys.put("allow_lang", "zh");
                querys.put("window", "w");
                querys.put("q", keyword.getStr("keyword"));

                String jsonStr = HttpKit.get(apiUrl, querys);
                //Logger.getLogger(this.getClass()).info(jsonStr);
                Pattern p = Pattern.compile("\\{(.*)\\}");

                Matcher m = p.matcher(jsonStr);

                if (m.find()) {
                    String json = m.group(0);

                    info group = JSON.parseObject(json, info.class);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    List<Record> infos = new ArrayList<Record>();
                    for (int i = 0; i < group.getResponse().getList().size(); i++) {
                        Record info = new Record();
                        info.set("title", group.getResponse().getList().get(i).getTitle());
                        info.set("content", group.getResponse().getList().get(i).getContent());
                        info.set("author", group.getResponse().getList().get(i).getTrackback_author_name());
                        info.set("authorImg", group.getResponse().getList().get(i).getTopsy_author_img());
                        info.set("url", group.getResponse().getList().get(i).getUrl());
                        info.set("type", group.getResponse().getList().get(i).getMytype());
                        info.set("stime", sdf.format(new Date(1000L * group.getResponse().getList().get(i).getFirstpost_date())));
                        info.set("itime", new Timestamp(System.currentTimeMillis()));
                        info.set("customerId", keyword.getInt("customerId"));
                        info.set("realmId", keyword.getInt("realmId"));
                        info.set("keyId", keyword.getInt("id"));
                        info.set("state", keyword.getInt("state"));
                        infos.add(info);
                        if(Information.dao.find("select * from information where title = ? and url = ?",info.get("title"), info.get("url")).size()==0) {
                            count++;
                            Logger.getLogger(this.getClass()).info("title:"+info.get("title"));
                            Logger.getLogger(this.getClass()).info("url:"+info.get("url"));
                            Db.save("information", info);
                            Logger.getLogger(this.getClass()).info("this time add"+count+"!");
                        }
                    }
                    //Db.batch("insert into information(title, content, author, authorImg, url, type, stime, customerId, realmId, itime) values(?,?,?,?,?,?,?,?,?,?)", "title, content, author, authorImg, url, type, stime, customerId, realmId, itime", infos, 100);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void parseRss() {
        //Api api = Api.dao.findFirst("select * from api where ukey = ?", "rss");
        //Logger.getLogger(this.getClass()).info("keyword:"+api.getStr("name")+"-->start");
        //String rss = "http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss&sub=0]http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss&sub=0";
        //String rss = api.getStr("url");
            String rss = "https://freeweibo.com/weibo/%E5%85%AD%E5%9B%9B?latest&rss";
        try {
            //File file = new File(rss);

            URL url = new URL(rss);
            url.openConnection();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.connect();

            SSLContext.getInstance("TLSv1");


            System.out.print(connection.getResponseCode());
            System.out.println(url.getProtocol());
            // 读取Rss源
            XmlReader reader = new XmlReader(url);
            System.out.println("Rss源的编码格式为：" + reader.getEncoding());
            SyndFeedInput input = new SyndFeedInput();
            // 得到SyndFeed对象，即得到Rss源里的所有信息
            SyndFeed feed = input.build(reader);
            //System.out.println(feed);
            // 得到Rss新闻中子项列表
            List entries = feed.getEntries();
            // 循环得到每个子项信息
            for (int i = 0; i < entries.size(); i++) {
                SyndEntry entry = (SyndEntry) entries.get(i);
                // 标题、连接地址、标题简介、时间是一个Rss源项最基本的组成部分
                System.out.println("标题：" + entry.getTitle());
                System.out.println("连接地址：" + entry.getLink());
                SyndContent description = entry.getDescription();
                System.out.println("标题简介：" + description.getValue());
                System.out.println("发布时间：" + entry.getPublishedDate());
                // 以下是Rss源可先的几个部分
                System.out.println("标题的作者：" + entry.getAuthor());
                // 此标题所属的范畴
                List categoryList = entry.getCategories();
                if (categoryList != null) {
                    for (int m = 0; m < categoryList.size(); m++) {
                        SyndCategory category = (SyndCategory) categoryList.get(m);
                        System.out.println("此标题所属的范畴：" + category.getName());
                    }
                }
                // 得到流媒体播放文件的信息列表
                List enclosureList = entry.getEnclosures();
                if (enclosureList != null) {
                    for (int n = 0; n < enclosureList.size(); n++) {
                        SyndEnclosure enclosure = (SyndEnclosure) enclosureList.get(n);
                        System.out.println("流媒体播放文件：" + entry.getEnclosures());
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //SearchJob test = new SearchJob();
        //test.parseRss();

        // 创建SSLContext对象，并使用我们指定的信任管理器初始化
//
       // System.setProperty("java.protocol.handler.pkgs",
                //"com.sun.net.ssl.internal.www.protocol");
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        TrustManager[] tm = {
                new TrustManager() {
                }
        };
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        // 创建URL对象
        URL myURL = new URL("https://freeweibo.com/weibo/%E5%85%AD%E5%9B%9B?latest&rss");
        // 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
        HttpsURLConnection httpsConn = (HttpsURLConnection) myURL.openConnection();
        httpsConn.setSSLSocketFactory(ssf);
        // 取得该连接的输入流，以读取响应内容
        InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream());
        // 读取服务器的响应内容并显示
        int respInt = insr.read();
        while (respInt != -1) {
            System.out.print((char) respInt);
            respInt = insr.read();
        }
    }
}
