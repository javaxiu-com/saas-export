package com.gyhqq.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyhqq.common.utils.FileUploadUtils;
import com.gyhqq.domain.cargo.*;

import com.gyhqq.service.cargo.ContractProductService;
import com.gyhqq.service.cargo.ContractService;
import com.gyhqq.service.cargo.FactoryService;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cargo/contractProduct/")
public class ContractProductController extends BaseController {

    @Reference
    private ContractProductService contractProductService;
    @Reference
    private FactoryService factoryService;
    @Reference
    private ContractService contractService;

    @RequestMapping("/list")
    public String list(String contractId, Model model,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer size) {
        //货物相关
        ContractProductExample cpExample = new ContractProductExample();
        ContractProductExample.Criteria cpCriteria = cpExample.createCriteria();
        cpCriteria.andContractIdEqualTo(contractId);
        PageInfo pageInfo = contractProductService.findAll(cpExample, page, size);
        model.addAttribute("page", pageInfo);
        //工厂相关
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria fCriteria = factoryExample.createCriteria();
        fCriteria.andCtypeEqualTo("货物");
        List<Factory> factories = factoryService.selectByExample(factoryExample);
        model.addAttribute("factoryList", factories);
        model.addAttribute("contractId", contractId);
        return "cargo/product/product-list";
    }

    @RequestMapping("/edit")
    public String edit(ContractProduct contractProduct, MultipartFile productPhoto) throws IOException {
        contractProduct.setCompanyId(getLoginCompanyId());
        contractProduct.setCompanyName(getLoginCompanyName());
        if (StringUtils.isEmpty(contractProduct.getId())) {
            //保存
            if (!productPhoto.isEmpty()) {
                String photoUrl = FileUploadUtils.fileUpload(productPhoto.getBytes());
                contractProduct.setProductImage(photoUrl);
            }
            contractProductService.save(contractProduct);
        } else {
            //更新
            contractProductService.update(contractProduct);

        }
        return "redirect:/cargo/contractProduct/list.do?contractId=" + contractProduct.getContractId();
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id, Model model) {
        ContractProduct contractProduct = contractProductService.findById(id);
        model.addAttribute("contractProduct", contractProduct);
        //工厂相关
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria fCriteria = factoryExample.createCriteria();
        fCriteria.andCtypeEqualTo("货物");
        List<Factory> factories = factoryService.selectByExample(factoryExample);
        model.addAttribute("factoryList", factories);
        return "cargo/product/product-update";
    }

    @RequestMapping("/delete")
    public String delete(String id, String contractId) {

        contractProductService.delete(id, contractId);
        return "redirect:/cargo/contractProduct/list.do?contractId=" + contractId;
    }

    @RequestMapping("/toImport")
    public String toImport(String contractId, Model model, MultipartFile file) throws IOException {
        model.addAttribute("contractId", contractId);
        return "cargo/product/product-import";
    }

    @RequestMapping("/import")
    public String fileImport(String contractId, Model model, MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        List<ContractProduct> list = new ArrayList();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Object[] objects = new Object[10];
            for (int j = 1; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                objects[j] = getCellValue(cell);
            }
            ContractProduct contractProduct = new ContractProduct(objects, getLoginCompanyId(), getLoginCompanyName());
            contractProduct.setContractId(contractId);
            list.add(contractProduct);
        }
        contractProductService.toImport(list);
        model.addAttribute("contractId", contractId);
        return "redirect:/cargo/contractProduct/list.do";
    }

    public static Object getCellValue(Cell cell) {

        Object object = null;
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case STRING: {
                object = cell.getStringCellValue();
                break;
            }
            case NUMERIC: {
                if (DateUtil.isCellDateFormatted(cell)) {
                    object = cell.getDateCellValue();
                } else {
                    object = cell.getNumericCellValue();
                }
                break;
            }
            case BOOLEAN: {
                object = cell.getBooleanCellValue();
                break;
            }
            default: {

            }

        }
        return object;
    }

}
