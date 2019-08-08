package com.gyhqq.vo;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "products")
public class ExportProductResult implements Serializable {
    private String exportProductId;
    private Double tax;

    public String getExportProductId() {
        return exportProductId;
    }

    public void setExportProductId(String exportProductId) {
        this.exportProductId = exportProductId;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

}
