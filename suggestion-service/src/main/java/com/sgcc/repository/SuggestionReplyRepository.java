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
import java.util.ArrayList;
import java.util.Date;
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
        String sql = "UPDATE b_suggestion_reply SET reply_content = ?,reply_date = ?,check_reject=?,check_date=?,check_state=? where suggestion_id = ?";
        jdbcTemplate.update(sql,new Object[]{dao.getReply_content(),dao.getReply_date(),null,null,null,dao.getSuggestion_id() });
//                new java.sql.Date(Utils.GetDate(dao.getReply_date()).getTime()),dao.getSuggestion_id() });
    }

    @Transactional
    public void update( SuggestionCheckDao dao  ){
        String sql = "UPDATE b_suggestion_reply SET check_state = ?,check_date = ? where suggestion_id = ?";
        jdbcTemplate.update(sql,new Object[]{dao.getCheck_state(),dao.getCheck_date(),dao.getSuggestion_id() });
    }

    @Transactional
    public void update(String sid, String reject, int check_state, Date date){
        String sql = "UPDATE b_suggestion_reply SET check_reject = ? ,check_state=?,check_date=? where suggestion_id = ?";
        jdbcTemplate.update(sql,new Object[]{reject,check_state,Utils.GetTime(date),sid});
    }

    public SuggestionReplyMappingDao GetBySuggestionID(String suggestionId )
    {
        String sql = "select id,suggestion_id,reply_content,reply_openid,reply_date,check_openid,check_state,check_date" +
                " from b_suggestion_reply where suggestion_id = ? ";
        return jdbcTemplate.queryForObject(sql,new Object[]{suggestionId}, new SSuggestionReplyMappingDaoRowMapper());
    }

    // 消息回复者根据自己openID查看所有需要回复的消息
    // 直接连表查询出数据
    public List<SuggestionReplyInfoDao> getSuggestionReplyByOpenId(String openId,Boolean status){
        //Flase 待处理 true 审批通过
        String sql0="select user_id,suggestion_content,suggestion_contact,suggestion_tel " +
                " ,submit_date,img_1,img_2,img_3,id,suggestion_id,reply_content,reply_openid " +
                " ,reply_date,check_openid,check_state,check_reject,check_date from (" +
                "select s.user_id,s.suggestion_content,s.suggestion_contact,s.suggestion_tel" +
                ",s.submit_date,s.img_1,s.img_2,s.img_3,r.id,s.suggestion_id,r.reply_content" +
                ",r.reply_openid,r.reply_date,r.check_openid,r.check_state,r.check_reject,r.check_date " +
                "from b_suggestion s LEFT JOIN b_suggestion_reply r on s.suggestion_id=r.suggestion_id   " +
                " where  s.user_location in(select major_region from d_customer_service_staff " +
                " where replier_openid = ? )) a ";
        if (status){
            String sql = sql0 +
                    "where a.check_state = 1;";
            return jdbcTemplate.query(sql,new SuggestionReplyInfoRowMapper(),new Object[]{openId});
        }else {
            String sql = sql0 +
                    "where a.check_state = 0 or a.check_state is null;";
            return jdbcTemplate.query(sql,new SuggestionReplyInfoRowMapper(),new Object[]{openId});

        }


    }




    // 消息审核者根据自己openID查看所有需要审核的消息
    // 直接连表查询出数据
    public List<SuggestionReplyCheckInfoDao> suggestionReplyCheckInfoDaoList(String checkerOpenid ,Integer checkState){
        //1 处理人未回复；2 未审批；3已审批
        if(checkState==1){
            String sql = "select s.id id,s.suggestion_id,user_id,suggestion_content,suggestion_contact, suggestion_tel" +
                    ",submit_date,img_1,img_2,img_3, sr.id reply_id,sr.reply_content reply_content" +
                    ",sr.reply_date reply_date,sr.check_reject check_reject,sr.check_state check_state" +
                    ",sr.check_date check_date from b_suggestion_reply sr right join b_suggestion s on sr.suggestion_id=s.suggestion_id   " +
                    "where sr.reply_content is null and sr.reply_date is null and  s.user_location =(  select major_region " +
                    "from d_customer_service_staff where checker_openid =? ) order by submit_date desc ";
            return jdbcTemplate.query(sql,new Object[]{checkerOpenid}, new SuggestionReplyCheckInfoDaoRowMapper());

        }
        if(checkState==2||checkState==3){
            Boolean state=false;
            if (checkState==3){
                state=true;
                String sql = "select s.id id,s.suggestion_id,user_id,suggestion_content,suggestion_contact," +
                        " suggestion_tel,submit_date,img_1,img_2,img_3," +
                        " sr.id reply_id,sr.reply_content reply_content,sr.reply_date reply_date,sr.check_reject check_reject,sr.check_state check_state,sr.check_date check_date" +
                        " from b_suggestion_reply sr left join b_suggestion s on sr.suggestion_id=s.suggestion_id " +
                        "  where check_state = ? and  sr.reply_openid in( " +
                        " select distinct(replier_openid) from d_customer_service_staff where checker_openid =? ) order by submit_date desc ";
                return jdbcTemplate.query(sql,new Object[]{state,checkerOpenid}, new SuggestionReplyCheckInfoDaoRowMapper());

            }else {
                String sql = "select s.id id,s.suggestion_id,user_id,suggestion_content,suggestion_contact," +
                        " suggestion_tel,submit_date,img_1,img_2,img_3, sr.id reply_id,sr.reply_content reply_content," +
                        "sr.reply_date reply_date,sr.check_reject check_reject,sr.check_state check_state," +
                        "sr.check_date check_date from b_suggestion_reply sr left join b_suggestion s " +
                        "on sr.suggestion_id=s.suggestion_id   where check_state is null and sr.reply_content is not null " +
                        "and  sr.reply_openid in(  select distinct(replier_openid) from d_customer_service_staff" +
                        " where checker_openid =?  ) order by submit_date desc ";
                return jdbcTemplate.query(sql, new Object[]{ checkerOpenid}, new SuggestionReplyCheckInfoDaoRowMapper());
            }
        }
        else {
            return null;
        }
    }

    public List<SuggestionReplyCheckInfoDao> getSuggestionsByUser(String userId) {
        String sql = "select s.id id,s.suggestion_id,user_id,suggestion_content,suggestion_contact," +
                " suggestion_tel,submit_date,img_1,img_2,img_3," +
                " sr.id reply_id,sr.reply_content reply_content,sr.reply_date reply_date,sr.check_reject check_reject,sr.check_state check_state,sr.check_date check_date" +
                " from b_suggestion_reply sr left join b_suggestion s on sr.suggestion_id=s.suggestion_id " +
                "  where user_id =?  order by submit_date desc ";
        return jdbcTemplate.query(sql,new Object[]{userId}, new SuggestionReplyCheckInfoDaoRowMapper());

    }


    //SuggestionReplyInstDao
    class SuggestionRejectDaoRowMapper implements RowMapper<SuggestionRejectDao>{
        @Override
        public SuggestionRejectDao mapRow(ResultSet rs, int i) throws SQLException {
            SuggestionRejectDao dao = new SuggestionRejectDao(
                    rs.getString("id"),
                    rs.getString("suggestion_id"),
                    rs.getString("user_id"),
                    rs.getString("suggestion_content"),
                    rs.getString("suggestion_contact"),
                    rs.getString("suggestion_tel"),
                    null,
                    rs.getString("img_1"),
                    rs.getString("img_2"),
                    rs.getString("img_3"),
                    rs.getString("reply_user_id"),
                    rs.getString("reply_content"),
                    null,
                    rs.getString("check_reject")
            );
            if( rs.getDate("submit_date") != null )
            {
                dao.setSubmitDate( Utils.GetDate( rs.getString("submit_date")) );
            }

            if( rs.getDate("reply_date") != null ){
                dao.setReplyDate( Utils.GetDate( rs.getString("reply_date") ));
            }
            return dao;
        }
    }


    class SuggestionReplyCheckInfoDaoRowMapper implements RowMapper<SuggestionReplyCheckInfoDao>{
        @Override
        public SuggestionReplyCheckInfoDao mapRow(ResultSet rs, int i) throws SQLException {
            SuggestionReplyCheckInfoDao dao = new SuggestionReplyCheckInfoDao(
                    rs.getString("id"),
                    rs.getString("suggestion_id"),
                    rs.getString("user_id"),
                    rs.getString("suggestion_content"),
                    rs.getString("suggestion_contact"),

                    rs.getString("suggestion_tel"),
                    Utils.GetDate(rs.getString("submit_date")),
                    rs.getString("img_1"),
                    rs.getString("img_2"),
                    rs.getString("img_3"),

                    rs.getString("reply_id"),
                    rs.getString("reply_content"),
                    rs.getString("reply_date")==null?null:Utils.GetDate(rs.getString("reply_date")),
                    rs.getString("check_reject"),
                    rs.getBoolean("check_state"),
                    rs.getString("check_date")==null?null:Utils.GetDate(rs.getString("check_date"))
                    );
            return dao;
        }
    }
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
                    rs.getString("submit_date")==null?null:Utils.GetDate(rs.getString("submit_date")),
                    rs.getString("img_1"),
                    rs.getString("img_2"),
                    rs.getString("img_3"),
                    rs.getString("id"),
                    rs.getString("suggestion_id"),
                    rs.getString("reply_content"),
                    rs.getString("reply_openid"),
                    rs.getString("reply_date")==null?null:Utils.GetDate(rs.getString("reply_date")),
                    rs.getString("check_openid"),
                    rs.getInt("check_state"),
                    rs.getString("check_reject"),
                    rs.getString("check_date")==null?null:Utils.GetDate(rs.getString("check_date")));
            return dao;
        }
    }



}
