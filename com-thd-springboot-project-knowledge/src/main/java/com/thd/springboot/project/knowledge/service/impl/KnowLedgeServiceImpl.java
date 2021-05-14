package com.thd.springboot.project.knowledge.service.impl;

import com.thd.springboot.framework.utils.UuidUtils;
import com.thd.springboot.project.knowledge.entity.KnowledgeInfoEntity;
import com.thd.springboot.project.knowledge.service.KnowledgeEsService;
import com.thd.springboot.project.knowledge.service.KnowledgeInfoService;
import com.thd.springboot.project.knowledge.service.KnowledgeService;
import com.thd.springboot.project.knowledge.vo.DocVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * com.thd.springboot.project.knowledge.service.impl.KnowLedgeServiceImpl
 *
 * @author: wanglei62
 * @DATE: 2021/5/12 17:53
 **/
@Service
public class KnowLedgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeEsService knowledgeEsService;
    @Autowired
    private KnowledgeInfoService knowledgeInfoService;
    public void createDoc(DocVO vo) throws Exception{
        String docId = UuidUtils.uuid();
        vo.setId(docId);
        KnowledgeInfoEntity entity = this.transerEsEntityToDbEntity(vo);

        // 数据库操作
        knowledgeInfoService.insert(entity);
        // es索引文档
        knowledgeEsService.index(vo);

    };


    public KnowledgeInfoEntity transerEsEntityToDbEntity(DocVO vo) throws Exception{
        KnowledgeInfoEntity dbEntity = new KnowledgeInfoEntity();

        BeanUtils.copyProperties(dbEntity,vo);
        dbEntity.setKnowledgeId(vo.getId());
        return dbEntity;
    };
}
