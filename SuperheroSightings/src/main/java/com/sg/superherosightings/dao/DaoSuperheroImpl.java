/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Superhero;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Reid
 */
public class DaoSuperheroImpl implements DaoSuperhero{
    
    private static final String SQL_INSERT_SUPERHERO
            ="insert into superheroes "
            + "(name, description, superpower) "
            + "values (?, ?, ?)";
    private static final String SQL_SELECT_SUPERHERO
            = "select * from superheroes where superhero_id = ?";
    private static final String SQL_SELECT_ALL_SUPERHEROES
            = "select * from superheroes";
    private static final String SQL_UPDATE_SUPERHERO
            = "update superheroes set "
            + "name = ?, description = ?, "
            + "superpower = ? "
            + "where superhero_id = ?";
    private static final String SQL_DELETE_SUPERHERO
            = "delete from superheroes where superhero_id = ?";
    private static final String SQL_INSERT_SUPERHEROES_ORGANIZATIONS
            = "insert into superheroes_organizations (superhero_id, organization_id) values(?, ?)";
    private static final String SQL_DELETE_SUPERHEROES_ORGANIZATIONS
            = "delete from superheroes_organizations where superhero_id = ?";
    private static final String SQL_DELETE_SIGHTINGS
            = "delete from sightings where superhero_id = ?";
    private static final String SQL_SELECT_SUPERHEROES_BY_LOCATION_ID
            = "select s.superhero_id, s.name, s.description, s.superpower "
            + "from superheroes s "
            + "join sightings sight "
            + "on location_id "
            + "where s.superhero_id = sight.superhero_id "
            + "and sight.location_id = ?";
    private static final String SQL_SELECT_SUPERHEROES_BY_ORGANIZATION_ID
            = "select s.superhero_id, s.name, s.description, s.superpower "
            + "from superheroes s "
            + "join superheroes_organizations so "
            + "on organization_id "
            + "where s.superhero_id = so.superhero_id "
            + "and so.organization_id = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Superhero createSuperhero(Superhero superhero, List<Integer> integerList) {
        jdbcTemplate.update(SQL_INSERT_SUPERHERO, 
                superhero.getName(),
                superhero.getDescription(),
                superhero.getSuperpower());
        
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        superhero.setSuperhero_id(newId);
        
        try {
            insertSuperheroOrganizations(newId, integerList);
        } catch(NullPointerException ex) {
            return superhero;
        }
        return superhero;
    }

    @Override
    public Superhero retrieveSuperhero(int superheroId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERHERO, new DaoSuperheroImpl.SuperheroMapper(), superheroId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superhero> retrieveAllSuperheroes() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPERHEROES, new SuperheroMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Superhero updateSuperhero(Superhero superhero, List<Integer> integerList) {
        jdbcTemplate.update(SQL_UPDATE_SUPERHERO,
                superhero.getName(),
                superhero.getDescription(),
                superhero.getSuperpower(),
                superhero.getSuperhero_id());
        jdbcTemplate.update(SQL_DELETE_SUPERHEROES_ORGANIZATIONS, 
                            superhero.getSuperhero_id());
        try {
            insertSuperheroOrganizations(superhero.getSuperhero_id(), integerList);
        } catch(NullPointerException ex) {
            return superhero;
        }
        return superhero;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int deleteSuperhero(int superheroId) {
        jdbcTemplate.update(SQL_DELETE_SIGHTINGS, superheroId);
        jdbcTemplate.update(SQL_DELETE_SUPERHEROES_ORGANIZATIONS, superheroId);
        jdbcTemplate.update(SQL_DELETE_SUPERHERO, superheroId);
        return superheroId;
    }
    
    //Helper
    
    @Override
    public List<Integer> insertSuperheroOrganizations(int superheroId, List<Integer> organizationsIdList) {
        
        

        for (Integer currentOrganizationId : organizationsIdList) {
            jdbcTemplate.update(SQL_INSERT_SUPERHEROES_ORGANIZATIONS, 
                                superheroId, 
                                currentOrganizationId);
        }
        return organizationsIdList;
    }
    
    @Override
    public List<Superhero> getSuperheroByLocationId(int locationId) {
        List<Superhero> superheroList = 
                jdbcTemplate.query(SQL_SELECT_SUPERHEROES_BY_LOCATION_ID, 
                                   new SuperheroMapper(), 
                                   locationId);
        return superheroList;
    }

    @Override
    public List<Superhero> getSuperheroesByOrganization(int organizationId) {
        List<Superhero> organizationList = 
                jdbcTemplate.query(SQL_SELECT_SUPERHEROES_BY_ORGANIZATION_ID, 
                                   new SuperheroMapper(), 
                                   organizationId);
        return organizationList;
    }
    
    private static final class SuperheroMapper implements RowMapper<Superhero> {
        
        public Superhero mapRow(ResultSet rs, int rowNum) throws SQLException {
            Superhero superhero = new Superhero();
            superhero.setSuperhero_id(rs.getInt("superhero_id"));
            superhero.setName(rs.getString("name"));
            superhero.setDescription(rs.getString("description"));
            superhero.setSuperpower(rs.getString("superpower"));
            return superhero;
        }
    }
}
