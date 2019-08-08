package com.gyhqq.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gyhqq.dao.cargo.ContractDao;
import com.gyhqq.dao.cargo.ContractProductDao;
import com.gyhqq.dao.cargo.ExtCproductDao;
import com.gyhqq.domain.cargo.*;
import com.gyhqq.service.cargo.ContractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ContractProductDao contractProductDao;
    @Autowired
    private ExtCproductDao extCproductDao;

    @Override
    public Contract findById(String id) {
        return contractDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Contract contract) {

        /**
         *
         state;//状态：0草稿 1已上报待报运	2 已报运
         proNum; //货物数量
         extNum; //附件数量
         ID
         time
         totalAmount
         */
        contract.setId(UUID.randomUUID().toString());
        contract.setCreateTime(new Date());
        contract.setState(0);
        contract.setProNum(0);
        contract.setExtNum(0);
        contract.setTotalAmount(0d);
        contractDao.insertSelective(contract);
    }

    @Override
    public void update(Contract contract) {
        contractDao.updateByPrimaryKeySelective(contract);
    }

    //稍后做
    public void delete(String id) {

        //1 删除附件 ,先根据合同id查找附件id
        ExtCproductExample extCproductExample = new ExtCproductExample();
        ExtCproductExample.Criteria extCproductExampleCriteria = extCproductExample.createCriteria();
        extCproductExampleCriteria.andContractIdEqualTo(id);
        List<ExtCproduct> extCproducts = extCproductDao.selectByExample(extCproductExample);
        for (ExtCproduct extCproduct : extCproducts) {
            extCproductDao.deleteByPrimaryKey(extCproduct.getId());
        }
        //2 删除货物
        ContractProductExample cpeExample = new ContractProductExample();
        ContractProductExample.Criteria cpeCriteria = cpeExample.createCriteria();
        cpeCriteria.andContractIdEqualTo(id);
        List<ContractProduct> contractProducts = contractProductDao.selectByExample(cpeExample);
        for (ContractProduct contractProduct : contractProducts) {
            contractProductDao.deleteByPrimaryKey(contractProduct.getId());
        }
        //3 删除合同
        contractDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ContractExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<Contract> contracts = contractDao.selectByExample(example);
        return new PageInfo(contracts);
    }

    @Override
    public List<Contract> findContractByDeliveryPeriod(String time) {
        return contractDao.findContractByDeliveryPeriod(time);
    }
}
