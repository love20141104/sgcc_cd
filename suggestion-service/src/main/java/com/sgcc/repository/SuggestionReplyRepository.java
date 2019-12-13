package com.sgcc.repository;

import com.example.Utils;
import com.sgcc.dao.*;
import com.sgcc.dto.SuggestionReplyInitDTO;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SuggestionReplyRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void save( SuggestionReplyInitDao dao  ){
        String sql = "INSERT INTO b_suggestion_reply(id,suggestion_id,reply_openid,check_openid) " +
                "values(?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{ dao.getId(),dao.getSuggestion_id(),
                dao.getReply_openid(),dao.getCheck_openid()});
    }

    public SuggestionReplyInstDao getInst( String sgstId )
    {
        String sql = "select id,suggestion_id,reply_openid,check_openid from b_suggestion_reply" +
                " where suggestion_id = ? ";
        return jdbcTemplate.queryForObject(sql,new Object[]{sgstId}, new SuggestionReplyInstDaoRowMapper());
    }

    @Transactional
    public void update( SuggestionReplyDao dao  ){
        String sql = "UPDATE b_suggestion_reply SET reply_content = ?,reply_date = ? where suggestion_id = ?";
        jdbcTemplate.update(sql,new Object[]{dao.getReply_content(),dao.getReply_date(),dao.getSuggestion_id() });
//                new java.sql.Date(Utils.GetDate(dao.getReply_date()).getTime()),dao.getSuggestion_id() });
    }

    @Transactional
    public void update( SuggestionCheckDao dao  ){
        String sql = "UPDATE b_suggestion_reply SET check_state = ?,check_date = ? where suggestion_id = ?";
        jdbcTemplate.update(sql,new Object[]{dao.getCheck_state(),dao.getCheck_date(),dao.getSuggestion_id() });
    }

    @Transactional
    public void update( String sid,String reject ){
        String sql = "UPDATE b_suggestion_reply SET check_reject = ? where suggestion_id = ?";
        jdbcTemplate.update(sql,new Object[]{reject,sid});
    }

    public SuggestionReplyMappingDao GetBySuggestionID(String suggestionId )
    {
        String sql = "select id,suggestion_id,reply_content,reply_openid,reply_date,check_openid,check_state,check_date" +
                " from b_suggestion_reply where suggestion_id = ? ";
        return jdbcTemplate.queryForObject(sql,new Object[]{suggestionId}, new SSuggestionReplyMappingDaoRowMapper());
    }

    // 消息回复者根据自己openID查看所有需要回复的消息
    // 直接连表查询出数据
    public List<SuggestionReplyInfoDao> getSuggestionReplyByOpenId(String openId,boolean status){

        if (status){
            String sql = "select s.user_id,s.suggestion_content,s.suggestion_contact,s.suggestion_tel," +
                    "s.submit_date,s.img_1,s.img_2,s.img_3,r.id,r.suggestion_id,r.reply_content," +
                    "r.reply_openid,r.reply_date,r.check_openid,r.check_state,r.check_date" +
                    " from b_suggestion_reply r,b_suggestion s where r.suggestion_id = s.suggestion_id" +
                    " and r.reply_openid = ? and r.reply_content is not null and r.reply_openid is not null " +
                    " and r.reply_date is not null ";
            return jdbcTemplate.query(sql,new SuggestionReplyInfoRowMapper(),new Object[]{openId});
        }else {
            String sql = "select s.user_id,s.suggestion_content,s.suggestion_contact,s.suggestion_tel," +
                    "s.submit_date,s.img_1,s.img_2,s.img_3,r.id,r.suggestion_id,r.reply_content," +
                    "r.reply_openid,r.reply_date,r.check_openid,r.check_state,r.check_date" +
                    " from b_suggestion_reply r,b_suggestion s where r.suggestion_id = s.suggestion_id" +
                    " and r.reply_openid = ? and r.reply_content is null and r.reply_openid is null " +
                    " and r.reply_date is null ";
            return jdbcTemplate.query(sql,new SuggestionReplyInfoRowMapper(),new Object[]{openId});
        }


    }




    // 消息审核者根据自己openID查看所有需要审核的消息
    // 直接连表查询出数据

    //SuggestionReplyInstDao

    class SSuggestionReplyMappingDaoRowMapper implements RowMapper<SuggestionReplyMappingDao>{
        @Override
        public SuggestionReplyMappingDao mapRow(ResultSet rs, int i) throws SQLException {
            SuggestionReplyMappingDao dao = new SuggestionReplyMappingDao(
                    rs.getString("id"),
                    rs.getString("suggestion_id"),
                    rs.getString("reply_content"),
                    rs.getString("reply_openid"),
                    rs.getString("reply_date"),
                    rs.getString("check_openid"),
                    rs.getInt("check_state"),
                    rs.getString("check_date"));
            return dao;
        }
    }

    class SuggestionReplyInstDaoRowMapper implements RowMapper<SuggestionReplyInstDao>{
        @Override
        public SuggestionReplyInstDao mapRow(ResultSet rs, int i) throws SQLException {
            SuggestionReplyInstDao dao = new SuggestionReplyInstDao(
                    rs.getString("id"),
                    rs.getString("suggestion_id"),
                    rs.getString("reply_openid"),
                    rs.getString("check_openid")
            );
            return dao;
        }
    }


    class SuggestionReplyInfoRowMapper implements RowMapper<SuggestionReplyInfoDao>{
        @Override
        public SuggestionReplyInfoDao mapRow(ResultSet rs, int i) throws SQLException {
            SuggestionReplyInfoDao dao = new SuggestionReplyInfoDao(
                    rs.getString("user_id"),
                    rs.getString("suggestion_content"),
                    rs.getString("suggestion_contact"),
                    rs.getString("suggestion_tel"),
                    Utils.GetDate(rs.getString("submit_date")),
                    rs.getString("img_1"),
                    rs.getString("img_2"),
                    rs.getString("img_3"),
                    rs.getString("id"),
                    rs.getString("suggestion_id"),
                    rs.getString("reply_content"),
                    rs.getString("reply_openid"),
                    rs.getString("reply_date"),
                    rs.getString("check_openid"),
                    rs.getInt("check_state"),
                    rs.getString("check_date"));
            return dao;
        }
    }



}
