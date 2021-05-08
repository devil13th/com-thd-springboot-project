package com.thd.springboot.project.note.service;

import com.thd.springboot.project.note.entity.NoteEntity;

import java.util.List;

/**
 * com.thd.springboot.project.note.service.NoteEsService
 *
 * @author: wanglei62
 * @DATE: 2021/5/8 16:26
 **/
public interface NoteEsService {

    /**
     * 创建索引
     */
    public boolean createIndex() throws Exception;
    /**
     * 删除索引
     */
    public boolean deleteIndex() throws Exception;
    /**
     * 索引doc
     * @param noteEntity
     * @return
     */
    public String index(NoteEntity noteEntity) throws Exception;

    /**
     * 检索
     * @param keyWords 关键字
     * @return
     * @throws Exception
     */
    public List<NoteEntity> search(String keyWords,String classify) throws Exception;
}
