package com.sgcc.repository;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.sgcc.dao.QuestionCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 问题类型增删改查
 */
@Repository
public class QCategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${precompile}")
    private Boolean precompile;
    /**
     * 查询所有问题类型
     * @param
     */
    public List<QuestionCategoryDao> findAllQCategory(){
        String sql = "select id,category_id,category_desc,category_order,category_detail,category_available from d_question_category where category_available=1";
        List<QuestionCategoryDao> categoryDaoList = jdbcTemplate.query(sql,new categoryRowMapper());
        return categoryDaoList;
    }


    /**
     * 新增问题类型
     * @param questionCategoryDao
     */
    public void addQCategory(QuestionCategoryDao questionCategoryDao){
        if (precompile) {
            String sql = "insert into d_question_category(id,category_id,category_desc,category_order) " +
                    "values(?,?,?,?)";
            jdbcTemplate.update(sql,new Object[]{
                    questionCategoryDao.getId()
                    ,questionCategoryDao.getCategoryId()
                    ,questionCategoryDao.getCategoryDesc()
                    ,questionCategoryDao.getCategoryOrder()
            });
        }else {
            String sql = "insert into d_question_category(id,category_id,category_desc,category_order) " +
                    "values('" + questionCategoryDao.getId() + "','" + questionCategoryDao.getCategoryId() + "'" +
                    ",'" + questionCategoryDao.getCategoryDesc() + "'," + questionCategoryDao.getCategoryOrder() + ")";
            jdbcTemplate.update(sql);
        }
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
     * @param questionCategoryDao
     */
    public void updateQCategory(QuestionCategoryDao questionCategoryDao){
        if (precompile) {
            String sql = "update d_question_category set category_desc=? " +
                    ",category_order=?  where category_id=? ";
            jdbcTemplate.update(sql,new Object[]{
                   questionCategoryDao.getCategoryDesc()
                    ,questionCategoryDao.getCategoryOrder()
                    ,questionCategoryDao.getCategoryId()
            });
        }else {
            String sql = "update d_question_category set category_desc='" + questionCategoryDao.getCategoryDesc() + "'" +
                    ",category_order=" + questionCategoryDao.getCategoryOrder() +
                    " where category_id='" + questionCategoryDao.getCategoryId() + "'";
            jdbcTemplate.update(sql);
        }
    }

    /**
     * 查询问题分类
     *
     * @param categoryId
     * @param categoryDesc
     */
    public List<QuestionCategoryDao> selectQuestionCategory(String categoryId, String categoryDesc) {
        if (precompile) {
            Object[] objects = {};
            ArrayList<Object> objects1 = new ArrayList<>();
            String sql = "select id,category_id,category_desc,category_order,category_available from d_question_category";
            StringBuffer sql_where = new StringBuffer();
            if (!Strings.isNullOrEmpty(categoryId)) {
                sql_where.append(" category_id like ? and ");
                objects1.add("%"+categoryId+"%");
            }
            if (!Strings.isNullOrEmpty(categoryDesc)) {
                sql_where.append("category_desc like ? and ");
                objects1.add("%"+categoryDesc+"%");
            }

            if (!Strings.isNullOrEmpty(sql_where.toString())) {
                sql += " where " + sql_where.toString().substring(0, sql_where.toString().length() - 4);
            }
            if(objects1.size()>0){
                for (int i = 0; i <objects1.size() ; i++) {
                    objects[i]=objects1.get(i);
                }
            }
            return jdbcTemplate.query(sql,objects, new categoryRowMapper());
        }else {
            String sql = "select id,category_id,category_desc,category_order,category_available from d_question_category";
            StringBuffer sql_where = new StringBuffer();
            if (!Strings.isNullOrEmpty(categoryId)) {
                sql_where.append(" category_id like '%").append(categoryId + "%' and ");
            }
            if (!Strings.isNullOrEmpty(categoryDesc)) {
                sql_where.append("category_desc like '%").append(categoryDesc + "%' and ");
            }

            if (!Strings.isNullOrEmpty(sql_where.toString())) {
                sql += " where " + sql_where.toString().substring(0, sql_where.toString().length() - 4);
            }
            return jdbcTemplate.query(sql, new categoryRowMapper());
        }

    }

    class categoryRowMapper implements RowMapper<QuestionCategoryDao> {
        @Override
        public QuestionCategoryDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new QuestionCategoryDao(
                    rs.getString("id"),
                    rs.getString("category_id"),
                    rs.getString("category_desc"),
                    rs.getInt("category_order"),
                    rs.getBoolean("category_available"),
                    rs.getString("category_detail")
            );
        }

    }


}
