package com.gyhqq.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gyhqq.domain.cargo.*;
import com.gyhqq.dao.cargo.*;
import com.gyhqq.service.cargo.ExportService;
import com.gyhqq.vo.ExportProductResult;
import com.gyhqq.vo.ExportResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private ExportDao exportDao;
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ContractProductDao contractProductDao;
    @Autowired
    private ExtCproductDao extCproductDao;
    @Autowired
    private ExportProductDao exportProductDao;
    @Autowired
    private ExtEproductDao extEproductDao;

    @Override
    public Export findById(String id) {
        return exportDao.selectByPrimaryKey(id);
    }

    //用第二种保存的方法再写一遍
    public void save(Export export) {
        // 保存报运单,要保存对应的报运货物,报运附件
        //1 设置报运单id
        export.setId(UUID.randomUUID().toString());
        export.setCreateTime(new Date());
        //2 获得合同id数组,把合同状态改为2
        String[] contractIds = export.getContractIds().split(",");
        // 添加合同号
        String contractNo = "";
        for (String contractId : contractIds) {
            Contract contract = contractDao.selectByPrimaryKey(contractId);
            contract.setState(2);
            contractNo += contract.getContractNo() + " ";
            contractDao.updateByPrimaryKeySelective(contract);
        }
        export.setCustomerContract(contractNo);
        //3 根据合同id获取货物,copy到报运货物中,并添加id
        ContractProductExample contractProductExample = new ContractProductExample();
        ContractProductExample.Criteria criteria = contractProductExample.createCriteria();
        criteria.andContractIdIn(Arrays.asList(contractIds));
        List<ContractProduct> contractProducts = contractProductDao.selectByExample(contractProductExample);
        int extNum = 0;
        for (ContractProduct contractProduct : contractProducts) {
            ExportProduct exportProduct = new ExportProduct();
            BeanUtils.copyProperties(contractProduct, exportProduct);
            exportProduct.setId(UUID.randomUUID().toString());
            exportProduct.setExportId(export.getId());
            exportProductDao.insertSelective(exportProduct);
            //4 在货物的循环中保存报运附件,添加id
            ExtCproductExample extCproductExample = new ExtCproductExample();
            ExtCproductExample.Criteria criteria1 = extCproductExample.createCriteria();
            criteria1.andContractProductIdEqualTo(contractProduct.getId());
            List<ExtCproduct> extCproducts = extCproductDao.selectByExample(extCproductExample);
            for (ExtCproduct extCproduct : extCproducts) {
                ExtEproduct extEproduct = new ExtEproduct();
                BeanUtils.copyProperties(extCproduct, extEproduct);
                extEproduct.setId(UUID.randomUUID().toString());
                extEproduct.setExportId(export.getId());
                extEproduct.setExportProductId(exportProduct.getId());
                extEproductDao.insertSelective(extEproduct);
                extNum++;
            }

        }
        //5 给报运单添加货物和附件的数量
        export.setProNum(contractProducts.size());
        export.setExtNum(extNum);
        //6 设置报运单状态为草稿0
        export.setState(0);
        //7 保存报运单
        exportDao.insertSelective(export);

    }

    @Override
    public void update(Export export) {
        exportDao.updateByPrimaryKeySelective(export);
        if (export.getExportProducts() != null) {
            for (ExportProduct exportProduct : export.getExportProducts()) {
                exportProductDao.updateByPrimaryKeySelective(exportProduct);
            }
        }
    }

    @Override
    public void delete(String id) {
        //根据报运单id查到合同数组,修改合同的状态为1
        Export export = exportDao.selectByPrimaryKey(id);
        String[] contractIds = export.getContractIds().split(",");
        for (String contractId : contractIds) {
            Contract contract = contractDao.selectByPrimaryKey(contractId);
            contract.setState(1);
            contractDao.updateByPrimaryKeySelective(contract);
        }
        //先根据报运单id删除报运附件
        ExtEproductExample extEproductExample = new ExtEproductExample();
        ExtEproductExample.Criteria extECriteria = extEproductExample.createCriteria();
        extECriteria.andExportIdEqualTo(id);
        List<ExtEproduct> extEproducts = extEproductDao.selectByExample(extEproductExample);
        for (ExtEproduct extEproduct : extEproducts) {
            extEproductDao.deleteByPrimaryKey(extEproduct.getId());
        }
        //再根据报运单id删除报运货物
        ExportProductExample ePExample = new ExportProductExample();
        ExportProductExample.Criteria ePCriteria = ePExample.createCriteria();
        ePCriteria.andExportIdEqualTo(id);
        List<ExportProduct> exportProducts = exportProductDao.selectByExample(ePExample);
        for (ExportProduct exportProduct : exportProducts) {
            exportProductDao.deleteByPrimaryKey(exportProduct.getId());
        }
        //删除报运单
        exportDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ExportExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<Export> list = exportDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public void updateResult(ExportResult exportResult) {

        //6 更改报运单状态
        String exportId = exportResult.getExportId();
        Export export1 = exportDao.selectByPrimaryKey(exportId);
        export1.setState(exportResult.getState());
        export1.setRemark(exportResult.getRemark());
        exportDao.updateByPrimaryKeySelective(export1);
        //7 给报运货物添加税收
        Set<ExportProductResult> products = exportResult.getProducts();
        for (ExportProductResult product : products) {
            ExportProduct exportProduct = new ExportProduct();
            exportProduct.setId(product.getExportProductId());
            exportProduct.setTax(product.getTax());
            exportProductDao.updateByPrimaryKeySelective(exportProduct);
        }

    }

    @Override
    public List<Export> findStateExport(ExportExample exportExample) {
        return exportDao.selectByExample(exportExample);
    }
}
