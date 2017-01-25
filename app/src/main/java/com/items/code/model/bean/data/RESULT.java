package com.items.code.model.bean.data;

import java.util.ArrayList;

/**
 * Created by lihongxin on 2016/11/30.
 */

public class RESULT {
    private String start;
    private ArrayList<dataInfo> data;
    public ArrayList<dataInfo> getData() {
        return data;
    }

    public void setData(ArrayList<dataInfo> data) {
        this.data = data;
    }

    public String getStart() {

        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

}
