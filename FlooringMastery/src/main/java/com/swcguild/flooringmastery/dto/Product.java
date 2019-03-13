/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author Reid
 */
public class Product {
    String productName;
    BigDecimal costMaterialFtSq;
    BigDecimal costLaborFtSq;
    
    public Product(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getCostMaterialFtSq() {
        return costMaterialFtSq;
    }

    public void setCostMaterialFtSq(BigDecimal costMaterialFtSq) {
        this.costMaterialFtSq = costMaterialFtSq;
    }

    public BigDecimal getCostLaborFtSq() {
        return costLaborFtSq;
    }

    public void setCostLaborFtSq(BigDecimal costLaborFtSq) {
        this.costLaborFtSq = costLaborFtSq;
    }
}
