/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.DaoLocation;
import com.sg.superherosightings.dao.DaoOrganization;
import com.sg.superherosightings.dao.DaoSighting;
import com.sg.superherosightings.dao.DaoSuperhero;
import com.sg.superherosightings.model.Location;
import java.util.List;

/**
 *
 * @author Reid
 */
public class ServiceLocationImpl implements ServiceLocation{
    
    private DaoLocation locationDao;
    private DaoOrganization organizationDao;
    private DaoSighting sightingDao;
    private DaoSuperhero superheroDao;
    
    public ServiceLocationImpl(DaoLocation locationDao, DaoOrganization organizationDao, DaoSighting sightingDao, DaoSuperhero superheroDao) {
        this.locationDao = locationDao;
        this.organizationDao = organizationDao;
        this.sightingDao = sightingDao;
        this.superheroDao = superheroDao;
    }

    @Override
    public Location createLocation(Location location) {
        Location newLocation = locationDao.createLocation(location);
        return newLocation;
    }

    @Override
    public Location retrieveLocationById(int locationId) {
        Location location = locationDao.retrieveLocationById(locationId);
        return location;
    }

    @Override
    public List<Location> retrieveAllLocations() {
        List<Location> locationList = locationDao.retrieveAllLocations();
        return locationList;
    }

    @Override
    public Location updateLocation(Location location) {
        Location newLocation = locationDao.updateLocation(location);
        return newLocation;
    }

    @Override
    public int deleteLocation(int locationId) {
        locationDao.deleteLocation(locationId);
        return locationId;
    }

    @Override
    public List<Location> getLocationsBySuperhero(int superheroId) {
        List<Location> locationList = locationDao.getLocationsBySuperhero(superheroId);
        return locationList;
    }
}
