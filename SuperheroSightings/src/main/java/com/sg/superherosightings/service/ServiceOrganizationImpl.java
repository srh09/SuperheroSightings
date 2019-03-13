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
import java.util.List;

/**
 *
 * @author Reid
 */
public class ServiceOrganizationImpl implements ServiceOrganization{
    
    private DaoLocation locationDao;
    private DaoOrganization organizationDao;
    private DaoSighting sightingDao;
    private DaoSuperhero superheroDao;
    
    public ServiceOrganizationImpl(DaoLocation locationDao, DaoOrganization organizationDao, DaoSighting sightingDao, DaoSuperhero superheroDao) {
        this.locationDao = locationDao;
        this.organizationDao = organizationDao;
        this.sightingDao = sightingDao;
        this.superheroDao = superheroDao;
    }

    @Override
    public Organization createOrganization(Organization organization) {
        Organization newOrganization = organizationDao.createOrganization(organization);
        return newOrganization;
    }

    @Override
    public Organization retrieveOrganizationById(int organizationId) {
        Organization organization = organizationDao.retrieveOrganizationById(organizationId);
        List<Superhero> superheroList = superheroDao.getSuperheroesByOrganization(organizationId);
        organization.setMembers(superheroList);
        return organization;
    }

    @Override
    public List<Organization> retrieveAllOrganizations() {
        List<Organization> organizationList = organizationDao.retrieveAllOrganizations();
        return organizationList;
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        Organization newOrganization = organizationDao.updateOrganization(organization);
        return newOrganization;
    }

    @Override
    public int deleteOrganization(int organizationId) {
        organizationDao.deleteOrganization(organizationId);
        return organizationId;
    }

    @Override
    public List<Organization> getOrganizationsBySuperhero(int superheroId) {
        List<Organization> organizationList = organizationDao.getOrganizationsBySuperhero(superheroId);
        return organizationList;
    }
    
}
