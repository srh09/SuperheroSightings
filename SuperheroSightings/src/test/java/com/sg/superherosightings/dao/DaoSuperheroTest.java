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
import java.util.ArrayList;
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
public class DaoSuperheroTest {
    
    private DaoOrganization daoOrganization;
    private DaoSighting daoSighting;
    private DaoLocation daoLocation;
    private DaoSuperhero daoSuperhero;
    
    public DaoSuperheroTest() {
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
    public void createSuperhero() {
        List<Integer> nullList = null;
        Superhero s1 = new Superhero();
        s1.setName("Batman");
        s1.setDescription("Likes Bats");
        s1.setSuperpower("Rich");
        daoSuperhero.createSuperhero(s1, nullList);
        Superhero fromDb = daoSuperhero.retrieveSuperhero(s1.getSuperhero_id());
        assertEquals(fromDb, s1);
        daoSuperhero.deleteSuperhero(s1.getSuperhero_id());
        assertNull(daoSuperhero.retrieveSuperhero(s1.getSuperhero_id()));
    }


    @Test
    public void testUpdateSuperhero() {
        List<Integer> nullList = null;
        Superhero s1 = new Superhero();
        s1.setSuperhero_id(5);
        s1.setName("Batman");
        s1.setDescription("Likes Bats");
        s1.setSuperpower("Rich");
        daoSuperhero.createSuperhero(s1, nullList);
        s1.setName("Superman");
        daoSuperhero.updateSuperhero(s1, nullList);
        Superhero fromDb = daoSuperhero.retrieveSuperhero(s1.getSuperhero_id());
        assertEquals(fromDb, s1);
    }
    
    @Test
    public void retrieveAllSuperheroes() {
        List<Integer> nullList = null;
        Superhero s1 = new Superhero();
        s1.setSuperhero_id(5);
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
        List<Superhero> cList = daoSuperhero.retrieveAllSuperheroes();
        assertEquals(cList.size(), 2);
    }
    
    @Test
    public void insertSuperheroesOrganizations() {
        List<Integer> nullList = null;
        Superhero s1 = new Superhero();
        s1.setName("Batman");
        s1.setDescription("Likes Bats");
        s1.setSuperpower("Rich");
        Superhero superhero = daoSuperhero.createSuperhero(s1, nullList);
        
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
        
        List<Organization> oList = daoOrganization.retrieveAllOrganizations();
        List<Integer> integerList = Arrays.asList(org1.getOrganization_id(), org2.getOrganization_id());
        
        List<Integer> actualList = daoSuperhero.insertSuperheroOrganizations(superhero.getSuperhero_id(), integerList);
        List<Integer> testList = new ArrayList<>();
        testList.add(r1.getOrganization_id());
        testList.add(r2.getOrganization_id());
        assertEquals(actualList, testList);
    }
    
    @Test
    public void getSuperheroByLocationId() {
        List<Integer> nullList = null;
        Superhero s1 = new Superhero();
        s1.setName("Batman");
        s1.setDescription("Likes Bats");
        s1.setSuperpower("Rich");
        Superhero superhero = daoSuperhero.createSuperhero(s1, nullList);
        
        Superhero s2 = new Superhero();
        s2.setSuperhero_id(6);
        s2.setName("Superman");
        s2.setDescription("Changes fast");
        s2.setSuperpower("xray vision");
        daoSuperhero.createSuperhero(s2, nullList);
        
        Location nl = new Location();
        nl.setName("Gotham");
        nl.setDescription("A fictional version of Chicago");
        nl.setAddress("1020 MainStreet IL");
        nl.setCoordinates("Lat 21-35.1N Long 087-15W");
        daoLocation.createLocation(nl);
        
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
        
        List<Superhero> searchList = daoSuperhero.getSuperheroByLocationId(nl.getLocation_id());
        assertEquals(searchList.size(), 2);
        assertEquals(searchList.get(0).getName(), "Batman");
        assertEquals(searchList.get(1).getName(), "Superman");
    }
        
    @Test
    public void getSuperheroByOrganizationId() {
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
        List<Integer> integerList = Arrays.asList(r1.getOrganization_id(), r2.getOrganization_id());
        
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
        
        List<Superhero> superheroList = daoSuperhero.getSuperheroesByOrganization(r2.getOrganization_id());
        assertEquals(superheroList.size(), 2);
        assertEquals(superheroList.get(0).getName(), "Batman");
        assertEquals(superheroList.get(1).getName(), "Superman");
    }
    
}
