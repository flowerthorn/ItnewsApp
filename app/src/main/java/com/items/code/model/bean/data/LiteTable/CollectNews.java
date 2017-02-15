package com.items.code.model.bean.data.LiteTable;

import org.litepal.crud.DataSupport;

/**
 * Created by lihongxin on 2017/2/14.
 */

public class CollectNews extends DataSupport{

    private long id;//Litepal数据库自动生成的自增的ID
    private String news_title;
    private String news_url;
    private String news_image;


    public String getNews_image() {
        return news_image;
    }

    public void setNews_image(String news_image) {
        this.news_image = news_image;
    }

    public String getNews_url() {

        return news_url;
    }

    public void setNews_url(String news_url) {
        this.news_url = news_url;
    }

    public String getNews_title() {

        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
