/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Order;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Reid
 */
public interface OrderDaoFM {
    
    ArrayList<Order> getOrderList();
    
    public LocalDate getLocalDate();
    
    void addOrder(Order currentOrder);
    
    void removeOrder(Order currentOrder);
    
    void writeOrders();
    
    List<File> collectFiles() throws IOException;
    
    void purgeFiles(List<File> fileList);
    
    void loadAllFiles(List<File> fileList);
    
    void loadFile(File orderFile);
    
}
