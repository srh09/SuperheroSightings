/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
import java.util.List;

/**
 *
 * @author Reid
 */
public interface DaoOrganization {
    
    Organization createOrganization(Organization organization);
    
    Organization retrieveOrganizationById(int organizationId);
    
    List<Organization> retrieveAllOrganizations();
    
    Organization updateOrganization(Organization organization);
    
    int deleteOrganization(int organizationId);
    
    List<Organization> getOrganizationsBySuperhero(int superheroId);
}
