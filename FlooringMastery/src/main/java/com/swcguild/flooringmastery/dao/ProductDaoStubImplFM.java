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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Reid
 */
public class ProductDaoStubImplFM implements ProductDaoFM {
    
    private ArrayList<Product> testProductList = new ArrayList<>();
    private int testOrderNumber;
    
    public static final String PRODUCT_FILE = "data/products.txt";
    public static final String COUNTER_FILE = "data/counter.txt";
    public static final String DELIMITER = "::";
    
    public ProductDaoStubImplFM() {
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
            
            testProductList.add(currentOrder);
        }
        scanner.close();
    }

    @Override
    public ArrayList<Product> getProductList() {
        return testProductList;
    }

    @Override
    public int getOrderNumber() {
        return 50;
    }

    @Override
    public int updateCounter() {
        //do nothing
        return 50;
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
            
            testProductList.add(currentOrder);
        }
        scanner.close();
    }

    @Override
    public void loadCounter() {
        //do nothing
    }

    @Override
    public void writeCounter() {
        //do nothing
    }
    
}
