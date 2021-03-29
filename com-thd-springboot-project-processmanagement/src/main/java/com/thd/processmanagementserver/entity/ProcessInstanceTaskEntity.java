package com.thd.processmanagementserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Map;

public class ProcessInstanceTaskEntity extends ProcessInstanceEntity {
    private String taskId;
    private String executionId;
    private String taskName;
    private String taskDefKey;
    private String taskOwner;
    private String taskAssignee;
    private String taskFormKey;
    @JsonFormat( pattern="yyyy-MM-dd  hh:mm:ss",timezone = "GMT+8")
    private Date taskStartTime;

    private Map<String,Object> taskVar;
    private Map<String,Object> executionVar;


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDefKey() {
        return taskDefKey;
    }

    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey;
    }

    public String getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(String taskOwner) {
        this.taskOwner = taskOwner;
    }

    public String getTaskAssignee() {
        return taskAssignee;
    }

    public void setTaskAssignee(String taskAssignee) {
        this.taskAssignee = taskAssignee;
    }

    public String getTaskFormKey() {
        return taskFormKey;
    }

    public void setTaskFormKey(String taskFormKey) {
        this.taskFormKey = taskFormKey;
    }

    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Map<String, Object> getTaskVar() {
        return taskVar;
    }

    public void setTaskVar(Map<String, Object> taskVar) {
        this.taskVar = taskVar;
    }

    public Map<String, Object> getExecutionVar() {
        return executionVar;
    }

    public void setExecutionVar(Map<String, Object> executionVar) {
        this.executionVar = executionVar;
    }
}
