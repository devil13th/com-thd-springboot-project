package com.thd.springboot.project.knowledge.vo;

/**
 * com.thd.springboot.project.knowledge.vo.TecBean
 * 对应tec.js中的json数组
 * @author: wanglei62
 * @DATE: 2021/5/12 17:23
 **/
public class TecBean {
    private String title;
    private String type;
    private String mes;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
