package com.hateck.spider.commons;

import java.util.HashMap;
import java.util.Map;
//对接口进行测试
public class TestMain {
    private String url = "https://freeweibo.com/weibo/%E5%85%AD%E5%9B%9B?latest&rss";
    private String charset = "utf-8";
    private HttpClientUtil httpClientUtil = null;

    public TestMain(){
        httpClientUtil = new HttpClientUtil();
    }

    public void test(){
        String httpOrgCreateTest = url;
        Map<String,String> createMap = new HashMap<String,String>();

        String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,createMap,charset);
        System.out.println("result:"+httpOrgCreateTestRtn);
    }

    public static void main(String[] args){
        TestMain main = new TestMain();
        main.test();
    }
}