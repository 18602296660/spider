package com.hateck.webserver.controllers;

import com.hateck.webserver.interceptors.AdminInterceptor;
import com.hateck.webserver.models.Admin;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by apple on 15/4/22.
 */
@Before(AdminInterceptor.class)
@ControllerBind(viewPath = "admin", controllerKey = "/admin")
public class AdminController extends Controller {
    @ClearInterceptor
    public void index(){

    }

    @ClearInterceptor
    public void login(){
        Admin user =  Admin.dao.findFirst("select * from users where username = ? and password = md5(?)", getPara("username"), getPara("password"));

        if(user!=null){
            setSessionAttr("user", user);
            setAttr("user", user);
            render("main.html");
        }else{
            setAttr("msg", "用户名密码错误,请重新输入！");
            render("/admin/index.html");
        }
    }
}
