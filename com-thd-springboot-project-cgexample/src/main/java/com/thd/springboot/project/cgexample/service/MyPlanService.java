package com.thd.springboot.project.cgexample.service;


import com.thd.springboot.framework.db.service.BasicService;
import com.thd.springboot.project.cgexample.entity.MyPlanEntity;

import java.util.List;

public interface MyPlanService extends BasicService<MyPlanEntity> {

    // 批量插入
    public void insertBatch(List<MyPlanEntity> list);
}
