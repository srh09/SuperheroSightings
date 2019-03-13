/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.service;

import com.swcguild.flooringmastery.dto.Order;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Reid
 */
public interface ServiceFM {
    
    
    
    /**
     * Takes in user data and places it in the Order object
     * @param orderNumber
     * @param currentDate
     * @param customerName
     * @param customerState
     * @param productName
     * @param Area
     * @param productMaterialCost
     * @param productLaborCost
     * @param stateTaxRate
     * @return a partially completed Order
     */
    Order createOrderTemplate(int orderNumber, LocalDate currentDate, String customerName, String customerState, 
            String productName, BigDecimal Area, BigDecimal productMaterialCost, BigDecimal productLaborCost, BigDecimal stateTaxRate);
    
    /**
     * Takes in an existing order and applies user modifications
     * @param existingOrder
     * @param newName
     * @param newState
     * @param newProductName
     * @param newArea
     * @param productMaterialsCost
     * @param productLaborCost
     * @param stateTaxRate
     * @return a partially completed order
     */
    Order modifyExistingOrder(Order existingOrder, String newName, String newState, 
            String newProductName, BigDecimal newArea, BigDecimal productMaterialsCost, BigDecimal productLaborCost, BigDecimal stateTaxRate);
    
    /**
     * Completes the cost calculations on the order template
     * @param orderTemplate
     * @return a fully completed Order
     */
    Order runOrderCalculations(Order orderTemplate);
    
    /**
     * Removes a specified order
     * @param customerPermission
     * @param requestedOrder
     * @return an ArrayList of remaining orders
     */
    ArrayList<Order> removeOrder(boolean customerPermission, Order requestedOrder);
    
    /**
     * Receives a completed Order and places it in the Order DAO
     * @param completedOrder 
     * @param customerPermission 
     */
    void storeOrder(Order completedOrder, boolean customerPermission);
    
    /**
     * Retrieves a list of orders from the Order DAO
     * @return ArrayList of Orders
     */
    ArrayList<Order> getOrderList();
    
    /**
     * Takes in given date and all current orders.  Returns only orders that coincide with date.
     * @param userDate
     * @param orderList
     * @return ArrayList of sorted Orders
     */
    ArrayList<Order> getOrderListByDate(LocalDate userDate, ArrayList<Order> orderList);
    
    /**
     * Retrieves a specific Order when given Order date and number
     * @param userDate
     * @param userOrderNumber
     * @return Order
     */
    Order getOrder(LocalDate userDate, int userOrderNumber);
    
    /**
     * Finds the users Product specific materials cost based on its given name
     * @param productName
     * @return the requested SqFt materials cost
     */
    BigDecimal getMaterialsCost(String productName);
    
    
    /**
     * Finds the users Product specific labor cost based on its given name
     * @param productName
     * @return the requested SqFt labor cost
     */
    BigDecimal getLaborCost(String productName);
    
    /**
     * Finds the proper state taxes based on given state
     * @param customerState
     * @return the requested tax rate
     */
    BigDecimal getTax(String customerState);
    
    /**
     * Generate the order number based on a order counter
     * @return integer from the counter
     */
    int getOrderNumber();
    
    /**
     * Generate the current Local Date
     * @return the current Local Date
     */
    LocalDate getLocalDate();
    
    /**
     * Loads all Order information on file
     * @throws java.io.IOException
     */
    void loadOrders()throws IOException;
    
    /**
     * Loads all Product information on file
     */
    void loadProducts();
    
    /**
     * Loads all Tax information on file
     */
    void loadTaxes();
    
    /**
     * Tells the Order DAO to save all orders to file
     * @param trainingMode
     * @throws java.io.IOException
     */
    void writeOrders(int trainingMode) throws IOException;
    
}
