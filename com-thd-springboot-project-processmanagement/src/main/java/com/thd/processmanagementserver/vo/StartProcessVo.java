package com.thd.processmanagementserver.vo;

import java.util.Map;

/**
 * com.thd.processmanagementserver.vo.StartProcessVo
 *
 * @author: wanglei62
 * @DATE: 2021/1/27 10:27
 **/
public class StartProcessVo {
    private String processKey;
    private String businessKey;
    private Map<String,Object> processVar;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }

    public Map<String, Object> getProcessVar() {
        return processVar;
    }

    public void setProcessVar(Map<String, Object> processVar) {
        this.processVar = processVar;
    }
}
