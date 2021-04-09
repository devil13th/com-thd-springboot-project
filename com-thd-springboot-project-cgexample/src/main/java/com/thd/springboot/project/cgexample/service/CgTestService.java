package com.thd.springboot.project.cgexample.service;


import com.thd.springboot.framework.db.service.BasicService;
import com.thd.springboot.project.cgexample.entity.CgTestEntity;

import java.util.List;

public interface CgTestService extends BasicService<CgTestEntity> {

    // 批量插入
    public void insertBatch(List<CgTestEntity> list);
}
