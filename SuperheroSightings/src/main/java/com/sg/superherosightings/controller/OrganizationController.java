/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.service.ServiceOrganization;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Reid
 */
@Controller
@RequestMapping({"/organization"})
public class OrganizationController {
        
    ServiceOrganization organizationService;

    @Inject
    public OrganizationController(ServiceOrganization organizationService) {
        this.organizationService = organizationService;
    }
    
    @RequestMapping(value = "/displayOrganizations", method = RequestMethod.GET)
    public String displayOrganizationsPage(Model model) {
        
        List<Organization> organizationList = organizationService.retrieveAllOrganizations();
        model.addAttribute("organizationList", organizationList);
        return "organizations";
    }
    
    @RequestMapping(value = "/displayCreateOrganization", method = RequestMethod.GET)
    public String displayCreateOrganizationPage(Model model) {
        
        Organization organization = new Organization();
        model.addAttribute("organization", organization);
        return "createOrganization";
    }
    
    @RequestMapping(value = "/displayViewOrganization", method = RequestMethod.GET)
    public String displayViewOrganizationPage(HttpServletRequest request, Model model) {
        
        String organizationIdString = request.getParameter("organization_id");
        int organizationId = Integer.parseInt(organizationIdString);
        Organization organization = organizationService.retrieveOrganizationById(organizationId);
        model.addAttribute("organization", organization);
        model.addAttribute("superheroList", organization.getMembers());
        
        return "viewOrganization";
    }
    
    @RequestMapping(value = "/displayEditOrganization", method = RequestMethod.GET)
    public String displayEditOrganizationPage(HttpServletRequest request, Model model) {
        
        String organizationIdString = request.getParameter("organization_id");
        int organizationId = Integer.parseInt(organizationIdString);
        Organization organization = organizationService.retrieveOrganizationById(organizationId);
        model.addAttribute("organization", organization);
        
        return "editOrganization";
    }
    
    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result) {
        
        if (result.hasErrors()) {
            return "createOrganization";
        }
        
        organizationService.createOrganization(organization);
        return "redirect:displayOrganizations";
    }
    
    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result) {
        
        if (result.hasErrors()) {
            return "createOrganization";
        }
        
        organizationService.updateOrganization(organization);
        
        return "redirect:displayOrganizations";
    }
    
    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String organizationIdString = request.getParameter("organization_id");
        int organizationId = Integer.parseInt(organizationIdString);
        organizationService.deleteOrganization(organizationId);
        return "redirect:displayOrganizations";
    }
}
