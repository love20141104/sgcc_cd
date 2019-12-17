package com.sgcc.repository;

import com.example.CurrentPage;
import com.example.PaginationHelper;
import com.example.Utils;
import com.sgcc.dao.SuggestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class SuggestionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${precompile}")
    private Boolean precompile;

    private Logger logger = Logger.getLogger(SuggestionRepository.class.toString());


    public List<SuggestionDao> findAllByUserID(String userId){
        if (precompile) {
            String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                    "suggestion_tel," + Utils.GetSQLDateStr("submit_date") + ",img_1,img_2,img_3,reply_user_id,reply_content,reply_date from b_suggestion";
//        String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
//                "suggestion_tel,submit_date,img_1,img_2,img_3,reply_user_id,reply_content,reply_date from b_suggestion";
            sql = sql + " where user_id = ? ";
            logger.info("查询所有意见信息:" + sql);
            return jdbcTemplate.query(sql,new Object[]{userId}, new suggestionRowMapper());
//        DATE_FORMAT(你的日期字段 ,"%Y-%m-%d") AS date
        }else {
            String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                    "suggestion_tel," + Utils.GetSQLDateStr("submit_date") + ",img_1,img_2,img_3,reply_user_id,reply_content,reply_date from b_suggestion";
//        String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
//                "suggestion_tel,submit_date,img_1,img_2,img_3,reply_user_id,reply_content,reply_date from b_suggestion";
            sql = sql + " where user_id = '" + userId + "'";
            logger.info("查询所有意见信息:" + sql);
            return jdbcTemplate.query(sql, new suggestionRowMapper());
//        DATE_FORMAT(你的日期字段 ,"%Y-%m-%d") AS date
        }
    }

    public SuggestionDao findBySuggestionId(String suggestion_id){
        if (precompile) {
            try {
                String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                        "suggestion_tel,submit_date,img_1,img_2,img_3,reply_user_id,reply_content,reply_date from b_suggestion";
                sql = sql + " where id = ? ";
                logger.info("查询所有意见信息:" + sql);
                return jdbcTemplate.queryForObject(sql,new Object[]{suggestion_id}, new suggestionRowMapper());
            } catch (Exception e) {
                return null;
            }
        }else {
            try {
                String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                        "suggestion_tel,submit_date,img_1,img_2,img_3,reply_user_id,reply_content,reply_date from b_suggestion";
                sql = sql + " where id = '" + suggestion_id + "'";
                logger.info("查询所有意见信息:" + sql);
                return jdbcTemplate.queryForObject(sql, new suggestionRowMapper());
            } catch (Exception e) {
                return null;
            }
        }
    }
    public SuggestionDao findById(String id){
        try{
            String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                    "suggestion_tel,submit_date,img_1,img_2,img_3,reply_user_id,reply_content,reply_date from b_suggestion";
            sql = sql + " where id = '" + id + "'";
            return jdbcTemplate.queryForObject(sql,new suggestionRowMapper());
        }catch (Exception e )
        {
            return null;
        }
    }
    public SuggestionDao findByReplyUserId(String reply_user_id){
        try{
            String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                    "suggestion_tel,submit_date,img_1,img_2,img_3,reply_user_id,reply_content,reply_date from b_suggestion";
            sql = sql + " where reply_user_id = '" + reply_user_id + "'";
            return jdbcTemplate.queryForObject(sql,new suggestionRowMapper());
        }catch (Exception e )
        {
            return null;
        }
    }

    @Transactional
    public SuggestionDao update(String reply_user_id , String reply_content, Date reply_date, String suggestion_id){
        String sql = "update b_suggestion set reply_user_id='" + reply_user_id+ "',"+
                "reply_content = '" + reply_content +"'," + "reply_date = '" + Utils.GetTime(reply_date) +"'";
        sql = sql + " where id = '" + suggestion_id + "'";
        jdbcTemplate.execute(sql);
        return findBySuggestionId(suggestion_id);
    }

    @Transactional
    public SuggestionDao update(SuggestionDao dao){
        String sql = "update b_suggestion set reply_user_id='" + dao.getReplyUserId()+ "',"+
                "reply_content = '" + dao.getReplyContent() +"'," + "reply_date = '" + Utils.GetTime(dao.getReplyDate()) +"' ";
        sql = sql + " where  id = '" + dao.getSuggestionId() + "'";
        jdbcTemplate.execute(sql);
        return findBySuggestionId(dao.getSuggestionId());
    }
    /**
     * 查询所有意见信息
     * @return
     */
    public List<SuggestionDao> findAll(){
        String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                "suggestion_tel,submit_date,img_1,img_2,img_3,reply_user_id,reply_content,reply_date from b_suggestion";
        logger.info("查询所有意见信息:"+sql);
        return jdbcTemplate.query(sql,new suggestionRowMapper());
    }

    public CurrentPage<SuggestionDao> findAll(int getPageNo, int getPageSize) {
        PaginationHelper<SuggestionDao> ph = new PaginationHelper<>();
        // 持久化
        String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                "suggestion_tel,submit_date,img_1,img_2,img_3,reply_user_id,reply_content,reply_date from b_suggestion";
        String countSql = "SELECT Count(id) FROM b_suggestion;";
        try {
            return ph.fetchPage(jdbcTemplate, countSql, sql, new Object[]{}, getPageNo,getPageSize, new suggestionRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加意见信息
     * @param suggestionDao
     */
    @Transactional
    public void saveAll(SuggestionDao suggestionDao){
        if (precompile) {
            String sql = "insert into b_suggestion(id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                    "suggestion_tel,submit_date,img_1,img_2,img_3) " +
                    "values(?,?,?,?,? ,?,?,?,?,? )";
            logger.info("添加意见信息:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                    suggestionDao.getId()
                    ,suggestionDao.getSuggestionId()
                    ,suggestionDao.getUserId()
                    ,suggestionDao.getSuggestionContent()
                    , suggestionDao.getSuggestionContact()

                    ,suggestionDao.getSuggestionTel()
                    , Utils.GetTime(suggestionDao.getSubmitDate())
                    , suggestionDao.getImg_1()
                    , suggestionDao.getImg_2()
                    , suggestionDao.getImg_3()
            });
        }else {
            String sql = "insert into b_suggestion(id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                    "suggestion_tel,submit_date,img_1,img_2,img_3) " +
                    "values('" + suggestionDao.getId() + "','" + suggestionDao.getSuggestionId() + "'" +
                    ",'" + suggestionDao.getUserId() + "','" + suggestionDao.getSuggestionContent() + "'" +
                    ",'" + suggestionDao.getSuggestionContact() + "','" + suggestionDao.getSuggestionTel() + "'" +
                    ",'" + Utils.GetTime(suggestionDao.getSubmitDate()) + "','" + suggestionDao.getImg_1() + "'" +
                    ",'" + suggestionDao.getImg_2() + "','" + suggestionDao.getImg_3() + "')";
            logger.info("添加意见信息:" + sql);
            jdbcTemplate.update(sql);
        }
    }

    /**
     * 修改意见信息
     * @param suggestionDao
     */
    @Transactional
    public void updateAll(SuggestionDao suggestionDao){
        if (precompile) {
            String sql = "update b_suggestion set suggestion_content=? " +
                    ",suggestion_contact=? " +
                    ",suggestion_tel=?  where id=? ";

            jdbcTemplate.update(sql,new Object[]{
                    suggestionDao.getSuggestionContent()
                    ,suggestionDao.getSuggestionContact()
                    ,suggestionDao.getSuggestionTel()
                    ,suggestionDao.getSuggestionId()
            });
        }else {
            String sql = "update b_suggestion set suggestion_content='" + suggestionDao.getSuggestionContent() + "'" +
                    ",suggestion_contact='" + suggestionDao.getSuggestionContact() + "'" +
                    ",suggestion_tel='" + suggestionDao.getSuggestionTel() + "' where suggestion_id='" + suggestionDao.getSuggestionId() + "'";

            jdbcTemplate.update(sql);
        }
    }

    /**
     * 删除意见信息
     * @param suggestionIds
     */
    @Transactional
    public void deleteAll(List<String> suggestionIds){
        if (precompile) {
            String sql = "delete from b_suggestion where id = ?";
            jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
                public int getBatchSize() {
                    return suggestionIds.size();
                }
                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    ps.setString(1,suggestionIds.get(i));
                }
            });
        }else {
            String sql = "delete from b_suggestion where suggestion_id in('" + Utils.joinStrings(suggestionIds, "','") + "')";
            jdbcTemplate.execute(sql);
        }
    }

    class suggestionRowMapper implements RowMapper<SuggestionDao>{
        @Override
        public SuggestionDao mapRow(ResultSet rs, int i) throws SQLException {
            SuggestionDao dao = new SuggestionDao(
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
                    rs.getString("user_location"),
                    rs.getString("reply_user_id"),
                    rs.getString("reply_content"),
                    null,
                    rs.getBoolean("")
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
}
