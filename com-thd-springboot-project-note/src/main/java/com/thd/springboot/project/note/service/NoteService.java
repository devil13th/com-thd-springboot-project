package com.thd.springboot.project.note.service;


import com.thd.springboot.framework.db.service.BasicService;
import com.thd.springboot.project.note.entity.NoteEntity;

import java.util.List;

public interface NoteService extends BasicService<NoteEntity> {

    // 批量插入
    public void insertBatch(List<NoteEntity> list);
}
