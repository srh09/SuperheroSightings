/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Order;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Reid
 */
public class OrderDaoStubImplFM implements OrderDaoFM {
    
    private final ArrayList<Order> testOrderList = new ArrayList<>();
    List<File> fileList = this.collectFiles();
    
    public OrderDaoStubImplFM() {
        Order testOrder = new Order(45);
        testOrder.setOrderDate(LocalDate.parse("2018-01-01"));
        testOrder.setCustomerName("Powell, Rob");
        testOrder.setState("MN");
        testOrder.setProductName("Bamboo");
        testOrder.setArea(new BigDecimal("23.59"));
        testOrder.setCostMaterialSqFt(new BigDecimal("7.99"));
        testOrder.setCostLaborSqFt(new BigDecimal("4.00"));
        testOrder.setTaxRate(new BigDecimal("0.072"));
        testOrder.setTotalMaterialCost(new BigDecimal("188.48"));
        testOrder.setTotalLaborCost(new BigDecimal("94.36"));
        testOrder.setTotalTax(new BigDecimal("20.36"));
        testOrder.setTotalCost(new BigDecimal("303.20"));
        
        testOrderList.add(testOrder);
        
        Order testOrderPartial = new Order(50);
        testOrderPartial.setOrderDate(LocalDate.parse("2018-01-01"));
        testOrderPartial.setCustomerName("Powell, Rob");
        testOrderPartial.setState("MN");
        testOrderPartial.setProductName("Bamboo");
        testOrderPartial.setArea(new BigDecimal("23.59"));
        testOrderPartial.setCostMaterialSqFt(new BigDecimal("7.99"));
        testOrderPartial.setCostLaborSqFt(new BigDecimal("4.00"));
        testOrderPartial.setTaxRate(new BigDecimal("0.072"));
        
        testOrderList.add(testOrderPartial);
    }

    @Override
    public ArrayList<Order> getOrderList() {
        return testOrderList;
    }

    @Override
    public LocalDate getLocalDate() {
        LocalDate localDate = LocalDate.now();
        return localDate;
    }

    @Override
    public void addOrder(Order currentOrder) {
        testOrderList.add(currentOrder);
    }

    @Override
    public void removeOrder(Order currentOrder) {
       testOrderList.remove(currentOrder);
    }

    @Override
    public void writeOrders() {
        //do nothing
    }

    @Override
    public List<File> collectFiles() {
        return fileList;
    }

    @Override
    public void purgeFiles(List<File> fileList) {
        //do nothing
    }

    @Override
    public void loadAllFiles(List<File> fileList) {
        //do nothing
    }

    @Override
    public void loadFile(File orderFile) {
        //do nothing
    }
}
