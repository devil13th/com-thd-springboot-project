package com.thd.springboot.project.knowledge.entity.parent;


import com.thd.springboot.framework.entity.BasicEntity;
import lombok.Data;

@Data
public class  KnowledgeInfoEntityParent extends BasicEntity {
    // 
    private String knowledgeId;
    // 
    private String docType;
    //
    private String filePath;
    // 
    private String title;
    // 
    private String desc;
    // 
    private String content;
}
