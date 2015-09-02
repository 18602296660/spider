package com.hateck.webserver.models;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by apple on 15/4/22.
 */
@TableBind(tableName = "admin")
public class Admin extends Model<Admin> {
    public static final Admin dao = new Admin();
}
