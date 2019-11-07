package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.PageStatisticsDao;
import com.sgcc.dto.HotPageDto;
import com.sgcc.dto.PageStatistcsDateDto;
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
public class PageStatisticsRepository {
    private Logger logger = Logger.getLogger(PageStatisticsRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void savePageStatistics(PageStatisticsDao pageStatisticsDao){
        if(!pageStatisticsDao.getPageName().equalsIgnoreCase("null")) {
            String sql = "insert into b_page_statistics(id,page_url,page_name,user_open_id,visit_date,client_ip)"
                    + " values ('" + pageStatisticsDao.getId() + "','"
                    + pageStatisticsDao.getPageUrl() + "','"
                    + pageStatisticsDao.getPageName() + "','"
                    + pageStatisticsDao.getUserOpenId() + "','"
                    + Utils.GetTime(pageStatisticsDao.getVisitDate()) + "','"
                    + pageStatisticsDao.getClientIp() + "')";
            logger.info("insertSQL:" + sql);
            jdbcTemplate.update(sql);
        }
    }

    public PageStatistcsDateDto getPageStatisticsCount(String visit_date_begin, String visit_date_end){
        String sql="select count(id) num ,count(distinct client_ip) client_ip_num,count(distinct user_open_id) user_open_id_num from b_page_statistics ";
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
        PageStatistcsDateDto query = jdbcTemplate.queryForObject(sql, new PageStatistcsMonthDtoRowMapper());
        if(query==null){
            return new PageStatistcsDateDto(null,0);
        }
        return query;
    }

    public List<HotPageDto> hotPageDtoList(){
        String sql="SELECT page_name,count(distinct(user_open_id)) user_num,COUNT(id) visit_num "
                + " FROM b_page_statistics GROUP BY page_name ORDER BY COUNT(id) desc";
        logger.info("insertSQL:"+sql);
        List<HotPageDto> query = jdbcTemplate.query(sql, new HotPageDtoRowMapper());
        return  query;
    }

    class PageStatistcsMonthDtoRowMapper implements RowMapper<PageStatistcsDateDto> {

        @Override
        public PageStatistcsDateDto mapRow(ResultSet rs, int i) throws SQLException {
            PageStatistcsDateDto pageStatistcsDateDto = new PageStatistcsDateDto();
            if(null==rs.getString("num")){
                pageStatistcsDateDto.setUrlNum(0);
            }
            pageStatistcsDateDto.setUrlNum(rs.getInt("num"));
            return pageStatistcsDateDto;
        }
    }
    class HotPageDtoRowMapper implements RowMapper<HotPageDto> {

        @Override
        public HotPageDto mapRow(ResultSet rs, int i) throws SQLException {
            HotPageDto hotPageDto = new HotPageDto();
            hotPageDto.setPageName(rs.getString("page_name"));
            hotPageDto.setUserNum(rs.getInt("user_num"));
            hotPageDto.setVisitNum(rs.getInt("visit_num"));
            return hotPageDto;
        }
    }
}
