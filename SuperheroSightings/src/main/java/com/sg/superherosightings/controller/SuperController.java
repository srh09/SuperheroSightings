package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Superhero;
import com.sg.superherosightings.service.ServiceOrganization;
import com.sg.superherosightings.service.ServiceSuperhero;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/superhero"})
public class SuperController {
    
    ServiceSuperhero superheroService;
    ServiceOrganization organizationService;

    @Inject
    public SuperController(ServiceSuperhero superheroService, ServiceOrganization organizationService) {
        this.superheroService = superheroService;
        this.organizationService = organizationService;
    }
    
    @RequestMapping(value = "/displaySuperheroes", method = RequestMethod.GET)
    public String displaySuperheroesPage(Model model) {
        
        List<Superhero> superheroList = superheroService.retrieveAllSuperheroes();
        model.addAttribute("superheroList", superheroList);
        return "superheroes";
    }
    
    @RequestMapping(value = "/displayCreateSuperhero", method = RequestMethod.GET)
    public String displayCreateSuperheroPage(Model model) {
        
        Superhero superhero = new Superhero();
        model.addAttribute("superhero", superhero);
        
        List<Organization> organizationList = organizationService.retrieveAllOrganizations();
        model.addAttribute("organizationList", organizationList);
        return "createSuperhero";
    }
    
    @RequestMapping(value = "/displayViewSuperhero", method = RequestMethod.GET)
    public String displayViewSuperheroPage(HttpServletRequest request, Model model) {
        
        
        String superheroIdString = request.getParameter("superhero_id");
        int superheroId = Integer.parseInt(superheroIdString);
        Superhero superhero = superheroService.retrieveSuperhero(superheroId);
        model.addAttribute("superhero", superhero);
        model.addAttribute("organizationList", superhero.getOrganizationList());
        return "viewSuperhero";
    }
    
    @RequestMapping(value = "/displayEditSuperhero", method = RequestMethod.GET)
    public String displayEditSuperheroPage(HttpServletRequest request, Model model) {
        
        String superheroIdString = request.getParameter("superhero_id");
        int superheroId = Integer.parseInt(superheroIdString);
        Superhero superhero = superheroService.retrieveSuperhero(superheroId);
        model.addAttribute("superhero", superhero);
        List<Organization> organizationList = organizationService.retrieveAllOrganizations();
        model.addAttribute("organizationList", organizationList);
        return "editSuperhero";
    }
    
    @RequestMapping(value = "/createSuperhero", method = RequestMethod.POST)
    public String createSuperhero(@Valid @ModelAttribute("superhero") Superhero superhero, BindingResult result, HttpServletRequest request, Model model) {
        
        if (result.hasErrors()) {
            List<Organization> organizationList = organizationService.retrieveAllOrganizations();
            model.addAttribute("organizationList", organizationList);
            return "createSuperhero";
        }
        String[] stringArray = request.getParameterValues("orgId");
        superheroService.createSuperhero(superhero, stringArray);
        return "redirect:displaySuperheroes";
    }
    
    @RequestMapping(value = "/editSuperhero", method = RequestMethod.POST)
    public String editSuperhero(@Valid @ModelAttribute("superhero") Superhero superhero, BindingResult result, HttpServletRequest request, Model model) {
        
        if (result.hasErrors()) {
            List<Organization> organizationList = organizationService.retrieveAllOrganizations();
            model.addAttribute("organizationList", organizationList);
            return "createSuperhero";
        }
        String[] stringArray = request.getParameterValues("orgId");
        superheroService.updateSuperhero(superhero, stringArray);
        
        return "redirect:displaySuperheroes";
    }
    
    @RequestMapping(value = "/deleteSuperhero", method = RequestMethod.GET)
    public String deleteSuperhero(HttpServletRequest request) {
        String superheroIdString = request.getParameter("superhero_id");
        int superheroId = Integer.parseInt(superheroIdString);
        superheroService.deleteSuperhero(superheroId);
        return "redirect:displaySuperheroes";
    }
}
