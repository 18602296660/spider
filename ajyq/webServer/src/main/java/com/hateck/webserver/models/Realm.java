package com.hateck.webserver.models;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * Created by apple on 15/4/22.
 */
@TableBind(tableName = "realm")
public class Realm extends Model<Realm> {
    public static final Realm dao = new Realm();

    public List<Information> getInfo() {
        return Information.dao.find("select * from news where realmId = ?", get("id"));
    }
}
