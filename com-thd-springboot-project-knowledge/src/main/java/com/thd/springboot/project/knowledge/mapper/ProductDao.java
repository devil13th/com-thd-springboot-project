package com.thd.springboot.project.knowledge.mapper;

import com.thd.springboot.project.knowledge.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends ElasticsearchRepository<Product,String> {
}
