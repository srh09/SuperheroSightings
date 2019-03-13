/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Superhero;
import java.util.List;

/**
 *
 * @author Reid
 */
public interface DaoSuperhero {
    
    Superhero createSuperhero(Superhero superhero , List<Integer> integerList);
    
    Superhero retrieveSuperhero(int superheroId);
    
    List<Superhero> retrieveAllSuperheroes();
    
    Superhero updateSuperhero(Superhero superhero, List<Integer> integerList);
    
    int deleteSuperhero(int superheroId);
    
    List<Integer> insertSuperheroOrganizations(int superheroId, List<Integer> integerList);
    
    List<Superhero> getSuperheroByLocationId(int locationId);
    
    List<Superhero> getSuperheroesByOrganization(int organizationId);
}
