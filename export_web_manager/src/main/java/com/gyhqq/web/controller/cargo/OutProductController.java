package com.gyhqq.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyhqq.common.utils.DownloadUtil;
import com.gyhqq.service.cargo.ContractProductService;

import com.gyhqq.vo.ContractProductVo;
import com.gyhqq.web.controller.BaseController;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/cargo/contract")
public class OutProductController extends BaseController {
    @Reference
    private ContractProductService contractProductService;

    @RequestMapping("/print")
    public String print() {

        return "cargo/print/contract-print";
    }

    @RequestMapping("/printExcel")
    public void printExcel(String inputDate, HttpServletResponse response) throws IOException {
        List<ContractProductVo> list = contractProductService.findByDate(inputDate);
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //第一行 大标题
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(1);
        cell.setCellValue(inputDate.replaceAll("-0", "-").replaceAll("-", "年") + "月份出货表");

        //第二行 小标题
        String[] title = {"", "客户", "订单号", "货号", "数量", "工厂", "工厂交期", "船期", "贸易条款"};
        row = sheet.createRow(1);
        for (int i = 1; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        // 出货表数据
        Integer index = 2;

        for (ContractProductVo vo : list) {
            for (int i = 0; i < 5000; i++) {
                row = sheet.createRow(index++);
                cell = row.createCell(1);
                cell.setCellValue(vo.getCustomName());

                cell = row.createCell(2);
                cell.setCellValue(vo.getContractNo());

                cell = row.createCell(3);
                cell.setCellValue(vo.getProductNo());

                cell = row.createCell(4);
                cell.setCellValue(vo.getCnumber());

                cell = row.createCell(5);
                cell.setCellValue(vo.getFactoryName());

                cell = row.createCell(6);
                String dp = new SimpleDateFormat("yyyy-MM-dd").format(vo.getDeliveryPeriod());
                cell.setCellValue(dp);

                cell = row.createCell(7);
                String shipTime = new SimpleDateFormat("yyyy-MM-dd").format(vo.getShipTime());
                cell.setCellValue(shipTime);

                cell = row.createCell(8);
                cell.setCellValue(vo.getTradeTerms());
            }

        }
        //excel构建完成,进行下载 ,将workbook中的内容写进一个输出流,
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        new DownloadUtil().download(outputStream, response, "出货表.xlsx");

    }

    @RequestMapping("/printExcelTemplate")
    public void printExcelTemplate(String inputDate, HttpServletResponse response) throws IOException {
        List<ContractProductVo> list = contractProductService.findByDate(inputDate);
        String path = session.getServletContext().getRealPath("/") + "/make/xlsprint/tOUTPRODUCT.xlsx";
        Workbook workbook = new XSSFWorkbook(path);
        Sheet sheet = workbook.getSheetAt(0);
        //第一行 大标题
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(1);
        cell.setCellValue(inputDate.replaceAll("-0", "-").replaceAll("-", "年") + "月份出货表");

        //第二行 小标题
        //略

        // 出货表数据
        CellStyle[] css = new CellStyle[9];
        row = sheet.getRow(2);
        for (int i = 1; i < row.getLastCellNum(); i++) {
            cell = row.getCell(i);
            css[i] = cell.getCellStyle();
        }
        Integer index = 2;
        for (ContractProductVo vo : list) {
            row = sheet.createRow(index++);
            cell = row.createCell(1);
            cell.setCellValue(vo.getCustomName());
            cell.setCellStyle(css[1]);

            cell = row.createCell(2);
            cell.setCellValue(vo.getContractNo());
            cell.setCellStyle(css[2]);

            cell = row.createCell(3);
            cell.setCellValue(vo.getProductNo());
            cell.setCellStyle(css[3]);

            cell = row.createCell(4);
            cell.setCellValue(vo.getCnumber());
            cell.setCellStyle(css[4]);

            cell = row.createCell(5);
            cell.setCellValue(vo.getFactoryName());
            cell.setCellStyle(css[5]);

            cell = row.createCell(6);
            String dp = new SimpleDateFormat("yyyy-MM-dd").format(vo.getDeliveryPeriod());
            cell.setCellValue(dp);
            cell.setCellStyle(css[6]);

            cell = row.createCell(7);
            String shipTime = new SimpleDateFormat("yyyy-MM-dd").format(vo.getShipTime());
            cell.setCellValue(shipTime);
            cell.setCellStyle(css[7]);

            cell = row.createCell(8);
            cell.setCellValue(vo.getTradeTerms());
            cell.setCellStyle(css[8]);

        }
        //excel构建完成,进行下载 ,将workbook中的内容写进一个输出流,
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        new DownloadUtil().download(outputStream, response, "出货表.xlsx");

    }

}
