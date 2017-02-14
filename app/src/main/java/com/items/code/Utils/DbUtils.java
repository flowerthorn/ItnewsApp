package com.items.code.Utils;

import com.items.code.model.bean.data.LiteTable.CollectNews;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

/**
 * Created by lihongxin on 2017/2/14.
 */

public class DbUtils {
    //创建
    public static void createDatabase(){
        Connector.getDatabase();
    }
    //查询某条新闻是否存在
    public static boolean ifExitInCollect(String url) {
        Boolean flag=false;
        List<CollectNews> collectNews = DataSupport.select("news_url").where("news_url=?", url).find(CollectNews.class);
        for (CollectNews collectNews1 : collectNews) {

            if (collectNews1.getNews_url() != null) {
                //数据库中存在
               flag=true;
            }
        }
        return flag;
    }
}
