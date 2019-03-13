/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superhero;
import com.sg.superherosightings.service.ServiceLocation;
import com.sg.superherosightings.service.ServiceOrganization;
import com.sg.superherosightings.service.ServiceSighting;
import com.sg.superherosightings.service.ServiceSuperhero;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

/**
 *
 * @author Reid
 */
@Controller
@RequestMapping({"/sight"})
public class SightingController {
    
    ServiceSighting sightingService;
    ServiceSuperhero superheroService;
    ServiceOrganization organizationService;
    ServiceLocation locationService;

    @Inject
    public SightingController(ServiceSighting sightingService, ServiceSuperhero superheroService, ServiceOrganization organizationService, ServiceLocation locationService) {
        this.sightingService = sightingService;
        this.superheroService = superheroService;
        this.organizationService = organizationService;
        this.locationService = locationService;
    }
    
    @RequestMapping(value = "/displayMainPage", method = RequestMethod.GET)
    public String displayMainPage(Model model) {
        
        List<Sighting> sightingList = sightingService.retrieveAllSightings();
        model.addAttribute("sightingList", sightingList);
        return "mainPage";
    }
    
    @RequestMapping(value = "/displaySightings", method = RequestMethod.GET)
    public String displaySightingsPage(Model model) {
        
        List<Sighting> sightingList = sightingService.retrieveAllSightings();
        model.addAttribute("sightingList", sightingList);
        return "sightings";
    }
    
    @RequestMapping(value = "/displayCreateSighting", method = RequestMethod.GET)
    public String displayCreateSightingPage(Model model) {
        
        Sighting sighting = new Sighting();
        model.addAttribute("sighting", sighting);
        List<Superhero> superheroList = superheroService.retrieveAllSuperheroes();
        List<Location> locationList = locationService.retrieveAllLocations();
        model.addAttribute("superheroList", superheroList);
        model.addAttribute("locationList", locationList);
        return "createSighting";
    }
    
    @RequestMapping(value = "/displayViewSighting", method = RequestMethod.GET)
    public String displayViewSightingPage(HttpServletRequest request, Model model) {
        
        String sightingIdString = request.getParameter("sighting_id");
        int sightingId = Integer.parseInt(sightingIdString);
        Sighting sighting = sightingService.retrieveSighting(sightingId);
        Superhero superhero = superheroService.retrieveSuperhero(sighting.getSuperhero_id());
        Location location = locationService.retrieveLocationById(sighting.getLocation_id());
        model.addAttribute("sighting", sighting);
        model.addAttribute("superhero", superhero);
        model.addAttribute("location", location);
        return "viewSighting";
    }
    
    @RequestMapping(value = "/displayEditSighting", method = RequestMethod.GET)
    public String displayEditSightingPage(HttpServletRequest request, Model model) {
        
        String sightingIdString = request.getParameter("sighting_id");
        int sightingId = Integer.parseInt(sightingIdString);
        Sighting sighting = sightingService.retrieveSighting(sightingId);
        model.addAttribute("sighting", sighting);
        List<Superhero> superheroList = superheroService.retrieveAllSuperheroes();
        List<Location> locationList = locationService.retrieveAllLocations();
        model.addAttribute("superheroList", superheroList);
        model.addAttribute("locationList", locationList);
        return "editSighting";
    }
    
    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(@Valid @ModelAttribute("sighting") Sighting sighting, BindingResult result, HttpServletRequest request, Model model) {
        
        if (result.hasErrors()) {
            List<Superhero> superheroList = superheroService.retrieveAllSuperheroes();
            List<Location> locationList = locationService.retrieveAllLocations();
            model.addAttribute("superheroList", superheroList);
            model.addAttribute("locationList", locationList);
            model.addAttribute("sighting", sighting);
            return "createSighting";
        }
        sighting.setSuperhero_id(Integer.parseInt(request.getParameter("superhero_id")));
        sighting.setLocation_id(Integer.parseInt(request.getParameter("location_id")));
        sightingService.createSighting(sighting);
        return "redirect:displaySightings";
    }
    
    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(@Valid @ModelAttribute("sighting") Sighting sighting, BindingResult result, HttpServletRequest request, Model model) {
        
        if (result.hasErrors()) {
            List<Superhero> superheroList = superheroService.retrieveAllSuperheroes();
            List<Location> locationList = locationService.retrieveAllLocations();
            model.addAttribute("superheroList", superheroList);
            model.addAttribute("locationList", locationList);
            model.addAttribute("sighting", sighting);
            return "editSighting";
        }
        sighting.setSuperhero_id(Integer.parseInt(request.getParameter("superhero_id")));
        sighting.setLocation_id(Integer.parseInt(request.getParameter("location_id")));
        sightingService.updateSighting(sighting);
        return "redirect:displaySightings";
    }
    
    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        String sightingIdString = request.getParameter("sighting_id");
        int sightingId = Integer.parseInt(sightingIdString);
        sightingService.deleteSighting(sightingId);
        return "redirect:displaySightings";
    }
}
