package com.hateck.topsyapi.topsy.beans;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by apple on 15/4/10.
 */
public class Parameters {
    private String allow_lang;
    private String perpage;
    private String langth;
    private String call_timestamp;
    private String q;
    private String callback;
    private String window;
    private String offset;
    @JSONField(serialize = false)
    //private String _;
    private String sort_method;

    public String getAllow_lang() {
        return allow_lang;
    }

    public void setAllow_lang(String allow_lang) {
        this.allow_lang = allow_lang;
    }

    public String getPerpage() {
        return perpage;
    }

    public void setPerpage(String perpage) {
        this.perpage = perpage;
    }

    public String getLangth() {
        return langth;
    }

    public void setLangth(String langth) {
        this.langth = langth;
    }

    public String getCall_timestamp() {
        return call_timestamp;
    }

    public void setCall_timestamp(String call_timestamp) {
        this.call_timestamp = call_timestamp;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    /*public String get_() {
        return _;
    }

    public void set_(String _) {
        this._ = _;
    }*/

    public String getSort_method() {
        return sort_method;
    }

    public void setSort_method(String sort_method) {
        this.sort_method = sort_method;
    }
}
