package com.items.code.model.bean.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lihongxin on 2016/12/11.
 */

public class InterestdataInfo implements Serializable {

    private List<String> titlelist;
    private List<String> urllist;
    private List<String> srclist;

    public List<String> getTitlelist() {
        return titlelist;
    }

    public void setTitlelist(List<String> titlelist) {
        this.titlelist = titlelist;
    }

    public List<String> getUrllist() {
        return urllist;
    }

    public List<String> getSrclist() {
        return srclist;
    }

    public void setSrclist(List<String> srclist) {
        this.srclist = srclist;
    }

    public void setUrllist(List<String> urllist) {
        this.urllist = urllist;
    }
}
