package com.hateck.webserver.models;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by apple on 15/4/22.
 */
@TableBind(tableName = "keyword")
public class Keyword extends Model<Keyword> {
    public static final Keyword dao = new Keyword();
}
