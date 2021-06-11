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
}
