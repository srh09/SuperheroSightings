/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Sighting;
import java.util.List;

/**
 *
 * @author Reid
 */
public interface DaoSighting {
    
    Sighting createSighting(Sighting sighting);
    
    Sighting retrieveSighting(int sightingId);
    
    List<Sighting> retrieveAllSightings();
    
    Sighting updateSighting(Sighting sighting);
    
    int deleteSighting(int sightingId);
}
