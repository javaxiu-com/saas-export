package com.gyhqq.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gyhqq.dao.cargo.ExportDao;
import com.gyhqq.dao.cargo.FinanceDao;
import com.gyhqq.dao.cargo.PackingListDao;
import com.gyhqq.domain.cargo.Export;
import com.gyhqq.domain.cargo.Finance;
import com.gyhqq.domain.cargo.FinanceExample;
import com.gyhqq.domain.cargo.PackingList;
import com.gyhqq.service.cargo.FinanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private FinanceDao financeDao;
    @Autowired
    private PackingListDao packingListDao;
    @Autowired
    private ExportDao exportDao;

    public Finance findById(String id) {
        return financeDao.selectByPrimaryKey(id);
    }

    public void save(Finance finance) {
        finance.setState(0);
        financeDao.insertSelective(finance);

        PackingList packingList = packingListDao.selectByPrimaryKey(finance.getFinanceId());
        String exportIds = packingList.getExportIds();
        String[] ids = exportIds.split(",");
        for (String id : ids) {
            Export export = new Export();
            export.setId(id);
            export.setState(7);
            exportDao.updateByPrimaryKeySelective(export);
        }
    }

    public void update(Finance finance) {
        financeDao.updateByPrimaryKeySelective(finance);
    }

    public void delete(String financeId) {
        financeDao.deleteByPrimaryKey(financeId);
    }

    public PageInfo findAll(FinanceExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<Finance> list = financeDao.selectByExample(example);
        return new PageInfo(list);
    }
}
