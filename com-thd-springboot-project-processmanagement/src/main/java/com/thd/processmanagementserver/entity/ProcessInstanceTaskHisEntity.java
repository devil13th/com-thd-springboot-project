package com.thd.processmanagementserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProcessInstanceTaskHisEntity extends ProcessInstanceTaskEntity {
    private String taskHisId;
    @JsonFormat( pattern="yyyy-MM-dd  hh:mm:ss",timezone = "GMT+8")
    private Date taskHisEndTime;
    @JsonFormat( pattern="yyyy-MM-dd  hh:mm:ss",timezone = "GMT+8")
    private Date taskHisStartTime;

    public String getTaskHisId() {
        return taskHisId;
    }

    public void setTaskHisId(String taskHisId) {
        this.taskHisId = taskHisId;
    }


    public Date getTaskHisEndTime() {
        return taskHisEndTime;
    }

    public void setTaskHisEndTime(Date taskHisEndTime) {
        this.taskHisEndTime = taskHisEndTime;
    }

    public Date getTaskHisStartTime() {
        return taskHisStartTime;
    }

    public void setTaskHisStartTime(Date taskHisStartTime) {
        this.taskHisStartTime = taskHisStartTime;
    }
}
