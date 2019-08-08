package com.gyhqq.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gyhqq.dao.cargo.ContractDao;
import com.gyhqq.dao.cargo.ContractProductDao;
import com.gyhqq.dao.cargo.ExtCproductDao;
import com.gyhqq.domain.cargo.*;
import com.gyhqq.service.cargo.ContractProductService;
import com.gyhqq.vo.ContractProductVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class ContractProductServiceImpl implements ContractProductService {

    @Autowired
    private ContractProductDao contractProductDao;
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ExtCproductDao extCproductDao;

    @Override
    public PageInfo findAll(ContractProductExample cpExample, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<ContractProduct> list = contractProductDao.selectByExample(cpExample);
        return new PageInfo(list);
    }

    @Override
    public void save(ContractProduct contractProduct) {

        contractProduct.setId(UUID.randomUUID().toString());
        //货物总金额
        double money = 0;
        if (contractProduct.getCnumber() != null && contractProduct.getPrice() != null) {
            money = contractProduct.getCnumber() * contractProduct.getPrice();
        }
        //保存货物
        contractProduct.setAmount(money);
        contractProductDao.insertSelective(contractProduct);
        //合同总金额
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        Double totalAmount = contract.getTotalAmount();
        Contract newContract = new Contract();
        newContract.setId(contractProduct.getContractId());
        newContract.setTotalAmount(totalAmount + money);
        //合同货物种类数量
        newContract.setProNum(contract.getProNum() + 1);
        //更新合同
        contractDao.updateByPrimaryKeySelective(newContract);
    }

    @Override
    public ContractProduct findById(String id) {
        return contractProductDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(ContractProduct contractProduct) {
        //更新牵扯到货物数量和单价,也就牵扯到合同的总金额

        //计算新货物总金额
        double newMoney = 0;
        if (contractProduct.getCnumber() != null && contractProduct.getPrice() != null) {
            newMoney = contractProduct.getCnumber() * contractProduct.getPrice();
        }
        //获取旧货物总金额
        ContractProduct oldContractProduct = contractProductDao.selectByPrimaryKey(contractProduct.getId());
        Double oldAmount = oldContractProduct.getAmount();
        //更新货物
        contractProduct.setAmount(newMoney);
        contractProductDao.updateByPrimaryKeySelective(contractProduct);
        //获取旧合同总金额
        Contract oldContract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        Double oldTotalAmount = oldContract.getTotalAmount();
        //旧合同总金额-旧货物总金额+新货物总金额
        oldContract.setTotalAmount(oldTotalAmount - oldAmount + newMoney);
        //更新合同
        contractDao.updateByPrimaryKeySelective(oldContract);

    }

    //删除货物,先删除附件,计算附件的总金额,附件的种类数量,
    public void delete(String id, String contractId) {
        //1 根据货物id删除货物,在删之前先查货物的总价格
        ContractProduct contractProduct = contractProductDao.selectByPrimaryKey(id);
        Double oldPAmount = contractProduct.getAmount();
        //根据货物id查找附件
        ExtCproductExample ece = new ExtCproductExample();
        ExtCproductExample.Criteria criteria = ece.createCriteria();
        criteria.andContractProductIdEqualTo(id);
        List<ExtCproduct> extCproducts = extCproductDao.selectByExample(ece);
        //查出所有附件的总金额,查出种类数量
        int extNumber = extCproducts.size();
        double extAmounts = 0;
        for (ExtCproduct extCproduct : extCproducts) {
            extAmounts += extCproduct.getAmount();
            //删除附件
            extCproductDao.deleteByPrimaryKey(extCproduct.getId());
        }

        //删除货物
        contractProductDao.deleteByPrimaryKey(id);
        //2 修改总金额
        Contract contract = contractDao.selectByPrimaryKey(contractId);
        Double oldTotalAmount = contract.getTotalAmount();
        contract.setTotalAmount(oldTotalAmount - oldPAmount - extAmounts);
        //3 修改合同上的货物种类数量,
        contract.setProNum(contract.getProNum() - 1);
        //修改合同上的附件种类数量
        contract.setExtNum(contract.getExtNum() - extNumber);
        //4 更新合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void toImport(List<ContractProduct> list) {
        for (ContractProduct contractProduct : list) {
            save(contractProduct);
        }
    }

    @Override
    public List<ContractProductVo> findByDate(String inputDate) {
        return contractProductDao.findByDate(inputDate);

    }
}
