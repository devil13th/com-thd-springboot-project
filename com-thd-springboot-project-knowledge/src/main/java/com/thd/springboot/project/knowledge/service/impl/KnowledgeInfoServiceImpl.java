package com.thd.springboot.project.knowledge.service.impl;


import com.thd.springboot.framework.db.service.BasicServiceImpl;
import com.thd.springboot.project.knowledge.entity.KnowledgeInfoEntity;
import com.thd.springboot.project.knowledge.mapper.KnowledgeInfoMapper;
import com.thd.springboot.project.knowledge.service.KnowledgeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("knowledgeInfoService")
@Transactional
public class KnowledgeInfoServiceImpl extends BasicServiceImpl<KnowledgeInfoEntity> implements KnowledgeInfoService {

	@Autowired
    private KnowledgeInfoMapper knowledgeInfoMapper;

	@Override
	public KnowledgeInfoMapper getMapper() {
		return knowledgeInfoMapper;
	}

	@Override
    public void insertBatch(List<KnowledgeInfoEntity> list){
        knowledgeInfoMapper.insertBatch(list);
    }

}
