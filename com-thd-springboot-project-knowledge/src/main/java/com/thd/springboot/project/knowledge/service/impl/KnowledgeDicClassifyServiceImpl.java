//package com.thd.springboot.project.knowledge.service.impl;
//
//import com.github.pagehelper.Page;
//import com.thd.springboot.framework.db.mapper.BasicMapper;
//import com.thd.springboot.framework.db.service.BasicServiceImpl;
//
//
//import com.thd.springboot.project.knowledge.entity.KnowledgeDicClassifyEntity;
//import com.thd.springboot.project.knowledge.mapper.KnowledgeDicClassifyMapper;
//import com.thd.springboot.project.knowledge.service.KnowledgeDicClassifyService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import java.util.List;
//
//@Service("knowledgeDicClassifyService")
//@Transactional
//public class KnowledgeDicClassifyServiceImpl extends BasicServiceImpl<KnowledgeDicClassifyEntity> implements KnowledgeDicClassifyService {
//
//	@Autowired
//    private KnowledgeDicClassifyMapper knowledgeDicClassifyMapper;
//
//	@Override
//	public KnowledgeDicClassifyMapper getMapper() {
//		return knowledgeDicClassifyMapper;
//	}
//
//	@Override
//    public void insertBatch(List<KnowledgeDicClassifyEntity> list){
//        knowledgeDicClassifyMapper.insertBatch(list);
//    }
//
//}
