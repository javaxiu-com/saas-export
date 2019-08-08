package com.gyhqq.domain.cargo;

import com.gyhqq.domain.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 合同下货物的实体类
 */

public class ContractProduct extends BaseEntity implements Serializable {

    private String id;
    private String productNo;        //货号
    private String productImage;    //图片路径
    private String productDesc;        //货描
    private String loadingRate;        //报运：装率    1/3
    private Integer boxNum;            //报运：箱数    100
    private String packingUnit;        //包装单位：PCS/SETS   支/箱
    private Integer cnumber;        //数量                            300
    private Integer outNumber;        //报运：出货数量            200
    private Integer finished;        //报运：是否完成		no
    private String productRequest;    //要求
    private Double price;            //单价
    private Double amount;            //总金额，冗余
    private Integer orderNo;        //排序号
    private String contractId;      //合同号

    private String factoryName;        //厂家名称，冗余字段
    private String factoryId;

    private List<ExtCproduct> extCproducts;    //货物和附件，一对多

    public ContractProduct() {
    }

    public ContractProduct(Object[] objs, String companyId, String companyName) {
        this.factoryName = objs[1].toString();
        this.productNo = objs[2].toString();
        this.cnumber = ((Double) objs[3]).intValue();
        this.packingUnit = objs[4].toString();
        this.loadingRate = objs[5].toString();
        this.boxNum = ((Double) objs[6]).intValue();
        this.price = (Double) objs[7];            //单价
        this.productRequest = objs[8].toString();
        this.productDesc = objs[9].toString();
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getLoadingRate() {
        return loadingRate;
    }

    public void setLoadingRate(String loadingRate) {
        this.loadingRate = loadingRate;
    }

    public Integer getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(Integer boxNum) {
        this.boxNum = boxNum;
    }

    public String getPackingUnit() {
        return packingUnit;
    }

    public void setPackingUnit(String packingUnit) {
        this.packingUnit = packingUnit;
    }

    public Integer getCnumber() {
        return cnumber;
    }

    public void setCnumber(Integer cnumber) {
        this.cnumber = cnumber;
    }

    public Integer getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(Integer outNumber) {
        this.outNumber = outNumber;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    public String getProductRequest() {
        return productRequest;
    }

    public void setProductRequest(String productRequest) {
        this.productRequest = productRequest;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public List<ExtCproduct> getExtCproducts() {
        return extCproducts;
    }

    public void setExtCproducts(List<ExtCproduct> extCproducts) {
        this.extCproducts = extCproducts;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @Override
    public String toString() {
        return "ContractProduct{" +
                "id='" + id + '\'' +
                ", productNo='" + productNo + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", loadingRate='" + loadingRate + '\'' +
                ", boxNum=" + boxNum +
                ", packingUnit='" + packingUnit + '\'' +
                ", cnumber=" + cnumber +
                ", outNumber=" + outNumber +
                ", finished=" + finished +
                ", productRequest='" + productRequest + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", orderNo=" + orderNo +
                ", contractId='" + contractId + '\'' +
                ", factoryName='" + factoryName + '\'' +
                ", factoryId='" + factoryId + '\'' +
                ", extCproducts=" + extCproducts +
                '}';
    }
}
