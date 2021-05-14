package com.thd.springboot.project.knowledge.service;

import com.thd.springboot.project.knowledge.entity.KnowledgeRelaInfoClassifyEntity;
import org.springframework.stereotype.Service;
import com.thd.springboot.framework.db.service.BasicService;
import java.util.List;
import java.util.Map;

public interface KnowledgeRelaInfoClassifyService extends BasicService<KnowledgeRelaInfoClassifyEntity> {

    // 批量插入
    public void insertBatch(List<KnowledgeRelaInfoClassifyEntity> list);
}
