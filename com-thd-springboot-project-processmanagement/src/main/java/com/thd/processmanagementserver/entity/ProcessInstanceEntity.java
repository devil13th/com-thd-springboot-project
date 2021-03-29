package com.thd.processmanagementserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Map;

public class ProcessInstanceEntity extends ProcDefEntity {
    private String processInstanceId;
    private String businessKey;
    @JsonFormat( pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date processInstanceStartTime;
    @JsonFormat( pattern="yyyy-MM-dd  hh:mm:ss",timezone = "GMT+8")
    private Date processInstanceEndTime;

    private Map<String,Object> processInstanceVar;


    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Date getProcessInstanceStartTime() {
        return processInstanceStartTime;
    }

    public void setProcessInstanceStartTime(Date processInstanceStartTime) {
        this.processInstanceStartTime = processInstanceStartTime;
    }

    public Date getProcessInstanceEndTime() {
        return processInstanceEndTime;
    }

    public void setProcessInstanceEndTime(Date processInstanceEndTime) {
        this.processInstanceEndTime = processInstanceEndTime;
    }

    public Map<String, Object> getProcessInstanceVar() {
        return processInstanceVar;
    }

    public void setProcessInstanceVar(Map<String, Object> processInstanceVar) {
        this.processInstanceVar = processInstanceVar;
    }
}
