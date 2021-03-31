package com.thd.springboot.project.cgexample.service;


import com.thd.springboot.framework.db.mapper.BasicMapper;
import com.thd.springboot.framework.db.service.BasicServiceImpl;
import com.thd.springboot.project.cgexample.entity.CgExampleEntity;
import com.thd.springboot.project.cgexample.mapper.CgExampleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CgExampleServiceImpl extends BasicServiceImpl<CgExampleEntity> implements CgExampleService {
    @Autowired
    private CgExampleMapper cgExampleMapper;

    @Override
    public BasicMapper<CgExampleEntity> getMapper() {
        return cgExampleMapper;
    }

    public List queryAllCgExample(){
        CgExampleEntity queryCondition = new CgExampleEntity();
        return cgExampleMapper.queryListEq(null);
    };

    public CgExampleEntity queryCgExampleById(String id){
        return cgExampleMapper.queryById(id);
    };


    public List<CgExampleEntity> queryCgExampleEq(CgExampleEntity entity){
        return cgExampleMapper.queryListEq(entity);
    }

    public List<CgExampleEntity> queryCgExampleLike(CgExampleEntity entity){
        return cgExampleMapper.queryListLike(entity);
    }

    public Map<String,CgExampleEntity> queryAllToMapKey(){
        return cgExampleMapper.queryAllToMapKey();
    }

}
