package com.hateck.topsyapi.topsy.beans;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Map;

/**
 * Created by apple on 15/4/10.
 */
public class list {
    @JSONField(serialize = false)
    private int hits;
    @JSONField(serialize = false)
    private int firstpost_date;
    @JSONField(serialize = true)
    private String title;
    @JSONField(serialize = true)
    private String url;
    @JSONField(serialize = false)
    private String topsy_author_img;
    @JSONField(serialize = false)
    private String trackback_author_url;
    @JSONField(serialize = false)
    private List<Map> url_expansions;
    @JSONField(serialize = false)
    private String trackback_author_name;
    @JSONField(serialize = false)
    private String content;
    @JSONField(serialize = false)
    private int trackback_total;
    @JSONField(serialize = false)
    private long score;
    @JSONField(serialize = false)
    private String trackback_author_nick;



    public String getSmall_thumbnail() {
        return small_thumbnail;
    }

    public void setSmall_thumbnail(String small_thumbnail) {
        this.small_thumbnail = small_thumbnail;
    }

    private String small_thumbnail;

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getFirstpost_date() {
        return firstpost_date;
    }

    public void setFirstpost_date(int firstpost_date) {
        this.firstpost_date = firstpost_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }



    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopsy_author_img() {
        return topsy_author_img;
    }

    public void setTopsy_author_img(String topsy_author_img) {
        this.topsy_author_img = topsy_author_img;
    }

    public String getTrackback_author_url() {
        return trackback_author_url;
    }

    public void setTrackback_author_url(String trackback_author_url) {
        this.trackback_author_url = trackback_author_url;
    }

    public List<Map> getUrl_expansions() {
        return url_expansions;
    }

    public void setUrl_expansions(List<Map> url_expansions) {
        this.url_expansions = url_expansions;
    }

    public String getTrackback_author_name() {
        return trackback_author_name;
    }

    public void setTrackback_author_name(String trackback_author_name) {
        this.trackback_author_name = trackback_author_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTrackback_total() {
        return trackback_total;
    }

    public void setTrackback_total(int trackback_total) {
        this.trackback_total = trackback_total;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getTrackback_author_nick() {
        return trackback_author_nick;
    }

    public void setTrackback_author_nick(String trackback_author_nick) {
        this.trackback_author_nick = trackback_author_nick;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getMytype() {
        return mytype;
    }

    public void setMytype(String mytype) {
        this.mytype = mytype;
    }

    public String getTrackback_permalink() {
        return trackback_permalink;
    }

    public void setTrackback_permalink(String trackback_permalink) {
        this.trackback_permalink = trackback_permalink;
    }

    @JSONField(serialize = false)
    private String highlight;
    @JSONField(serialize = false)
    private String mytype;
    @JSONField(serialize = false)
    private String trackback_permalink;
}
