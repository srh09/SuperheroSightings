/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.service.ServiceLocation;
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
@RequestMapping({"/location"})
public class LocationController {
        
    ServiceLocation locationService;

    @Inject
    public LocationController(ServiceLocation locationService) {
        this.locationService = locationService;
    }
    
    @RequestMapping(value = "/displayLocations", method = RequestMethod.GET)
    public String displayLocationsPage(Model model) {
        
        List<Location> locationList = locationService.retrieveAllLocations();
        model.addAttribute("locationList", locationList);
        return "locations";
    }
    
    @RequestMapping(value = "/displayCreateLocation", method = RequestMethod.GET)
    public String displayCreateLocationPage(Model model) {
        
        Location location = new Location();
        model.addAttribute("location", location);
        return "createLocation";
    }
    
    @RequestMapping(value = "/displayViewLocation", method = RequestMethod.GET)
    public String displayViewLocationPage(HttpServletRequest request, Model model) {
        
        String locationIdParameter = request.getParameter("location_id");
        int location_id = Integer.parseInt(locationIdParameter);
        Location location = locationService.retrieveLocationById(location_id);
        model.addAttribute("location", location);
        
        return "viewLocation";
    }
    
    @RequestMapping(value = "/displayEditLocation", method = RequestMethod.GET)
    public String displayEditLocationPage(HttpServletRequest request, Model model) {
        
        String locationIdParameter = request.getParameter("location_id");
        int location_id = Integer.parseInt(locationIdParameter);
        Location location = locationService.retrieveLocationById(location_id);
        model.addAttribute("location", location);
        
        return "editLocation";
    }
    
    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {
        
        if (result.hasErrors()) {
            return "createLocation";
        }
        
        locationService.createLocation(location);
        return "redirect:displayLocations";
    }
    
    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {
        
        if (result.hasErrors()) {
            return "editLocation";
        }
        
        locationService.updateLocation(location);
        return "redirect:displayLocations";
    }
    
    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String locationIdString = request.getParameter("location_id");
        int locationId = Integer.parseInt(locationIdString);
        locationService.deleteLocation(locationId);
        return "redirect:displayLocations";
    }
}
