/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
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
public class DaoOrganizationImpl implements DaoOrganization{
    
    private static final String SQL_INSERT_ORGANIZATION
            ="insert into organizations "
            + "(name, description, contact_info) "
            + "values (?, ?, ?)";
    private static final String SQL_SELECT_ORGANIZATION
            = "select * from organizations where organization_id = ?";
    private static final String SQL_SELECT_ALL_ORGANIZATION
            = "select * from organizations";
    private static final String SQL_UPDATE_ORGANIZATION
            = "update organizations set "
            + "name = ?, description = ?, "
            + "contact_info = ? "
            + "where organization_id = ?";
    private static final String SQL_DELETE_SUPERHEROES_ORGANIZATIONS
            = "delete from superheroes_organizations where organization_id = ?";
    private static final String SQL_DELETE_ORGANIZATION
            = "delete from organizations where organization_id = ?";
    private static final String SQL_SELECT_ORGANIZATIONS_BY_SUPERHERO_ID
            = "select o.organization_id, o.name, o.description, o.contact_info "
            + "from organizations o "
            + "join superheroes_organizations so "
            + "on superhero_id "
            + "where o.organization_id = so.organization_id "
            + "and so.superhero_id = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization createOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION, 
                organization.getName(),
                organization.getDescription(),
                organization.getContactInfo());
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        organization.setOrganization_id(newId);
        return organization;
    }

    @Override
    public Organization retrieveOrganizationById(int organizationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, new DaoOrganizationImpl.OrganizationMapper(), organizationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> retrieveAllOrganizations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATION, new OrganizationMapper());
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getContactInfo(),
                organization.getOrganization_id());
        return organization;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int deleteOrganization(int organizationId) {
        jdbcTemplate.update(SQL_DELETE_SUPERHEROES_ORGANIZATIONS, organizationId);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationId);
        return organizationId;
    }

    @Override
    public List<Organization> getOrganizationsBySuperhero(int superheroId) {
        List<Organization> organizationList = 
                jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_SUPERHERO_ID, 
                                   new OrganizationMapper(), 
                                   superheroId);
        return organizationList;
    }
    
    private static final class OrganizationMapper implements RowMapper<Organization> {
        
        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganization_id(rs.getInt("organization_id"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            organization.setContactInfo(rs.getString("contact_info"));
            return organization;
        }
    }
}
