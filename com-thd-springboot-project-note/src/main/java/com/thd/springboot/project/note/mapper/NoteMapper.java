package com.thd.springboot.project.note.mapper;

import com.thd.springboot.framework.db.mapper.BasicMapper;
import com.thd.springboot.project.note.entity.NoteEntity;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface NoteMapper extends BasicMapper<NoteEntity> {

    // 返回map, key为指定属性，value为实体类结果集
    @MapKey("id")  // 指定key属性取哪列
    public Map<String,NoteEntity> queryAllToMapKey();

}
