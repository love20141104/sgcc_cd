package com.sgcc.repository;
import com.example.Utils;
import com.sgcc.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

    private Logger logger = Logger.getLogger(ArticleRepository.class.toString());

    public List<ArticleDao> findAll( ){
        String sql = "select id,article_title,article_desc,article_img,article_url," +
                "article_recommended,article_type" + Utils.GetSQLDateStr("submit_date")+ " from b_article";
        try{
            return jdbcTemplate.query(sql,new articleRowMapper());
        }
        catch (NoSuchElementException e )
        {
            return null;
        }
    }

    public List<ArticleDao> findAllByRecommended( Boolean isRecommended ){
        String sql = "select id,article_title,article_desc,article_img,article_url," +
                "article_recommended,article_type" + Utils.GetSQLDateStr("submit_date")+ " from b_article";
        sql = sql + " where article_recommended = '" + isRecommended + "'";
        try{
            return jdbcTemplate.query(sql,new articleRowMapper());
        }
        catch (NoSuchElementException e )
        {
            return null;
        }
    }

    public List<ArticleDao> findAllByArticleType( String articleType ){
        String sql = "select id,article_title,article_desc,article_img,article_url," +
                "article_recommended,article_type" + Utils.GetSQLDateStr("submit_date")+ " from b_article";
        sql = sql + " where article_type = '" + articleType + "'";
        try{
            return jdbcTemplate.query(sql,new articleRowMapper());
        }
        catch (NoSuchElementException e )
        {
            return null;
        }
    }
    public ArticleDao findByID( String id ){
        String sql = "select id,article_title,article_desc,article_img,article_url," +
                "article_recommended,article_type" + Utils.GetSQLDateStr("submit_date")+ " from b_article";
        sql = sql + " where id = '" + id + "'";
        try{
            return jdbcTemplate.queryForObject(sql,new articleRowMapper());
        }
        catch (NoSuchElementException e )
        {
            return null;
        }
    }

    @Transactional
    public void save(ArticleDao dao){
        String sql = "insert into b_article(id,article_title,article_desc,article_img,article_url," +
                "article_recommended,article_type,submit_time) values('"+dao.getId()+"'" +
                ",'"+dao.getArticle_title()+"','"+dao.getArticle_desc()+"','"+dao.getArticle_img()+"'" +
                ",'"+ dao.getArticle_url() +"','"+dao.isArticle_recommended()+"'" +
                ",'"+dao.getArticle_type()+"','"+dao.getSubmitDate()+"')";
        jdbcTemplate.execute(sql);
    }

    @Transactional
    public ArticleDao update(ArticleDao dao){
        String sql = "update b_article set article_title='"+dao.getArticle_title()+"'" +
                ",article_desc='"+dao.getArticle_desc()+"',article_img='"+dao.getArticle_img()+"'" +
                ",article_url='"+ dao.getArticle_url()+"'" +
                ",article_recommended='"+dao.isArticle_recommended()+
                ",article_type='"+dao.getArticle_type()+
                ",submit_time='"+dao.getSubmitDate()+
                "' where id='"+dao.getId()+"'";
        jdbcTemplate.update(sql);
        return findByID(dao.getId());
    }

    @Transactional
    public void deleteAll(List<String> articleIds){
        String strIds = Utils.joinStrings(articleIds,"','");
        String sql = "delete from b_article where id in('"+ strIds +"')";
        jdbcTemplate.execute(sql);
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
                    rs.getBoolean("article_recommended"),
                    rs.getString("article_type"),
                    null
            );
            if( rs.getDate("submit_date") != null )
            {
                dao.setSubmitDate( Utils.GetDate( rs.getString("submit_date")) );
            }

            return dao;
        }
    }
}
