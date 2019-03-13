/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Reid
 */
public class OrderDaoImplFMTest {
    
    private final OrderDaoFM testDao = new OrderDaoImplFM();
    
    
    public OrderDaoImplFMTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Order testOrder1 = new Order(1);
        testOrder1.setOrderDate(LocalDate.parse("2018-01-01"));
        testOrder1.setArea(new BigDecimal("25.05"));
        
        testDao.getOrderList().add(testOrder1);
        
        Order testOrder2 = new Order(2);
        testOrder1.setOrderDate(LocalDate.parse("2018-01-01"));
        testOrder1.setArea(new BigDecimal("15.60"));
        
        testDao.getOrderList().add(testOrder2);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getOrderList method, of class OrderDaoImplFM.
     */
    @Test
    public void testGetOrderList() {
        assertEquals(2, testDao.getOrderList().size());
    }
    
    @Test
    public void testAddOrder() {
        Order testOrder = new Order(3);
        ArrayList<Order> orderList = testDao.getOrderList();
        testDao.addOrder(testOrder);
        assertEquals(3, orderList.size());
    }
    
    @Test
    public void testRemoveOrder() {
        ArrayList<Order> orderList = testDao.getOrderList();
        Order testOrder = testDao.getOrderList().get(0);
        testDao.removeOrder(testOrder);
        assertEquals(1, orderList.size());
    }

    /**
     * Test of getLocalDate method, of class OrderDaoImplFM.
     */
    @Test
    public void testGetLocalDate() {
        LocalDate actual = testDao.getLocalDate();
        LocalDate expected = LocalDate.now();
        assertEquals(expected, actual);
    }
}
