package com.sgcc.repository;

import com.example.Utils;
import com.sgcc.dao.LineUpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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


}
