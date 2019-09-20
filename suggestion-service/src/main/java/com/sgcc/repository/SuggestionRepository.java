package com.sgcc.repository;

import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionImgDao;
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
public class SuggestionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger = Logger.getLogger(SuggestionRepository.class.toString());



    /**
     * 查询所有意见信息
     * @return
     */
    public List<SuggestionDao> findAllSuggestion(){
        String sql = "select id,suggestion_id,user_id,suggestion_content,suggestion_contact," +
                "suggestion_tel,submit_date,img_1,img_2,img_3 from b_suggestion";
        logger.info("查询所有意见信息:"+sql);
        return jdbcTemplate.query(sql,new suggestionRowMapper());
    }

    /**
     * 查询所有意见图片信息
     * @return
     */
    public List<SuggestionImgDao> findAllSuggestionImg(){
        String sql = "select id,img_id,user_id,img_url,img_date from b_suggestion_img";
        return jdbcTemplate.query(sql,new suggestionImgRowMapper());
    }



    /**
     * 添加意见信息
     * @param suggestionDaoList
     */
    public void addSuggestion(List<SuggestionDao> suggestionDaoList){
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
     * 添加意见图片信息
     * @param suggestionImgDaoList
     */
    public void addSuggestionImg(List<SuggestionImgDao> suggestionImgDaoList){
        String sql = "insert into b_suggestion_img(id,img_id,user_id,img_url,img_date) values(?,?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,suggestionImgDaoList.get(i).getId());
                ps.setString(2,suggestionImgDaoList.get(i).getImgId());
                ps.setString(3,suggestionImgDaoList.get(i).getUserId());
                ps.setString(4,suggestionImgDaoList.get(i).getImgUrl());
                ps.setDate(5,new Date(suggestionImgDaoList.get(i).getSubmitDate().getTime()));
            }

            @Override
            public int getBatchSize() {
                return suggestionImgDaoList.size();
            }
        });

    }



    /**
     * 修改意见信息
     * @param suggestionDaoList
     */
    public void updateSuggestion(List<SuggestionDao> suggestionDaoList){
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
     * 修改意见图片信息
     * @param suggestionImgDaoList
     */
    public void updateSuggestionImg(List<SuggestionImgDao> suggestionImgDaoList){
        String sql = "update b_suggestion_img set img_url=? where img_id=?";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,suggestionImgDaoList.get(i).getImgUrl());
                ps.setString(2,suggestionImgDaoList.get(i).getImgId());
            }

            @Override
            public int getBatchSize() {
                return suggestionImgDaoList.size();
            }
        });

    }



    /**
     * 删除意见信息
     * @param suggestionDaoList
     */
    public void delSuggestion(List<SuggestionDao> suggestionDaoList){
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


    /**
     * 删除意见图片信息
     * @param suggestionImgDaoList
     */
    public void delSuggestionImg(List<SuggestionImgDao> suggestionImgDaoList){
        String sql = "delete from b_suggestion_img where img_id=?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,suggestionImgDaoList.get(i).getImgId());
            }

            @Override
            public int getBatchSize() {
                return suggestionImgDaoList.size();
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

    class suggestionImgRowMapper implements RowMapper<SuggestionImgDao>{

        @Override
        public SuggestionImgDao mapRow(ResultSet rs, int i) throws SQLException {
            return new SuggestionImgDao(
                    rs.getString("id"),
                    rs.getString("img_id"),
                    rs.getString("user_id"),
                    rs.getString("img_url"),
                    rs.getDate("img_date")
            );
        }
    }



}
