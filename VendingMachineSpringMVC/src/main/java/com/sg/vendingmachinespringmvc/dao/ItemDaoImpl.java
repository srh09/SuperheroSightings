/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Reid
 */
public class ItemDaoImpl implements ItemDao{
    
    private BigDecimal totalMoney = new BigDecimal("0.00");
    private String orderNumber;
    private List<Item> itemList = new ArrayList<>();
    
    @Override
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }
    
    @Override
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
    
    @Override
    public String getOrderNumber() {
        return orderNumber;
    }
    
    @Override
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    @Override
    public List<Item> getItemList() {
        return itemList;
    }
    
    @Override
    public Item getItem(int code) {
        return itemList.stream()
                .filter(s-> s.getCode() == code)
                .findAny()
                .get();
    }
    
    @Override
    public void populateItems() {
        
        Item snickers = new Item();
        snickers.setName("Snickers");
        snickers.setCode(1);
        snickers.setCost(new BigDecimal("1.85"));
        snickers.setInventory(9);
        itemList.add(snickers);
        
        Item mms = new Item();
        mms.setName("M&Ms");
        mms.setCode(2);
        mms.setCost(new BigDecimal("1.50"));
        mms.setInventory(2);
        itemList.add(mms);
        
        Item pringles = new Item();
        pringles.setName("Pringles");
        pringles.setCode(3);
        pringles.setCost(new BigDecimal("2.10"));
        pringles.setInventory(5);
        itemList.add(pringles);
        
        Item reeses = new Item();
        reeses.setName("Reese's");
        reeses.setCode(4);
        reeses.setCost(new BigDecimal("1.85"));
        reeses.setInventory(4);
        itemList.add(reeses);
        
        Item pretzels = new Item();
        pretzels.setName("Pretzels");
        pretzels.setCode(5);
        pretzels.setCost(new BigDecimal("1.25"));
        pretzels.setInventory(9);
        itemList.add(pretzels);
        
        Item twinkies = new Item();
        twinkies.setName("Twinkies");
        twinkies.setCode(6);
        twinkies.setCost(new BigDecimal("1.95"));
        twinkies.setInventory(3);
        itemList.add(twinkies);
        
        Item doritos = new Item();
        doritos.setName("Doritos");
        doritos.setCode(7);
        doritos.setCost(new BigDecimal("1.75"));
        doritos.setInventory(11);
        itemList.add(doritos);
        
        Item almondJoy = new Item();
        almondJoy.setName("Almond Joy");
        almondJoy.setCode(8);
        almondJoy.setCost(new BigDecimal("1.85"));
        almondJoy.setInventory(0);
        itemList.add(almondJoy);
        
        Item trident = new Item();
        trident.setName("Trident");
        trident.setCode(9);
        trident.setCost(new BigDecimal("1.95"));
        trident.setInventory(6);
        itemList.add(trident);
    }
    
}
