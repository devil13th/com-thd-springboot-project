package com.thd.springboot.project.knowledge.service;

import com.thd.springboot.project.knowledge.vo.ClassifyVO;
import com.thd.springboot.project.knowledge.vo.DocVO;
import com.thd.springboot.project.knowledge.vo.SearchVO;

import java.util.List;

/**
 * com.thd.springboot.project.knowledge.service.KnowledgeEsService
 *
 * @author: wanglei62
 * @DATE: 2021/5/12 11:40
 **/
public interface KnowledgeEsService {
    /**
     * 创建索引
     */
    public boolean createDocIndex() throws Exception;
    /**
     * 删除索引
     */
    public boolean deleteDocIndex() throws Exception;
    /**
     * 索引是否存在
     */
    public boolean existIndex(String indexName) throws Exception;


    /**
     * 创建分类字典
     */
    public boolean createClassifyIndex() throws Exception;

    /**
     * 初始化分类字典数据
     */
    public boolean initClassifyData() throws Exception;

    /**
     * 创建classify
     * @param classify
     * @return
     * @throws Exception
     */
    public boolean createClassify(String classify) throws Exception;

    /**
     * 获取classify字典
     * @return
     */
    public List<ClassifyVO> queryAllClassify() throws Exception;


    /**
     * 索引doc
     * @param docBean
     * @return
     */
    public void index(DocVO docBean) throws Exception;
    public void modify(DocVO docBean) throws Exception;
    public void reIndex(DocVO docBean) throws Exception;


    /**
     * 全文检索
     * @param vo
     * @return
     */
    public List<DocVO> search(SearchVO vo) throws Exception;

    /**
     * 索引thd tec文章
     * @param path
     */
    public void indexThdTecFile(String path) ;


    /**
     * 全部重新索引thd tec文章
     * @param path
     */
    public void reIndexThdTecFile(String path) throws Exception;

    /**
     * 删除thd tec类文章
     */
    public void deleteIndexThdTecDoc() throws Exception;

    /**
     * 根据id查询文档
     * @param id
     * @return
     */
    public DocVO loadDocById(String id);

}
