package com.thd.springboot.project.knowledge.service;

import com.thd.springboot.project.knowledge.entity.KnowledgeInfoEntity;
import com.thd.springboot.project.knowledge.vo.DocVO;

/**
 * com.thd.springboot.project.knowledge.service.KnowledgeService
 *
 * @author: wanglei62
 * @DATE: 2021/5/12 17:53
 **/
public interface KnowledgeService {
    /**
     * 创建文档
     * @param vo
     */
    public void createDoc(DocVO vo) throws Exception;

    /**
     * 更新文档
     * @param vo
     * @throws Exception
     */
    public void modifyDoc(DocVO vo) throws Exception;

    /**
     * 转换KnowledgeInfoEntity成DocVo
     * @param vo
     * @return
     */
    public KnowledgeInfoEntity transerEsEntityToDbEntity(DocVO vo) throws Exception;
}
