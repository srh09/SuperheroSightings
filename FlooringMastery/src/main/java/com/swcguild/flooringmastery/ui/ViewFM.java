/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.ui;

import com.swcguild.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 *
 * @author Reid
 */
public class ViewFM {
    private UserIO io;
    
    public ViewFM(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Display Orders");
        io.print("2. Add Order");
        io.print("3. Edit Order");
        io.print("4. Remove Order");
        io.print("5. Save Current Work");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices", 1, 6);
    }
    
    public String getCustomerName() {
        String lastName = io.readString("Please enter your Last name:");
        String firstName = io.readString("Please enter your First name:");
        String customerName = lastName + ", " + firstName;
        return customerName;
    }
    
    public String getCustomerState() {
        String customerState = io.readString("Please enter your state abbreviation (MN, KY):");
        return customerState;
    }
    
    public String getProductName() {
        String productName = io.readString("Please enter the product name (Laminate, Hardwood):");
        return productName;
    }
    
    public BigDecimal getArea() {
        BigDecimal totalArea = io.readBigDecimal("Please enter the total area in SqFt that you want to buy (42.5):");
        return totalArea;
    }
    
    public LocalDate getUserDate() throws DateTimeParseException {
        LocalDate userInput = io.readLocalDate("Please enter a date (yyyy-mm-dd):");
        return userInput;
    }
    
    public int getOrderNumber() {
        int userInput = io.readInt("Please enter the order number (27):");
        return userInput;
    }
    
    public String getCustomerNameEdit(Order currentOrder) {
        String currentName = currentOrder.getCustomerName();
        String newName = io.readString("Please enter a new name or press enter to skip: (" + currentName + ")");
        if (newName.equals("")) {
            newName = currentName;
        }
        return newName;
    }
    
    public String getCustomerStateEdit(Order currentOrder) {
        String currentState = currentOrder.getState();
        String newState = io.readString("Please enter a new state or press enter to skip: (" + currentState + ")");
        if (newState.equals("")) {
            newState = currentState;
        }
        return newState;
    }
    
    public String getProductNameEdit(Order currentOrder) {
        String currentProductName = currentOrder.getProductName();
        String newProductName = io.readString("Please enter a new product name or press enter to skip: (" + currentProductName + ")");
        if (newProductName.equals("")) {
            newProductName = currentProductName;
        }
        return newProductName;
    }
    
    public BigDecimal getAreaEdit(Order currentOrder) {
        BigDecimal currentArea = currentOrder.getArea();
        BigDecimal newArea;
        String newAreaString = io.readString("Please enter a new total area or press enter to skip: (" + currentArea + ")");
        if (newAreaString.equals("")) {
            newArea = currentArea;
        } else {
            newArea = new BigDecimal(newAreaString);
        }
        return newArea;
    }
    
    public boolean getPermissionStore() {
        boolean userChoice;
        String userInput = io.readString("Do you want to store this order? (y/n)");
        if (userInput.equalsIgnoreCase("y")) {
            userChoice = true;
        } else {
            userChoice = false;
        }
        return userChoice;
    }
    
    public boolean getPermissionRemove() {
        boolean userChoice;
        
        String userInput = io.readString("Do you want to remove this order? (y/n)");
        if (userInput.equalsIgnoreCase("y")) {
            userChoice = true;
        } else {
            userChoice = false;
        }
        return userChoice;
    }
    
    public int getOperatingMode() throws NumberFormatException {
        int userInt = io.readInt("Press (1) to enter Prod mode or (2) to enter Training mode:", 1, 2);
        return userInt;
    }
    
    public void displayOrderList(ArrayList<Order> orderList) {
        for (Order currentOrder : orderList) {
            io.print("#" + currentOrder.getOrderNumber() + " " + currentOrder.getOrderDate() + " " + currentOrder.getCustomerName());
        }
    }
    
    public void displayOrder(Order customerOrder) {
        io.print("Order Number:        " + customerOrder.getOrderNumber());
        io.print("Order Date:          " + customerOrder.getOrderDate().format(DateTimeFormatter.ISO_DATE));
        io.print("Customer Name:       " + customerOrder.getCustomerName());
        io.print("Customer State:      " + customerOrder.getState());
        io.print("Product Name:        " + customerOrder.getProductName());
        io.print("Total Area:          " + customerOrder.getArea());
        io.print("Material Cost SqFt:  " + customerOrder.getCostMaterialSqFt());
        io.print("Labor Cost SqFt:     " + customerOrder.getCostLaborSqFt());
        io.print("State Tax Rate:      " + customerOrder.getTaxRate());
        io.print("Total Material Cost: " + customerOrder.getTotalMaterialCost());
        io.print("Total Labor Cost:    " + customerOrder.getTotalLaborCost());
        io.print("Total Tax Cost:      " + customerOrder.getTotalTax());
        io.print("Total Cost:          " + customerOrder.getTotalCost());
    }
    
    public void displayOrderSaved(boolean customerPermission) {
        if (customerPermission) {
            io.print("--Order Created--");
        } else {
            io.print("--Order Erased--");
        }
    }
    
    public void displayWorkSaved(int trainingMode) {
        if (trainingMode == 1) {
            io.print("Your work has been saved!");
        }
    }
    
    public void displayTrainingMode(int trainingMode) {
        if (trainingMode == 2) {
            io.print("Unable to save in Training Mode.");
        }
    }
    
    public void displayOrderModified() {
        io.print("--Order Modified--");
    }
    
    public void displayOrderRemoved(boolean customerPermission) {
        if (customerPermission) {
            io.print("--Order Removed--");
        } else {
            io.print("--Order Retained--");
        }
    }
    
    public void displayErrorMessage(String errorMessage) {
        io.print("=== ERROR ===");
        io.print(errorMessage);
    }
    
    public void displayAbsentDateError() {
        io.print("No orders exist for this date");
    }
    
    public void displayOrderNumberError() {
        io.print("Please choose a valid order number from the list.");
    }
    
    public void displayInvalidDateError() {
        io.print("You did not enter a valid date ex.(yyyy-mm-dd)");
    }
    
    public void displayInvalidStateError() {
        io.print("You did not enter a valid state abbreviation ex.(NY)");
    }
    
    public void displayInvalidProductError() {
        io.print("You did not enter a valid product name ex.(Cork)");
    }
    
    public void displayInvalidAreaError() {
        io.print("You must enter a valid decimal ex.(34.3)");
    }
    
    public void displayUnknownCommand() {
        io.print("Unknown Command");
    }
    
    public void displayExitMessage() {
        io.print("Goodbye!");
    }
    
}
