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
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superhero;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Reid
 */
public class ServiceSightingImpl implements ServiceSighting{
    
    private DaoLocation locationDao;
    private DaoOrganization organizationDao;
    private DaoSighting sightingDao;
    private DaoSuperhero superheroDao;
    
    public ServiceSightingImpl(DaoLocation locationDao, DaoOrganization organizationDao, DaoSighting sightingDao, DaoSuperhero superheroDao) {
        this.locationDao = locationDao;
        this.organizationDao = organizationDao;
        this.sightingDao = sightingDao;
        this.superheroDao = superheroDao;
    }

    @Override
    public Sighting createSighting(Sighting sighting) {
        Sighting newSighting = sightingDao.createSighting(sighting);
        return newSighting;
    }

    @Override
    public Sighting retrieveSighting(int sightingId) {
        Sighting sighting = sightingDao.retrieveSighting(sightingId);
        return sighting;
    }

    @Override
    public List<Sighting> retrieveAllSightings() {
        List<Sighting> sightingList = sightingDao.retrieveAllSightings();
        sightingList.forEach(s-> {
            s.setSuperhero(superheroDao.retrieveSuperhero(s.getSuperhero_id()));
        });
        sightingList.forEach(s-> {
            s.setLocation(locationDao.retrieveLocationById(s.getLocation_id()));
        });
        return sightingList;
    }

    @Override
    public Sighting updateSighting(Sighting sighting) {
        Sighting newSighting = sightingDao.updateSighting(sighting);
        return newSighting;
    }

    @Override
    public int deleteSighting(int sightingId) {
        sightingDao.deleteSighting(sightingId);
        return sightingId;
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        List<Sighting> sightingList = sightingDao.retrieveAllSightings();
        List<Sighting> sightingListDate = sightingList.stream()
                .filter(s-> s.getDate().equals(date))
                .collect(Collectors.toList());
        
        return sightingListDate;
    }
}
