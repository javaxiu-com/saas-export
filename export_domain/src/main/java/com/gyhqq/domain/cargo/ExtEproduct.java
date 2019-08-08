package com.gyhqq.domain.cargo;

import com.gyhqq.domain.BaseEntity;

import java.io.Serializable;

/**
 * 报运单中货物附件的实体类
 */
public class ExtEproduct extends BaseEntity implements Serializable {

    private String id;
    private String productNo;
    private String productImage;
    private String productDesc;
    private Integer cnumber;
    private String packingUnit;            //PCS/SETS
    private Double price;
    private Double amount;            //自动计算: 数量x单价
    private String productRequest;
    private Integer orderNo;
    private String exportProductId;        //附件和货物，多对一
    private String exportId;
    private String factoryId;
    private String factoryName;//工厂名

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

    public Integer getCnumber() {
        return cnumber;
    }

    public void setCnumber(Integer cnumber) {
        this.cnumber = cnumber;
    }

    public String getPackingUnit() {
        return packingUnit;
    }

    public void setPackingUnit(String packingUnit) {
        this.packingUnit = packingUnit;
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

    public String getExportProductId() {
        return exportProductId;
    }

    public void setExportProductId(String exportProductId) {
        this.exportProductId = exportProductId;
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

    public String getExportId() {
        return exportId;
    }

    public void setExportId(String exportId) {
        this.exportId = exportId;
    }
}
