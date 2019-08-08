package com.gyhqq.service.message.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gyhqq.dao.message.ProductDao;
import com.gyhqq.domain.message.Product;
import com.gyhqq.domain.message.ProductExample;
import com.gyhqq.service.message.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public PageInfo findAll(ProductExample productExample, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Product> products = productDao.selectByExample(productExample);
        return new PageInfo(products);
    }

    @Override
    public void save(Product product) {
        //设置id
        product.setId(UUID.randomUUID().toString());
        //设置录入时间
        product.setInputTime(new Date());
        product.setCreateTime(new Date());
        productDao.insertSelective(product);
    }

    @Override
    public Product findById(String id) {
        return productDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productDao.updateByPrimaryKeySelective(product);
    }

    @Override
    public void delete(String id) {
        productDao.deleteByPrimaryKey(id);
    }
}
