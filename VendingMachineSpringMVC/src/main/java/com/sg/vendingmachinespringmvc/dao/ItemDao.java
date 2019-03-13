/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Reid
 */
public interface ItemDao {
    
    BigDecimal getTotalMoney();
    
    void setTotalMoney(BigDecimal totalMoney);
    
    String getOrderNumber();
    
    void setOrderNumber(String orderNumber);
    
    List<Item> getItemList();
    
    Item getItem(int code);
    
    void populateItems();
    
}
