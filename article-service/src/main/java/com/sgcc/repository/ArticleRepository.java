package com.sgcc.repository;
import com.example.Utils;
import com.google.common.base.Converter;
import com.sgcc.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Repository
public class ArticleRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${precompile}")
    private Boolean precompile;

    private Logger logger = Logger.getLogger(ArticleRepository.class.toString());

    public List<ArticleDao> findAll() {

        String sql = "select id,article_title,article_desc,article_img,article_url," +
                "article_recommended,article_type," + Utils.GetSQLDateStr("submit_time")
                + ",order_no from d_article order by order_no desc";
        try {
            logger.info("SQL:" + sql);
            return jdbcTemplate.query(sql, new Object[]{}, new articleRowMapper());
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public List<ArticleDao> findAllByRecommended(Boolean isRecommended) {
        String sql = "select id,article_title,article_desc,article_img,article_url," +
                " article_recommended,article_type," + Utils.GetSQLDateStr("submit_time")
                + ",order_no from d_article"
                + " where article_recommended = ? order by order_no desc";
        try {
            logger.info("SQL:" + sql);
            return jdbcTemplate.query(sql, new Object[]{isRecommended}
                    , new articleRowMapper());
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public List<ArticleDao> findAllByArticleType(String articleType) {

        String sql = "select id,article_title,article_desc,article_img,article_url," +
                " article_recommended,article_type," + Utils.GetSQLDateStr("submit_time")
                + ",order_no from d_article"
                + " where article_type = ? order by order_no desc";
        try {
            logger.info("SQL:" + sql);
            return jdbcTemplate.query(sql, new Object[]{articleType}, new articleRowMapper());
        } catch (NoSuchElementException e) {
            return null;
        }
    }
    public ArticleDao findByID( String id ){
        if (precompile) {
            String sql = "select id,article_title,article_desc,article_img,article_url," +
                    "article_recommended,article_type," + Utils.GetSQLDateStr("submit_time") + " from d_article"
             + " where id = ?";
            try {
                logger.info("SQL:" + sql);
                return jdbcTemplate.queryForObject(sql,new Object[]{id}, new articleRowMapper());
            } catch (NoSuchElementException e) {
                return null;
            }
        }else {
            String sql = "select id,article_title,article_desc,article_img,article_url," +
                    "article_recommended,article_type," + Utils.GetSQLDateStr("submit_time") + " from d_article";
            sql = sql + " where id = '" + id + "'";
            try {
                logger.info("SQL:" + sql);
                return jdbcTemplate.queryForObject(sql, new articleRowMapper());
            } catch (NoSuchElementException e) {
                return null;
            }
        }
    }

    @Transactional
    public void save(ArticleDao dao) {

        String sql = "insert into d_article(id,article_title,article_desc,article_img,article_url," +
                "article_recommended,article_type,submit_time,order_no) values(?,?,?,? ,?,?,?,?,?)";
        logger.info("SQL:" + sql);
        jdbcTemplate.update(sql, new Object[]{
                dao.getId()
                , dao.getArticle_title()
                , dao.getArticle_desc()
                , dao.getArticle_img()

                , dao.getArticle_url()
                , dao.isArticle_recommended()
                , dao.getArticle_type()
                , Utils.GetTime(dao.getSubmit_time())
                ,dao.getOrder_no()
        });

    }

    @Transactional
    public ArticleDao update(ArticleDao dao){
        if (precompile) {
            String sql = "update d_article set article_title=?" +
                    ",article_desc=?" +
                    ",article_img=?" +
                    ",article_url=?" +
                    ",article_recommended=?" +
                    ",article_type=?" +
                    ",submit_time=?" +
                    " where id=?";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                    dao.getArticle_title()
                    ,dao.getArticle_desc()
                    ,dao.getArticle_img()
                    ,dao.getArticle_url()

                    ,dao.isArticle_recommended()
                    ,dao.getArticle_type()
                    ,Utils.GetTime(dao.getSubmit_time())
                    ,dao.getId()
            });
            return findByID(dao.getId());
        }else {
            String sql = "update d_article set article_title='" + dao.getArticle_title() + "'" +
                    ",article_desc='" + dao.getArticle_desc() + "',article_img='" + dao.getArticle_img() + "'" +
                    ",article_url='" + dao.getArticle_url() + "'" +
                    ",article_recommended='" + Utils.Boolean2Int(dao.isArticle_recommended()) + "'" +
                    ",article_type='" + dao.getArticle_type() + "'" +
                    ",submit_time='" + Utils.GetTime(dao.getSubmit_time()) + "'" +
                    " where id='" + dao.getId() + "'";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql);
            return findByID(dao.getId());
        }
    }

    @Transactional
    public void deleteAll(List<String> articleIds){
        if (precompile) {
            String sql = "delete from d_article where id =?";
            logger.info("SQL:" + sql);
            jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
                        public int getBatchSize() {
                            return articleIds.size();
                        }
                        public void setValues(PreparedStatement ps, int i)
                                throws SQLException {
                            ps.setString(1,articleIds.get(i));
                        }
                    });
        }else {
            String strIds = Utils.joinStrings(articleIds, "','");
            String sql = "delete from d_article where id in('" + strIds + "')";
            logger.info("SQL:" + sql);
            jdbcTemplate.execute(sql);
        }
    }

    public List<ArticleDao> findAllByArticleTitle(String articleTitle) {

        articleTitle = "%" + articleTitle + "%";
        String sql = "select id,article_title,article_desc,article_img,article_url," +
                "article_recommended,article_type," + Utils.GetSQLDateStr("submit_time")
                + ",order_no from d_article"
                + " where article_title like ?";
        try {
            logger.info("SQL:" + sql);
            return jdbcTemplate.query(sql, new Object[]{articleTitle}, new articleRowMapper());
        } catch (NoSuchElementException e) {
            return null;
        }

    }
    public Integer getOrderById(String id) {
        String sql = "select order_no from d_article where id = ?";
        try {
            logger.info("SQL:" + sql);
            return jdbcTemplate.queryForObject(sql,new Object[]{id}, Integer.class);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
    public Integer getMaxOrder() {
        String sql = "select max(order_no) from d_article ";
        try {
            logger.info("SQL:" + sql);
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
    public void updateOrderById(String id,Integer orderNo) {
        String sql = "update d_article set order_no=? where id = ? ";

            logger.info("SQL:" + sql);
             jdbcTemplate.update(sql, new Object[]{orderNo,id});

    }

    class articleRowMapper implements RowMapper<ArticleDao>{
        @Override
        public ArticleDao mapRow(ResultSet rs, int i) throws SQLException {
            ArticleDao dao = new ArticleDao(
                    rs.getString("id"),
                    rs.getString("article_title"),
                    rs.getString("article_desc"),
                    rs.getString("article_img"),
                    rs.getString("article_url"),
                    Utils.Int2Boolean(rs.getInt("article_recommended")),
                    rs.getString("article_type"),
                    null,
                    rs.getInt("order_no")
            );
            if( rs.getDate("submit_time") != null )
            {
                dao.setSubmit_time( Utils.GetDate( rs.getString("submit_time")) );
            }

            return dao;
        }
    }
}
