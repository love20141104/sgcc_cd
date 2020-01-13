package com.sgcc.repository;

import com.example.Utils;
import com.sgcc.dao.LineUpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LineUpRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void addLineUp(LineUpDao dao){
        String sql = "insert into b_line_up(id,user_open_id,service_hall_id,business_id,contact,phone,line_up_no,line_up_time," +
                "submit_date) values (?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{
                dao.getId(),
                dao.getUserOpenId(),
                dao.getServiceHallId(),
                dao.getBusiId(),
                dao.getContact(),
                dao.getPhone(),
                dao.getLineUpNo(),
                Utils.GetTime(dao.getLineUpTime()),
                Utils.GetTime(dao.getSubmitDate())
        });

    }


    public List<LineUpDao> getAllRecords(){
        String sql = "select id,user_open_id,service_hall_id,business_id,contact,phone,line_up_no,line_up_time,submit_date " +
                "from b_line_up";
        return jdbcTemplate.query(sql,new LineUpRowMapper());
    }


    public List<LineUpDao> getLineUpByOpenId(String openId){
        String sql = "select id,user_open_id,service_hall_id,business_id,contact,phone,line_up_no,line_up_time,submit_date " +
                "from b_line_up where user_open_id = ?";
        return jdbcTemplate.query(sql,new Object[]{openId},new LineUpRowMapper());
    }

    public List<LineUpDao> getLineUpNoByOpenId(String openId){
        String sql = "select id,user_open_id,service_hall_id,business_id,contact,phone,line_up_no,line_up_time,submit_date " +
                "from b_line_up where user_open_id = ? and to_days(submit_date) = to_days(now())";
        return jdbcTemplate.query(sql,new Object[]{openId},new LineUpRowMapper());
    }


    class LineUpRowMapper implements RowMapper<LineUpDao>{

        @Override
        public LineUpDao mapRow(ResultSet rs, int i) throws SQLException {
            return new LineUpDao(
                    rs.getString("id"),
                    rs.getString("user_open_id"),
                    rs.getString("service_hall_id"),
                    rs.getString("business_id"),
                    rs.getString("contact"),
                    rs.getString("phone"),
                    rs.getString("line_up_no"),
                    Utils.GetDate(rs.getString("line_up_time")),
                    Utils.GetDate(rs.getString("submit_date"))
            );
        }
    }


}
