package com.thd.springboot.project.note.service.impl;


import com.thd.springboot.framework.db.service.BasicServiceImpl;
import com.thd.springboot.project.note.entity.NoteEntity;
import com.thd.springboot.project.note.mapper.NoteMapper;
import com.thd.springboot.project.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("noteService")
@Transactional
public class NoteServiceImpl extends BasicServiceImpl<NoteEntity> implements NoteService {

	@Autowired
    private NoteMapper noteMapper;

	@Override
	public NoteMapper getMapper() {
		return noteMapper;
	}

	@Override
    public void insertBatch(List<NoteEntity> list){
        noteMapper.insertBatch(list);
    }

}
