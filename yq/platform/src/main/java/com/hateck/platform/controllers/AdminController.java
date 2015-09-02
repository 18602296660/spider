package com.hateck.platform.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hateck.platform.interceptors.AdminInterceptor;
import com.hateck.platform.models.Admin;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by apple on 15/5/27.
 */
@Before(AdminInterceptor.class)
@ControllerBind(viewPath = "admin", controllerKey = "/admin")
public class AdminController extends Controller{
    @Clear
    public void index(){
        redirect("/admin/index.html#/access/signin");
    }

    @Clear
    public void login() {


        String myRs= "{'params':" + getPara("admin") + "}";
        System.out.println(myRs);
        JSONObject obj = JSON.parseObject(myRs);
        // Object[] items=null;
        JSONArray items;
        Admin user = getModel(Admin.class);

        System.out.println("==="+user.getStr("name"));
        items = obj.getJSONArray("params");

        for (int idx= 0; idx < items.size(); idx++) {
            String name = items.getJSONObject(idx).getString("name");
            String pwd = items.getJSONObject(idx).getString("pwd");
            System.out.println(name);
            System.out.println(pwd);
        }

        //Admin.dao.findFirst("select * from users where username = ? and password = md5(?)", getPara("username"), getPara("password"));

        if (user != null) {
            setAttr("admin", user);
            renderJson("admin", user);
        } else {
            setAttr("msg", "用户名密码错误,请重新输入！");
            render("/admin/index.html");
        }
    }

}
