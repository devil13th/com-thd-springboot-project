package com.thd.springboot.project.note.service;

import com.thd.springboot.project.note.entity.NoteEntity;
import org.springframework.stereotype.Service;
import com.thd.springboot.framework.db.service.BasicService;
import java.util.List;
import java.util.Map;

public interface NoteService extends BasicService<NoteEntity> {

    // 批量插入
    public void insertBatch(List<NoteEntity> list);



}
