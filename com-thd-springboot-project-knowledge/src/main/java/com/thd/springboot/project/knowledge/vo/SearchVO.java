package com.thd.springboot.project.knowledge.vo;

/**
 * com.thd.springboot.project.knowledge.vo.SearchVO
 *
 * @author: wanglei62
 * @DATE: 2021/5/14 15:41
 **/
public class SearchVO extends DocVO {
    private String keyWords;
    private String classify;
    private String type;
    private Integer page = 1;
    private Integer pageSize = 10;

    @Override
    public String getClassify() {
        return classify;
    }

    @Override
    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
