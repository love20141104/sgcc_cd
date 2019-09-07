package com.sgcc.repository;

import com.sgcc.dao.QuestionAnswerDao;
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

@Repository
public class QuestionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /***********************************问题回答增删改查**************************************/
    /**
     * 查询所有问题回答
     * @param
     */
    public List<QuestionAnswerDao> findAllQAnswer(){
        String sql = "select id,category_id,question_desc,answer,available from d_question_answer";
        List<QuestionAnswerDao> answerDaoList = jdbcTemplate.query(sql,new answerRowMapper());
        return answerDaoList;
    }


    /**
     * 新增问题回答
     * @param answerDaoList
     */
    public void addQAnswer(List<QuestionAnswerDao> answerDaoList){
        String sql = "insert into d_question_answer(id,category_id,question_desc,answer) " +
                "values(?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,answerDaoList.get(i).getId());
                ps.setString(2,answerDaoList.get(i).getCategoryId());
                ps.setString(3,answerDaoList.get(i).getQuestionDesc());
                ps.setString(4,answerDaoList.get(i).getAnswer());
            }

            @Override
            public int getBatchSize() {
                return answerDaoList.size();
            }
        });
    }

    /**
     * 删除问题回答
     * @param answerDaoList
     */
    public void delQAnswer(List<QuestionAnswerDao> answerDaoList){
        String sql = "delete from d_question_answer where id=?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,answerDaoList.get(i).getId());
            }

            @Override
            public int getBatchSize() {
                return answerDaoList.size();
            }
        });
    }

    /**
     * 修改问题回答
     * @param answerDaoList
     */
    public void updateQAnswer(List<QuestionAnswerDao> answerDaoList){
        String sql = "update d_question_answer set question_desc=?,answer=?  where id=?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,answerDaoList.get(i).getQuestionDesc());
                ps.setString(2,answerDaoList.get(i).getAnswer());
                ps.setString(3,answerDaoList.get(i).getId());
            }

            @Override
            public int getBatchSize() {
                return answerDaoList.size();
            }
        });
    }

    class answerRowMapper implements RowMapper<QuestionAnswerDao> {
        @Override
        public QuestionAnswerDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new QuestionAnswerDao(
                    rs.getString("id"),
                    rs.getString("category_id"),
                    rs.getString("question_desc"),
                    rs.getString("answer"),
                    rs.getBoolean("available")
            );
        }

    }

    /***********************************问题类型增删改查**************************************/
    /**
     * 查询所有问题类型
     * @param
     */
    public List<QuestionCategoryDao> findAllQCategory(){
        String sql = "select id,category_id,category_desc,category_order,category_available from d_question_category";
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
     * 删除问题类型
     * @param categoryList
     */
    public void delQCategory(List<QuestionCategoryDao> categoryList){
        String sql = "delete from d_question_category where category_id=?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,categoryList.get(i).getCategoryId());
            }

            @Override
            public int getBatchSize() {
                return categoryList.size();
            }
        });
    }

    /**
     * 修改问题类型
     * @param categoryList
     */
    public void updateQCategory(List<QuestionCategoryDao> categoryList){
        String sql = "update d_question_category set category_desc=?,category_order=?  where category_id=?";
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
