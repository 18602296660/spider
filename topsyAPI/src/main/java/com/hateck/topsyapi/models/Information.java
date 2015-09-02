package com.hateck.topsyapi.models;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by apple on 15/4/21.
 */
@TableBind(tableName="information")
public class Information extends Model<Information> {
    public static final Information dao = new Information();
}
