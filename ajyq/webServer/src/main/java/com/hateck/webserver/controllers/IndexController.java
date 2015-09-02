package com.hateck.webserver.controllers;

import com.hateck.webserver.models.Information;
import com.hateck.webserver.models.Keyword;
import com.hateck.webserver.models.Realm;
import com.hateck.webserver.services.EChartsService;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by apple on 15/4/22.
 */
@ControllerBind(viewPath = "client", controllerKey = "/")
public class IndexController extends Controller {
    public void index() {

    }

    /**
     * 取10条信息标题
     */
    public void ajaxhot() {
        List<Information> top = Information.dao.find("select * from information order by id desc limit 0, 8");

        //System.out.println( JsonKit.toJson(top));
        renderJson(top);
    }

    /**
     * 取本年度所有信息数量以关键字为条件
     * eventriver
     */
    public void ajaxkeyword() {
        //System.out.println(EChartsService.getInstance().getEventRiver().toString());
        renderText(EChartsService.getInstance().getEventRiver().toString());
    }

    /**
     * 当前月前半年的信息数量以领域为条件
     * radar
     */
    public void ajaxrealm() {
        //System.out.println(EChartsService.getInstance().getRealm().toString());
        renderText(EChartsService.getInstance().getRealm().toString());
    }

    /**
     * 24小时内的采集数量，及信息类型  tweet link un image
     * bar
     */
    public void ajaxsearchcount() {
        //System.out.println(EChartsService.getInstance().getCountFor24().toString());
        renderText(EChartsService.getInstance().getCountFor24().toString());
    }

    /**
     * 获取采集服务器状态
     * map
     */
    public void ajaxserver() {
        renderText(EChartsService.getInstance().getServer().toString());
    }

    public void ajaxinfos() {

        String rid = getPara(1);
        String keys = getPara(0);
        //System.out.println(rid);
        //System.out.println(keys);
        List<Information> infos;
        if(keys.equals("0") && rid.equals("0")) {
            infos = Information.dao.find("select * from information as info where TO_DAYS(itime) = TO_DAYS(now()) order by itime desc");
        }else{
            //System.out.println("ok");
            try {
                keys = URLDecoder.decode(keys, "utf8");
                //System.out.println(keys);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            int kid = 0;
            if(Keyword.dao.find("select * from keyword where keyword = ?", keys).size()==0) {
                Keyword keyword = new Keyword().set("keyword", keys).set("realmId", rid).set("customerId", 1).set("state", 1);
                keyword.save();
                kid = keyword.getInt("id");
            }else {
                Keyword keyword = Keyword.dao.findFirst("select * from keyword where keyword = ?", keys);
                kid = keyword.getInt("id");
            }
            infos = Information.dao.find("select * from information as info where keyId = ? and realmId = ? and TO_DAYS(itime) = TO_DAYS(now())  order by itime desc",kid , rid);
        }
        //System.out.println(JsonKit.toJson(infos));
        renderJson(infos);
    }

    public void ajaxgetrealm () {
        List<Realm> realms = Realm.dao.find("select * from realm order by id");
        renderJson(realms);
    }


}
