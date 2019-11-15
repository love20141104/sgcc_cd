package com.sgcc.repository;

import com.google.common.base.Strings;
import com.sgcc.dao.ApiDescDao;
import com.sgcc.dto.PageStatistcsDateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

@Repository
public class ApiDescRepository {
    private Logger logger = Logger.getLogger(ApiDescRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${precompile}")
    private Boolean precompile;

    public ApiDescDao getApiDesc(String requestMapping, String requestMethod){
        if (precompile) {
            String sql = "select id,request_mapping,request_method,request_desc from d_api_desc where" +
                    " request_method = ? and request_mapping = ?";
            try {
                return jdbcTemplate.queryForObject(sql,new Object[]{requestMethod,requestMapping}, new ApiDescDaoRowMapper());
            } catch (Exception e) {
                return null;
            }
        }else {
            String sql = "select id,request_mapping,request_method,request_desc from d_api_desc where" +
                    " request_method = '"+requestMethod+"' and request_mapping = '"+requestMapping+"'";
            try {
                return jdbcTemplate.queryForObject(sql, new ApiDescDaoRowMapper());
            } catch (Exception e) {
                return null;
            }
        }
    }


    class ApiDescDaoRowMapper implements RowMapper<ApiDescDao> {

        @Override
        public ApiDescDao mapRow(ResultSet rs, int i) throws SQLException {
            ApiDescDao apiDescDao = new ApiDescDao();
            apiDescDao.setId(rs.getString("id"));
            apiDescDao.setRequestMapping(rs.getString("request_mapping"));
            apiDescDao.setRequestMethod(rs.getString("request_method"));
            apiDescDao.setRequestDesc(rs.getString("request_desc"));
            return apiDescDao;
        }
    }
}
