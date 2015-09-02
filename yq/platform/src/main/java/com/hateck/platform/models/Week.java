package com.hateck.platform.models;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by apple on 15/5/29.
 */
@TableBind(tableName = "week")
public class Week extends Model<Week> {
    public static final Week dao = new Week();
}
