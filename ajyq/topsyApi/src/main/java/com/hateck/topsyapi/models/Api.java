package com.hateck.topsyapi.models;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by apple on 15/4/22.
 */
@TableBind(tableName = "api")
public class Api extends Model<Api> {
    public static final Api dao = new Api();
}
