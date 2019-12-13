package com.sgcc.repository;

import com.sgcc.dao.ReplierAndCheckerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReplierAndCheckerRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ReplierAndCheckerDao> findAllByRegion(String region ){
        String sql = "select id,major_region,replier_openid,replier_name,checker_openid,checker_name from d_customer_service_staff " +
                " where major_region like ?";
        return jdbcTemplate.query(sql,new Object[]{"%"+region+"%"},new ReplierAndCheckerRowMapper());
    }

    public List<ReplierAndCheckerDao> findAll(   ){
        String sql = "select id,major_region,replier_openid,replier_name,checker_openid,checker_name from d_customer_service_staff";
        return jdbcTemplate.query(sql,new Object[]{},new ReplierAndCheckerRowMapper());
    }

    @Transactional
    public void save( ReplierAndCheckerDao dao  ){
        String sql = "INSERT INTO d_customer_service_staff(id,major_region,replier_openid,replier_name,checker_openid,checker_name) " +
                "values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{ dao.getId(),dao.getMajor_region(),
        dao.getReplier_openid(),dao.getReplier_name(),dao.getChecker_openid(),dao.getChecker_name()});
    }

    @Transactional
    public void update( ReplierAndCheckerDao dao  ){
        String sql = "UPDATE d_customer_service_staff SET major_region = ?,replier_openid = ?,replier_name = ?,checker_openid = ?," +
                "checker_name = ? where id = ?";
        jdbcTemplate.update(sql,new Object[]{ dao.getMajor_region(),
                dao.getReplier_openid(),dao.getReplier_name(),dao.getChecker_openid(),dao.getChecker_name(),dao.getId()});
    }

    @Transactional
    public void delete( String id ){
        String sql = "delete from d_customer_service_staff where id = ?";
        jdbcTemplate.update(sql,new Object[]{ id });
    }

    class ReplierAndCheckerRowMapper implements RowMapper<ReplierAndCheckerDao> {
        @Override
        public ReplierAndCheckerDao mapRow(ResultSet rs, int i) throws SQLException {
            return new ReplierAndCheckerDao(
                    rs.getString("id"),
                    rs.getString("major_region"),
                    rs.getString("replier_openid"),
                    rs.getString("replier_name"),
                    rs.getString("checker_openid"),
                    rs.getString("checker_name"));
        }
    }
}
