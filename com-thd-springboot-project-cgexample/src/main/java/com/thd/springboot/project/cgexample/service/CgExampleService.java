package com.thd.springboot.project.cgexample.service;


import com.thd.springboot.framework.db.service.BasicService;
import com.thd.springboot.project.cgexample.entity.CgExampleEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CgExampleService extends BasicService<CgExampleEntity> {
    public List queryAllCgExample();
    public CgExampleEntity queryCgExampleById(String id);
    public List<CgExampleEntity> queryCgExampleEq(CgExampleEntity entity);
    public List<CgExampleEntity> queryCgExampleLike(CgExampleEntity entity);
    public Map<String,CgExampleEntity> queryAllToMapKey();
}
