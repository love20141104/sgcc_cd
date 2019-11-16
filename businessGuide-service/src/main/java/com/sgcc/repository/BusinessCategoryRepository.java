package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.BusinessCategoryDao;
import com.sgcc.dto.BusinessCategoryDto;
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
import java.util.List;
import java.util.logging.Logger;

@Repository
public class BusinessCategoryRepository {
    private Logger logger = Logger.getLogger(BusinessGuideRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${precompile}")
    private Boolean precompile;

    @Transactional
    public void insertBusinessCategory(BusinessCategoryDao businessCategoryDao){
        if (precompile) {
            String sql = "insert into d_business_category(id,category_name,note)values (?,?,? ) ";
            logger.info("insertSQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                     businessCategoryDao.getId()
                    ,businessCategoryDao.getCategoryName()
                    ,businessCategoryDao.getNote()});
        }else {
            String sql = "insert into d_business_category(id,category_name,note)values ( "
                    + "'" + businessCategoryDao.getId() + "' , '"
                    + businessCategoryDao.getCategoryName() + "' , '"
                    + businessCategoryDao.getNote()
                    + "' ) ";
            logger.info("insertSQL:" + sql);
            jdbcTemplate.execute(sql);
        }
    }
    @Transactional
    public void updateBusinessCategory(BusinessCategoryDao businessCategoryDao){
        if (precompile) {
            String sql = "update  d_business_category set "
                    + " category_name = ?"
                    + ", note= ?"
                    + " where id = ?" ;
            logger.info("insertSQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                     businessCategoryDao.getCategoryName()
                    ,businessCategoryDao.getNote()
                    ,businessCategoryDao.getId()
            });
        }else {
            String sql = "update  d_business_category set "
                    + " category_name = '" + businessCategoryDao.getCategoryName()
                    + "', note= '" + businessCategoryDao.getNote()
                    + "' where id = '" + businessCategoryDao.getId() + "'";
            logger.info("insertSQL:" + sql);
            jdbcTemplate.execute(sql);
        }
    }
    @Transactional
    public void deleteBusinessCategory(List<String> ids){
        if (precompile) {
            String sql = "delete from d_business_category where id in = ?";
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
            String sql = "delete from d_business_category where id in('" + Utils.joinStrings(ids, "','") + "')";
            jdbcTemplate.execute(sql);
            logger.info("deleteSQL:" + sql);
        }
    }

    public List<BusinessCategoryDao> selectBusinessCategory(){
        if (precompile) {
            String sql = "select id ,category_name ,note from d_business_category";
            logger.info("selectSQL:" + sql);
            return jdbcTemplate.query(sql,new Object[]{}, new BusinessCategoryDaoRowMapper());
        } else {
            String sql = "select id ,category_name ,note from d_business_category";
            logger.info("selectSQL:" + sql);
            return jdbcTemplate.query(sql, new BusinessCategoryDaoRowMapper());
        }
    }

    /**
     * 查询热点业务指南分类
     * @return
     */
    public List<String> selectHotBusinessCategory(){
        String sql = "select\n" +
                "d.id\n" +
                "from d_business_category d\n" +
                "left join b_page_statistics b on b.page_url =concat('https://sgcc.link/businessGuideDetails?id=',d.id)\n" +
                "group by d.id\n" +
                "order by count(*) desc\n" +
                "  limit 2 offset 0";
        List<String> ids = jdbcTemplate.queryForList(sql,String.class);
        return ids;
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
