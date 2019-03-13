/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Reid
 */
public class Order {
    int orderNumber;
    LocalDate orderDate;
    String customerName;
    String state;
    String productName;
    BigDecimal area;
    
    BigDecimal costMaterialSqFt;
    BigDecimal costLaborSqFt;
    BigDecimal taxRate;
    
    BigDecimal totalMaterialCost;
    BigDecimal totalLaborCost;
    BigDecimal totalTax;
    BigDecimal totalCost;
    
    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostMaterialSqFt() {
        return costMaterialSqFt;
    }

    public void setCostMaterialSqFt(BigDecimal costMaterialSqFt) {
        this.costMaterialSqFt = costMaterialSqFt;
    }

    public BigDecimal getCostLaborSqFt() {
        return costLaborSqFt;
    }

    public void setCostLaborSqFt(BigDecimal costLaborSqFt) {
        this.costLaborSqFt = costLaborSqFt;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTotalMaterialCost() {
        return totalMaterialCost;
    }

    public void setTotalMaterialCost(BigDecimal totalMaterialCost) {
        this.totalMaterialCost = totalMaterialCost;
    }

    public BigDecimal getTotalLaborCost() {
        return totalLaborCost;
    }

    public void setTotalLaborCost(BigDecimal totalLaborCost) {
        this.totalLaborCost = totalLaborCost;
    }
    
    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
    
    @Override
    public String toString() {
        return "Order#: " + orderNumber + " |Date: " + orderDate + " |Name: " + customerName + " |";
    }
}
