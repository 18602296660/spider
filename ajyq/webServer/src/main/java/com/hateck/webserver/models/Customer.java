package com.hateck.webserver.models;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * Created by apple on 15/4/22.
 */
@TableBind(tableName = "customer")
public class Customer extends Model<Customer> {
    public static final Customer dao = new Customer();
    public List<Information> getInfo() {
        return Information.dao.find("select * from news where customerId = ?", get("id"));
    }
}
