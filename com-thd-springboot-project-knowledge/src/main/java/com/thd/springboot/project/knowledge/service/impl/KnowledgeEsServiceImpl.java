package com.thd.springboot.project.knowledge.service.impl;

import com.thd.springboot.framework.utils.MyFileUtils;
import com.thd.springboot.framework.utils.MyStringUtils;
import com.thd.springboot.project.knowledge.constant.KnowledgeConstants;
import com.thd.springboot.project.knowledge.service.KnowledgeEsService;
import com.thd.springboot.project.knowledge.vo.DocVO;
import com.thd.springboot.project.knowledge.vo.SearchVO;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * com.thd.springboot.project.knowledge.service.impl.KnowledgeEsServiceImpl
 *
 * @author: wanglei62
 * @DATE: 2021/5/12 11:40
 **/
@Service
public class KnowledgeEsServiceImpl implements KnowledgeEsService {
    @Autowired
    private RestHighLevelClient esClient;

    @Override
    public boolean createDocIndex() throws Exception {
        if(!this.existIndex(KnowledgeConstants.MODULE_NAME)) {
            CreateIndexRequest req = new CreateIndexRequest(KnowledgeConstants.MODULE_NAME);
            // 使用json设置索引内容
            req.source("{\n" +
                    "    \"settings\" : {\n" +
                    "        \"number_of_shards\" : 2,\n" +
                    "        \"number_of_replicas\" : 2\n" +
                    "    },\n" +
                    "    \"mappings\" : {\n" +
                    "        \"properties\" : {\n" +
                    "            \"title\" : { \"type\" : \"text\",\"analyzer\":\"ik_max_word\",\"search_analyzer\":\"ik_smart\" },\n" +
                    "            \"desc\" : { \"type\" : \"text\" ,\"analyzer\":\"ik_max_word\",\"search_analyzer\":\"ik_smart\"},\n" +
                    "            \"docType\" : { \"type\" : \"keyword\" },\n" +
                    "            \"classify\" : { \"type\" : \"text\" },\n" +
                    "            \"content\" : { \"type\" : \"text\" ,\"analyzer\":\"ik_max_word\",\"search_analyzer\":\"ik_smart\"}\n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"aliases\" : {\n" +
                    "        \"knowledge_\" : {}\n" +
                    "    }\n" +
                    "}", XContentType.JSON);
            CreateIndexResponse res = esClient.indices().create(req, RequestOptions.DEFAULT);
            return res.isAcknowledged();
        }else{
            throw new RuntimeException(String.format("Index[%s] already exist",KnowledgeConstants.MODULE_NAME));
        }

    }





    public boolean deleteDocIndex() throws Exception{
        DeleteIndexRequest request = new DeleteIndexRequest(KnowledgeConstants.MODULE_NAME);
        request.timeout(TimeValue.timeValueMinutes(1));
        AcknowledgedResponse response = esClient.indices().delete(request, RequestOptions.DEFAULT);
        return response.isAcknowledged();
    };


    public boolean existIndex(String index) throws Exception{
        GetIndexRequest getIndexRequest = new GetIndexRequest(KnowledgeConstants.MODULE_NAME);
        return esClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
    };


