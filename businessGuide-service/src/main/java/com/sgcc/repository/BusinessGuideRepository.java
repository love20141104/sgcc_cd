package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.BusinessGuideBriefDao;
import com.sgcc.dao.BusinessGuideDao;
import com.sgcc.dto.BusinessGuideDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class BusinessGuideRepository {
    private Logger logger = Logger.getLogger(BusinessGuideRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${precompile}")
    private Boolean precompile;

    @Transactional
    public void insertBusinessGuide(BusinessGuideDao businessGuideDao){
        if (precompile) {
            String sql = "insert into d_business_guide(id,title,content,content_url,category_id,create_date)" +
                    " values (?,?,?, ?,?,? )";
            logger.info("insertSQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                    businessGuideDao.getId()
                    ,businessGuideDao.getTitle()
                    ,businessGuideDao.getContent()
                    ,businessGuideDao.getContentUrl()
                    ,businessGuideDao.getCategoryId()
                    ,Utils.GetTime(businessGuideDao.getCreateDate())
            });
        }else {
            String sql = "insert into d_business_guide(id,title,content,content_url,category_id,create_date)values ( " +
                    "'" + businessGuideDao.getId() + "'  , '"
                    + businessGuideDao.getTitle() + "'  , '"
                    + businessGuideDao.getContent() + "'  , '"
                    + businessGuideDao.getContentUrl() + "'  , '"
                    + businessGuideDao.getCategoryId() + "'  , '"
                    + Utils.GetTime(businessGuideDao.getCreateDate())
                    + "' )";
            logger.info("insertSQL:" + sql);
            jdbcTemplate.execute(sql);
        }
    }
    @Transactional
    public void updateBusinessGuide(BusinessGuideDao businessGuideDao){
        if (precompile) {
            String sql = "update  d_business_guide set"
                    + " title= ?"
                    + " ,content= ?"
                    + " ,content_url= ?"
                    + " ,category_id=  ?"
                    + " where id= ?" ;
            logger.info("updateSQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                    businessGuideDao.getTitle()
                    ,businessGuideDao.getContent()
                    ,businessGuideDao.getContentUrl()
                    ,businessGuideDao.getCategoryId()
                    ,businessGuideDao.getId()
            });
        }else {
            String sql = "update  d_business_guide set"
                    + " title= '" + businessGuideDao.getTitle()
                    + "' ,content= '" + businessGuideDao.getContent()
                    + "' ,content_url= '" + businessGuideDao.getContentUrl()
                    + "' ,category_id=  '" + businessGuideDao.getCategoryId()
                    + "' where id= '" + businessGuideDao.getId() + "'";
            logger.info("updateSQL:" + sql);
            jdbcTemplate.execute(sql);
        }
    }
    @Transactional
    public void deleteBusinessGuide(List<String> ids){
        if (precompile) {
            String sql = "delete from d_business_guide where id =?";
            jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
                public int getBatchSize() {
                    return ids.size();
                }
                public void setValues(PreparedStatement ps, int i)
                                throws SQLException {
                    ps.setString(1,ids.get(i));
                }
            });
            logger.info("deleteSQL:" + sql);
        }else {
            String sql = "delete from d_business_guide where id in('" + Utils.joinStrings(ids, "','") + "')";
            jdbcTemplate.execute(sql);
            logger.info("deleteSQL:" + sql);
        }
    }

    public List<BusinessGuideDao> selectBusinessGuide(String categoryId) {

        if (Strings.isNullOrEmpty(categoryId)) {
            String sql = "select bg.id id ,bg.title title,content, bg.content_url content_url,"
                    + " bg.category_id category_id ,bc.category_name category_name,bg.create_date create_date from"
                    + " d_business_guide bg left join d_business_category bc on bc.id=bg.category_id";
            logger.info("selectSQL:" + sql);
            return jdbcTemplate.query(sql, new Object[]{}, new BusinessGuideDaoRowMapper());
        } else {
            String sql = "select bg.id id ,bg.title title,content, bg.content_url content_url,"
                    + " bg.category_id category_id ,bc.category_name category_name,bg.create_date create_date from"
                    + " d_business_guide bg left join d_business_category bc on bc.id=bg.category_id"
                    + " where  category_id = ?";
            logger.info("selectSQL:" + sql);
            return jdbcTemplate.query(sql, new Object[]{categoryId}, new BusinessGuideDaoRowMapper());
        }

    }

    public List<BusinessGuideBriefDao> selectBusinessGuideBrief(String categoryId,String title) {

        String sql = "select bg.id id ,bg.title title, bg.content_url content_url,"
                + " bg.category_id category_id ,bc.category_name category_name,bg.create_date create_date from"
                + " d_business_guide bg left join d_business_category bc on bc.id=bg.category_id"
                + " where 1=1 ";
        ArrayList<String> agrs = new ArrayList<>();
        if(!Strings.isNullOrEmpty(categoryId)){
            sql=sql+"and category_id = ?";
            agrs.add(categoryId);
        }
        if(!Strings.isNullOrEmpty(title)){
            sql=sql+"and title like ?";
            agrs.add("%"+title+"%");
        }
        Object[] objects = new Object[agrs.size()];
        for (int i = 0; i <agrs.size() ; i++) {
            objects[i]=agrs.get(i);
        }
        logger.info("selectSQL:" + sql);
        return jdbcTemplate.query(sql, objects, new BusinessGuideBriefDaoRowMapper());
    }

    public BusinessGuideDao getBusinessGuide(String id) {
        String sql = "select bg.id id ,bg.title title,content, bg.content_url content_url,"
                + " bg.category_id category_id ,bc.category_name category_name,bg.create_date create_date from"
                + " d_business_guide bg left join d_business_category bc on bc.id=bg.category_id"
                + " where  bg.id = ?";
        logger.info("selectSQL:" + sql);
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BusinessGuideDaoRowMapper());

    }


    class BusinessGuideDaoRowMapper implements RowMapper<BusinessGuideDao>{

        @Override
        public BusinessGuideDao mapRow(ResultSet rs, int i) throws SQLException {
            BusinessGuideDao businessGuideDao = new BusinessGuideDao(
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("content_url"),
                    rs.getString("category_id"),
                    rs.getString("category_name"),
                    null
            );
            businessGuideDao.setCreateDate(Utils.GetDate( rs.getString("create_date")));
            return businessGuideDao;
        }
    }
    class BusinessGuideBriefDaoRowMapper implements RowMapper<BusinessGuideBriefDao>{

        @Override
        public BusinessGuideBriefDao mapRow(ResultSet rs, int i) throws SQLException {
            BusinessGuideBriefDao dao = new BusinessGuideBriefDao(
                    rs.getString("id"),
                    rs.getString("title"),
                    //rs.getString("content"),
                    rs.getString("content_url"),
                    rs.getString("category_id"),
                    rs.getString("category_name"),
                    null
            );
            dao.setCreateDate(Utils.GetDate( rs.getString("create_date")));
            return dao;
        }
    }
}
