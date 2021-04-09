package com.thd.springboot.project.cgexample.service;


import com.thd.springboot.framework.db.service.BasicServiceImpl;
import com.thd.springboot.project.cgexample.entity.CgTestEntity;
import com.thd.springboot.project.cgexample.mapper.CgTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cgTestService")
@Transactional
public class CgTestServiceImpl extends BasicServiceImpl<CgTestEntity> implements CgTestService {

	@Autowired
    private CgTestMapper cgTestMapper;

	@Override
	public CgTestMapper getMapper() {
		return cgTestMapper;
	}

	@Override
    public void insertBatch(List<CgTestEntity> list){
        cgTestMapper.insertBatch(list);
    }

}