    public boolean checkIndex(String index) {
        try {
            return esClient.indices().exists(new GetIndexRequest(index), RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE ;
    }


    public void index(DocVO docBean) throws Exception{
        IndexRequest indexRequest = new IndexRequest(KnowledgeConstants.MODULE_NAME);

        indexRequest.id(docBean.getId());
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("title", docBean.getTitle());
        jsonMap.put("content", docBean.getContent());
        jsonMap.put("classify", docBean.getClassify());
        jsonMap.put("desc", docBean.getDesc());
        jsonMap.put("docType", docBean.getDocType());
        jsonMap.put("filePath", docBean.getFilePath());

        // 索引内容
        indexRequest.source(jsonMap);

        this.esClient.index(indexRequest,RequestOptions.DEFAULT);
    };


    @Override
    public void modify(DocVO docBean) throws Exception {
        if(MyStringUtils.isEmpty(docBean.getId())){
            throw new RuntimeException("Id can't be empty");
        }
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(KnowledgeConstants.MODULE_NAME);
        updateRequest.id(docBean.getId());

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("title", docBean.getTitle());
        jsonMap.put("classify", docBean.getClassify());
        jsonMap.put("content", docBean.getContent());
        jsonMap.put("desc", docBean.getDesc());

        updateRequest.doc(jsonMap);
        esClient.update(updateRequest,RequestOptions.DEFAULT);

    }

    public void reIndex(DocVO docBean) throws Exception{
        if(MyStringUtils.isEmpty(docBean.getId())){
            throw new RuntimeException("Id can't be empty");
        }
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(KnowledgeConstants.MODULE_NAME);
        updateRequest.id(docBean.getId());

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("title", docBean.getTitle());
        jsonMap.put("classify", docBean.getClassify());
        jsonMap.put("content", docBean.getContent());
        jsonMap.put("desc", docBean.getDesc());

        updateRequest.doc(jsonMap);
        esClient.update(updateRequest,RequestOptions.DEFAULT);
    };



    public List<DocVO> search(SearchVO vo) throws Exception{
        SearchRequest searchRequest = new SearchRequest(KnowledgeConstants.MODULE_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();


        BoolQueryBuilder condition =  QueryBuilders.boolQuery();
        if(MyStringUtils.isNotEmpty(vo.getClassify())){
            condition
                .must(QueryBuilders.matchQuery("classify",vo.getClassify()))
                .must(
                    QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("content",vo.getKeyWords()).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("title",vo.getKeyWords()).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("desc",vo.getKeyWords()).operator(Operator.AND))
                );

        }else{
            condition.must(
                QueryBuilders.boolQuery()
                    .should(QueryBuilders.matchQuery("content",vo.getKeyWords()).operator(Operator.AND))
                    .should(QueryBuilders.matchQuery("title",vo.getKeyWords()).operator(Operator.AND))
                    .should(QueryBuilders.matchQuery("desc",vo.getKeyWords()).operator(Operator.AND))
            );
        }

        searchSourceBuilder.query(condition);
//        searchSourceBuilder.query(
//                QueryBuilders.boolQuery()
//                        .must(QueryBuilders.matchQuery("classify",vo.getClassify()))
//                        .must(
//                                QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("content",vo.getKeyWords()).operator(Operator.AND))
//                                .should(QueryBuilders.matchQuery("title",vo.getKeyWords()).operator(Operator.AND))
//                                .should(QueryBuilders.matchQuery("desc",vo.getKeyWords()).operator(Operator.AND))
//                        )
//
//        );


        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();

        highlightBuilder.preTags("<b style='color:red'>");
        highlightBuilder.postTags("</b>");


        HighlightBuilder.Field highlightTitle = new HighlightBuilder.Field("title");
        highlightBuilder.field(highlightTitle);

        HighlightBuilder.Field highlightContent = new HighlightBuilder.Field("content");
        highlightBuilder.field(highlightContent);



        searchSourceBuilder.highlighter(highlightBuilder);





        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = esClient.search(searchRequest,RequestOptions.DEFAULT);
        SearchHit[] result = searchResponse.getHits().getHits();

        List<DocVO> list = Stream.of(result).map(item -> {
            DocVO doc = new DocVO();
            Map docMap = item.getSourceAsMap();
            Map<String, HighlightField> hlMap = item.getHighlightFields();
            doc.setId(item.getId());
            doc.setTitle(null == docMap.get("title") ? null : docMap.get("title").toString());
            doc.setClassify(null == docMap.get("classify") ? null : docMap.get("classify").toString());
            doc.setFilePath(null == docMap.get("filePath") ? null : docMap.get("filePath").toString());
            doc.setHighLight(
                Optional.ofNullable(hlMap.get("content")).map( hl -> {
                    return Stream.of(hl.getFragments()).map(txt -> txt.toString()).collect(Collectors.toList());
                }).orElse(
                    new ArrayList<String>()
                )
            );



            return doc;
        }).collect(Collectors.toList());
        return list;

    };




    public void indexThdTecFile(String path) {
        String folderPath = path;
        File folder = new File(folderPath);
        String[] folderList = folder.list();
        String[] classifyArrays = new String[]{"REACT","JS","JVM","VUE","JAVA"};
        Stream.of(folderList).forEach( item -> {

            File f = new File(folder.getAbsolutePath() + "\\" + item);
            if(f.isFile()) {
//                if(f.getName().toLowerCase().indexOf(".html") != -1
//                        ||f.getName().toLowerCase().indexOf(".htm") != -1
//                        ||f.getName().toLowerCase().indexOf(".md") != -1
//                )
                if(f.getName().toLowerCase().indexOf(".md") != -1){
                    System.out.println(f.getAbsolutePath());
                    if(f.length() <= 1 * 1024 * 1024) {
                        String content = MyFileUtils.readFile(f.getAbsolutePath());
                        DocVO doc = new DocVO();
                        doc.setTitle(f.getName());
                        doc.setContent(content);
                        doc.setFilePath(f.getAbsolutePath());

                        int c = new Random().nextInt(3);
                        doc.setClassify(classifyArrays[c]);
                        try {
                           this.index(doc);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }else{
                this.indexThdTecFile(f.getAbsolutePath());
            }
        });
    };


    public DocVO loadDocById(String id){
        SearchRequest sr = new SearchRequest(KnowledgeConstants.MODULE_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.idsQuery().addIds(id));
        sr.source(searchSourceBuilder);
        try{
            SearchResponse response = esClient.search(sr,RequestOptions.DEFAULT);
            SearchHit[] list = response.getHits().getHits();
            if(list.length == 0 ){
                throw new RuntimeException(String.format("Id[%s] Not Be Found",id));
            }
            SearchHit r = list[0];
            Map<String,Object> map = r.getSourceAsMap();

            DocVO arc = new DocVO();
            arc.setId(r.getId());
            arc.setClassify(null == map.get("classify") ? null : map.get("classify").toString());
            arc.setTitle(null == map.get("title") ? null : map.get("title").toString());
            arc.setContent(null == map.get("content") ? null : map.get("content").toString());
            arc.setDesc(null == map.get("desc") ? null : map.get("desc").toString());
            arc.setFilePath(null == map.get("filePath") ? null : map.get("filePath").toString());
            return arc;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }


    };
}
