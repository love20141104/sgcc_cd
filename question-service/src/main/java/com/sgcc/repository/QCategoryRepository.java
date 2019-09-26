package com.sgcc.repository;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.sgcc.dao.QuestionCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 问题类型增删改查
 */
@Repository
public class QCategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询所有问题类型
     * @param
     */
    public List<QuestionCategoryDao> findAllQCategory(){
        String sql = "select id,category_id,category_desc,category_order,category_available from d_question_category where category_available=1";
        List<QuestionCategoryDao> categoryDaoList = jdbcTemplate.query(sql,new categoryRowMapper());
        return categoryDaoList;
    }


    /**
     * 新增问题类型
     * @param categoryList
     */
    public void addQCategory(List<QuestionCategoryDao> categoryList){
        String sql = "insert into d_question_category(id,category_id,category_desc,category_order) " +
                "values(?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,categoryList.get(i).getId());
                ps.setString(2,categoryList.get(i).getCategoryId());
                ps.setString(3,categoryList.get(i).getCategoryDesc());
                ps.setInt(4,categoryList.get(i).getCategoryOrder());
            }

            @Override
            public int getBatchSize() {
                return categoryList.size();
            }
        });
    }

    /**
     * 作废问题类型
     * @param categoryIds
     */
    public void delQCategory(List<String> categoryIds){
        String sql = "update d_question_category set category_available=0 where category_id in ('"
                + Joiner.on("','").join(categoryIds)
                +"')";
        jdbcTemplate.execute(sql);
    }

    /**
     * 修改问题类型
     * @param categoryList
     */
    public void updateQCategory(List<QuestionCategoryDao> categoryList){
        String sql = "update d_question_category set category_desc=?,category_order=?  where category_id=? and category_available=1";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,categoryList.get(i).getCategoryDesc());
                ps.setInt(2,categoryList.get(i).getCategoryOrder());
                ps.setString(3,categoryList.get(i).getCategoryId());
            }

            @Override
            public int getBatchSize() {
                return categoryList.size();
            }
        });
    }

    /**
     * 查询问题分类
     *
     * @param categoryId
     * @param categoryDesc
     */
    public List<QuestionCategoryDao> selectQuestionCategory(String categoryId, String categoryDesc) {
        String sql = "select id,category_id,category_desc,category_order,category_available from d_question_category";
        StringBuffer sql_where = new StringBuffer();
        if(!Strings.isNullOrEmpty(categoryId)){
            sql_where.append(" category_id like '%").append("d_question_category%' and ");
        }if(Strings.isNullOrEmpty(categoryDesc)){
            sql_where.append("category_desc like '%").append("category_desc%' and ");
        }

        if(!Strings.isNullOrEmpty(sql_where.toString())){
            sql +=" where " + sql_where.toString().substring(0,sql_where.toString().length() - 4);
        }
        return jdbcTemplate.query(sql,new categoryRowMapper());

    }

    class categoryRowMapper implements RowMapper<QuestionCategoryDao> {
        @Override
        public QuestionCategoryDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new QuestionCategoryDao(
                    rs.getString("id"),
                    rs.getString("category_id"),
                    rs.getString("category_desc"),
                    rs.getInt("category_order"),
                    rs.getBoolean("category_available")
            );
        }

    }


}
