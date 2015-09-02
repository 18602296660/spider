package com.hateck.webserver.models;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by apple on 15/4/27.
 */
@TableBind(tableName = "server")
public class Server extends Model<Server> {
    public static final Server dao = new Server();
}
