package com.gyhqq.domain.system;

import java.io.Serializable;
import java.util.Objects;

public class Module implements Serializable {
    /**
     *
     */
    private String id;

    /**
     *
     */
    private String parentId;

    /**
     *
     */
    private String parentName;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private Integer layerNum;

    /**
     *
     */
    private Integer isLeaf;

    /**
     *
     */
    private String ico;

    /**
     *
     */
    private String cpermission;

    /**
     *
     */
    private String curl;

    /**
     * 0 主菜单/1 左侧菜单/2按钮/3 链接/4 状态
     */
    private Integer ctype;

    /**
     * 1启用0停用
     */
    private Integer state;

    /**
     * 从属关系
     * 0：sass系统内部菜单
     * 1：租用企业菜单
     */
    private Integer belong;

    /**
     *
     */
    private String cwhich;

    /**
     *
     */
    private Integer quoteNum;

    /**
     *
     */
    private String remark;

    /**
     *
     */
    private Integer orderNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module)) return false;
        Module module = (Module) o;
        return Objects.equals(id, module.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLayerNum() {
        return layerNum;
    }

    public void setLayerNum(Integer layerNum) {
        this.layerNum = layerNum;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getCpermission() {
        return cpermission;
    }

    public void setCpermission(String cpermission) {
        this.cpermission = cpermission;
    }

    public String getCurl() {
        return curl;
    }

    public void setCurl(String curl) {
        this.curl = curl;
    }

    public Integer getCtype() {
        return ctype;
    }

    public void setCtype(Integer ctype) {
        this.ctype = ctype;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getBelong() {
        return belong;
    }

    public void setBelong(Integer belong) {
        this.belong = belong;
    }

    public String getCwhich() {
        return cwhich;
    }

    public void setCwhich(String cwhich) {
        this.cwhich = cwhich;
    }

    public Integer getQuoteNum() {
        return quoteNum;
    }

    public void setQuoteNum(Integer quoteNum) {
        this.quoteNum = quoteNum;
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
}