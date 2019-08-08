package com.gyhqq.dao.cargo;

import com.gyhqq.domain.cargo.ContractExample;
import com.gyhqq.domain.cargo.ShippingOrder;
import com.gyhqq.domain.cargo.ShippingOrderExample;

import java.util.List;

public interface ShippingOrderDao {

    int deleteByPrimaryKey(String shippingOrderId);

    int insert(ShippingOrder record);

    int insertSelective(ShippingOrder record);

    List<ShippingOrder> selectByExample(ShippingOrderExample example);

    ShippingOrder selectByPrimaryKey(String shippingOrderId);

    int updateByPrimaryKeySelective(ShippingOrder record);

}