package com.gyhqq.domain.cargo;

import com.gyhqq.domain.BaseEntity;

import java.io.Serializable;

/**
 * 生产厂家的实体类
 */
public class Factory extends BaseEntity implements Serializable {

    private String id;
    private String ctype;            //厂家类型：货物/附件
    private String fullName;        //厂家全称
    private String factoryName;        //厂家简称
    private String contacts;        //联系人
    private String phone;            //电话
    private String mobile;            //手机
    private String fax;                //传真
    private String address;            //地址
    private String inspector;        //验货员，杰信代表
    private String remark;            //说明
    private Integer orderNo;        //排序号
    private Integer state;            //状态：1正常0停用(伪删除)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
