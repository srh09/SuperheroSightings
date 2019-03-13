/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author Reid
 */
public class UserIOImpl implements UserIO{
    Scanner sc = new Scanner (System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) throws NumberFormatException {
        System.out.println(prompt);
        String userInput = sc.nextLine();
        double userDouble = Double.parseDouble(userInput);
        return userDouble;
    }

    @Override
    public double readDouble(String prompt, double min, double max) throws NumberFormatException {

        boolean invalidRange = true;
        double userDouble = 0;

        while (invalidRange) {
            System.out.println(prompt);
            String userInput = sc.nextLine();
            userDouble = Double.parseDouble(userInput);

            if (userDouble >= min && userDouble <= max) {
                invalidRange = false;
            }
        }
        return userDouble;
    }

    @Override
    public float readFloat(String prompt) throws NumberFormatException {
        System.out.println(prompt);
        String userInput = sc.nextLine();
        float userFloat = Float.parseFloat(userInput);
        return userFloat;
    }

    @Override
    public float readFloat(String prompt, float min, float max) throws NumberFormatException {
        boolean invalidRange = true;
        float userFloat = 0;

        while (invalidRange) {
            System.out.println(prompt);
            String userInput = sc.nextLine();
            userFloat = Float.parseFloat(userInput);

            if (userFloat >= min && userFloat <= max) {
                invalidRange = false;
            }
        }
        return userFloat;
    }

    @Override
    public int readInt(String prompt) throws NumberFormatException {
        System.out.println(prompt);
        String userInput = sc.nextLine();
        int userInt = Integer.parseInt(userInput);
        return userInt;
    }

    @Override
    public int readInt(String prompt, int min, int max) throws NumberFormatException {
        boolean invalidRange = true;
        int userInt = 0;

        while (invalidRange) {
            System.out.println(prompt);
            String userInput = sc.nextLine();
            userInt = Integer.parseInt(userInput);
            
            if (userInt >= min && userInt <= max) {
                invalidRange = false;
            }
        }
        return userInt;
    }

    @Override
    public long readLong(String prompt) throws NumberFormatException {
        System.out.println(prompt);
        String userInput = sc.nextLine();
        long userLong = Long.parseLong(userInput);
        return userLong;
    }

    @Override
    public long readLong(String prompt, long min, long max) throws NumberFormatException {
        boolean invalidRange = true;
        long userLong = 0;

        while (invalidRange) {
            System.out.println(prompt);
            String userInput = sc.nextLine();
            userLong = Long.parseLong(userInput);
            
            if (userLong >= min && userLong <= max) {
                invalidRange = false;
            }
        }
        return userLong;
    }
    
    @Override
    public LocalDate readLocalDate(String prompt) throws DateTimeParseException {
        System.out.println(prompt);
        String userInput = sc.nextLine();
        
        LocalDate convertedDate = LocalDate.parse(userInput);
        return convertedDate;
    }
    
    @Override
    public BigDecimal readBigDecimal(String prompt) {
        System.out.println(prompt);
        String userInput = sc.nextLine();
        BigDecimal userBigDecimal = new BigDecimal(userInput);
        return userBigDecimal;
    } 

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String userInput = sc.nextLine();
        return userInput;
    }
}
