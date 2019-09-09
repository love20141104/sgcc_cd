package com.sgcc.repository;

import com.sgcc.dao.BusinessTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BusinessTypeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询业务类型
     * @return
     */
    public List<BusinessTypeDao> findAllBTypeList(){
        String sql = "select id,business_type_id,business_type,`order`,business_type_available from d_business_type";
        List<BusinessTypeDao> bussinessTypeDaoList = jdbcTemplate.query(sql,new businessTypeRowMapper());

        return bussinessTypeDaoList;
    }

    /**
     * 添加业务类型
     * @param businessTypeDaoList
     */
    public void addBType(List<BusinessTypeDao> businessTypeDaoList){
        String sql = "insert into d_business_type(id,business_type_id,business_type,`order`) " +
                "values(?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,businessTypeDaoList.get(i).getId());
                ps.setString(2,businessTypeDaoList.get(i).getBusinessTypeId());
                ps.setString(3,businessTypeDaoList.get(i).getBusinessType());
                ps.setInt(4,businessTypeDaoList.get(i).getOrder());
            }

            @Override
            public int getBatchSize() {
                return businessTypeDaoList.size();
            }
        });

    }

    /**
     * 删除业务类型
     * @param businessTypeDaoList
     */
    public void delBType(List<BusinessTypeDao> businessTypeDaoList){
        String sql = "delete from d_business_type where business_type_id=?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,businessTypeDaoList.get(i).getBusinessTypeId());
            }

            @Override
            public int getBatchSize() {
                return businessTypeDaoList.size();
            }
        });

    }

    /**
     * 修改业务类型
     * @param businessTypeDaoList
     */
    public void updateBType(List<BusinessTypeDao> businessTypeDaoList){
        String sql = "update d_business_type set business_type=? where business_type_id=?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,businessTypeDaoList.get(i).getBusinessType());
                ps.setString(2,businessTypeDaoList.get(i).getBusinessTypeId());
            }

            @Override
            public int getBatchSize() {
                return businessTypeDaoList.size();
            }
        });
    }


    class businessTypeRowMapper implements RowMapper<BusinessTypeDao>{

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
