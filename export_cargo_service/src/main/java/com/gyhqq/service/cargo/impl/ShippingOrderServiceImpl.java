package com.gyhqq.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gyhqq.dao.cargo.ExportDao;
import com.gyhqq.dao.cargo.PackingListDao;
import com.gyhqq.dao.cargo.ShippingOrderDao;
import com.gyhqq.domain.cargo.Export;
import com.gyhqq.domain.cargo.PackingList;
import com.gyhqq.domain.cargo.ShippingOrder;
import com.gyhqq.domain.cargo.ShippingOrderExample;
import com.gyhqq.service.cargo.ShippingOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Service
public class ShippingOrderServiceImpl implements ShippingOrderService {

    @Autowired
    private ShippingOrderDao shippingOrderDao;
    @Autowired
    private PackingListDao packingListDao;
    @Autowired
    private ExportDao exportDao;

    //删除
    @Override
    public void deleteByPrimaryKey(String shippingOrderId) {
        shippingOrderDao.deleteByPrimaryKey(shippingOrderId);
    }

    //插入
    @Override
    public void insertSelective(ShippingOrder record) {
        shippingOrderDao.insertSelective(record);

    }

    //条件查询
    @Override
    public List<ShippingOrder> selectByExample(ShippingOrderExample example) {
        return shippingOrderDao.selectByExample(example);
    }

    //主键查询
    @Override
    public ShippingOrder selectByPrimaryKey(String shippingOrderId) {
        return shippingOrderDao.selectByPrimaryKey(shippingOrderId);
    }

    //更新
    @Override
    public void updateByPrimaryKeySelective(ShippingOrder record) {
        shippingOrderDao.updateByPrimaryKeySelective(record);
    }

    //分页查询委托单
    @Override
    public PageInfo findAll(ShippingOrderExample shippingOrderExample, int page, int size) {
        PageHelper.startPage(page, size);
        List<ShippingOrder> list = shippingOrderDao.selectByExample(shippingOrderExample);
        return new PageInfo(list);
    }

    //保存委托单,id是装箱单id
    @Override
    public void save(ShippingOrder shippingOrder) {
        shippingOrder.setCreateTime(new Date());
        shippingOrderDao.insertSelective(shippingOrder);

        PackingList packingList = packingListDao.selectByPrimaryKey(shippingOrder.getShippingOrderId());
        String exportIds = packingList.getExportIds();
        String[] ids = exportIds.split(",");
        for (String id : ids) {
            Export export = new Export();
            export.setId(id);
            export.setState(5);
            exportDao.updateByPrimaryKeySelective(export);
        }
    }

    //更新委托单,id是装箱单id
    @Override
    public void update(ShippingOrder shippingOrder) {
        shippingOrder.setUpdateTime(new Date());
        shippingOrderDao.updateByPrimaryKeySelective(shippingOrder);
    }

    // //更新之前回显委托单数据
    @Override
    public ShippingOrder findById(String shippingOrderId) {
        return shippingOrderDao.selectByPrimaryKey(shippingOrderId);
    }

    //删除委托单
    @Override
    public void delete(String shippingOrderId) {
        shippingOrderDao.deleteByPrimaryKey(shippingOrderId);
    }
}
