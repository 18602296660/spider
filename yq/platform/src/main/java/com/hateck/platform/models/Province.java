package com.hateck.platform.models;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by apple on 15/5/29.
 */
@TableBind(tableName = "province")
public class Province extends Model<Province> {
    public static final Province dao = new Province();
}
