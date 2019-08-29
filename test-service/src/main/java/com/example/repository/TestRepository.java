package com.example.repository;

import com.example.test.TestRedisDTO;
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
public class TestRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TestRedisDTO> findAllUesrs() {
        String sql = "select id,name,age from users";
        try {
            return jdbcTemplate.query(sql, new TestRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("失败！！");
        }
    }

    public void saveUser(TestRedisDTO testRedisDTO) {
        String sql = "INSERT INTO users(id,name,age) value(?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, testRedisDTO.getId());
                ps.setString(2, testRedisDTO.getName());
                ps.setInt(3, testRedisDTO.getAge());
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });
    }
}

class TestRowMapper implements RowMapper<TestRedisDTO> {
    @Override
    public TestRedisDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TestRedisDTO(
                rs.getString("id")
                , rs.getString("name")
                , rs.getInt("age")
        );
    }

}
