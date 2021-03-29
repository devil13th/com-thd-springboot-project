package com.thd.processmanagementserver.entity;

import java.util.Map;

/**
 * com.thd.processmanagementserver.entity.SearchCommonBean
 *
 * @author: wanglei62
 * @DATE: 2021/3/2 13:54
 **/
public class ProcCommonBean extends MyPage {
    private String keyWords;
    private Map processVariable;

    public Map getProcessVariable() {
        return processVariable;
    }

    public void setProcessVariable(Map processVariable) {
        this.processVariable = processVariable;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
}
