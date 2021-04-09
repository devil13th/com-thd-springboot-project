package com.thd.springboot.project.cgexample.service;


import com.thd.springboot.framework.db.service.BasicService;
import com.thd.springboot.project.cgexample.entity.CgExampleEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CgExampleService extends BasicService<CgExampleEntity> {
    /**
     * 查询所有数据
     * @return
     */
    public List queryAllCgExample();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public CgExampleEntity queryCgExampleById(String id);

    /**
     * 查询列表 EQ
     * @param entity
     * @return
     */
    public List<CgExampleEntity> queryCgExampleEq(CgExampleEntity entity);

    /**
     * 查询列表 LIKE
     * @param entity
     * @return
     */
    public List<CgExampleEntity> queryCgExampleLike(CgExampleEntity entity);

    public Map<String,CgExampleEntity> queryAllToMapKey();
}
