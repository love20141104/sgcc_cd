package com.sgcc.repository;

import com.example.Utils;
import com.sgcc.dao.BusinessTypeDao;
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

@Repository
public class BusinessTypeRepository {
    private Logger logger = Logger.getLogger(BusinessTypeRepository.class.toString());
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${precompile}")
    private Boolean precompile;

    /**
     * 查询业务类型
     *
     * @return
     */
    public List<BusinessTypeDao> findAllBTypeList() {
        if (precompile) {
            String sql = "select id,business_type_id,business_type,`order`,business_type_available from d_business_type where business_type_available=1";
            List<BusinessTypeDao> bussinessTypeDaoList = jdbcTemplate.query(sql,new Object[]{}, new businessTypeRowMapper());
            logger.info("SQL:" + sql);
            return bussinessTypeDaoList;
        }else {
            String sql = "select id,business_type_id,business_type,`order`,business_type_available from d_business_type where business_type_available=1";
            List<BusinessTypeDao> bussinessTypeDaoList = jdbcTemplate.query(sql, new businessTypeRowMapper());
            logger.info("SQL:" + sql);
            return bussinessTypeDaoList;
        }
    }

    /**
     * 添加业务类型
     *
     * @param businessTypeDao
     */
    public void addBType(BusinessTypeDao businessTypeDao) {
        if (precompile) {
            String sql = "insert into d_business_type(id,business_type_id,business_type,`order`) " +
                    "values(?,?,?,?)";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                    businessTypeDao.getId()
                    ,businessTypeDao.getBusinessTypeId()
                    ,businessTypeDao.getBusinessType()
                    ,businessTypeDao.getOrder()
            });
        }else {
            String sql = "insert into d_business_type(id,business_type_id,business_type,`order`) " +
                    "values('" + businessTypeDao.getId() + "','" + businessTypeDao.getBusinessTypeId() + "'" +
                    ",'" + businessTypeDao.getBusinessType() + "'," + businessTypeDao.getOrder() + ")";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql);
        }

    }

    /**
     * 删除业务类型
     *
     * @param businessTypeIds
     */
    public void delBType(List<String> businessTypeIds) {
        if (precompile) {
            String sql = "delete from d_business_type where business_type_id =? ";
            logger.info("SQL:" + sql);
            jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
                public int getBatchSize() {
                    return businessTypeIds.size();
                }
                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    ps.setString(1,businessTypeIds.get(i));
                }
            });
        }else {
            String strIds = Utils.joinStrings(businessTypeIds, "','");
            String sql = "delete from d_business_type where business_type_id in('" + strIds + "')";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql);
        }

    }

    /**
     * 修改业务类型
     *
     * @param businessTypeDao
     */
    public void updateBType(BusinessTypeDao businessTypeDao) {
        if (precompile) {
            String sql = "update d_business_type set business_type=?  where business_type_id=? ";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{businessTypeDao.getBusinessType(),businessTypeDao.getBusinessTypeId()});
        }else {
            String sql = "update d_business_type set business_type='" + businessTypeDao.getBusinessType() +
                    "' where business_type_id='" + businessTypeDao.getBusinessTypeId() + "'";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql);
        }
    }


    class businessTypeRowMapper implements RowMapper<BusinessTypeDao> {

        @Override
        public BusinessTypeDao mapRow(ResultSet rs, int i) throws SQLException {
            return new BusinessTypeDao(
                    rs.getString("id"),
                    rs.getString("business_type_id"),
                    rs.getString("business_type"),
                    rs.getInt("order"),
                    rs.getBoolean("business_type_available")
            );
        }
    }


}
