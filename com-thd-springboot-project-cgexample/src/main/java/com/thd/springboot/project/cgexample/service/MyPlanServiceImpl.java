package com.thd.springboot.project.cgexample.service;


import com.thd.springboot.framework.db.service.BasicServiceImpl;
import com.thd.springboot.project.cgexample.entity.MyPlanEntity;
import com.thd.springboot.project.cgexample.mapper.MyPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("myPlanService")
@Transactional
public class MyPlanServiceImpl extends BasicServiceImpl<MyPlanEntity> implements MyPlanService {

	@Autowired
    private MyPlanMapper myPlanMapper;

	@Override
	public MyPlanMapper getMapper() {
		return myPlanMapper;
	}

	@Override
    public void insertBatch(List<MyPlanEntity> list){
        myPlanMapper.insertBatch(list);
    }

}
