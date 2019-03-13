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
import java.time.LocalDate;
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
public class DaoLocationTest {
    
    private DaoOrganization daoOrganization;
    private DaoSighting daoSighting;
    private DaoLocation daoLocation;
    private DaoSuperhero daoSuperhero;
    
    public DaoLocationTest() {
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
    public void createRetrieveDeleteLocation() {
        Location nl = new Location();
        nl.setName("Gotham");
        nl.setDescription("A fictional version of Chicago");
        nl.setAddress("1020 MainStreet IL");
        nl.setCoordinates("Lat 21-35.1N Long 087-15W");
        daoLocation.createLocation(nl);
        Location fromDb = daoLocation.retrieveLocationById(nl.getLocation_id());
        assertEquals(fromDb, nl);
        daoLocation.deleteLocation(nl.getLocation_id());
        assertNull(daoLocation.retrieveLocationById(nl.getLocation_id()));
    }


    @Test
    public void testUpdateLocation() {
        Location nl = new Location();
        nl.setLocation_id(5);
        nl.setName("Gotham");
        nl.setDescription("A fictional version of Chicago");
        nl.setAddress("1020 MainStreet IL");
        nl.setCoordinates("Lat 31-35.1N Long 087-15W");
        daoLocation.createLocation(nl);
        nl.setName("Metropolis");
        daoLocation.updateLocation(nl);
        Location fromDb = daoLocation.retrieveLocationById(nl.getLocation_id());
        assertEquals(fromDb, nl);
    }
    
    @Test
    public void getAllLocations() {
        
        Location nl = new Location();
        nl.setLocation_id(6);
        nl.setName("Gotham");
        nl.setDescription("A fictional version of Chicago");
        nl.setAddress("1020 MainStreet IL");
        nl.setCoordinates("Lat 41-35.1N Long 087-15W");
        daoLocation.createLocation(nl);
        
        Location n2 = new Location();
        n2.setLocation_id(6);
        n2.setName("Metropolis");
        n2.setDescription("A fictional version of New York");
        n2.setAddress("1020 MainStreet IL");
        n2.setCoordinates("Lat 51-35.1N Long 087-15W");
        daoLocation.createLocation(n2);
        List<Location> cList = daoLocation.retrieveAllLocations();
        assertEquals(cList.size(), 2);
    }
    
    @Test
    public void getLocationsBySuperhero() {
        List<Integer> nullList = null;
        Superhero s1 = new Superhero();
        s1.setName("Batman");
        s1.setDescription("Likes Bats");
        s1.setSuperpower("Rich");
        daoSuperhero.createSuperhero(s1, nullList);
        
        Superhero s2 = new Superhero();
        s2.setSuperhero_id(6);
        s2.setName("Superman");
        s2.setDescription("Changes fast");
        s2.setSuperpower("xray vision");
        daoSuperhero.createSuperhero(s2, nullList);
        
        Location nl = new Location();
        nl.setName("Gotham");
        nl.setDescription("A fictional version of Chicago s");
        nl.setAddress("1020 MainStreet IL");
        nl.setCoordinates("Lat 21-35.1N Long 087-15W");
        daoLocation.createLocation(nl);
        
        Location n2 = new Location();
        n2.setLocation_id(6);
        n2.setName("Metropolis");
        n2.setDescription("A fictional version of New York s2");
        n2.setAddress("1020 MainStreet IL");
        n2.setCoordinates("Lat 51-35.1N Long 087-15W");
        daoLocation.createLocation(n2);
        
        Sighting sight1 = new Sighting();
        sight1.setDate(LocalDate.parse("2012-02-10"));
        sight1.setSuperhero_id(s1.getSuperhero_id());
        sight1.setLocation_id(nl.getLocation_id());
        daoSighting.createSighting(sight1);
        
        Sighting sight2 = new Sighting();
        sight2.setDate(LocalDate.parse("2012-02-10"));
        sight2.setSuperhero_id(s2.getSuperhero_id());
        sight2.setLocation_id(nl.getLocation_id());
        daoSighting.createSighting(sight2);
        
        Sighting sight3 = new Sighting();
        sight3.setDate(LocalDate.parse("2012-02-15"));
        sight3.setSuperhero_id(s1.getSuperhero_id());
        sight3.setLocation_id(n2.getLocation_id());
        daoSighting.createSighting(sight3);
        
        List<Location> locationList = daoLocation.getLocationsBySuperhero(s1.getSuperhero_id());
        assertEquals(locationList.size(), 2);
        assertEquals(locationList.get(0).getName(), "Gotham");
        assertEquals(locationList.get(1).getName(), "Metropolis");
    }

    
    
}
