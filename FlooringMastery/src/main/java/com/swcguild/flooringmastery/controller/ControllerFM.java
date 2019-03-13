/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.controller;

import com.swcguild.flooringmastery.dto.Order;
import com.swcguild.flooringmastery.service.ServiceFM;
import com.swcguild.flooringmastery.ui.UserIO;
import com.swcguild.flooringmastery.ui.UserIOImpl;
import com.swcguild.flooringmastery.ui.ViewFM;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author Reid
 */
public class ControllerFM {
    UserIO io = new UserIOImpl();
    
    private ViewFM view;
    private ServiceFM service;
    private int trainingMode = 0;
    
    public ControllerFM(ServiceFM service, ViewFM view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        
        
	boolean keepGoing = true;
	int menuSelection;
        while (trainingMode == 0) {
            try {
                trainingMode = selectMode();
            } catch (NumberFormatException e) {
                view.displayErrorMessage(e.toString());
            }
        }
        
        try {
            loadData();
        } catch (IOException e) {
            view.displayErrorMessage(e.toString());
        }
            
        while (keepGoing) {
            try{
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        saveCurrentWork();
                        break;
                    case 6:
                        keepGoing = false;
                        saveCurrentWork();
                        exitMessage();
                        break;
                    default:
                        unknownCommand();
                }
            } catch (IOException | NumberFormatException | NoSuchElementException e) {
            view.displayErrorMessage(e.toString());
            }
        }
    }
        
    private int getMenuSelection() {
            return view.printMenuAndGetSelection();
    }
        
    private void displayOrders() {
        LocalDate userDate = validateUserDate();
        ArrayList<Order> orderList = service.getOrderList();
        ArrayList<Order> filteredOrderList = service.getOrderListByDate(userDate, orderList);
        view.displayOrderList(filteredOrderList);
        if (filteredOrderList.isEmpty()) {
            view.displayAbsentDateError();
        }
    }
        
    private void addOrder() {
        int orderNumber = service.getOrderNumber();
        LocalDate currentDate = service.getLocalDate();
        String customerName = view.getCustomerName();
        String customerState = validateStateInfo();
        BigDecimal stateTaxRate = service.getTax(customerState);
        String productName = validateProductInfo();
        BigDecimal productMaterialsCost = service.getMaterialsCost(productName);
        BigDecimal productLaborCost = service.getLaborCost(productName);
        BigDecimal area = validateAreaInfo();
        
        Order customerOrderTemplate = service.createOrderTemplate(orderNumber, currentDate,
            customerName, customerState, productName, area, productMaterialsCost, productLaborCost, stateTaxRate);
        Order customerOrderComplete = service.runOrderCalculations(customerOrderTemplate);
        
        view.displayOrder(customerOrderComplete);
        boolean customerPermission = view.getPermissionStore();
        service.storeOrder(customerOrderComplete, customerPermission);
        view.displayOrderSaved(customerPermission);
    }
        
    private void editOrder() {
        LocalDate userDate = validateUserDate();
        ArrayList<Order> orderList = service.getOrderList();
        ArrayList<Order> filteredOrderList = service.getOrderListByDate(userDate, orderList);
        view.displayOrderList(filteredOrderList);
        if (filteredOrderList.isEmpty()) {
            view.displayAbsentDateError();
        }
        int userOrderNumber = validateOrderNumber(userDate);
        Order requestedOrder = service.getOrder(userDate, userOrderNumber);
        String newName = view.getCustomerNameEdit(requestedOrder);
        String newState = validateStateInfoEdit(requestedOrder);
        String newProductName = validateProductInfoEdit(requestedOrder);
        BigDecimal newArea = validateAreaInfoEdit(requestedOrder);
        BigDecimal productMaterialsCost = service.getMaterialsCost(newProductName);
        BigDecimal productLaborCost = service.getLaborCost(newProductName);
        BigDecimal stateTaxRate = service.getTax(newState);
        
        Order modifiedOrderPartial = service.modifyExistingOrder(requestedOrder, newName, 
                newState, newProductName, newArea, productMaterialsCost, productLaborCost, stateTaxRate);
        Order modifiedOrder = service.runOrderCalculations(modifiedOrderPartial);
        
        view.displayOrder(modifiedOrder);
        view.displayOrderModified();
    }
        
    private void removeOrder() {
        LocalDate userDate = validateUserDate();
        int userOrderNumber = view.getOrderNumber();
        Order requestedOrder = service.getOrder(userDate, userOrderNumber);
        view.displayOrder(requestedOrder);
        boolean customerPermission = view.getPermissionRemove();
        service.removeOrder(customerPermission, requestedOrder);
        view.displayOrderRemoved(customerPermission);
    }
        
    private void saveCurrentWork() throws IOException {
        service.writeOrders(trainingMode);
        view.displayWorkSaved(trainingMode);
        view.displayTrainingMode(trainingMode);
    }
    
    private LocalDate validateUserDate() {
        boolean invalidData = true;
        LocalDate userDate = LocalDate.parse("1900-01-01");
        while(invalidData) {
            try {
                userDate = view.getUserDate();
                return userDate;
            } catch (DateTimeParseException e) {
                view.displayInvalidDateError();
            }
        }
        return userDate;
    }
    
    private int validateOrderNumber(LocalDate userDate) {
        boolean invalidData = true;
        int userNumber = 0;
        while(invalidData) {
            try {
                userNumber = view.getOrderNumber();
                service.getOrder(userDate, userNumber);
                return userNumber;
            } catch (NoSuchElementException e) {
                view.displayOrderNumberError();
            }
        }
        return userNumber;
    }
    
    private String validateStateInfo() {
        boolean invalidData = true;
        String customerState = "";
        while(invalidData) {
            try {
                customerState = view.getCustomerState();
                service.getTax(customerState);
                return customerState;
            } catch (NoSuchElementException e) {
                view.displayInvalidStateError();
            }
        }
        return customerState;
    }
    
    private String validateStateInfoEdit(Order currentOrder) {
        boolean invalidData = true;
        String customerState = "";
        while(invalidData) {
            try {
                customerState = view.getCustomerStateEdit(currentOrder);
                service.getTax(customerState);
                return customerState;
            } catch (NoSuchElementException e) {
                view.displayInvalidStateError();
            }
        }
        return customerState;
    }
    
    private String validateProductInfo() {
        boolean invalidData = true;
        String productName = "";
        while(invalidData) {
            try {
                productName = view.getProductName();
                service.getMaterialsCost(productName);
                return productName;
            } catch (NoSuchElementException e) {
                view.displayInvalidProductError();
            }
        }
        return productName;
    }
    
    private String validateProductInfoEdit(Order currentOrder) {
        boolean invalidData = true;
        String productName = "";
        while(invalidData) {
            try {
                productName = view.getProductNameEdit(currentOrder);
                service.getMaterialsCost(productName);
                return productName;
            } catch (NoSuchElementException e) {
                view.displayInvalidProductError();
            }
        }
        return productName;
    }
    
    private BigDecimal validateAreaInfo() {
        boolean invalidData = true;
        BigDecimal area = new BigDecimal("0");
        while(invalidData) {
            try {
                area = view.getArea();
                return area;
            } catch (NumberFormatException e) {
                view.displayInvalidAreaError();
            }
        }
        return area;
    }
    
    private BigDecimal validateAreaInfoEdit(Order currentOrder) {
        boolean invalidData = true;
        BigDecimal area = new BigDecimal("0");
        while(invalidData) {
            try {
                area = view.getAreaEdit(currentOrder);
                return area;
            } catch (NumberFormatException e) {
                view.displayInvalidAreaError();
            }
        }
        return area;
    }
        
    private void unknownCommand() {
        view.displayUnknownCommand();
    }
        
    private void exitMessage() {
        view.displayExitMessage();
    }
    
    private void loadData() throws IOException {
        service.loadOrders();
        service.loadProducts();
        service.loadTaxes();
    }
    
    private int selectMode() throws NumberFormatException {
        trainingMode = view.getOperatingMode();
        return trainingMode;
    }
}
