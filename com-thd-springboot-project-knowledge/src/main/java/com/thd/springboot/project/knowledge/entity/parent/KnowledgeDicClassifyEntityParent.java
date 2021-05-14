package com.thd.springboot.project.knowledge.entity.parent;

import lombok.Data;
import com.thd.springboot.framework.entity.BasicEntity;
import java.util.Date;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;


@Data
public class  KnowledgeDicClassifyEntityParent extends BasicEntity {
    // 
    private String classifyId;
    // 
    private String parentTreeCode;
    // 
    private String classifyName;
    // 
    private String classifyDesc;
    // 
    private Integer classifyOrder;
}
