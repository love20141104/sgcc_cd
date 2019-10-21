package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.BusinessGuideDao;
import com.sgcc.dto.BusinessGuideDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class BusinessGuideRepository {
    private Logger logger = Logger.getLogger(BusinessGuideRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void insertBusinessGuide(BusinessGuideDao businessGuideDao){
        String sql="insert into business_guide(id,title,content,content_url,category_id,create_date)values ( " +
                businessGuideDao.getId() + "  , " +
                businessGuideDao.getTitle() + "  , " +
                businessGuideDao.getContent()+ "  , " +
                businessGuideDao.getContentUrl()+ "  , " +
                businessGuideDao.getCategoryId()+ "  , " +
                businessGuideDao.getCreateDate()+ "  , " +
                " )";
        logger.info("insertSQL:"+sql);
        jdbcTemplate.execute(sql);
    }
    @Transactional
    public void updateBusinessGuide(BusinessGuideDao businessGuideDao){
        String sql="update  business_guide set" +
                " title= " +businessGuideDao.getTitle()+
                " ,content= " +businessGuideDao.getContent()+
                " ,content_url= " +businessGuideDao.getContentUrl()+
                " ,category_id=  " +businessGuideDao.getCategoryId()+
                " where id= "+businessGuideDao.getId();
        logger.info("updateSQL:"+sql);
        jdbcTemplate.execute(sql);
    }
    @Transactional
    public void deleteBusinessGuide(List<String> ids){
        String sql = "delete from business_guide where id in('"+ Utils.joinStrings(ids,"','")+"')";
        jdbcTemplate.execute(sql);
        logger.info("deleteSQL:"+sql);
    }

    public List<BusinessGuideDao> selectBusinessGuide(String categoryId){
        if(Strings.isNullOrEmpty(categoryId)){
            String sql = "select id ,title, content, content_url,category_id ,create_date from business_guide";
            logger.info("selectSQL:"+sql);
            return jdbcTemplate.query(sql, new BusinessGuideDaoRowMapper());
        }else {
            String sql = "select id ,title, content, content_url,category_id,create_date from business_guide  " +
                    " where  category_id = "+categoryId;
            logger.info("selectSQL:"+sql);
            return jdbcTemplate.query(sql, new BusinessGuideDaoRowMapper());
        }
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
                    null
            );
            businessGuideDao.setCreateDate(Utils.GetDate( rs.getString("create_date")));
            return businessGuideDao;
        }
    }
}
