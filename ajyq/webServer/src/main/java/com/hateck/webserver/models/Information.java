package com.hateck.webserver.models;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by apple on 15/4/22.
 */
@TableBind(tableName = "information")
public class Information extends Model<Information> {
    public static final Information dao = new Information();
    public Realm getRealm(){
        return Realm.dao.findById(get("realmId"));
    }
    public Customer getCustomer(){
        return Customer.dao.findById(get("customerId"));
    }

}
