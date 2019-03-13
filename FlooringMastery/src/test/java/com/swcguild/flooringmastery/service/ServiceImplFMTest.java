/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.service;

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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Reid
 */
public class ServiceImplFMTest {
    
    ServiceFM service;
//    OrderDaoFM orderDao = new OrderDaoStubImplFM();
//    ProductDaoFM productDao = new ProductDaoStubImplFM();
//    TaxDaoFM taxDao = new TaxDaoStubImplFM();
//    AuditDaoFM auditDao = new AuditDaoStubImplFM();
//    
//    ServiceImplFM service = new ServiceImplFM(orderDao, productDao, taxDao, auditDao);
    
    public ServiceImplFMTest() {
        ApplicationContext ctx =  new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", ServiceFM.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createOrderTemplate method, of class ServiceImplFM.
     */
    @Test
    public void testCreateOrderTemplate() {
        
        int orderNumber = 50;
        LocalDate currentDate = LocalDate.parse("2018-01-01");
        String customerName = "Powell, Rob";
        String customerState = "MN";
        String productName = "Bamboo";
        BigDecimal Area = new BigDecimal("23.59");
        BigDecimal productMaterialCost = new BigDecimal("7.99");
        BigDecimal productLaborCost = new BigDecimal("4.00");
        BigDecimal stateTaxRate = new BigDecimal("0.072");
        Order expResult = service.getOrder(currentDate, orderNumber);
        Order result = service.createOrderTemplate(orderNumber, currentDate, customerName, customerState, productName, Area, productMaterialCost, productLaborCost, stateTaxRate);
        assertEquals(expResult.getCostMaterialSqFt(), result.getCostMaterialSqFt());
    }

    /**
     * Test of modifyExistingOrder method, of class ServiceImplFM.
     */
    @Test
    public void testModifyExistingOrder() {
        Order existingOrder = service.getOrder(LocalDate.parse("2018-01-01"), 50);
        String expResult = service.getOrder(LocalDate.parse("2018-01-01"), 50).getProductName();
        String newName = "Powell, Rob";
        String newState = "MN";
        String newProductName = "Laminate";
        BigDecimal newArea = new BigDecimal("23.59");
        BigDecimal productMaterialsCost = new BigDecimal("7.99");
        BigDecimal productLaborCost = new BigDecimal("4.00");
        BigDecimal stateTaxRate = new BigDecimal("0.072");
        
        String result = service.modifyExistingOrder(existingOrder, newName, newState, newProductName, newArea, productMaterialsCost, productLaborCost, stateTaxRate).getProductName();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of runOrderCalculations method, of class ServiceImplFM.
     */
    @Test
    public void testRunOrderCalculations() {
        Order testOrder = service.getOrder(LocalDate.parse("2018-01-01"), 50);
        service.runOrderCalculations(testOrder);
        BigDecimal expResult = new BigDecimal("303.20");
        BigDecimal result = service.getOrder(LocalDate.parse("2018-01-01"), 50).getTotalCost();
        assertEquals(expResult, result);
    }

    /**
     * Test of removeOrder method, of class ServiceImplFM.
     */
    @Test
    public void testRemoveOrder() {
        
        Order requestedOrder = service.getOrder(LocalDate.parse("2018-01-01"), 45);
        service.removeOrder(true, requestedOrder);
        ArrayList<Order> orderList = service.getOrderList();
        assertEquals(1, orderList.size());
    }

    /**
     * Test of storeOrder method, of class ServiceImplFM.
     */
    @Test
    public void testStoreOrder() {
        Order testOrder = new Order(20);
        service.storeOrder(testOrder, true);
        ArrayList<Order> orderList = service.getOrderList();
        assertEquals(3, orderList.size());
    }

    /**
     * Test of getOrderList method, of class ServiceImplFM.
     */
    @Test
    public void testGetOrderList() {
        ArrayList<Order> orderList = service.getOrderList();
        assertEquals(orderList.size(), 2);
    }

    /**
     * Test of getOrderListByDate method, of class ServiceImplFM.
     */
    @Test
    public void testGetOrderListByDate() {
        LocalDate testDate = LocalDate.parse("2018-01-01");
        ArrayList<Order> orderList = service.getOrderListByDate(testDate, service.getOrderList());
        assertEquals(orderList.size(), 2);
    }

    /**
     * Test of getOrder method, of class ServiceImplFM.
     */
    @Test
    public void testGetOrder() {
        int orderNumber = 45;
        LocalDate testDate = LocalDate.parse("2018-01-01");
        Order testOrder = service.getOrder(testDate, orderNumber);
        
        assertEquals(new BigDecimal("303.20"), testOrder.getTotalCost());
    }

    /**
     * Test of getMaterialsCost method, of class ServiceImplFM.
     */
    @Test
    public void testGetMaterialsCost() {
        BigDecimal expResult = new BigDecimal("3.99");
        BigDecimal result = service.getMaterialsCost("Laminate");
        assertEquals(expResult, result);
    }

    /**
     * Test of getLaborCost method, of class ServiceImplFM.
     */
    @Test
    public void testGetLaborCost() {
        BigDecimal expResult = new BigDecimal("2.25");
        BigDecimal result = service.getLaborCost("Laminate");
        assertEquals(expResult, result);
    }

    /**
     * Test of getTax method, of class ServiceImplFM.
     */
    @Test
    public void testGetTax() {
        BigDecimal expResult = new BigDecimal("0.068");
        BigDecimal result = service.getTax("MN");
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderNumber method, of class ServiceImplFM.
     */
    @Test
    public void testGetOrderNumber() {
        int expResult = 50;
        int result = service.getOrderNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLocalDate method, of class ServiceImplFM.
     */
    @Test
    public void testGetLocalDate() {
        LocalDate expResult = LocalDate.now();
        LocalDate result = service.getLocalDate();
        assertEquals(expResult, result);
    }
}
