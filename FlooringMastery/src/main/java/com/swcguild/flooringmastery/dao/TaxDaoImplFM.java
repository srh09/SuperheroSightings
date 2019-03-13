/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Tax;
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
public class TaxDaoImplFM implements TaxDaoFM{

    private ArrayList<Tax> taxList = new ArrayList<>();
    
    public static final String TAX_FILE = "data/taxes.txt";
    public static final String DELIMITER = "::";
    
    
    @Override
    public ArrayList<Tax> getTaxList() {
        return taxList;
    }
    
    @Override
    public void loadTaxes() {
        
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            Tax currentStateTax = new Tax(currentTokens[0]);
            currentStateTax.setStateName(currentTokens[0]);
            currentStateTax.setTaxRate(new BigDecimal(currentTokens[1]));
            
            taxList.add(currentStateTax);
        }
        scanner.close();
    }
    
}
