/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.advice;

import com.swcguild.flooringmastery.dao.AuditDaoFM;
import com.swcguild.flooringmastery.dto.Order;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Reid
 */
public class LoggingAdvice {
    AuditDaoFM auditDao;
    
    public LoggingAdvice(AuditDaoFM auditDao) {
        this.auditDao = auditDao;
    }
    
    public void createAuditEntry(JoinPoint jp, Exception e) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        auditEntry += e.getClass().getName();
        auditDao.writeAuditEntry(auditEntry);
    }
    
    public void createAuditEntryOrder(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            if (currentArg instanceof Order) {
                auditEntry += currentArg;
            }
        }
        auditDao.writeAuditEntry(auditEntry);
    }
    
}
