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
public class ProductDaoImplFMTest {
    
    private final ProductDaoFM testDao = new ProductDaoImplFM();
    
    public ProductDaoImplFMTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Product test1 = new Product("blueSteel");
        test1.setCostLaborFtSq(new BigDecimal("10"));
        
        Product test2 = new Product("laTigre");
        test2.setCostLaborFtSq(new BigDecimal("20"));
        
        Product test3 = new Product("magnum");
        test3.setCostLaborFtSq(new BigDecimal("30"));
        
        testDao.getProductList().add(test1);
        testDao.getProductList().add(test2);
        testDao.getProductList().add(test3);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getProductList method, of class ProductDaoImplFM.
     */
    @Test
    public void testGetProductList() {
        ArrayList<Product> productList = testDao.getProductList();
        assertEquals(3, productList.size());
    }

    /**
     * Test of updateCounter method, of class ProductDaoImplFM.
     */
    @Test
    public void testUpdateCounter() {
        assertEquals(1, testDao.updateCounter());
    }
}
