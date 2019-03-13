/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.ItemDao;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Reid
 */
public class ServiceImpl implements Service{
    
    ItemDao dao;
    
    private final BigDecimal DOLLAR = new BigDecimal("1.00");
    private final BigDecimal QUARTER = new BigDecimal("0.25");
    private final BigDecimal DIME = new BigDecimal("0.10");
    private final BigDecimal NICKEL = new BigDecimal("0.05");
    private final BigDecimal PENNY = new BigDecimal("0.01");
    
    @Inject
    public ServiceImpl(ItemDao dao) {
        this.dao = dao;
        dao.populateItems();
    }
    
    @Override
    public BigDecimal getTotalMoney() {
        BigDecimal totalMoney = dao.getTotalMoney();
        return totalMoney;
    }
    
    @Override
    public String getOrderNumber() {
        String orderNumber = dao.getOrderNumber();
        return orderNumber;
    }
    
    @Override
    public void setOrderNumber(String orderNumber) {
        dao.setOrderNumber(orderNumber);
    }
    
    @Override
    public List<Item> getItemList() {
        List<Item> itemList = dao.getItemList();
        return itemList;
    }
    
    @Override
    public Item getItem(int code) {
        Item item = dao.getItem(code);
        return item;
    }
    
    @Override
    public BigDecimal addMoney(String userMoney) {
        BigDecimal totalMoney = dao.getTotalMoney();
        
        if (userMoney.equals("Add Dollar")) {
            dao.setTotalMoney(totalMoney.add(DOLLAR));
        }
        if (userMoney.equals("Add Quarter")) {
            dao.setTotalMoney(totalMoney.add(QUARTER));
        }
        if (userMoney.equals("Add Dime")) {
            dao.setTotalMoney(totalMoney.add(DIME));
        }
        if (userMoney.equals("Add Nickel")) {
            dao.setTotalMoney(totalMoney.add(NICKEL));
        }
        return totalMoney;
    }
    
    @Override
    public String purchaseFunction() throws NoInventoryException, InsufficientFundsException, NoItemSelectedException{
        String message;
        String orderNumberString = dao.getOrderNumber();
        if (orderNumberString == null || orderNumberString.equals("") ) {
            throw new NoItemSelectedException("SELECT ITEM!");
        }
        
        int orderNumber = Integer.parseInt(orderNumberString);
        BigDecimal totalMoney = dao.getTotalMoney();
        Item userItem = dao.getItem(orderNumber);
        BigDecimal itemCost = userItem.getCost();
        int itemInventory = userItem.getInventory();
        BigDecimal moneyDifference = totalMoney.subtract(itemCost);
        
        if (itemInventory == 0) {
            throw new NoInventoryException("SOLD OUT!!!");
        } else if (moneyDifference.compareTo(BigDecimal.ZERO) < 0) {
            BigDecimal addAmount = moneyDifference.abs();
            throw new InsufficientFundsException("Please insert: " + addAmount);
        } else {
            dao.setTotalMoney(moneyDifference);
            userItem.setInventory(itemInventory - 1);
            message = "Thank You!!!";
        }
        
        
        return message;
    }
    
    @Override
    public String changeFunction() {
        String changeString = "";
        BigDecimal totalMoney = dao.getTotalMoney();
        BigDecimal zero = new BigDecimal("0.00");
        int quarter;
        int dime;
        int nickel;
        int penny;
        
        MathContext mc = new MathContext(2);
        BigDecimal quarterChange[] = totalMoney.divideAndRemainder(QUARTER, mc);
        quarter= quarterChange[0].intValue();
        BigDecimal dimeChange[] = quarterChange[1].divideAndRemainder(DIME, mc);
        dime = dimeChange[0].intValue();
        BigDecimal nickelChange[] = dimeChange[1].divideAndRemainder(NICKEL, mc);
        nickel = nickelChange[0].intValue();
        BigDecimal pennyChange[] = nickelChange[1].divideAndRemainder(PENNY, mc);
        penny = pennyChange[0].intValue();
        
        
        if (quarter != 0) {
            changeString += "Q " + quarter;
        }
        if (dime != 0) {
            changeString += " | D " + dime;
        }
        if (nickel != 0) {
            changeString += " | N " + nickel;
        }
        if (penny != 0) {
            changeString += " | P " + penny;
        }
        
        dao.setTotalMoney(zero);
        dao.setOrderNumber("");
        
        return changeString;
    }
}
