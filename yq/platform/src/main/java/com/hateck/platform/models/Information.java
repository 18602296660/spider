package com.hateck.platform.models;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by apple on 15/5/29.
 */
@TableBind(tableName = "information")
public class Information extends Model<Information> {
    public static final Information dao = new Information();
}
