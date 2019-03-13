/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Reid
 */
public interface Service {
    
    BigDecimal getTotalMoney();
    
    String getOrderNumber();
    
    void setOrderNumber(String orderNumber);
    
    List<Item> getItemList();
    
    Item getItem(int code);
    
    BigDecimal addMoney(String userMoney);
    
    String purchaseFunction()throws NoInventoryException, InsufficientFundsException, NoItemSelectedException;
    
    String changeFunction();
}
