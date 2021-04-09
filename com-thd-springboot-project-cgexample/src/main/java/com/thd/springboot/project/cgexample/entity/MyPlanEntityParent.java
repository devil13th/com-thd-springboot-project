package com.thd.springboot.project.cgexample.entity;

import lombok.Data;
import com.thd.springboot.framework.entity.BasicEntity;
import java.util.Date;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;


@Data
public class  MyPlanEntityParent extends BasicEntity {
    // 
    private String planId;
    // 
    private String title;
    // 
    private String detail;
    // 
    private Integer days;
    // 
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date fromDate;
    // 
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date toDate;
}
