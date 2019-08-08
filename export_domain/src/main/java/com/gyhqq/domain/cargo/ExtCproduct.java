package com.gyhqq.domain.cargo;

import com.gyhqq.domain.BaseEntity;
import lombok.ToString;

import java.io.Serializable;

/**
 * 合同下货物的附件
 */
@ToString
public class ExtCproduct extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String productNo;            //产品号
    private String productImage;        //图片
    private String productDesc;            //产品描述
    private String packingUnit;            //  包装单位   PCS/SETS
    private Integer cnumber;            //数量
    private Double price;                //单价
    private Double amount;                //总金额 　自动计算: 数量x单价
    private String productRequest;        //要求
    private Integer orderNo;           //排序号
    private String contractProductId;
    private String factoryId;
    private String factoryName;//工厂名
    private String contractId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProductRequest() {
        return productRequest;
    }

    public void setProductRequest(String productRequest) {
        this.productRequest = productRequest;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getContractProductId() {
        return contractProductId;
    }

    public void setContractProductId(String contractProductId) {
        this.contractProductId = contractProductId;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
}
