/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Reid
 */
public class OrderDaoImplFM implements OrderDaoFM{
    
    private final ArrayList<Order> orderList = new ArrayList<>();
    
    public static final String ORDER_FOLDER = "data/orders";
    public static final String DELIMITER = "::";
    
    @Override
    public ArrayList<Order> getOrderList() {
        return orderList;
    }
    
    @Override
    public LocalDate getLocalDate() {
        LocalDate currentDate = LocalDate.now();
        return currentDate;
    }
    
    @Override
    public void addOrder(Order currentOrder) {
        orderList.add(currentOrder);
    }
    
    @Override
    public void removeOrder(Order currentOrder) {
        orderList.remove(currentOrder);
    }
    
    @Override
    public void writeOrders() {
	try {
	for (Order currentOrder : orderList) {
            String ORDER_FILE = "data/orders/Orders_"+ currentOrder.getOrderDate().format(DateTimeFormatter.ISO_DATE) + ".txt";
            PrintWriter out = new PrintWriter(new FileWriter(ORDER_FILE, true));
	    out.println(currentOrder.getOrderNumber() + DELIMITER
                    + currentOrder.getOrderDate().format(DateTimeFormatter.ISO_DATE) + DELIMITER 
                    + currentOrder.getCustomerName() + DELIMITER
                    + currentOrder.getState() + DELIMITER
                    + currentOrder.getProductName() + DELIMITER
                    + currentOrder.getArea().toString() + DELIMITER
                    + currentOrder.getCostMaterialSqFt() + DELIMITER
                    + currentOrder.getCostLaborSqFt() + DELIMITER
                    + currentOrder.getTaxRate() + DELIMITER
                    + currentOrder.getTotalMaterialCost() + DELIMITER
                    + currentOrder.getTotalLaborCost() + DELIMITER
                    + currentOrder.getTotalTax() + DELIMITER
                    + currentOrder.getTotalCost()
            );
	    out.flush();
            out.close();
	}
        } catch (IOException e) {
                System.out.println("IOException");
	}
    }
    
    @Override
    public List<File> collectFiles() throws IOException {
        return Files.walk(Paths.get(ORDER_FOLDER))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }
    
    @Override
    public void purgeFiles(List<File> fileList) {
        fileList.stream()
                .forEach(s -> s.delete());
    }
    
    @Override
    public void loadAllFiles(List<File> fileList) {
        fileList.stream()
               .forEach(file -> {loadFile(file);} );
    }

    @Override
    public void loadFile(File orderFile) {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(orderFile)));
            String currentLine;
            String[] currentTokens;
                while (scanner.hasNextLine()) {
                    currentLine = scanner.nextLine();
                    currentTokens = currentLine.split(DELIMITER);
                    Order currentOrder = new Order(Integer.parseInt(currentTokens[0]));
                    currentOrder.setOrderDate(LocalDate.parse(currentTokens[1]));
                    currentOrder.setCustomerName(currentTokens[2]);
                    currentOrder.setState(currentTokens[3]);
                    currentOrder.setProductName(currentTokens[4]);
                    currentOrder.setArea(new BigDecimal(currentTokens[5]));
                    currentOrder.setCostMaterialSqFt(new BigDecimal(currentTokens[6]));
                    currentOrder.setCostLaborSqFt(new BigDecimal(currentTokens[7]));
                    currentOrder.setTaxRate(new BigDecimal(currentTokens[8]));
                    currentOrder.setTotalMaterialCost(new BigDecimal(currentTokens[9]));
                    currentOrder.setTotalLaborCost(new BigDecimal(currentTokens[10]));
                    currentOrder.setTotalTax(new BigDecimal(currentTokens[11]));
                    currentOrder.setTotalCost(new BigDecimal(currentTokens[12]));
            
                    orderList.add(currentOrder);
                }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.getMessage();
            System.out.println("File not Found");
        }
    }
}
