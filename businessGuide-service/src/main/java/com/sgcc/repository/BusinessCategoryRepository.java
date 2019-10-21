package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.BusinessCategoryDao;
import com.sgcc.dto.BusinessCategoryDto;
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
public class BusinessCategoryRepository {
    private Logger logger = Logger.getLogger(BusinessGuideRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional
    public void insertBusinessCategory(BusinessCategoryDao businessCategoryDao){
        String sql="insert into business_category(id,category_name,note)values ( " +
                businessCategoryDao.getId()+" , " +
                businessCategoryDao.getCategoryName()+" , " +
                businessCategoryDao.getNote()+
                " ) ";
        logger.info("insertSQL:"+sql);
        jdbcTemplate.execute(sql);
    }
    @Transactional
    public void updateBusinessCategory(BusinessCategoryDao businessCategoryDao){
        String sql="update  business_category set " +
                " category_name = "+businessCategoryDao.getCategoryName() +
                " note= " +businessCategoryDao.getNote()+
                "where id = "+businessCategoryDao.getId();
        logger.info("insertSQL:"+sql);
        jdbcTemplate.execute(sql);
    }
    @Transactional
    public void deleteBusinessCategory(List<String> ids){
        String sql = "delete from business_category where id in('"+ Utils.joinStrings(ids,"','")+"')";
        jdbcTemplate.execute(sql);
        logger.info("deleteSQL:"+sql);
    }

    public List<BusinessCategoryDao> selectBusinessCategory(){
            String sql = "select id ,category_name ,note from business_category";
            logger.info("selectSQL:"+sql);
            return jdbcTemplate.query(sql, new BusinessCategoryDaoRowMapper());
    }
    class BusinessCategoryDaoRowMapper implements RowMapper<BusinessCategoryDao> {

        @Override
        public BusinessCategoryDao mapRow(ResultSet rs, int i) throws SQLException {
            return new BusinessCategoryDao(
                    rs.getString("id"),
                    rs.getString("category_name"),
                    rs.getString("note")
            );
        }
    }
}
