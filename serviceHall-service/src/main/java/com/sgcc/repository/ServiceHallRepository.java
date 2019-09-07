package com.sgcc.repository;

import com.sgcc.dao.ServiceHallDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ServiceHallRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<ServiceHallDao> findHallList(){
        String sql = "select id,service_hall_id,service_hall_name,service_hall_addr,service_hall_opentime,service_hall_longitude," +
                "service_hall_latitude,service_hall_district,service_hall_tel,service_hall_available from d_service_hall";

        try {
            return jdbcTemplate.query(sql, new TestRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("失败！！");
        }

    }

    /**
     * 修改网点
     * @param list
     */
    public void updateServiceHall(List<ServiceHallDao> list){
        String sql = "update d_service_hall set service_hall_name=?,service_hall_addr=? where service_hall_id=?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, list.get(i).getServiceHallName());
                ps.setString(2, list.get(i).getServiceHallAddr());
                ps.setString(3, list.get(i).getServiceHallId());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }



    /**
     * 新增网点
     * @param list
     */
    public void saveServiceHall(List<ServiceHallDao> list){
        String sql = "INSERT INTO d_service_hall(id,service_hall_id,service_hall_name,service_hall_addr" +
                ",service_hall_opentime,service_hall_longitude,service_hall_latitude,service_hall_district," +
                "service_hall_tel,service_hall_available) values(?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, list.get(i).getId());
                ps.setString(2, list.get(i).getServiceHallId());
                ps.setString(3, list.get(i).getServiceHallName());
                ps.setString(4, list.get(i).getServiceHallAddr());
                ps.setString(5, list.get(i).getOpenTime());
                ps.setDouble(6, list.get(i).getServiceHallLongitude());
                ps.setDouble(7, list.get(i).getServiceHallLatitude());
                ps.setString(8, list.get(i).getServiceHallDistrict());
                ps.setString(9, list.get(i).getServiceHallTel());
                ps.setBoolean(10, list.get(i).getServiceHallAvailable());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }


    /**
     * 删除网点
     * @param list
     */
    public void delServiceHall(List<ServiceHallDao> list){
        String sql = "delete from d_service_hall where service_hall_id=?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, list.get(i).getServiceHallId());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }


    class TestRowMapper implements RowMapper<ServiceHallDao> {
        @Override
        public ServiceHallDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ServiceHallDao(
                    rs.getString("id"),
                    rs.getString("service_hall_id"),
                    rs.getString("service_hall_name"),
                    rs.getString("service_hall_addr"),
                    rs.getString("service_hall_opentime"),
                    rs.getString("service_hall_tel"),
                    rs.getDouble("service_hall_longitude"),
                    rs.getDouble("service_hall_latitude"),
                    rs.getString("service_hall_district"),
                    rs.getBoolean("service_hall_available")
            );
        }

    }




}
