package com.sgcc.repository;

import com.example.Utils;
import com.sgcc.dao.SuggestionImgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${precompile}")
    private Boolean precompile;
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
     * @param ids
     */
    public void delSuggestionImg(List<String> ids){
        if (precompile) {
            String sql = "delete from b_suggestion_img where img_id = ?";
            jdbcTemplate.update(sql,new BatchPreparedStatementSetter() {
                public int getBatchSize() {
                    return ids.size();
                }
                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    ps.setString(1,ids.get(i));
                }
            });
        }else {
            String sql = "delete from b_suggestion_img where img_id in('" + Utils.joinStrings(ids, ",") + "')";
            jdbcTemplate.update(sql);
        }
    }
    /**
     * 修改意见图片信息
     * @param suggestionImgDao
     */
    public void updateSuggestionImg(SuggestionImgDao suggestionImgDao){
        if (precompile) {
            String sql = "update b_suggestion_img set img_url=? " +
                    "where img_id=? ";
            jdbcTemplate.update(sql,new Object[]{suggestionImgDao.getImgUrl(),suggestionImgDao.getImgId()});
        }else {
            String sql = "update b_suggestion_img set img_url='" + suggestionImgDao.getImgUrl() + "' " +
                    "where img_id='" + suggestionImgDao.getImgId() + "'";
            jdbcTemplate.update(sql);
        }

    }
    /**
     * 添加意见图片信息
     * @param suggestionImgDao
     */
    public void addSuggestionImg(SuggestionImgDao suggestionImgDao){
        if (precompile) {
            String sql = "insert into b_suggestion_img(id,img_id,user_id,img_url,img_date) " +
                    "values(?,?,?,?,?)";
            jdbcTemplate.update(sql,new Object[]{
                    suggestionImgDao.getId()
                    ,suggestionImgDao.getImgId()
                    ,suggestionImgDao.getUserId()
                    ,suggestionImgDao.getImgUrl()
                    ,Utils.GetTime(suggestionImgDao.getSubmitDate())

            });
        }else {
            String sql = "insert into b_suggestion_img(id,img_id,user_id,img_url,img_date) " +
                    "values('" + suggestionImgDao.getId() + "','" + suggestionImgDao.getImgId() + "'" +
                    ",'" + suggestionImgDao.getUserId() + "','" + suggestionImgDao.getImgUrl() + "'" +
                    ",'" + Utils.GetTime(suggestionImgDao.getSubmitDate()) + "')";
            jdbcTemplate.update(sql);
        }
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
