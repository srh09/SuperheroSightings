/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Tax;
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
public class TaxDaoImplFMTest {
    
    private final TaxDaoFM testDao = new TaxDaoImplFM();
    
    public TaxDaoImplFMTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Tax testTax = new Tax("State");
        testDao.getTaxList().add(testTax);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTaxList method, of class TaxDaoImplFM.
     */
    @Test
    public void testGetTaxList() {
         ArrayList<Tax> productList = testDao.getTaxList();
        assertEquals(1, productList.size());
    }
}
