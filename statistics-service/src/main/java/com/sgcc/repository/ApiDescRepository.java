package com.sgcc.repository;

import com.google.common.base.Strings;
import com.sgcc.dao.ApiDescDao;
import com.sgcc.dto.PageStatistcsDateDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ApiDescDao getApiDesc(String requestMapping, String requestMethod){
        String sql="select id,request_mapping,request_method,request_desc from d_api_desc ";
        StringBuffer sql_where = new StringBuffer();
        if(!Strings.isNullOrEmpty(requestMethod)){
            sql_where.append("request_method = '").append(requestMethod).append("' and ");
        }
        if(!Strings.isNullOrEmpty(requestMapping)){
            sql_where.append("request_mapping = '").append(requestMapping).append("'");
        }
        if(!Strings.isNullOrEmpty(sql_where.toString())){
            sql +=" where " + sql_where.toString();
        }
        logger.info("select:"+sql);
        try {
           return   jdbcTemplate.queryForObject(sql, new ApiDescDaoRowMapper());
        }catch (Exception e){
            return null;
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
