/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import java.util.List;

/**
 *
 * @author Reid
 */
public interface DaoLocation {
    
    Location createLocation(Location location);
    
    Location retrieveLocationById(int locationId);
    
    List<Location> retrieveAllLocations();
    
    Location updateLocation(Location location);
    
    int deleteLocation(int locationId);
    
    List<Location> getLocationsBySuperhero(int superheroId);
}
