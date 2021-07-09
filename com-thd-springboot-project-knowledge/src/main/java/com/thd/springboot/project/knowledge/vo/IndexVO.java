package com.thd.springboot.project.knowledge.vo;

/**
 * com.thd.springboot.project.knowledge.vo.IndexVO
 *
 * @author: wanglei62
 * @DATE: 2021/7/9 11:07
 **/
public class IndexVO {
    private String path;
    private String classify;
    private String suffix;
    private Boolean reIndex;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Boolean getReIndex() {
        return reIndex;
    }

    public void setReIndex(Boolean reIndex) {
        this.reIndex = reIndex;
    }
}
