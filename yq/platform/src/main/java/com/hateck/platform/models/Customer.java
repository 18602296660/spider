package com.hateck.platform.models;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by apple on 15/5/27.
 */
@TableBind(tableName = "customer")
public class Customer extends Model<Customer>{
    public static final Customer dao = new Customer();
}

