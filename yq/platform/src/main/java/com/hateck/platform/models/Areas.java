package com.hateck.platform.models;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by apple on 15/5/29.
 */
@TableBind(tableName = "areas")
public class Areas extends Model<Areas> {
    public static final Areas dao = new Areas();
}
