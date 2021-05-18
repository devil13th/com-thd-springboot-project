//package com.thd.springboot.project.note.service;
//
//import com.thd.springboot.framework.utils.MyStringUtils;
//import com.thd.springboot.framework.utils.UuidUtils;
//import com.thd.springboot.project.note.entity.NoteEntity;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.support.master.AcknowledgedResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.indices.CreateIndexRequest;
//import org.elasticsearch.client.indices.CreateIndexResponse;
//import org.elasticsearch.client.indices.GetIndexRequest;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.elasticsearch.search.sort.ScoreSortBuilder;
//import org.elasticsearch.search.sort.SortOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
///**
// * com.thd.springboot.project.note.service.NoteEsServiceImpl
// *
// * @author: wanglei62
// * @DATE: 2021/5/8 16:26
// **/
//@Service
//public class NoteEsServiceImpl implements NoteEsService {
//    @Autowired
//    private RestHighLevelClient esClient;
//
//    @Override
//    public boolean createIndex() throws Exception {
//
//        CreateIndexRequest req = new CreateIndexRequest("note");
//        // 使用json设置索引内容
//        req.source("{\n" +
//                "    \"settings\" : {\n" +
//                "        \"number_of_shards\" : 1,\n" +
//                "        \"number_of_replicas\" : 0\n" +
//                "    },\n" +
//                "    \"mappings\" : {\n" +
//                "        \"properties\" : {\n" +
//                "            \"title\" : { \"type\" : \"text\" },\n" +
//                "            \"classify\" : { \"type\" : \"keyword\" },\n" +
//                "            \"content\" : { \"type\" : \"text\" }\n" +
//                "        }\n" +
//                "    },\n" +
//                "    \"aliases\" : {\n" +
//                "        \"note_\" : {}\n" +
//                "    }\n" +
//                "}", XContentType.JSON);
//        CreateIndexResponse res = esClient.indices().create(req, RequestOptions.DEFAULT);
//        return res.isAcknowledged();
//    }
//
//
//    public boolean deleteIndex() throws Exception{
//        DeleteIndexRequest request = new DeleteIndexRequest("note");
//        AcknowledgedResponse response = esClient.indices().delete(request, RequestOptions.DEFAULT);
//        return response.isAcknowledged();
//    };
//
//    public boolean checkIndex(String index) {
//        try {
//            return esClient.indices().exists(new GetIndexRequest(index), RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return Boolean.FALSE ;
//    }
//
//
//    public String index(NoteEntity noteEntity) throws Exception{
////        String id = UuidUtils.uuid();
//        String id = noteEntity.getNoteId();
//
//        if(!this.checkIndex("note")){ // 如果没有创建索引则创建
//            this.createIndex();
//        }
//        IndexRequest req = new IndexRequest("note");
//
//        // 设置id
//        req.id(id);
//
//        Map<String, Object> jsonMap = new HashMap<>();
//        jsonMap.put("title", noteEntity.getTitle());
//        jsonMap.put("content", noteEntity.getContent());
//        jsonMap.put("classify", noteEntity.getClassify());
//
//        // 索引内容
//        req.source(jsonMap);
//
//        this.esClient.index(req, RequestOptions.DEFAULT);
//
//        return id;
//    }
//
//
//    public List<NoteEntity> search(String keyWords,String classify) throws Exception{
//        SearchRequest searchRequest = new SearchRequest();
//        // 查询哪个索引
//        searchRequest.indices("note");
//
//
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        // 返回的字段集合
//        String[] includeFields = new String[] {"title","classify"};
//        searchSourceBuilder.fetchSource(includeFields,null);
//
//        // 查询条件
//
//        BoolQueryBuilder queryBuilder =  QueryBuilders.boolQuery();
//        if(MyStringUtils.isNotEmpty(classify)){
//            queryBuilder.must(QueryBuilders.matchQuery("classify",classify));
//        }
//        queryBuilder.must(
//                    QueryBuilders.boolQuery()
//                    .should(QueryBuilders.matchQuery("content",keyWords))
//                    .should(QueryBuilders.matchQuery("title",keyWords))
//                );
//        searchSourceBuilder.query(queryBuilder);
//
//        // 得分排序
//        searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));// 默认得分倒叙
//        // 分页
//        searchSourceBuilder.from(0);
//        searchSourceBuilder.size(100);
//
//        // 高亮显示
//        // 高亮
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//
//        highlightBuilder.preTags("<b>");
//        highlightBuilder.postTags("</b>");
//
//        HighlightBuilder.Field highlightTitle = new HighlightBuilder.Field("title");
//        highlightTitle.highlighterType("unified");
//        highlightBuilder.field(highlightTitle);
//
//        HighlightBuilder.Field highlightContent = new HighlightBuilder.Field("content");
//        highlightBuilder.field(highlightContent);
//
//        searchSourceBuilder.highlighter(highlightBuilder);
//
//        System.out.println("|||| request body ||||| : " + searchSourceBuilder);
//        searchRequest.source(searchSourceBuilder);
//
//
//        SearchResponse searchResponse = this.esClient.search(searchRequest,RequestOptions.DEFAULT);
//
//
//        SearchHit[] r = searchResponse.getHits().getHits();
//        List<NoteEntity> l = Stream.of(r).map(item -> {
//            NoteEntity arc = new NoteEntity();
//            Map arcMap = item.getSourceAsMap();
//            arc.setId(item.getId());
//            arc.setNoteId(item.getId());
//            arc.setTitle(null == arcMap.get("title") ? null : arcMap.get("title").toString());
//
//            arc.setClassify(null == arcMap.get("classify") ? null : arcMap.get("classify").toString());
//            arc.setHighLight(item.getHighlightFields().get("content").toString());
//            return arc;
//        }).collect(Collectors.toList());
//
//        return l;
//    };
//
//}
