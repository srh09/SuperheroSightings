/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Reid
 */
public class ProductDaoImplFM implements ProductDaoFM{
    
    private ArrayList<Product> productList = new ArrayList<>();
    private int orderNumber;
    
    public static final String PRODUCT_FILE = "data/products.txt";
    public static final String COUNTER_FILE = "data/counter.txt";
    public static final String DELIMITER = "::";
    
    
    
    @Override
    public ArrayList<Product> getProductList() {
        return productList;
    }
    
    @Override
    public int getOrderNumber() {
        return orderNumber;
    }
    
    @Override
    public int updateCounter() {
        orderNumber += 1;
        return orderNumber;
    }

    @Override
    public void loadProducts() {
        
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            Product currentOrder = new Product(currentTokens[0]);
            currentOrder.setCostMaterialFtSq(new BigDecimal(currentTokens[1]));
            currentOrder.setCostLaborFtSq(new BigDecimal(currentTokens[2]));
            
            productList.add(currentOrder);
        }
        scanner.close();
    }
    
    @Override
    public void loadCounter() {
        
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(COUNTER_FILE)));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        orderNumber = Integer.parseInt(scanner.nextLine());
        scanner.close();
    }
    
    @Override
    public void writeCounter() {
        try {
	    PrintWriter out = new PrintWriter(new FileWriter(COUNTER_FILE));
	    out.println(orderNumber);
	    out.flush();
            out.close();
        } catch (IOException e) {
                System.out.println("IOException");
	}
    }
}
