package com.hateck.topsyapi.topsy.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 15/4/10.
 */
public class info {
    public request getRequest() {
        return request;
    }

    public void setRequest(request request) {
        this.request = request;
    }

    private response response;

    public response getResponse() {
        return response;
    }

    public void setResponse(response response) {
        this.response = response;
    }

    private request request;

    public List<list> getLists() {
        return lists;
    }

    public void setLists(List<list> lists) {
        this.lists = lists;
    }

    private List<list> lists = new ArrayList<list>();


}
