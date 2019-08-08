package com.gyhqq.domain.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class Dept implements Serializable {
    private String id; // varchar(40)
    private String deptName; // varchar(50)
    //private String parentId;// varchar(40)
    private Dept parent;
    private Integer state;  //decimal(6,0)
    private String companyId;//  varchar(40)
    private String companyName;//  varchar(40)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Dept getParent() {
        return parent;
    }

    public void setParent(Dept parent) {
        this.parent = parent;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
