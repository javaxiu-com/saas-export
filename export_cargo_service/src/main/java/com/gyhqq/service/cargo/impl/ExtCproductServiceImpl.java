package com.gyhqq.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gyhqq.dao.cargo.ContractDao;
import com.gyhqq.dao.cargo.ExtCproductDao;
import com.gyhqq.domain.cargo.Contract;
import com.gyhqq.domain.cargo.ExtCproduct;
import com.gyhqq.domain.cargo.ExtCproductExample;
import com.gyhqq.service.cargo.ExtCproductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class ExtCproductServiceImpl implements ExtCproductService {
    @Autowired
    private ExtCproductDao extCproductDao;
    @Autowired
    private ContractDao contractDao;

    @Override
    public PageInfo findAll(ExtCproductExample extCproductExample, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<ExtCproduct> list = extCproductDao.selectByExample(extCproductExample);
        return new PageInfo(list);
    }

    @Override
    public void save(ExtCproduct extCproduct) {
        extCproduct.setId(UUID.randomUUID().toString());
        double money = 0;
        //1 计算附件总金额,并保存
        if (!StringUtils.isEmpty(extCproduct.getPrice()) && !StringUtils.isEmpty(extCproduct.getCnumber())) {
            money = extCproduct.getPrice() * extCproduct.getCnumber();
        }
        extCproduct.setAmount(money);
        //2 保存附件
        extCproductDao.insertSelective(extCproduct);
        //3 获取合同总金额
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        Double oldTotalAmount = contract.getTotalAmount();
        //4 修改合同总金额
        contract.setTotalAmount(oldTotalAmount + money);
        //5 合同中的附件数加一
        contract.setExtNum(contract.getExtNum() + 1);
        //6 更新合同
        contractDao.updateByPrimaryKeySelective(contract);

    }

    @Override
    public ExtCproduct findById(String id) {
        ExtCproduct extCproduct = extCproductDao.selectByPrimaryKey(id);
        return extCproduct;
    }

    @Override
    public void update(ExtCproduct extCproduct) {
        //计算附件的总价格,并保存
        double money = 0;
        if (!StringUtils.isEmpty(extCproduct.getPrice()) && !StringUtils.isEmpty(extCproduct.getCnumber())) {
            money = extCproduct.getPrice() * extCproduct.getCnumber();
        }
        ExtCproduct oldExtCproduct = extCproductDao.selectByPrimaryKey(extCproduct.getId());
        Double oldAmount = oldExtCproduct.getAmount();
        extCproduct.setAmount(money);
        extCproductDao.updateByPrimaryKeySelective(extCproduct);
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        Double oldTotalAmount = contract.getTotalAmount();
        contract.setTotalAmount(oldTotalAmount - oldAmount + money);
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void delete(String id) {
        ExtCproduct extCproduct = extCproductDao.selectByPrimaryKey(id);
        //1 查找附件的总金额
        Double oldAmount = extCproduct.getAmount();
        //2 删除附件
        extCproductDao.deleteByPrimaryKey(id);
        //3 查找合同的总金额
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        Double oldTotalAmount = contract.getTotalAmount();
        //4 修改合同的总金额,减去以前的金额
        contract.setTotalAmount(oldTotalAmount - oldAmount);
        //5 修改合同中附件的数量
        contract.setExtNum(contract.getExtNum() - 1);
        //6 更新合同
        contractDao.updateByPrimaryKeySelective(contract);
    }
}
