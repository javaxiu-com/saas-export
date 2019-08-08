package com.gyhqq.service.cargo;

import com.gyhqq.domain.cargo.ShippingOrder;
import com.gyhqq.domain.cargo.ShippingOrderExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ShippingOrderService {
    void deleteByPrimaryKey(String shippingOrderId);

    //int insert(ShippingOrder record);

    void insertSelective(ShippingOrder record);

    List<ShippingOrder> selectByExample(ShippingOrderExample example);

    ShippingOrder selectByPrimaryKey(String shippingOrderId);

    void updateByPrimaryKeySelective(ShippingOrder record);

    //分页查询委托单
    PageInfo findAll(ShippingOrderExample shippingOrderExample, int page, int size);

    //保存委托单,id是装箱单id
    void save(ShippingOrder shippingOrder);

    //更新委托单,id是装箱单id
    void update(ShippingOrder shippingOrder);

    //更新之前回显委托单数据
    ShippingOrder findById(String shippingOrderId);

    //删除委托单
    void delete(String shippingOrderId);
}
