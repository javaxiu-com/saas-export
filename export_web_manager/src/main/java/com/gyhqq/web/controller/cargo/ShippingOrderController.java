package com.gyhqq.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyhqq.domain.cargo.*;
import com.gyhqq.service.cargo.*;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import com.gyhqq.service.cargo.ExportProductService;
import com.gyhqq.service.cargo.PackingListService;
import com.gyhqq.service.cargo.ShippingOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cargo/shipping")
public class ShippingOrderController extends BaseController {

    @Reference
    private ShippingOrderService shippingOrderService;
    @Reference
    private PackingListService packingListService;
    @Reference
    private ExportProductService exportProductService;

    /**
     * 进入合同管理的页面
     * 合同管理是把购销合同模块里state=1的查询出来再分页显示的
     * 应该是点击了已上报就跳转到此页面,应该是装箱单那边写
     @RequestMapping("/contractList") public String contractList(@RequestParam(defaultValue = "1") int page,
     @RequestParam(defaultValue = "5") int size){

     //购销合同模块里state=1的查询出来
     ContractExample example=new ContractExample();
     ContractExample.Criteria criteria = example.createCriteria();
     criteria.andCompanyIdEqualTo(getLoginCompanyId());
     criteria.andStateEqualTo(1);
     PageInfo info = contractService.findAll(example, page, size);
     request.setAttribute("page",info);
     return "cargo/export/export-contractList";
     }*/
    /**
     * 点击"委托管理" 的菜单进入报运单列表list
     * 查询所有的委托单并分页
     */
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "5") int size) {
        ShippingOrderExample shippingOrderExample = new ShippingOrderExample();
        ShippingOrderExample.Criteria shipcriteria = shippingOrderExample.createCriteria();
        shipcriteria.andCompanyIdEqualTo(getLoginCompanyId());
        PageInfo all = shippingOrderService.findAll(shippingOrderExample, page, size);
        request.setAttribute("page", all);
        return "cargo/shippingorder/shipping-list";
    }

    /**
     * 在合同管选中购销合同,点击报运,进入新增报运单界面
     * 参数: 多个同名参数(购销合同id)
     * 在springmvc中:数组形式接受多个同名id,String id[]
     * 在web规范中,使用字符串接受(中间用逗号隔开多个id)
     */
    /*@RequestMapping("/toExport")
    public String toExport(String shippingOrderId){
        request.setAttribute("shippingOrderId",shippingOrderId);
        return "cargo/shippingorder/shipping-toExport";
    }
*/
    //点击保存委托单
    @RequestMapping("/toAdd")
    public String toAdd(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "5") int size) {
        //2.显示所有不是草稿状态的装箱单和状态
        PackingListExample packingListExample = new PackingListExample();
        PackingListExample.Criteria criteria = packingListExample.createCriteria();
        criteria.andCompanyIdEqualTo(getLoginCompanyId());
        //criteria.andStateEqualTo(1);
        PageInfo all = packingListService.findAll(page, size, packingListExample);
        request.setAttribute("page", all);
        return "cargo/shippingorder/shipping-add";
    }

    /**
     * 点击保存按钮,进行保存委托单操作:
     */
    @RequestMapping("/edit")
    public String edit(ShippingOrder shippingOrder, String PackingListId) {
        //System.out.println("aaaaaaaaaa"+shippingOrder);
        //System.out.println("aaaaaaaaaa"+PackingListId);
        shippingOrder.setCompanyId(getLoginCompanyId());
        shippingOrder.setCompanyName(getLoginCompanyName());
        if (StringUtils.isEmpty(shippingOrder.getShippingOrderId())) {
            shippingOrder.setCheckBy(getLoginUser().getUserName());
            shippingOrder.setCreateBy(getLoginUser().getId());
            shippingOrder.setCreateDept(getLoginUser().getDeptId());
            shippingOrder.setShippingOrderId(PackingListId);
            //为空,就是正在进行保存
            shippingOrderService.save(shippingOrder);
            PackingList packingList = new PackingList();
            packingList.setPackingListId(PackingListId);
            packingList.setState(2);
            packingListService.update(packingList);
        } else {
            shippingOrder.setUpdateBy(getLoginUser().getUserName());
            shippingOrderService.update(shippingOrder);
        }
        return "redirect:/cargo/shipping/list.do";
    }

    /**
     * 进入委托单修改页面
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(String shippingOrderId) {
        //1.根据id查询委托单shippingOrder信息
        ShippingOrder shippingOrder = shippingOrderService.findById(shippingOrderId);
        request.setAttribute("shippingOrder", shippingOrder);
        //2.显示依赖的本条装箱单和状态
        PackingList packingList = packingListService.findById(shippingOrderId);
        request.setAttribute("page", packingList);
        return "cargo/shippingorder/shipping-update";
    }

    /**
     * 进入委托单修改页面
     */
    @RequestMapping("/toView")
    public String toView(String shippingOrderId) {
        //1.根据id查询委托单shippingOrder信息
        ShippingOrder shippingOrder = shippingOrderService.findById(shippingOrderId);
        request.setAttribute("shippingOrder", shippingOrder);
        //2.显示依赖的本条装箱单和状态
        PackingList packingList = packingListService.findById(shippingOrderId);
        request.setAttribute("page", packingList);
        return "cargo/shippingorder/shipping-view";
    }

    /**
     * 提交方法:
     * 参数:报运单的id
     * 将报运单状态由0改成1
     */
    @RequestMapping("/submit")
    public String submit(String shippingOrderId) {
        ShippingOrder shippingOrder = new ShippingOrder();
        shippingOrder.setShippingOrderId(shippingOrderId);
        shippingOrder.setState(1);
        shippingOrderService.update(shippingOrder);
        return "redirect:/cargo/shipping/list.do";
    }

    /**
     * 取消方法:
     * 参数:报运单的id
     * 将报运单状态由1改成0
     */
    @RequestMapping("/cancel")
    public String cancel(String shippingOrderId) {
        ShippingOrder shippingOrder = new ShippingOrder();
        shippingOrder.setShippingOrderId(shippingOrderId);
        shippingOrder.setState(0);
        shippingOrderService.update(shippingOrder);
        return "redirect:/cargo/shipping/list.do";
    }

    /**
     * exportE点击电子报运
     * 参数:报运单id
     */
    /*@RequestMapping("/exportE")
    public String exportE(String id){
        //1.根据报运单id查询报运单对象;
        Export export = exportService.findById(id);
        //2.根据报运单id查询报运单对应的商品类对象;
        ExportProductExample epExample = new ExportProductExample();
        ExportProductExample.Criteria epcriteria = epExample.createCriteria();
        epcriteria.andExportIdEqualTo(id);
        List<ExportProduct> eps = exportProductService.findAll(epExample);
        //3.创建海关报运的报运单对象;
        ExportVo exportVo = new ExportVo();
        //4.将自己的报运单对象封装到海关报运单中;同名参数使用工具类copy,id进行set方法;
        BeanUtils.copyProperties(export,exportVo);
        exportVo.setExportId(id);
        //5.创建海关报运商品对象集合;
        List<ExportProductVo> epsV=new ArrayList<ExportProductVo>();
        //6.将自己的商品对象循环封装到海关报运商品对象中;同名参数使用工具类copy,报运单id和商品id进行set方法;
        for (ExportProduct ep : eps) {
            ExportProductVo epV = new ExportProductVo();
            BeanUtils.copyProperties(ep,epV);
            epV.setExportId(id);
            epV.setExportProductId(ep.getId());
            epsV.add(epV);
        }
        //7.将海关报运商品对象放入海关报运对象中;
        exportVo.setProducts(epsV);
        //8.通过工具类请求路径,再用post方式将海关报运对象传过去;
        WebClient webClient=WebClient.create("http://localhost:8080/jk_export_war/ws/export/user");
        webClient.post(exportVo);
        export.setState(3);//已发送
        exportService.update(export);
       9.通过工具类请求路径,携带参数,再用get方法接收海关回传的数据(1.报运是否成功;2.税金);
        webClient = WebClient.create("http://localhost:8080/jk_export_war/ws/export/user/" + id);
        ExportResult result = webClient.get(ExportResult.class);
        //System.out.println("---"+result.getProducts().size());
        //10.调用自己写的service方法进行修改报运单状态,修改税金;
        exportService.updateExportResult(result);*//*
        return "redirect:/cargo/export/list.do";
    }*/
    @RequestMapping("/delete")
    public String delete(String shippingOrderId) {
        shippingOrderService.delete(shippingOrderId);
        return "redirect:/cargo/shipping/list.do";
    }
}
