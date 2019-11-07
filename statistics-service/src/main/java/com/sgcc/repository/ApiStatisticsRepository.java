package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.ApiStatisticsDao;
import com.sgcc.dto.ApiStatistcsDateDto;
import com.sgcc.dto.ApiStatisticsActiveDto;
import com.sgcc.dto.ApiStatisticsDto;
import com.sgcc.dto.ApiStatisticsQueryDto;
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
public class ApiStatisticsRepository {
    private Logger logger = Logger.getLogger(ApiStatisticsRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveApiStatistics(ApiStatisticsDao apiStatisticsDao){
        if(!apiStatisticsDao.getApiUrlDesc().equalsIgnoreCase("null")) {
            String sql = "insert into b_api_statistics(id,api_url,request_method,request_uri,user_open_id,visit_date,client_ip,api_url_desc)" +
                    "values ('" + apiStatisticsDao.getId() + "','"
                    + apiStatisticsDao.getApiUrl() + "','"
                    + apiStatisticsDao.getRequestMethod() + "','"
                    + apiStatisticsDao.getRequestURI() + "','"
                    + apiStatisticsDao.getUserOpenId() + "','"
                    + Utils.GetTime(apiStatisticsDao.getVisitDate()) + "','"
                    + apiStatisticsDao.getClientIp() + "','"
                    + apiStatisticsDao.getApiUrlDesc()
                    + "')";
            logger.info("insertSQL:" + sql);
            jdbcTemplate.execute(sql);
        }
    }
    /**
    *@Description: 查询ApiStatisticsQueryDto结果集
    *@Param: 
    *@return: 
    *@Author: raphealxx
    *@date: 2019/10/18 0018
    */
    public List<ApiStatisticsQueryDto> getApiStatisticsQuery(String visit_date_begin, String visit_date_end){
        String sql="select api_url,count(id) call_num from b_api_statistics ";
        StringBuffer sql_where = new StringBuffer();
        if(!Strings.isNullOrEmpty(visit_date_begin)){
            sql_where.append("visit_date >= '").append(visit_date_begin).append("' and ");
        }
        if(!Strings.isNullOrEmpty(visit_date_end)){
            sql_where.append("visit_date <= '").append(visit_date_end).append("'");
        }
        if(!Strings.isNullOrEmpty(sql_where.toString())){
            sql +=" where " + sql_where.toString();
        }
        sql+=" group by api_url";
        logger.info("select:"+sql);
        return jdbcTemplate.query(sql,new ApiStatisticsQueryRowMapper());
    }
    /**
     *@Description: 查询ApiStatisticsQueryDto结果集
     *@Param:
     *@return:
     *@Author: raphealxx
     *@date: 2019/10/18 0018
     */
    public ApiStatistcsDateDto getApiStatisticsCount(String visit_date_begin, String visit_date_end){
        String sql="select count(id) num ,count(distinct client_ip) client_ip_num,count(distinct user_open_id) user_open_id_num from b_api_statistics ";
        StringBuffer sql_where = new StringBuffer();
        if(!Strings.isNullOrEmpty(visit_date_begin)){
            sql_where.append("visit_date >= '").append(visit_date_begin).append("' and ");
        }
        if(!Strings.isNullOrEmpty(visit_date_end)){
            sql_where.append("visit_date <= '").append(visit_date_end).append("'");
        }
        if(!Strings.isNullOrEmpty(sql_where.toString())){
            sql +=" where " + sql_where.toString();
        }
        logger.info("select:"+sql);
        ApiStatistcsDateDto query = jdbcTemplate.queryForObject(sql, new ApiStatistcsMonthDtoRowMapper());
        if(query==null){
            return new ApiStatistcsDateDto(null,0,0,0);
        }
        return query;
    }
    /**
    *@Description: 查询ApiStatisticsDto列表
    *@Param: String visit_date_begin, String visit_date_end
    *@return: ApiStatisticsDto
    *@Author: raphealxx
    *@date: 2019/10/18 0018
    */
    public List<ApiStatisticsDto> getApiStatistics(String visit_date_begin, String visit_date_end){
        String sql="select id, api_url,user_open_id,visit_date,client_ip from b_api_statistics ";
        StringBuffer sql_where = new StringBuffer();
        if(!Strings.isNullOrEmpty(visit_date_begin)){
            sql_where.append("visit_date >= '").append(visit_date_begin).append("' and ");
        }
        if(!Strings.isNullOrEmpty(visit_date_end)){
            sql_where.append("visit_date <= '").append(visit_date_end).append("'");
        }
        if(!Strings.isNullOrEmpty(sql_where.toString())){
            sql +=" where " + sql_where.toString();
        }
        return jdbcTemplate.query(sql,new ApiStatisticsRowMapper());
    }

    /**
     *@Description: 查询时间段内接口访问次数
     *@Param:
     *@return:
     *@Author: raphealxx
     *@date: 2019/10/18 0018
     */
    public Integer getApiStatisticsOpenIdNum(String visit_date_begin, String visit_date_end){
        String sql="select count(DISTINCT(user_open_id)) usernum from b_api_statistics ";
        StringBuffer sql_where = new StringBuffer();
        if(!Strings.isNullOrEmpty(visit_date_begin)){
            sql_where.append("visit_date >= '").append(visit_date_begin).append("' and ");
        }
        if(!Strings.isNullOrEmpty(visit_date_end)){
            sql_where.append("visit_date <= '").append(visit_date_end).append("'");
        }
        if(!Strings.isNullOrEmpty(sql_where.toString())){
            sql +=" where " + sql_where.toString();
        }
        List<ApiStatisticsActiveDto> query = jdbcTemplate.query(sql, new ApiStatisticsActiveDtoRowMapper());
        ApiStatisticsActiveDto apiStatisticsActiveDto = new ApiStatisticsActiveDto();
        if(null==query||query.size()==0){
            apiStatisticsActiveDto.setUsernum(1);
        }else {
            apiStatisticsActiveDto = query.get(0);
            if (null == apiStatisticsActiveDto.getUsernum() || 0 == apiStatisticsActiveDto.getUsernum()) {
                apiStatisticsActiveDto.setUsernum(1);
            }
        }
        return apiStatisticsActiveDto.getUsernum();
    }

    class ApiStatisticsQueryRowMapper implements RowMapper<ApiStatisticsQueryDto>{

        @Override
        public ApiStatisticsQueryDto mapRow(ResultSet rs, int i) throws SQLException {
            return new ApiStatisticsQueryDto(
                    rs.getString("api_url"),
                    rs.getInt("call_num")
            );
        }
    }

    class ApiStatisticsActiveDtoRowMapper implements RowMapper<ApiStatisticsActiveDto>{

        @Override
        public ApiStatisticsActiveDto mapRow(ResultSet rs, int i) throws SQLException {
            return new ApiStatisticsActiveDto(
                    rs.getInt("usernum")
            );
        }
    }

    class ApiStatisticsRowMapper implements RowMapper<ApiStatisticsDto>{

        @Override
        public ApiStatisticsDto mapRow(ResultSet rs, int i) throws SQLException {
            return new ApiStatisticsDto(
                    rs.getString("id"),
                    rs.getString("api_url"),
                    rs.getString("user_open_id"),
                    rs.getDate("visit_date"),
                    rs.getString("client_ip")
            );
        }
    }
    class ApiStatistcsMonthDtoRowMapper implements RowMapper<ApiStatistcsDateDto>{

        @Override
        public ApiStatistcsDateDto mapRow(ResultSet rs, int i) throws SQLException {
            ApiStatistcsDateDto apiStatistcsDateDto = new ApiStatistcsDateDto();
            if(null==rs.getString("num")){
                apiStatistcsDateDto.setUrlNum(0);
            }
            if(null==rs.getString("client_ip_num")){
                apiStatistcsDateDto.setClientIpNum(0);
            }
            if(null==rs.getString("user_open_id_num")){
                apiStatistcsDateDto.setUserOpenIdNum(0);
            }
            apiStatistcsDateDto.setUrlNum(rs.getInt("num"));
            apiStatistcsDateDto.setClientIpNum(rs.getInt("client_ip_num"));
            apiStatistcsDateDto.setUserOpenIdNum(rs.getInt("user_open_id_num"));
            return apiStatistcsDateDto;
        }
    }
}
