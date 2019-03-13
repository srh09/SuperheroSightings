/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.service;

import com.swcguild.flooringmastery.dao.AuditDaoFM;
import com.swcguild.flooringmastery.dao.OrderDaoFM;
import com.swcguild.flooringmastery.dao.ProductDaoFM;
import com.swcguild.flooringmastery.dao.TaxDaoFM;
import com.swcguild.flooringmastery.dto.Order;
import com.swcguild.flooringmastery.dto.Product;
import com.swcguild.flooringmastery.dto.Tax;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Reid
 */
public class ServiceImplFM implements ServiceFM{
    private OrderDaoFM orderDao;
    private ProductDaoFM productDao;
    private TaxDaoFM taxDao;
    private AuditDaoFM auditDao;
    
    public ServiceImplFM(OrderDaoFM orderDao, ProductDaoFM productDao, TaxDaoFM taxDao, AuditDaoFM auditDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
        this.auditDao = auditDao;
    }
    
    
    
    @Override
    public Order createOrderTemplate(int orderNumber, LocalDate currentDate, String customerName, String customerState,
            String productName, BigDecimal Area, BigDecimal productMaterialCost, BigDecimal productLaborCost, BigDecimal stateTaxRate) {
        
        Order orderTemplate = new Order(orderNumber);
        orderTemplate.setOrderDate(currentDate);
        orderTemplate.setCustomerName(customerName);
        orderTemplate.setState(customerState);
        orderTemplate.setProductName(productName);
        orderTemplate.setArea(Area);
        orderTemplate.setCostMaterialSqFt(productMaterialCost);
        orderTemplate.setCostLaborSqFt(productLaborCost);
        orderTemplate.setTaxRate(stateTaxRate);
        return orderTemplate;
    }
    
    @Override
    public Order modifyExistingOrder(Order existingOrder, String newName, String newState, String newProductName, 
            BigDecimal newArea, BigDecimal productMaterialsCost, BigDecimal productLaborCost, BigDecimal stateTaxRate) {
        
        existingOrder.setCustomerName(newName);
        existingOrder.setState(newState);
        existingOrder.setProductName(newProductName);
        existingOrder.setArea(newArea);
        existingOrder.setCostMaterialSqFt(productMaterialsCost);
        existingOrder.setCostLaborSqFt(productLaborCost);
        existingOrder.setTaxRate(stateTaxRate);
        return existingOrder;
    }
    
     @Override
    public Order runOrderCalculations(Order orderTemplate) {
        
        Order calculatedOrder = orderTemplate;
        BigDecimal totalMaterialCost = orderTemplate.getArea().multiply(orderTemplate.getCostMaterialSqFt());
        totalMaterialCost = totalMaterialCost.setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalLaborCost = orderTemplate.getArea().multiply(orderTemplate.getCostLaborSqFt());
        totalLaborCost = totalLaborCost.setScale(2, RoundingMode.HALF_UP);
        BigDecimal subtotalCost = totalMaterialCost.add(totalLaborCost);
        BigDecimal totalTax = subtotalCost.multiply(orderTemplate.getTaxRate());
        totalTax = totalTax.setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalCost = subtotalCost.add(totalTax);
        
        calculatedOrder.setTotalMaterialCost(totalMaterialCost);
        calculatedOrder.setTotalLaborCost(totalLaborCost);
        calculatedOrder.setTotalTax(totalTax);
        calculatedOrder.setTotalCost(totalCost);
        return calculatedOrder;
    }
    
    @Override
    public ArrayList<Order> removeOrder(boolean customerPermission, Order requestedOrder) {
        if (customerPermission) {
            orderDao.removeOrder(requestedOrder);
        }
        ArrayList<Order> orderList= orderDao.getOrderList();
        return orderList;
    }
    
    @Override
    public void storeOrder(Order completedOrder, boolean customerPermission) {
        if (customerPermission) {
            orderDao.addOrder(completedOrder);
            productDao.updateCounter();
        }
    }
    
    @Override
    public ArrayList<Order> getOrderList() {
        ArrayList<Order> orderList = orderDao.getOrderList();
        return orderList;
    }

    @Override
    public ArrayList<Order> getOrderListByDate(LocalDate userDate, ArrayList<Order> orderList) {
        return orderList.stream()
                .filter(s-> s.getOrderDate().isEqual(userDate))
                .collect(Collectors.toCollection(ArrayList::new)); //How do I rewrite this in the other form (s-> s.)???
    }
    
    @Override
    public Order getOrder(LocalDate userDate, int userOrderNumber) {
        ArrayList<Order> orderList = orderDao.getOrderList();
        return orderList.stream()
                .filter(s-> s.getOrderNumber() == userOrderNumber)
                .filter(s-> s.getOrderDate().isEqual(userDate))
                .findAny()
                .get();
    }

    @Override
    public BigDecimal getMaterialsCost(String productName) {
        ArrayList<Product> productList = productDao.getProductList();
        return productList.stream()
                .filter(s-> s.getProductName().equalsIgnoreCase(productName))
                .findAny()
                .get()
                .getCostMaterialFtSq();
    }

    @Override
    public BigDecimal getLaborCost(String productName) {
        ArrayList<Product> productList = productDao.getProductList();
        return productList.stream()
                .filter(s-> s.getProductName().equalsIgnoreCase(productName))
                .findAny()
                .get()
                .getCostLaborFtSq();
    }
    
    @Override
    public BigDecimal getTax(String customerState) {
        ArrayList<Tax> taxList = taxDao.getTaxList();
        return taxList.stream()
                .filter(s-> s.getStateName().equalsIgnoreCase(customerState))
                .findAny()
                .get()
                .getTaxRate();
    }

    @Override
    public int getOrderNumber() {
        int orderNumber = productDao.getOrderNumber();
        return orderNumber;
    }

    @Override
    public LocalDate getLocalDate() {
        LocalDate currentDate = orderDao.getLocalDate();
        return currentDate;
    }

    @Override
    public void loadOrders() throws IOException{
        List<File> fileList = orderDao.collectFiles();
        orderDao.loadAllFiles(fileList);
    }

    @Override
    public void loadProducts() {
        productDao.loadProducts();
        productDao.loadCounter();
    }

    @Override
    public void loadTaxes() {
        taxDao.loadTaxes();
    }
    
    @Override
    public void writeOrders(int trainingMode) throws IOException {
        if (trainingMode == 1) {
            List<File> fileList = orderDao.collectFiles();
            orderDao.purgeFiles(fileList);
            orderDao.writeOrders();
            productDao.writeCounter();
        }
    }
}
