package com.sgcc.repository;

import com.example.CurrentPage;
import com.example.PaginationHelper;
import com.example.Utils;
import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionRejectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class SuggestionsRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${precompile}")
    private Boolean precompile;

    private Logger logger = Logger.getLogger(SuggestionsRepository.class.toString());

    // 回复人员未回复
    public List<SuggestionDao> findAllByReplyOpenID(String reply_openId )
    {
        String sql = "select bs.id,bs.suggestion_id,bs.user_id,bs.suggestion_content,bs.suggestion_contact,bs.suggestion_tel," +
               "bs.submit_date,bs.img_1,bs.img_2,bs.img_3,bs.reply_user_id,bs.reply_content,bs.reply_date" +
                " from b_suggestion bs left join b_suggestion_reply bsr on bs.suggestion_id = bsr.suggestion_id " +
                " where bsr.reply_openid = ? and bsr.reply_content is null and bsr.check_date is null and bsr.check_reject is null";

        return jdbcTemplate.query(sql,new Object[]{reply_openId}, new suggestionRowMapper());
    }


    // 回复人员回复被驳回
    public List<SuggestionRejectDao> findCheckNotPassedByReplyOpenID(String reply_openId )
    {
        String sql = "select bs.id,bs.suggestion_id,bs.user_id,bs.suggestion_content,bs.suggestion_contact,bs.suggestion_tel," +
                "bs.submit_date,bs.img_1,bs.img_2,bs.img_3,bsr.reply_openid as reply_user_id,bsr.reply_content,bsr.reply_date" +
                ",bsr.check_reject from b_suggestion bs left join b_suggestion_reply bsr on bs.suggestion_id = bsr.suggestion_id " +
                " where bsr.reply_openid = ? and bsr.check_reject is not null ";

        return jdbcTemplate.query(sql,new Object[]{reply_openId}, new SuggestionRejectDaoRowMapper());
    }

    // 审核人员已驳回
    public List<SuggestionRejectDao> findRejected(String check_openid )
    {
        String sql = "select bs.id,bs.suggestion_id,bs.user_id,bs.suggestion_content,bs.suggestion_contact,bs.suggestion_tel," +
                "bs.submit_date,bs.img_1,bs.img_2,bs.img_3,bsr.reply_openid as reply_user_id,bsr.reply_content,bsr.reply_date" +
                ",bsr.check_reject from b_suggestion bs left join b_suggestion_reply bsr on bs.suggestion_id = bsr.suggestion_id " +
                " where bsr.check_openid = ? and bsr.check_reject is not null ";

        return jdbcTemplate.query(sql,new Object[]{check_openid}, new SuggestionRejectDaoRowMapper());
    }

    // 审核人员待审核
    public List<SuggestionDao> findAllByCheckOpenID(String check_openId )
    {
        String sql = "select bs.id,bs.suggestion_id,bs.user_id,bs.suggestion_content,bs.suggestion_contact,bs.suggestion_tel," +
                "bs.submit_date,bs.img_1,bs.img_2,bs.img_3,bsr.reply_openid as reply_user_id,bsr.reply_content,bsr.reply_date" +
                " from b_suggestion bs left join b_suggestion_reply bsr on bs.suggestion_id = bsr.suggestion_id " +
                " where bsr.check_openid = ? and bsr.reply_content is not null and bsr.check_date is null and bsr.check_reject is null";

        return jdbcTemplate.query(sql,new Object[]{check_openId}, new suggestionRowMapper());
    }

    public List<SuggestionDao> findAllByUserID(String userId){
        if (precompile) {
            String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                    "suggestion_tel," + Utils.GetSQLDateStr("submit_date") + ",img_1,img_2,img_3,reply_user_id,reply_content,reply_date from b_suggestion" +
                    " where user_id = ? ";
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
                sql = sql + " where suggestion_id = ? ";
                logger.info("查询所有意见信息:" + sql);
                return jdbcTemplate.queryForObject(sql,new Object[]{suggestion_id}, new suggestionRowMapper());
            } catch (Exception e) {
                return null;
            }
        }else {
            try {
                String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                        "suggestion_tel,submit_date,img_1,img_2,img_3,reply_user_id,reply_content,reply_date from b_suggestion";
                sql = sql + " where suggestion_id = '" + suggestion_id + "'";
                logger.info("查询所有意见信息:" + sql);
                return jdbcTemplate.queryForObject(sql, new suggestionRowMapper());
            } catch (Exception e) {
                return null;
            }
        }
    }


    public SuggestionDao findById(String id){
        if (precompile) {
            try {
                String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                        "suggestion_tel,submit_date,img_1,img_2,img_3,reply_user_id,reply_content,reply_date from b_suggestion";
                sql = sql + " where id = ? ";
                return jdbcTemplate.queryForObject(sql,new Object[]{id}, new suggestionRowMapper());
            } catch (Exception e) {
                return null;
            }
        }else {
            try {
                String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                        "suggestion_tel,submit_date,img_1,img_2,img_3,reply_user_id,reply_content,reply_date from b_suggestion";
                sql = sql + " where id = '" + id + "'";
                return jdbcTemplate.queryForObject(sql, new suggestionRowMapper());
            } catch (Exception e) {
                return null;
            }
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
        if (precompile) {
            String sql = "update b_suggestion set reply_user_id=? ," +
                    "reply_content = ? ," + "reply_date = ? ";
            sql = sql + " where suggestion_id = ? ";
            jdbcTemplate.update(sql,new Object[]{
                    reply_user_id
                    ,reply_content
                    , Utils.GetTime(reply_date)
                    ,suggestion_id});
            return findBySuggestionId(suggestion_id);
        }else {
            String sql = "update b_suggestion set reply_user_id='" + reply_user_id + "'," +
                    "reply_content = '" + reply_content + "'," + "reply_date = '" + Utils.GetTime(reply_date) + "'";
            sql = sql + " where suggestion_id = '" + suggestion_id + "'";
            jdbcTemplate.execute(sql);
            return findBySuggestionId(suggestion_id);
        }
    }

    @Transactional
    public SuggestionDao update(SuggestionDao dao){
        if (precompile) {
            String sql = "update b_suggestion set reply_user_id=? ," +
                    "reply_content = ? ," + "reply_date = ? ";
            sql = sql + " where suggestion_id = ? ";
            jdbcTemplate.update(sql,new Object[]{
                    dao.getReplyUserId()
                    ,dao.getReplyContent()
                    , Utils.GetTime(dao.getReplyDate())
                    ,dao.getSuggestionId()});
            return dao;
        }else {
            String sql = "update b_suggestion set reply_user_id='" + dao.getReplyUserId() + "'," +
                    "reply_content = '" + dao.getReplyContent() + "'," + "reply_date = '" + Utils.GetTime(dao.getReplyDate()) + "'";
            sql = sql + " where suggestion_id = '" + dao.getSuggestionId() + "'";
            jdbcTemplate.execute(sql);
            return findBySuggestionId(dao.getSuggestionId());
        }
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
     * @param suggestionDaoList
     */
    @Transactional
    public void saveAll(List<SuggestionDao> suggestionDaoList){
        String sql = "insert into b_suggestion(id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                "suggestion_tel,submit_date,img_1,img_2,img_3) values(?,?,?,?,?,?,?,?,?,?)";
        logger.info("添加意见信息:"+sql);
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,suggestionDaoList.get(i).getId());
                ps.setString(2,suggestionDaoList.get(i).getSuggestionId());
                ps.setString(3,suggestionDaoList.get(i).getUserId());
                ps.setString(4,suggestionDaoList.get(i).getSuggestionContent());
                ps.setString(5,suggestionDaoList.get(i).getSuggestionContact());
                ps.setString(6,suggestionDaoList.get(i).getSuggestionTel());
                ps.setString(7,Utils.GetTime(suggestionDaoList.get(i).getSubmitDate()));
                ps.setString(8,suggestionDaoList.get(i).getImg_1());
                ps.setString(9,suggestionDaoList.get(i).getImg_2());
                ps.setString(10,suggestionDaoList.get(i).getImg_3());
            }

            @Override
            public int getBatchSize() {
                return suggestionDaoList.size();
            }
        });

    }

    /**
     * 修改意见信息
     * @param suggestionDaoList
     */
    @Transactional
    public void updateAll(List<SuggestionDao> suggestionDaoList){
        String sql = "update b_suggestion set suggestion_content=?,suggestion_contact=?,suggestion_tel=? where suggestion_id=?";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,suggestionDaoList.get(i).getSuggestionContent());
                ps.setString(2,suggestionDaoList.get(i).getSuggestionContact());
                ps.setString(3,suggestionDaoList.get(i).getSuggestionTel());
                ps.setString(4,suggestionDaoList.get(i).getSuggestionId());
            }

            @Override
            public int getBatchSize() {
                return suggestionDaoList.size();
            }
        });

    }

    /**
     * 删除意见信息
     * @param suggestionIds
     */
    @Transactional
    public void deleteAll(List<String> suggestionIds){
        String sql = "delete from b_suggestion where suggestion_id=?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,suggestionIds.get(i));
            }

            @Override
            public int getBatchSize() {
                return suggestionIds.size();
            }
        });
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
                    rs.getString("reply_user_id"),
                    rs.getString("reply_content"),
                    null
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

}
