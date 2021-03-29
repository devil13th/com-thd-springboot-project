package com.thd.processmanagementserver.entity;

//import com.alibaba.fastjson.annotation.JSONField;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.thd.processmanagementserver.utils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * com.thd.springboot.framework.model.Page
 *
 * @author: wanglei62
 * @DATE: 2020/1/21 17:28
 **/

public class MyPage implements Serializable {
//    @TableField(exist = false)
    private Integer current;
//    @JSONField(serialize = false)
//    @TableField(exist = false)
    private Integer pageSize;
//    @JSONField(serialize = false)
//    @TableField(exist = false)
    private String sortField;
//    @JSONField(serialize = false)
//    @TableField(exist = false)
    private String sortOrder;
//    @JSONField(serialize = false)
    @JsonIgnore
//    @TableField(exist = false)
    private String dataSql;

//    @TableField(exist = false)
    private String orderBy;

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        String orderBy = null;
        if (StringUtils.isNotBlank(this.sortField)) {
            orderBy = PropertyUtils.camel2Underline(this.sortField);
        }

        if (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(this.sortOrder)) {
            orderBy = orderBy + " " + this.sortOrder;
        }

        return orderBy;
    }

    public MyPage() {
    }



    public String toString() {
        return "Page(pageNum=" + this.getCurrent() + ", pageSize=" + this.getPageSize() + ", sortField=" + this.getSortField() + ", sortOrder=" + this.getSortOrder() + ", dataSql=" + this.getDataSql() + ")";
    }


    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getDataSql() {
        return dataSql;
    }

    public void setDataSql(String dataSql) {
        this.dataSql = dataSql;
    }
}
