package com.thd.springboot.project.cgexample.mapper;

import com.thd.springboot.framework.db.mapper.BasicMapper;
import com.thd.springboot.project.cgexample.entity.MyPlanEntity;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface MyPlanMapper extends BasicMapper<MyPlanEntity> {

    // 返回map, key为指定属性，value为实体类结果集
    @MapKey("id")  // 指定key属性取哪列
    public Map<String,MyPlanEntity> queryAllToMapKey();

}
