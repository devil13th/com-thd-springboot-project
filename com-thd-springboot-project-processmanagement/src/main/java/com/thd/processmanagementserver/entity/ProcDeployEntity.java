package com.thd.processmanagementserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * com.thd.processmanagementserver.entity.ProcDeploy
 *
 * @author: wanglei62
 * @DATE: 2021/3/25 10:04
 **/
public class ProcDeployEntity extends ProcCommonBean{
    private String deployId;
    @JsonFormat( pattern="yyyy-MM-dd  hh:mm:ss",timezone = "GMT+8")
    private Date deployTime;

    public String getDeployId() {
        return deployId;
    }

    public void setDeployId(String deployId) {
        this.deployId = deployId;
    }

    public Date getDeployTime() {
        return deployTime;
    }

    public void setDeployTime(Date deployTime) {
        this.deployTime = deployTime;
    }
}
