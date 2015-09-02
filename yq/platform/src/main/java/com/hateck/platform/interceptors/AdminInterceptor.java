package com.hateck.platform.interceptors;

import com.hateck.platform.models.Admin;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class AdminInterceptor implements Interceptor {
	
	public void intercept(Invocation ai) {
		Controller controller = ai.getController();
	    Admin loginUser = controller.getSessionAttr("user");
	    if (loginUser != null){
	      ai.invoke();
	    }else{
	      controller.setAttr("msg", "您离开太久请重新登录!");
	      controller.render("/admin/index.html");
	    }
	}
}

