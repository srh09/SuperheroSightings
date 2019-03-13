/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class DaoSightingImpl implements DaoSighting{
    
    private static final String SQL_INSERT_SIGHTING
            ="insert into sightings "
            + "(date, superhero_id, location_id) "
            + "values (?, ?, ?)";
    private static final String SQL_SELECT_SIGHTING
            = "select * from sightings where sighting_id = ?";
    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from sightings";
    private static final String SQL_UPDATE_SIGHTING
            = "update sightings set "
            + "date = ?, superhero_id = ?, "
            + "location_id = ? "
            + "where sighting_id = ?";
    private static final String SQL_DELETE_SIGHTING
            = "delete from sightings where sighting_id = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting createSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING, 
                sighting.getDate().toString(),
                sighting.getSuperhero_id(),
                sighting.getLocation_id());
        
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        sighting.setSighting_id(newId);
        return sighting;
    }

    @Override
    public Sighting retrieveSighting(int sightingId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING, new SightingMapper(), sightingId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public List<Sighting> retrieveAllSightings() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());
    }
    
    @Override
    public Sighting updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getDate().toString(),
                sighting.getSuperhero_id(),
                sighting.getLocation_id(),
                sighting.getSighting_id());
        return sighting;
    
    }

    @Override
    public int deleteSighting(int sightingId) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
        return sightingId;
    }

    
    
    
    private static final class SightingMapper implements RowMapper<Sighting> {
        
        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSighting_id(rs.getInt("sighting_id"));
            sighting.setDate(LocalDate.parse(rs.getString("date")));
            sighting.setSuperhero_id(rs.getInt("superhero_id"));
            sighting.setLocation_id(rs.getInt("location_id"));
            return sighting;
        }
    }

    
    
}
