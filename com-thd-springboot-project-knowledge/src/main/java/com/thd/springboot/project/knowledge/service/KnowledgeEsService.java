package com.thd.springboot.project.knowledge.service;

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
     * 索引doc
     * @param docBean
     * @return
     */
    public void index(DocVO docBean) throws Exception;
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

}
