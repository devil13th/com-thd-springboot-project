//package com.thd.springboot.project.knowledge.service.impl;
//
//import com.github.pagehelper.Page;
//import com.thd.springboot.framework.db.mapper.BasicMapper;
//import com.thd.springboot.framework.db.service.BasicServiceImpl;
//
//
//import com.thd.springboot.project.knowledge.entity.KnowledgeRelaInfoClassifyEntity;
//import com.thd.springboot.project.knowledge.mapper.KnowledgeRelaInfoClassifyMapper;
//import com.thd.springboot.project.knowledge.service.KnowledgeRelaInfoClassifyService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import java.util.List;
//
//@Service("knowledgeRelaInfoClassifyService")
//@Transactional
//public class KnowledgeRelaInfoClassifyServiceImpl extends BasicServiceImpl<KnowledgeRelaInfoClassifyEntity> implements KnowledgeRelaInfoClassifyService {
//
//	@Autowired
//    private KnowledgeRelaInfoClassifyMapper knowledgeRelaInfoClassifyMapper;
//
//	@Override
//	public KnowledgeRelaInfoClassifyMapper getMapper() {
//		return knowledgeRelaInfoClassifyMapper;
//	}
//
//	@Override
//    public void insertBatch(List<KnowledgeRelaInfoClassifyEntity> list){
//        knowledgeRelaInfoClassifyMapper.insertBatch(list);
//    }
//
//}
