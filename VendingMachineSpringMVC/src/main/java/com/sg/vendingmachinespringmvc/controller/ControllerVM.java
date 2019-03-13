/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.NoInventoryException;
import com.sg.vendingmachinespringmvc.service.NoItemSelectedException;
import com.sg.vendingmachinespringmvc.service.Service;
import com.sg.vendingmachinespringmvc.ui.View;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Reid
 */
@Controller
public class ControllerVM {
    
    Service service;
    View view;
    
    @Inject
    public ControllerVM(Service service, View view) {
        this.service = service;
        this.view = view;
    }
        
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayContactsPage(Model model) {
        List<Item> itemList = service.getItemList();
        BigDecimal totalMoneyDisplay = service.getTotalMoney();
        String messageDisplay = view.getGeneralMessage();
        String changeDisplay = view.getChangeMessage();
        String orderNumber = service.getOrderNumber();

        model.addAttribute("itemList", itemList);
        model.addAttribute("totalMoneyDisplay", totalMoneyDisplay);
        model.addAttribute("messageDisplay", messageDisplay);
        model.addAttribute("changeDisplay", changeDisplay);
        model.addAttribute("orderNumber", orderNumber);

        return "index";
    }
    
    @RequestMapping(value = "/inputMoney", method = RequestMethod.POST)
    public String moneyIn(HttpServletRequest request, Model model) {
        String userInput = request.getParameter("moneySelection");
        service.addMoney(userInput);
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/makePurchase", method = RequestMethod.POST)
    public String userPurchase(HttpServletRequest request, Model model) {
        try {
            String generalMessage = service.purchaseFunction();
            view.setGeneralMessage(generalMessage);
        } catch (NoInventoryException | InsufficientFundsException | NoItemSelectedException e) {
            view.setGeneralMessage(e.getMessage());
        }
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/makeChange", method = RequestMethod.POST)
    public String userChange(HttpServletRequest request, Model model) {
        view.setGeneralMessage("");
        view.setChangeMessage(service.changeFunction());
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/inputItemNumber", method = RequestMethod.POST)
    public String selectItem(HttpServletRequest request, Model model) {
        String userInput = request.getParameter("itemNumber");
        service.setOrderNumber(userInput);
        
        return "redirect:/";
    }

}
