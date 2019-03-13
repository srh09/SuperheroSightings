/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superhero;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Reid
 */
public interface ServiceSuperhero {
    Superhero createSuperhero(Superhero superhero, String[] stringArray);
    
    Superhero retrieveSuperhero(int superheroId);
    
    List<Superhero> retrieveAllSuperheroes();
    
    Superhero updateSuperhero(Superhero superhero, String[] stringArray);
    
    int deleteSuperhero(int superheroId);
    
    List<Superhero> searchSuperheroesByLocation(int locationId);
    
    List<Superhero> getSuperheroesByOrganization(int organizationId);
    
}
