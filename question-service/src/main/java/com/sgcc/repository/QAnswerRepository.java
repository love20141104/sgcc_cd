package com.sgcc.repository;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.sgcc.dao.QuestionAnswerDao;
import com.sgcc.dao.QuestionAnswerDetailDao;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.dtomodel.question.QAnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * 问题回答增删改查
 */
@Repository
public class QAnswerRepository {
    private Logger logger = Logger.getLogger(QAnswerRepository.class.toString());
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${precompile}")
    private Boolean precompile;


    public List<QuestionAnswerDetailDao> findAllQAnswerDetail(String id){
        String sql = "select a.id,a.category_id,b.category_desc,a.question_desc,a.answer,a.available " +
                "from d_question_answer a,d_question_category b " +
                "where a.category_id=b.category_id and a.available = 1 and a.id = ?;";
        List<QuestionAnswerDetailDao> answerDaoList = jdbcTemplate.query(sql,new Object[]{id},new answerDetailRowMapper());
        return answerDaoList;
    }


    public List<QuestionCategoryDao> findQCategorys(){

        String sql = "select id,category_id,category_desc,category_order,category_available,category_detail from d_question_category"
                + " where category_available = 1 ";
        List<QuestionCategoryDao> questionCategoryDaos = jdbcTemplate.query(sql,new answerCategoryRowMapper());
        return questionCategoryDaos;
    }

    /**
     * 查询所有问题回答
     * @param
     */
    public List<QuestionAnswerDao> findAllQAnswer(String keyword){
        String sql = "select id,category_id,question_desc,answer,available from d_question_answer where available = 1 ";
        if (!Strings.isNullOrEmpty(keyword))
            sql += " and answer like ?";
        List<QuestionAnswerDao> answerDaoList = jdbcTemplate.query(sql,new Object[]{"%"+keyword+"%"},new answerRowMapper());
        return answerDaoList;
    }

    public List<QAnswerDTO> findQAnswerList(String category_name, String question_desc, String answer){

        String sql = "select a.id,a.category_id,b.category_desc as category_name,a.question_desc,a.answer,a.available " +
                "from d_question_answer a,d_question_category b "+
                "where available = 1 and a.category_id = b.category_id ";
        StringBuffer sql_where = new StringBuffer();
        if(!Strings.isNullOrEmpty(category_name)){
            sql_where.append(" b.category_desc like '%").append(category_name+"%' and ");
        }if(!Strings.isNullOrEmpty(question_desc)){
            sql_where.append("a.question_desc like '%").append(question_desc+"%' and ");
        }if(!Strings.isNullOrEmpty(answer)){
            sql_where.append("a.answer like '%").append(answer+"%' and ");
        }

        if(!Strings.isNullOrEmpty(sql_where.toString())){
            sql += "and "+sql_where.toString().substring(0,sql_where.toString().length() - 4);
        }

        List<QAnswerDTO> answerDaoList = jdbcTemplate.query(sql,new questionAnswerRowMapper());
        return answerDaoList;
    }

    public List<QuestionAnswerDao> findQAnswerByCategoryId(String categoryId){

        String sql = "select id,category_id,question_desc,answer,available from d_question_answer"
                + " where available = 1 and category_id = '"+categoryId+"'";
        List<QuestionAnswerDao> answerDaoList = jdbcTemplate.query(sql,new answerRowMapper());
        return answerDaoList;
    }


    /**
     * 新增问题回答
     * @param questionAnswerDao
     */
    public void addQAnswer(QuestionAnswerDao questionAnswerDao){
        if (precompile) {
            String sql = "insert into d_question_answer(id,category_id,question_desc,answer) " +
                    "values(?,?,?,?)";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                    questionAnswerDao.getId()
                    ,questionAnswerDao.getCategoryId()
                    ,questionAnswerDao.getQuestionDesc()
                    ,questionAnswerDao.getAnswer()
            });
        }else {
            String sql = "insert into d_question_answer(id,category_id,question_desc,answer) " +
                    "values('" + questionAnswerDao.getId() + "','" + questionAnswerDao.getCategoryId() + "'" +
                    ",'" + questionAnswerDao.getQuestionDesc() + "','" + questionAnswerDao.getAnswer() + "')";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql);
        }
    }

    /**
     * 删除问题回答
     * @param ids
     */
    public void delQAnswer(List<String> ids){
        String sql = "update d_question_answer set available = 0 where id in ('"
                +Joiner.on("','").join(ids)+"')";
        jdbcTemplate.execute(sql);
    }

    /**
                    rs.getString("category_id"),
     * 修改问题回答
     * @param questionAnswerDao
     */
    public void updateQAnswer(QuestionAnswerDao questionAnswerDao){
        if (precompile) {
            String sql = "update d_question_answer set question_desc=? " +
                    ",answer=?  where id=? ";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                        questionAnswerDao.getQuestionDesc()
                        ,questionAnswerDao.getAnswer()
                        ,questionAnswerDao.getId()
            });
        }else {
            String sql = "update d_question_answer set question_desc='" + questionAnswerDao.getQuestionDesc() + "'" +
                    ",answer='" + questionAnswerDao.getAnswer() + "'  where id='" + questionAnswerDao.getId() + "'";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql);
        }
    }

    class answerCategoryRowMapper implements RowMapper<QuestionCategoryDao> {
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

    class answerDetailRowMapper implements RowMapper<QuestionAnswerDetailDao> {
        @Override
        public QuestionAnswerDetailDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new QuestionAnswerDetailDao(
                    rs.getString("id"),
                    rs.getString("category_id"),
                    rs.getString("category_desc"),
                    rs.getString("question_desc"),
                    rs.getString("answer"),
                    rs.getBoolean("available")
            );
        }

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

    class questionAnswerRowMapper implements RowMapper<QAnswerDTO> {
        @Override
        public QAnswerDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new QAnswerDTO(
                    rs.getString("id"),
                    rs.getString("category_id"),
                    rs.getString("category_name"),
                    rs.getString("question_desc"),
                    rs.getString("answer"),
                    rs.getBoolean("available")
            );
        }

    }




}
