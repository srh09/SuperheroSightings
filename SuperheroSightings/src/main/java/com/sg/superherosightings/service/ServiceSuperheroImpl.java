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
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Superhero;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Reid
 */
public class ServiceSuperheroImpl implements ServiceSuperhero{
    
    private DaoLocation locationDao;
    private DaoOrganization organizationDao;
    private DaoSighting sightingDao;
    private DaoSuperhero superheroDao;
    
    public ServiceSuperheroImpl(DaoLocation locationDao, DaoOrganization organizationDao, DaoSighting sightingDao, DaoSuperhero superheroDao) {
        this.locationDao = locationDao;
        this.organizationDao = organizationDao;
        this.sightingDao = sightingDao;
        this.superheroDao = superheroDao;
    }
    
    @Override
    public Superhero createSuperhero(Superhero superhero, String[] stringArray) {
        if (stringArray == null) {
            List<Integer> integerList = new ArrayList<>();
            Superhero newSuperhero = superheroDao.createSuperhero(superhero, integerList);
            return newSuperhero;
        }
        List<String> stringList = Arrays.asList(stringArray);
        List<Integer> integerList = stringList.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        
        Superhero newSuperhero = superheroDao.createSuperhero(superhero, integerList);
        return newSuperhero;
    }
    
    @Override
    public Superhero retrieveSuperhero(int superheroId) {
        Superhero superhero = superheroDao.retrieveSuperhero(superheroId);
        List<Organization> organizationList = organizationDao.getOrganizationsBySuperhero(superheroId);
        superhero.setOrganizationList(organizationList);
        return superhero;
    }

    @Override
    public List<Superhero> retrieveAllSuperheroes() {
        List<Superhero> superheroList = superheroDao.retrieveAllSuperheroes();
        return superheroList;
    }

    @Override
    public Superhero updateSuperhero(Superhero superhero, String[] stringArray) {
        if (stringArray == null) {
            List<Integer> integerList = new ArrayList<>();
            Superhero newSuperhero = superheroDao.updateSuperhero(superhero, integerList);
            return newSuperhero;
        }
        List<String> stringList = Arrays.asList(stringArray);
        List<Integer> integerList = stringList.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        
        Superhero newSuperhero = superheroDao.updateSuperhero(superhero, integerList);
        return newSuperhero;
    }
    
    @Override
    public int deleteSuperhero(int superheroId) {
        superheroDao.deleteSuperhero(superheroId);
        return superheroId;
    }

    @Override
    public List<Superhero> searchSuperheroesByLocation(int locationId) {
        List<Superhero> superheroList = superheroDao.getSuperheroByLocationId(locationId);
        return superheroList;
    }

    @Override
    public List<Superhero> getSuperheroesByOrganization(int organizationId) {
        List<Superhero> superheroList = superheroDao.getSuperheroesByOrganization(organizationId);
        return superheroList;
    }
}
