package com.gyhqq.service.message;

import com.gyhqq.domain.message.Product;
import com.gyhqq.domain.message.ProductExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductService {

    PageInfo findAll(ProductExample productExample, Integer page, Integer size);

    void save(Product Product);

    Product findById(String id);

    void update(Product Product);

    void delete(String id);

}
