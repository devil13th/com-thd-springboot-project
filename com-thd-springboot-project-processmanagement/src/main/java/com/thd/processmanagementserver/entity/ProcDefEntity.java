package com.thd.processmanagementserver.entity;

/**
 * com.thd.processmanagementserver.entity.ActReProcdef
 *
 * @author: wanglei62
 * @DATE: 2021/1/26 17:22
 **/
public class ProcDefEntity extends ProcDeployEntity {
    private String procDefId;
    private Integer rev;
    private String category;
    private String defName;
    private String defKey;
    private String defVersion;
    private String deploymentId;

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDefName() {
        return defName;
    }

    public void setDefName(String defName) {
        this.defName = defName;
    }

    public String getDefKey() {
        return defKey;
    }

    public void setDefKey(String defKey) {
        this.defKey = defKey;
    }

    public String getDefVersion() {
        return defVersion;
    }

    public void setDefVersion(String defVersion) {
        this.defVersion = defVersion;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }
}
