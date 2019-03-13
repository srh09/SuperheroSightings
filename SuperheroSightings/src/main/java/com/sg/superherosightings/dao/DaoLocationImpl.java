/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Reid
 */
public class DaoLocationImpl implements DaoLocation{
    
    private static final String SQL_INSERT_LOCATION
            ="insert into locations "
            + "(name, description, address, coordinates) "
            + "values (?, ?, ?, ?)";
    private static final String SQL_SELECT_LOCATION
            = "select * from locations where location_id = ?";
    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from locations";
    private static final String SQL_UPDATE_LOCATION
            = "update locations set "
            + "name = ?, description = ?, "
            + "address = ?, coordinates = ? "
            + "where location_id = ?";
    private static final String SQL_DELETE_SIGHTINGS
            = "delete from sightings where location_id = ?";
    private static final String SQL_DELETE_LOCATION
            = "delete from locations where location_id = ?";
    private static final String SQL_SELECT_LOCATIONS_BY_SUPERHERO_ID
            = "select l.location_id, l.name, l.description, l.address, l.coordinates "
            + "from locations l "
            + "join sightings sight "
            + "on superhero_id "
            + "where l.location_id = sight.location_id "
            + "and sight.superhero_id = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location createLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION, 
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getCoordinates());
        
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        location.setLocation_id(newId);
        return location;
    }

    @Override
    public Location retrieveLocationById(int locationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(), locationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public List<Location> retrieveAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    public Location updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getCoordinates(),
                location.getLocation_id());
        return location;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int deleteLocation(int locationId) {
        jdbcTemplate.update(SQL_DELETE_SIGHTINGS, locationId);
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
        return locationId;
    }

    @Override
    public List<Location> getLocationsBySuperhero(int superheroId) {
        List<Location> locationList = 
                jdbcTemplate.query(SQL_SELECT_LOCATIONS_BY_SUPERHERO_ID, 
                                   new LocationMapper(), 
                                   superheroId);
        return locationList;
    }
    
    private static final class LocationMapper implements RowMapper<Location> {
        
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location location = new Location();
            location.setLocation_id(rs.getInt("location_id"));
            location.setName(rs.getString("name"));
            location.setDescription(rs.getString("description"));
            location.setAddress(rs.getString("address"));
            location.setCoordinates(rs.getString("coordinates"));
            return location;
        }
    }
    
}
