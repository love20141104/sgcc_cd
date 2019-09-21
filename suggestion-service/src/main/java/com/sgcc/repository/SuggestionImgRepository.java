package com.sgcc.repository;

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

@Repository
public class SuggestionImgRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * 查询所有意见图片信息
     * @return
     */
    public List<SuggestionImgDao> findAllSuggestionImg(){
        String sql = "select id,img_id,user_id,img_url,img_date from b_suggestion_img";
        return jdbcTemplate.query(sql,new suggestionImgRowMapper());
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
    class suggestionImgRowMapper implements RowMapper<SuggestionImgDao> {

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
