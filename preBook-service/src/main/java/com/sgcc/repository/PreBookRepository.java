package com.sgcc.repository;

import com.sgcc.dao.PreBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PreBookRepository {

    private Logger logger = Logger.getLogger(PreBookRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询所有预约信息
     * @return
     */
//    public List<PreBookDao> findAllPreBookList(){
//        String sql = "select id,user_id,service_hall_id,business_type_id,prebook_code,prebook_date," +
//                "prebook_start_time,contact,contact_tel,submit_time from b_prebook";
//        List<PreBookDao> preBookDaoList = jdbcTemplate.query(sql,new preBookRowMapper());
//
//        return preBookDaoList;
//    }

    /**
     * 添加预约信息
     * @param preBookDaoList
     */
    public void addPreBook(List<PreBookDao> preBookDaoList){
        String sql = "insert into b_prebook(id,user_open_id,service_hall_id,prebook_code,prebook_date," +
                        "prebook_start_time,contact,contact_tel,submit_time) values(?,?,?,?,?,?,?,?,?)";
        logger.info("添加预约信息sql:"+sql);
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,preBookDaoList.get(i).getId());
                ps.setString(2,preBookDaoList.get(i).getUserId());
                ps.setString(3,preBookDaoList.get(i).getServiceHallId());
                ps.setString(4,preBookDaoList.get(i).getPrebookCode());
                ps.setDate(5,new Date(preBookDaoList.get(i).getPrebookDate().getTime()));
                ps.setDate(6,new Date(preBookDaoList.get(i).getPrebookStartTime().getTime()));
                ps.setString(7,preBookDaoList.get(i).getContact());
                ps.setString(8,preBookDaoList.get(i).getContactTel());
                ps.setDate(9,new Date(preBookDaoList.get(i).getSubmitDate().getTime()));

            }

            @Override
            public int getBatchSize() {
                return preBookDaoList.size();
            }
        });

    }

//    /**
//     * 删除预约信息
//     * @param preBookDaoList
//     */
//    public void delPreBook(List<PreBookDao> preBookDaoList){
//        String sql = "delete from b_prebook where id=?";
//        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setString(1,preBookDaoList.get(i).getId());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return preBookDaoList.size();
//            }
//        });
//
//    }
//
//    /**
//     * 修改预约信息
//     * @param preBookDaoList
//     */
//    public void updatePreBook(List<PreBookDao> preBookDaoList){
//        String sql = "update b_prebook set contact=?,contact_tel=? where id=?";
//        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setString(1,preBookDaoList.get(i).getContact());
//                ps.setString(2,preBookDaoList.get(i).getContactTel());
//                ps.setString(3,preBookDaoList.get(i).getId());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return preBookDaoList.size();
//            }
//        });
//    }


//    class preBookRowMapper implements RowMapper<PreBookDao>{
//
//        @Override
//        public PreBookDao mapRow(ResultSet rs, int i) throws SQLException {
//            return new PreBookDao(
//                    rs.getLong("live_time"),
//                    rs.getString("id"),
//                    rs.getString("user_id"),
//                    rs.getString("service_hall_id"),
//                    rs.getDate("prebook_date"),
//                    rs.getDate("prebook_start_time"),
//                    rs.getString("prebook_code"),
//                    rs.getString("contact"),
//                    rs.getString("contact_tel"),
//                    rs.getDate("submit_time")
//            );
//        }
//    }


}
