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
public class DaoSightingTest {
    
    private DaoOrganization daoOrganization;
    private DaoSighting daoSighting;
    private DaoLocation daoLocation;
    private DaoSuperhero daoSuperhero;
    
    
    public DaoSightingTest() {
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
    public void createRetrieveDeleteSighting() {
        List<Integer> nullList = null;
        Superhero hero1 = new Superhero();
        hero1.setName("Batman");
        hero1.setDescription("Likes bats");
        hero1.setSuperpower("Rich");
        Superhero heroAfter = daoSuperhero.createSuperhero(hero1, nullList);
        
        Location location1 = new Location();
        location1.setName("Gotham");
        location1.setDescription("Fake Chicago");
        location1.setAddress("Somewhere in USA");
        location1.setCoordinates("Lat 41-23.1N Long 132-12.1W");
        Location locationAfter = daoLocation.createLocation(location1);
        
        Sighting s1 = new Sighting();
        s1.setDate(LocalDate.now());
        s1.setSuperhero_id(heroAfter.getSuperhero_id());
        s1.setLocation_id(locationAfter.getLocation_id());
        Sighting sightingAfter = daoSighting.createSighting(s1);
        Sighting fromDb = daoSighting.retrieveSighting(sightingAfter.getSighting_id());
        assertEquals(fromDb, s1);
        daoSighting.deleteSighting(sightingAfter.getSighting_id());
        assertNull(daoSighting.retrieveSighting(sightingAfter.getSighting_id()));
        
    }
    
    @Test
    public void getAllSightings() {
        List<Integer> nullList = null;
        Superhero hero1 = new Superhero();
        hero1.setName("Batman");
        hero1.setDescription("Likes bats");
        hero1.setSuperpower("Rich");
        Superhero heroAfter = daoSuperhero.createSuperhero(hero1, nullList);
        
        Location location1 = new Location();
        location1.setName("Gotham");
        location1.setDescription("Fake Chicago");
        location1.setAddress("Somewhere in USA");
        location1.setCoordinates("Lat 41-23.1N Long 132-12.1W");
        Location locationAfter = daoLocation.createLocation(location1);
        
        Sighting s1 = new Sighting();
        s1.setDate(LocalDate.parse("2012-02-10"));
        s1.setSuperhero_id(heroAfter.getSuperhero_id());
        s1.setLocation_id(locationAfter.getLocation_id());
        daoSighting.createSighting(s1);
        
        Sighting s2 = new Sighting();
        s2.setDate(LocalDate.parse("2010-01-22"));
        s2.setSuperhero_id(heroAfter.getSuperhero_id());
        s2.setLocation_id(locationAfter.getLocation_id());
        daoSighting.createSighting(s2);
        
        List<Sighting> sList = daoSighting.retrieveAllSightings();
        assertEquals(sList.size(), 2);
    }
    
    @Test
    public void testUpdateSighting() {
        List<Integer> nullList = null;
        Superhero hero1 = new Superhero();
        hero1.setName("Batman");
        hero1.setDescription("Likes bats");
        hero1.setSuperpower("Rich");
        Superhero heroAfter = daoSuperhero.createSuperhero(hero1, nullList);
        
        Location location1 = new Location();
        location1.setName("Gotham");
        location1.setDescription("Fake Chicago");
        location1.setAddress("Somewhere in USA");
        location1.setCoordinates("Lat 41-23.1N Long 132-12.1W");
        Location locationAfter = daoLocation.createLocation(location1);
        
        Sighting s1 = new Sighting();
        s1.setDate(LocalDate.parse("2012-02-10"));
        s1.setSuperhero_id(heroAfter.getSuperhero_id());
        s1.setLocation_id(locationAfter.getLocation_id());
        daoSighting.createSighting(s1);
        s1.setDate(LocalDate.parse("2012-02-15"));
        daoSighting.updateSighting(s1);
        Sighting fromDb = daoSighting.retrieveSighting(s1.getSighting_id());
        assertEquals(fromDb.getDate().toString(), "2012-02-15");
    }


    
}
