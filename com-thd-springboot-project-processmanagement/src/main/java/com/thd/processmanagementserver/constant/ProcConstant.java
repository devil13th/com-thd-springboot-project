package com.thd.processmanagementserver.constant;

/**
 * com.thd.processmanagementserver.constant.ProcConstant
 *
 * @author: wanglei62
 * @DATE: 2021/3/25 10:42
 **/
public enum ProcConstant {
    PROCDEPL("Process Deployment",4),
    PROCDEF("Process Definition",3),
    PROCINST("Process Instance",2),
    PROCINSTTASK("Task",1);
    private String alias;
    private Integer level;
    ProcConstant(String alias,Integer level){
        this.alias = alias;
        this.level = level;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
