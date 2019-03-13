/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery;

import com.swcguild.flooringmastery.controller.ControllerFM;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Reid
 */
public class AppFM {
    
    public static void main(String[] args) {
//        UserIO myIo = new UserIOImpl();
//        ViewFM myView = new ViewFM(myIo);
//        OrderDaoFM myOrderDao = new OrderDaoImplFM();
//        ProductDaoFM myProductDao = new ProductDaoImplFM();
//        TaxDaoFM myTaxDao = new TaxDaoImplFM();
//        AuditDaoFM myAuditDao = new AuditDaoImplFM();
//        ServiceFM myService = new ServiceImplFM(myOrderDao, myProductDao, myTaxDao, myAuditDao);
//        ControllerFM controller = new ControllerFM(myService, myView);
//        controller.run();

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ControllerFM controller = ctx.getBean("controller", ControllerFM.class);
        controller.run();
    }
}
