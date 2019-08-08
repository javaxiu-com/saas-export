package com.gyhqq.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyhqq.common.utils.BeanMapUtils;
import com.gyhqq.domain.cargo.Export;
import com.gyhqq.domain.cargo.ExportProduct;
import com.gyhqq.domain.cargo.ExportProductExample;
import com.gyhqq.domain.cargo.ShippingOrder;
import com.gyhqq.service.cargo.ExportProductService;
import com.gyhqq.service.cargo.ExportService;
import com.gyhqq.service.cargo.ShippingOrderService;
import com.gyhqq.web.controller.BaseController;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cargo/export")
public class PdfController extends BaseController {
    @Reference
    private ExportService exportService;
    @Reference
    private ExportProductService exportProductService;
    @Reference
    private ShippingOrderService shippingOrderService;

    @RequestMapping("/exportPdf")
    public void exportPdf(String id) throws Exception {

        Export export = exportService.findById(id);
        Map<String, Object> exportMap = BeanMapUtils.beanToMap(export);

        ExportProductExample exportProductExample = new ExportProductExample();
        ExportProductExample.Criteria criteria = exportProductExample.createCriteria();
        criteria.andExportIdEqualTo(id);
        List<ExportProduct> list = exportProductService.findAll(exportProductExample);
        JRBeanCollectionDataSource jrc = new JRBeanCollectionDataSource(list);
        String path = session.getServletContext().getRealPath("/") + "/jasper/export.jasper";
        JasperPrint jasperPrint = JasperFillManager.fillReport(path, exportMap, jrc);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

    /**
     * 进行电子报运单的pdf下载
     */
    @RequestMapping("/shipingPdf")
    public void shipingPdf(String shippingOrderId) throws Exception {
        //0.先查询委托单数据;
        ShippingOrder shippingOrder = shippingOrderService.findById(shippingOrderId);

        //1.加载pdf模板;(通过session找到模板在项目中的路径);
        String path = session.getServletContext().getRealPath("/") + "jasper/shipping.jasper";
        //2.填充数据(需要三个参数);
           /* 参数一:模板路径;
            参数二:需要填充到模板中的数据的类型;
            参数三:(jasper中的数据源):需要填充到模板中的list集合类型的数据;*/
        //使用工具类完成shippingOrder对象放入map集合
        Map<String, Object> map = BeanMapUtils.beanToMap(shippingOrder);
        //传递对象集合的dataSource,使用JRBeanCollectionDataSource
        //JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(eplist);
        JasperPrint jp = JasperFillManager.fillReport(path, map, new JREmptyDataSource());
        //3.输出pdf文件(预览/下载)需要两个参数;
//            参数一:jasperprint对象;
//            参数二:outputStream对象;
        JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());

    }

    @RequestMapping("/exportPdf5")
    public void exportPdf5() throws Exception {
        List list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map map = new HashMap<>();
            map.put("name", "andy" + i);
            map.put("count", 5 * i);
            list.add(map);
        }

        String path = session.getServletContext().getRealPath("/") + "/jasper/test05.jasper";
        JRBeanCollectionDataSource jrcD = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(path, new HashMap<>(), jrcD);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
    /*@RequestMapping("/exportPdf3")
    public void exportPdf3() throws Exception {
        List list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("柳岩"+i);
            user.setAge(18+i);
            user.setAddress("北京"+i);
            list.add(user);
        }

        String path = session.getServletContext().getRealPath("/")+"/jasper/test03.jasper";
        JRBeanCollectionDataSource jrcD = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(path, new HashMap<>(), jrcD);
        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
    }
    @RequestMapping("/exportPdf4")
    public void exportPdf4() throws Exception {
        List list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setName("柳岩"+i);
            user.setAge(18+i);
            user.setAddress("北京");
            list.add(user);
        }
        for (int i = 0; i < 4; i++) {
                    User user = new User();
                    user.setName("柳岩"+i);
                    user.setAge(18+i);
                    user.setAddress("上海");
                    list.add(user);
                }

        String path = session.getServletContext().getRealPath("/")+"/jasper/test04.jasper";
        JRBeanCollectionDataSource jrcD = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(path, new HashMap<>(), jrcD);
        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
    }*/

    @RequestMapping("/exportPdf2")
    public void exportPdf2() throws Exception {
        String path = session.getServletContext().getRealPath("/") + "/jasper/test02.jasper";
        Map map = new HashMap();
        map.put("name", "柳岩");
        map.put("age", 18);
        map.put("address", "北京");
        JasperPrint jasperPrint = JasperFillManager.fillReport(path, map, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

    @RequestMapping("/exportPdf1")
    public void exportPdf1() throws Exception {
        String path = session.getServletContext().getRealPath("/") + "/jasper/test01.jasper";
        JasperPrint jasperPrint = JasperFillManager.fillReport(path, new HashMap<>(), new JREmptyDataSource());
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

}
