package com.sgcc.repository;

import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionImgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class SuggestionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger = Logger.getLogger(SuggestionRepository.class.toString());


    public List<SuggestionDao> findAllByUserID(String userId){
        String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                "suggestion_tel,submit_date,img_1,img_2,img_3 from b_suggestion";
        sql = sql + " where user_id = '" + userId + "'";
        logger.info("查询所有意见信息:"+sql);
        return jdbcTemplate.query(sql,new suggestionRowMapper());
    }

    public SuggestionDao findBySuggestionId(String suggestion_id){
        String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                "suggestion_tel,submit_date,img_1,img_2,img_3 from b_suggestion";
        sql = sql + " where suggestion_id = '" + suggestion_id + "'";
        logger.info("查询所有意见信息:"+sql);
        return jdbcTemplate.query(sql,new suggestionRowMapper()).get(0);
    }


    /**
     * 查询所有意见信息
     * @return
     */
    public List<SuggestionDao> findAll(){
        String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                "suggestion_tel,submit_date,img_1,img_2,img_3 from b_suggestion";
        logger.info("查询所有意见信息:"+sql);
        return jdbcTemplate.query(sql,new suggestionRowMapper());
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
                ps.setDate(7,new Date(suggestionDaoList.get(i).getSubmitDate().getTime()));
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
     * @param suggestionDaoList
     */
    public void delAll(List<SuggestionDao> suggestionDaoList){
        String sql = "delete from b_suggestion where suggestion_id=?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,suggestionDaoList.get(i).getSuggestionId());
            }

            @Override
            public int getBatchSize() {
                return suggestionDaoList.size();
            }
        });
    }

    class suggestionRowMapper implements RowMapper<SuggestionDao>{

        @Override
        public SuggestionDao mapRow(ResultSet rs, int i) throws SQLException {
            return new SuggestionDao(
                    rs.getString("id"),
                    rs.getString("suggestion_id"),
                    rs.getString("user_id"),
                    rs.getString("suggestion_content"),
                    rs.getString("suggestion_contact"),
                    rs.getString("suggestion_tel"),
                    rs.getDate("submit_date"),
                    rs.getString("img_1"),
                    rs.getString("img_2"),
                    rs.getString("img_3")
                    );
        }
    }





}
