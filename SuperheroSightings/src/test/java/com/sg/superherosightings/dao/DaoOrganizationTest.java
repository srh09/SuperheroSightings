/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superhero;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Reid
 */
public class DaoOrganizationTest {
    
    private DaoOrganization daoOrganization;
    private DaoSighting daoSighting;
    private DaoLocation daoLocation;
    private DaoSuperhero daoSuperhero;
    
    public DaoOrganizationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
            = new ClassPathXmlApplicationContext("test-applicationContext.xml");
            daoSighting = ctx.getBean("sightingDao", DaoSighting.class);
            daoSuperhero = ctx.getBean("superheroDao", DaoSuperhero.class);
            daoLocation = ctx.getBean("locationDao", DaoLocation.class);
            daoOrganization = ctx.getBean("organizationDao", DaoOrganization.class);
            
            List<Sighting> sightings = daoSighting.retrieveAllSightings();
            for (Sighting currentSighting : sightings) {
            daoSighting.deleteSighting(currentSighting.getSighting_id());
            }
            List<Superhero> superheroes = daoSuperhero.retrieveAllSuperheroes();
            for (Superhero currentSuperhero : superheroes) {
            daoSuperhero.deleteSuperhero(currentSuperhero.getSuperhero_id());
            }
            List<Organization> organizations = daoOrganization.retrieveAllOrganizations();
            for (Organization currentOrganization : organizations) {
            daoOrganization.deleteOrganization(currentOrganization.getOrganization_id());
            }
            List<Location> locations = daoLocation.retrieveAllLocations();
            for (Location currentLocation : locations) {
            daoLocation.deleteLocation(currentLocation.getLocation_id());
            }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void createLocation() {
        Organization organization = new Organization();
        organization.setName("Avengers");
        organization.setDescription("Group of heroes");
        organization.setContactInfo("1020 SecretBase NV");
        daoOrganization.createOrganization(organization);
        Organization fromDb = daoOrganization.retrieveOrganizationById(organization.getOrganization_id());
        assertEquals(fromDb, organization);
        daoOrganization.deleteOrganization(organization.getOrganization_id());
        assertNull(daoOrganization.retrieveOrganizationById(organization.getOrganization_id()));
    }


    @Test
    public void testUpdateLocation() {
        Organization organization = new Organization();
        organization.setOrganization_id(5);
        organization.setName("Avengers");
        organization.setDescription("Group of heroes");
        organization.setContactInfo("1020 SecretBase NV");
        daoOrganization.createOrganization(organization);
        organization.setName("Justice League");
        daoOrganization.updateOrganization(organization);
        Organization fromDb = daoOrganization.retrieveOrganizationById(organization.getOrganization_id());
        assertEquals(fromDb, organization);
    }
    
    @Test
    public void getAllLocations() {
        
        Organization organization = new Organization();
        organization.setName("Avengers");
        organization.setDescription("Group of heroes");
        organization.setContactInfo("1020 SecretBase NV");
        daoOrganization.createOrganization(organization);
        
        Organization org2 = new Organization();
        org2.setName("Justice League");
        org2.setDescription("Another group of Heroes");
        org2.setContactInfo("Far Away");
        daoOrganization.createOrganization(org2);
        List<Organization> oList = daoOrganization.retrieveAllOrganizations();
        assertEquals(oList.size(), 2);
    }
    
    @Test
    public void getOrganizationsBySuperhero() {
        Organization org1 = new Organization();
        org1.setName("Avengers");
        org1.setDescription("Group of heroes");
        org1.setContactInfo("1020 SecretBase NV");
        Organization r1 = daoOrganization.createOrganization(org1);
        
        Organization org2 = new Organization();
        org2.setName("Justice League");
        org2.setDescription("Another group of Heroes");
        org2.setContactInfo("Far Away");
        Organization r2 = daoOrganization.createOrganization(org2);
        
        List<Organization> organizationList = daoOrganization.retrieveAllOrganizations();
        List<Integer> integerList = Arrays.asList(org1.getOrganization_id(), org2.getOrganization_id());
        
        Superhero s1 = new Superhero();
        s1.setName("Batman");
        s1.setDescription("Likes Bats");
        s1.setSuperpower("Rich");
        Superhero superhero = daoSuperhero.createSuperhero(s1, integerList);
        
        Superhero s2 = new Superhero();
        s2.setSuperhero_id(6);
        s2.setName("Superman");
        s2.setDescription("Changes fast");
        s2.setSuperpower("xray vision");
        daoSuperhero.createSuperhero(s2, integerList);
        
        List<Organization> testList = daoOrganization.getOrganizationsBySuperhero(s1.getSuperhero_id());
        assertEquals(testList.size(), 2);
        assertEquals(testList.get(0).getName(), "Avengers");
        assertEquals(testList.get(1).getName(), "Justice League");
    }
    
}
