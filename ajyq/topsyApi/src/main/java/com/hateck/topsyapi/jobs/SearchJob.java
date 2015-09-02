package com.hateck.topsyapi.jobs;

import com.alibaba.fastjson.JSON;
import com.hateck.topsyapi.models.Api;
import com.hateck.topsyapi.models.Information;
import com.hateck.topsyapi.models.Keyword;
import com.hateck.topsyapi.topsy.beans.info;
import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.apache.log4j.Logger;

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
}
