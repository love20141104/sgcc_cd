package com.sgcc.repository;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.sgcc.dao.QuestionAnswerDao;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 问题回答增删改查
 */
@Repository
public class QAnswersRepository {
    private Logger logger = Logger.getLogger(QAnswersRepository.class.toString());
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${precompile}")
    private Boolean precompile;
    /**
     * 查询所有问题回答
     * @param
     */
    public List<QuestionAnswerDao> findAllQAnswer(){
        String sql = "select id,category_id,question_desc,answer,available from d_question_answer where available = 1 ";
        List<QuestionAnswerDao> answerDaoList = jdbcTemplate.query(sql,new answerRowMapper());
        return answerDaoList;
    }

    public List<QAnswerDTO> findQAnswerList(String category_name, String question_desc, String answer) {
        if (precompile) {
            Object[] objects = {};
            ArrayList<Object> objects1 = new ArrayList<>();

            String sql = "select a.id,a.category_id,b.category_desc as category_name,a.question_desc,a.answer,a.available " +
                    "from d_question_answer a,d_question_category b " +
                    "where available = 1 and a.category_id = b.category_id ";
            StringBuffer sql_where = new StringBuffer();
            if (!Strings.isNullOrEmpty(category_name)) {
                sql_where.append(" b.category_desc like ? and ");
                objects1.add("%"+category_name+"%");

            }
            if (!Strings.isNullOrEmpty(question_desc)) {
                sql_where.append("a.question_desc like ? and ");
                objects1.add("%"+question_desc+"%");
            }
            if (!Strings.isNullOrEmpty(answer)) {
                sql_where.append("a.answer like ? and ");
                objects1.add("%"+answer+"%");
            }

            if (!Strings.isNullOrEmpty(sql_where.toString())) {
                sql += "and " + sql_where.toString().substring(0, sql_where.toString().length() - 4);
            }
            if(objects1.size()>0){
                Object[] objects2 = new Object[objects1.size()];
                for (int i = 0; i <objects1.size() ; i++) {
                    objects2[i]=objects1.get(i);
                }
                objects=objects2;
            }
            logger.info("SQL:" + sql);
            List<QAnswerDTO> answerDaoList = jdbcTemplate.query(sql,objects, new questionAnswerRowMapper());
            return answerDaoList;
        } else {
            String sql = "select a.id,a.category_id,b.category_desc as category_name,a.question_desc,a.answer,a.available " +
                    "from d_question_answer a,d_question_category b " +
                    "where available = 1 and a.category_id = b.category_id ";
            StringBuffer sql_where = new StringBuffer();
            if (!Strings.isNullOrEmpty(category_name)) {
                sql_where.append(" b.category_desc like '%").append(category_name + "%' and ");
            }
            if (!Strings.isNullOrEmpty(question_desc)) {
                sql_where.append("a.question_desc like '%").append(question_desc + "%' and ");
            }
            if (!Strings.isNullOrEmpty(answer)) {
                sql_where.append("a.answer like '%").append(answer + "%' and ");
            }

            if (!Strings.isNullOrEmpty(sql_where.toString())) {
                sql += "and " + sql_where.toString().substring(0, sql_where.toString().length() - 4);
            }
            logger.info("SQL:" + sql);
            List<QAnswerDTO> answerDaoList = jdbcTemplate.query(sql, new questionAnswerRowMapper());
            return answerDaoList;
        }
    }

    public List<QuestionAnswerDao> findQAnswerByCategoryId(String categoryId){
        if (precompile) {
            String sql = "select id,category_id,question_desc,answer,available from d_question_answer"
                    + " where available = 1 and category_id = ? ";
            logger.info("SQL:" + sql);
            List<QuestionAnswerDao> answerDaoList = jdbcTemplate.query(sql,new Object[]{categoryId}, new answerRowMapper());
            return answerDaoList;
        }else {
            String sql = "select id,category_id,question_desc,answer,available from d_question_answer"
                    + " where available = 1 and category_id = '" + categoryId + "'";
            logger.info("SQL:" + sql);
            List<QuestionAnswerDao> answerDaoList = jdbcTemplate.query(sql, new answerRowMapper());
            return answerDaoList;
        }
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
     * @param ids
     */
    public void delQAnswer(List<String> ids){
        if (precompile) {
            String sql = "update d_question_answer set available = 0 where id = ? ";
            jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
                public int getBatchSize() {
                    return ids.size();
                }
                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    ps.setString(1,ids.get(i));
                }
            });
        }else {
            String sql = "update d_question_answer set available = 0 where id in ('"
                    + Joiner.on("','").join(ids) + "')";
            jdbcTemplate.execute(sql);
        }
    }

    /**
                    rs.getString("category_id"),
     * 修改问题回答
     * @param answerDaoList
     */
    public void updateQAnswer(List<QuestionAnswerDao> answerDaoList){
        String sql = "update d_question_answer set question_desc=?,answer=?,category_id=?  where id=?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,answerDaoList.get(i).getQuestionDesc());
                ps.setString(2,answerDaoList.get(i).getAnswer());
                ps.setString(3,answerDaoList.get(i).getCategoryId());
                ps.setString(4,answerDaoList.get(i).getId());
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
