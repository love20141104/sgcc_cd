package com.example.repository;

import com.example.test.TestRedisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TestRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TestRedisDTO> findAllUesrs(){
        String sql = "select id,name,age from users";
        try{
            return jdbcTemplate.query(sql,new TestRowMapper());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("失败！！");
        }
    }
}

class TestRowMapper implements RowMapper<TestRedisDTO>{
    @Override
    public TestRedisDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TestRedisDTO(
                rs.getString("id")
                ,rs.getString("name")
                ,rs.getInt("age")
        );
    }

}
