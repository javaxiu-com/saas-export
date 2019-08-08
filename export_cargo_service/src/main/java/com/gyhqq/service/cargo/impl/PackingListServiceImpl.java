package com.gyhqq.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gyhqq.dao.cargo.ExportDao;
import com.gyhqq.dao.cargo.PackingListDao;
import com.gyhqq.domain.cargo.Export;
import com.gyhqq.domain.cargo.PackingList;
import com.gyhqq.domain.cargo.PackingListExample;
import com.gyhqq.service.cargo.PackingListService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class PackingListServiceImpl implements PackingListService {

    @Autowired
    private PackingListDao packingListDao;

    @Autowired
    private ExportDao exportDao;

    @Override
    public PageInfo findAll(Integer page, Integer size, PackingListExample example) {
        PageHelper.startPage(page, size);
        List<PackingList> list = packingListDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public PackingList findById(String id) {
        return packingListDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(PackingList packingList) {
        packingList.setPackingListId(UUID.randomUUID().toString());
        packingList.setState(0);
        packingListDao.insert(packingList);
        String exportIds = packingList.getExportIds();
        String[] ids = exportIds.split(",");
        for (String id : ids) {
            Export export = new Export();
            export.setId(id);
            export.setState(4);
            exportDao.updateByPrimaryKeySelective(export);
        }

    }

    //更新方法
    @Override
    public void update(PackingList packingList) {
        packingListDao.updateByPrimaryKeySelective(packingList);

    }

    @Override
    public void delete(String id) {
        packingListDao.deleteByPrimaryKey(id);

    }
}
